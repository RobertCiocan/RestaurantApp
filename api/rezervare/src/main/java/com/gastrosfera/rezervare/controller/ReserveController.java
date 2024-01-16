package com.gastrosfera.rezervare.controller;

import com.gastrosfera.rezervare.service.ReserveService;
import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.constraint.PutValidation;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.reserve.dto.ReserveDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.RESERVE_V1_URI)
public class ReserveController extends BaseController {
    private final ReserveService reserveService;
    public ReserveController(HttpServletRequest request, ReserveService reserveService) {
        super(request);
        this.reserveService = reserveService;
    }

    @GetMapping
    public String ceva(){
        return "Salut boss";
    }
    @PostMapping(ApiConstant.CREATE)
    public ResponseEntity<ReserveDTO> createReserve(@RequestBody @Validated(PostValidation.class) ReserveDTO reserveDTO){
        return buildCreatedResponse(reserveService.createReserve(reserveDTO));
    }


    @GetMapping(ApiConstant.BY_ID_PATH)
    public ResponseEntity<ReserveDTO> getReserveById(@PathVariable("id") String id){
        return buildResponse(reserveService.getReserve(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ReserveDTO> updateReserve(@RequestBody @Validated(PutValidation.class) ReserveDTO reserveDTO){
        return buildResponse(reserveService.updateReserve(reserveDTO), HttpStatus.OK);
    }

    @DeleteMapping(ApiConstant.BY_ID_PATH)
    public ResponseEntity<Void> deleteReserve(@PathVariable("id") String id){
        return reserveService.deleteReserve(id);
    }

}
