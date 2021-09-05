package ar.com.ada.api.boyas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.models.request.BoyaRequest;
import ar.com.ada.api.boyas.models.request.ColorEnumRequest;
import ar.com.ada.api.boyas.models.response.GenericResponse;
import ar.com.ada.api.boyas.services.BoyaService;

@RestController
public class BoyaController {

    @Autowired
    BoyaService service;

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> traerBoyas() {
        return ResponseEntity.ok(service.traerBoyas());
    }

    @GetMapping("/boyas/{id}")
    public ResponseEntity<Boya> traerBoyasPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarBoyaPorId(id));
    }

    @PostMapping("/boyas")
    public ResponseEntity<?> crearBoya(@RequestBody BoyaRequest request){
        
        GenericResponse respuesta = new GenericResponse();

        Boya boya = service.crearBoya(request.longitudInstalacion, request.latitudInstalacion);

        respuesta.isOk = true;
        respuesta.id = boya.getBoyaId();
        respuesta.message = "boya creada con exito";

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/boyas/{id}")
    public ResponseEntity<GenericResponse> modificarColor(@PathVariable Integer id, @RequestBody ColorEnumRequest color) {

        GenericResponse respuesta = new GenericResponse();

        

        Boya boya = service.buscarBoyaPorId(id);
        boya.setLuzColor(color.color);

        service.actualizar(boya);

        respuesta.isOk = true;
        respuesta.id = boya.getBoyaId();

        respuesta.message = "boya actualizada";
        
        return ResponseEntity.ok(respuesta);
    }

    
}
