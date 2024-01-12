package com.gastrosfera.ospatar.controller;

import com.gastrosfera.ospatar.service.OspatarService;
import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.ospatar.dto.OspatarDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.OSPATAR_V1_URI)
public class OspatarController extends BaseController {

    private final OspatarService ospatarService;

    public OspatarController(HttpServletRequest request, OspatarService ospatarService) {
        super(request);
        this.ospatarService = ospatarService;
    }

    @PostMapping
    public ResponseEntity<OspatarDTO> createOspatar(@RequestBody @Validated(PostValidation.class) OspatarDTO ospatarDTO) {
        return buildCreatedResponse(ospatarService.createOspatar(ospatarDTO));
    }

    @GetMapping(ApiConstant.BY_ID_PATH)
    public ResponseEntity<OspatarDTO> getOspatarById(@PathVariable("id") String uid) {
        return buildResponse(ospatarService.getOspatar(uid), HttpStatus.OK);
    }

}
