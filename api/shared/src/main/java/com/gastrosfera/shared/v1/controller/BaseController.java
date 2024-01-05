package com.gastrosfera.shared.v1.controller;

import com.gastrosfera.shared.v1.model.Identifiable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.util.UriComponentsBuilder;


@AllArgsConstructor
public abstract class BaseController {

    private final HttpServletRequest request;

    protected <T extends Identifiable<String>> ResponseEntity<T> buildCreatedResponse(T entity) {
        String location = UriComponentsBuilder.fromUriString(request.getRequestURL().toString()).path("/{id}").buildAndExpand(entity.getIdentifier()).toString();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Location", location);
        return new ResponseEntity(entity, httpHeaders, HttpStatus.CREATED);
    }

    protected <T> ResponseEntity<T> buildResponse(T entity, HttpStatusCode status) {
        return new ResponseEntity<>(entity, status);
    }

}
