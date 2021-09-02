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

import ar.com.ada.api.boyas.entities.Anomalia;
import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;
import ar.com.ada.api.boyas.models.request.MuestraRequest;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.models.response.MuestraResponse;
import ar.com.ada.api.boyas.services.BoyaService;
import ar.com.ada.api.boyas.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    @Autowired
    BoyaService serviceBoya;

    @Autowired
    Anomalia anomalia;

    


    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> obtenerMuestraBoyaId(@PathVariable Integer idBoya){

        List<Muestra> muestras = service.traerMuestrasPorBoyaId(idBoya);
        
        return ResponseEntity.ok(muestras);
        
    }

    //bonus 1
    @GetMapping("/muestras/colores/{color}")
    public ResponseEntity<List<Boya>> obtenerBoyaPorColor(@PathVariable ColorBoyaEnum color){
        
       List<Boya> boyas = service.traerBoyasPorColor(color);
       
       return ResponseEntity.ok(boyas);
        
    }

    @GetMapping("/muestras/minima/{idBoya}")
    public ResponseEntity<Muestra> obtenerMuestraMinimaPorBoyaId(@PathVariable Integer idBoya){
        
        
        
        return ResponseEntity.ok(service.traerMuestraMinimaProBoyaId(idBoya));

    }
    // fin bonus 1

    //epic bonus

    @GetMapping("/muestras/anomalias/{idBoya}")
    public ResponseEntity<?> alertaAnomalias(@PathVariable Integer idBoya){
        
               
        List<Anomalia> anomalias = anomalia.traerAnomalias(service.traerMuestrasPorBoyaId(idBoya));
        
                       
        return ResponseEntity.ok(anomalias);
        
    }

    //fin epic bonus

    @PostMapping("/muestras")
    public ResponseEntity<?> crearMuestra(@RequestBody MuestraRequest req) throws ParseException{

        MuestraResponse respuesta = new MuestraResponse();

        Muestra muestra = service.crearMuestra(req.boyaId,req.horario,req.matricula,req.latitud,req.longitud,req.alturaNivelDelMar);

        respuesta.id = muestra.getMuestraId();

        respuesta.color = muestra.getBoya().getLuzColor();
                  
        
        return ResponseEntity.ok(respuesta);
        
    }

    //no funciona:
    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<?> eliminarMuestra(@PathVariable Integer id){
        
        GenericResponse respuesta = new GenericResponse();

        Muestra muestra = service.buscarMuestraPorId(id);
        
        Boya boya = serviceBoya.buscarBoyaPorId(muestra.getBoya().getBoyaId());

        serviceBoya.ResetearColor(boya);

        respuesta.isOk = true;
        respuesta.id = muestra.getBoya().getBoyaId();
        respuesta.message = "luz reseteada con éxito";
        
        return ResponseEntity.ok(respuesta);


    }
    
}
