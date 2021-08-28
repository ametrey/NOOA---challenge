package ar.com.ada.api.boyas.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;



import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.models.request.MuestraRequest;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.models.response.MuestraResponse;

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

    @PostMapping("/muestras")
    public ResponseEntity<?> crearMuestra(@RequestBody MuestraRequest muestraReq){
        
        MuestraResponse respuesta = new MuestraResponse();

        Muestra muestra = service.crearMuestra(muestra);

        respuesta.id = muestra.getMuestraId();

        respuesta.color = muestra.getBoya().getLuzColor();
                  
        
        return ResponseEntity.ok(respuesta);
        
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<?> eliminarMuestra(@PathVariable Integer id, @RequestBody Muestra muestra){
        
        GenericResponse respuesta = new GenericResponse();

        muestra = service.traerMuestrasPorId(id);

        service.eliminarMuestra(muestra);

        respuesta.isOk = true;
        respuesta.id = muestra.getMuestraId();
        respuesta.message = "muestra elminada con éxito";
        
        return ResponseEntity.ok(respuesta);


    }
    
}
