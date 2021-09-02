package ar.com.ada.api.boyas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.repositories.MuestraRepository;

@Service
public class AnomaliaService {

    @Autowired
    MuestraRepository repo;

    

}
