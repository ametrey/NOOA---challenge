package ar.com.ada.api.boyas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.repositories.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public List<Muestra> traerMuestrasPorBoyaId(Integer boyaId) {
        
        Boya boya = boyaService.buscarMuestra(boyaId);
        
        return boya.getMuestras();
    }
    
}
