package com.gastrosfera.client.controller;


import com.gastrosfera.client.service.ClientService;
import com.gastrosfera.shared.v1.base.ApiConstant;
import com.gastrosfera.shared.v1.constraint.PostValidation;
import com.gastrosfera.shared.v1.controller.BaseController;
import com.gastrosfera.shared.v1.client.dto.ClientDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API_PATH)
public class ClientController extends BaseController {

    private final ClientService clientService;

    public ClientController(HttpServletRequest request, ClientService clientService) {
        super(request);
        this.clientService = clientService;
    }

    @PostMapping("/create_client")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        System.out.println("creez client "+clientDTO);
        return new ResponseEntity<>(clientService.createClient(clientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get_client/{id}")
    public ResponseEntity<ClientDTO> getClientbyId(@PathVariable("id") Long id) {
        return buildResponse(clientService.getClient(id), HttpStatus.OK);
    }

    @GetMapping("/get_clients")
    public ResponseEntity<List<ClientDTO>> getClients() {
        return buildResponse(clientService.getClients(), HttpStatus.OK);
    }
}