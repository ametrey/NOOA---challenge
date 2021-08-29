package ar.com.ada.api.boyas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;
import ar.com.ada.api.boyas.repositories.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;

    public List<Boya> traerBoyas() {
        return repo.findAll();
    }

    public Boya buscarBoyaPorId(Integer boyaId) {

        Optional<Boya> resultado = repo.findById(boyaId);
        Boya boya = null;

        if (resultado.isPresent())
            boya = resultado.get();

        return boya;

    }

    public void crearBoya(Boya boya) {
        repo.save(boya);

    }

    public void actualizar(Boya boya) {
        repo.save(boya);
    }

    public Boya buscarMuestra(Integer idBoya) {
        return null;
    }

    public List <Boya> traerBoyasPorColor(ColorBoyaEnum color) {

        List <Boya> boyas = new ArrayList<>();
        
        for(Boya boya : repo.findAll()){

            if(boya.getLuzColor().equals(color)){
                boyas.add(boya);
            }
        }

        return boyas;


    }
    
}
