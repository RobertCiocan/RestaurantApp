package com.gastrosfera.user.controller;

import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.user.dto.AuthenticationRequest;
import com.gastrosfera.shared.v1.user.dto.AuthenticationResponse;
import com.gastrosfera.shared.v1.user.dto.RegistrationRequest;
import com.gastrosfera.user.utils.JwtUtil;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.user.dto.UserDTO;
import com.gastrosfera.user.service.UserService;
import com.gastrosfera.user.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping(ApiConstant.API_PATH + ApiConstant.V1_PATH)
public class AuthenticationController extends BaseController {
    private final UserService userService;

    public AuthenticationController(HttpServletRequest request, UserService userService) {
        super(request);
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Username and password must not be empty");
            }

            UserDTO userDTO = userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());

            if (userDTO == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }

            String jwt = JwtUtil.generateJwt(
                    userDTO.getIdUser(),
                    userDTO.getRole(),
                    userDTO.getUsername(),
                    JwtUtil.TOKEN_EXPIRATION_TIME
            );

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + jwt);

            return new ResponseEntity<>("User authenticated successfully", headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        try {
            if (request.getUsername().isEmpty() || request.getPassword().isEmpty() || request.getRole().isEmpty()) {
                return ResponseEntity.badRequest().body(new AuthenticationResponse("Username, password, and role must not be empty"));
            }

            System.out.println(request.getUsername());

            UserDTO userDTO = userService.createUser(
                    new UserDTO(request.getUsername(), request.getPassword(), request.getRole())
            );

            AuthenticationResponse response = new AuthenticationResponse(userDTO.getUsername(), userDTO.getIdUser(), userDTO.getRole());
            return ResponseEntity.ok(response);
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse("User already exists"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AuthenticationResponse("Internal Server Error"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            if (Objects.requireNonNull(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Invalid Authorization header");
            }

            String token = authorizationHeader.substring(7);

            RedisUtil.blacklistToken(token);

            return ResponseEntity.ok("Logout successful");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        try {
            if (Objects.requireNonNull(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Invalid Authorization header");
            }

            String token = authorizationHeader.substring(7); // after "Bearer "

            if (!JwtUtil.validateJwt(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            if (RedisUtil.isBlacklisted(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is blacklisted");
            }

            return ResponseEntity.ok("Token is valid");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
