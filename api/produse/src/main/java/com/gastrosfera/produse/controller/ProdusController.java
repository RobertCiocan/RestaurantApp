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

    @GetMapping
    public ResponseEntity<List<ProdusDTO>> getAllFoods(@RequestParam Category category){
        return buildResponse(produsService.getAllProducts(category), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProdusDTO> getProduct(@PathVariable String name){
        return buildResponse(produsService.getProduct(name), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProdusDTO> createProdus(@RequestBody ProdusDTO produsDTO){
        return buildResponse(produsService.createProduct(produsDTO), HttpStatus.CREATED);
        //return buildCreatedResponse(produsService.createProduct(produsDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteProdus(@PathVariable String name){
        produsService.deleteProduct(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Void> updateProdus(@PathVariable String name, @RequestBody ProdusDTO produsDTO){
        produsDTO.setName(name);
        produsService.updateProduct(produsDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProdusDTO>> getAllProducts(){
        return buildResponse(produsService.getAllProducts(), HttpStatus.OK);
    }
}
