package com.gastrosfera.user.controller;

import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.exception._4xx.EntityAlreadyExistsException;
import com.gastrosfera.shared.v1.user.dto.AuthenticationRequest;
import com.gastrosfera.shared.v1.user.dto.AuthenticationResponse;
import com.gastrosfera.shared.v1.user.dto.RegistrationRequest;
import com.gastrosfera.shared.v1.user.dto.UserDTO;
import com.gastrosfera.user.service.UserService;
import com.gastrosfera.user.utils.JwtUtil;
import com.gastrosfera.user.utils.RedisUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("Received login request");
        try {
            if (request.getUsername().isEmpty() || request.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Username and password must not be empty"));
            }

            UserDTO userDTO = userService.getUserByUsernameAndPassword(request.getUsername(), request.getPassword());

            if (userDTO == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Invalid credentials"));
            }

            String jwt = JwtUtil.generateJwt(
                    userDTO.getIdUser(),
                    userDTO.getRole(),
                    userDTO.getUsername(),
                    JwtUtil.TOKEN_EXPIRATION_TIME
            );

            Map<String, Object> response = new HashMap<>();
            response.put("message", "User authenticated successfully");
            response.put("token", jwt);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Internal Server Error"));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest request) {
        System.out.println( "Received register request");
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
    public ResponseEntity<Map<String, String>> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        System.out.println("Received logout request");
        try {
            if (Objects.requireNonNull(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid Authorization header");

                return ResponseEntity.badRequest().body(response);
            }

            String token = authorizationHeader.substring(7);

            RedisUtil.blacklistToken(token);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Logout successful");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Internal Server Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        System.out.println("Received validate request");
        try {
            if (Objects.requireNonNull(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid Authorization header");

                return ResponseEntity.badRequest().body(response);
            }
            System.out.println(1);
            String token = authorizationHeader.substring(7); // after "Bearer "

            if (!JwtUtil.validateJwt(token)) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid JWT");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            System.out.println(2);
            if (RedisUtil.isBlacklisted(token)) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "JWT is blacklisted");

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            System.out.println(3);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Token is valid");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/claims")
    public ResponseEntity<Map<String, String>> getUserClaims(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        System.out.println("Received claims request");
        try {
            if (Objects.requireNonNull(authorizationHeader).isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid Authorization header");
                return ResponseEntity.badRequest().body(response);
            }

            String token = authorizationHeader.substring(7);
            try{
                Claims claims = JwtUtil.getUserClaims(token);

                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                response.put("role", claims.get("role", String.class));
                response.put("id", claims.getId());

                return ResponseEntity.ok(response);
            }catch (Exception e){
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid JWT");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}