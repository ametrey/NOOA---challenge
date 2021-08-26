package ar.com.ada.api.boyas.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> obtenerMuestraBoyaId(@PathVariable Integer boyaId){

        List<Muestra> muestras = service.traerMuestrasPorBoyaId(boyaId);
        
        return ResponseEntity.ok(muestras);
        
    }
    
}
