package ar.com.ada.api.boyas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    
    
}
