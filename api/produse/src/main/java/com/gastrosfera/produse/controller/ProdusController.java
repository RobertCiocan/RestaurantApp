package com.gastrosfera.produse.controller;

import com.gastrosfera.produse.service.ProdusService;
import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.produs.Category;
import com.gastrosfera.shared.v1.produs.dto.ProdusDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.PRODUS_V1_URI)
public class ProdusController extends BaseController {
    private final ProdusService produsService;

    public ProdusController(HttpServletRequest request, ProdusService produsService) {
        super(request);
        this.produsService = produsService;
    }

//    @GetMapping
//    public ResponseEntity<List<ProdusDTO>> getAllProducts(){
//        return buildResponse(produsService.getAllProducts(), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<ProdusDTO>> getAllFoods(@RequestParam Category category){
        return buildResponse(produsService.getAllProducts(category), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdusDTO> getProduct(@PathVariable String id){
        return buildResponse(produsService.getProduct(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProdusDTO> createProdus(@RequestBody ProdusDTO produsDTO){
        return buildResponse(produsService.createProduct(produsDTO), HttpStatus.CREATED);
        //return buildCreatedResponse(produsService.createProduct(produsDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdus(@PathVariable String id){
        produsService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProdus(@PathVariable String id, @RequestBody ProdusDTO produsDTO){
        produsDTO.setId(id);
        produsService.updateProduct(produsDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
