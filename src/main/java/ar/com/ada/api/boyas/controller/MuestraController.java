package ar.com.ada.api.boyas.controller;

import java.text.ParseException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;
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

    @GetMapping("/muestras/colores/{color}")
    public ResponseEntity<List<Boya>> obtenerBoyaPorColor(@PathVariable ColorBoyaEnum color){
        
       

        List<Boya> boyas = service.traerBoyasPorColor(color);
        
        
        
        return ResponseEntity.ok(boyas);
        
    }

    @PostMapping("/muestras")
    public ResponseEntity<?> crearMuestra(@RequestBody MuestraRequest req) throws ParseException{

        MuestraResponse respuesta = new MuestraResponse();

        Muestra muestra = service.crearMuestra(req.boyaId,req.horario,req.matricula,req.latitud,req.longitud,req.alturNivelDelMar);

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
        respuesta.message = "muestra eliminada con éxito";
        
        return ResponseEntity.ok(respuesta);


    }
    
}
