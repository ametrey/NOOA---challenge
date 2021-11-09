package ar.com.ada.api.boyas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
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

    public Boya crearBoya(Double longitud, Double latitud) {

        Boya boya = new Boya();
        boya.setLongitudInstalacion(longitud);
        boya.setLatitudInstalacion(latitud);
        boya.setLuzColor(ColorBoyaEnum.AZUL);
        repo.save(boya);
        return boya;

    }

    public void actualizar(Boya boya) {

        repo.save(boya);
    }

    

    public List<Boya> traerBoyasPorColor(ColorBoyaEnum color) {

        List<Boya> boyas = new ArrayList<>();

        for (Boya boya : repo.findAll()) {

            if (boya.getLuzColor().equals(color)) {
                boyas.add(boya);
            }
        }

        return boyas;

    }

    public void ResetearColor(Boya boya) {

        boya.setLuzColor(ColorBoyaEnum.AZUL);
        repo.save(boya);
    }

    public void definirColorBoya(Boya boya, Muestra muestra) {

        if (muestra.getAlturaNivelMar() < -100 || muestra.getAlturaNivelMar() > 100) {
            boya.setLuzColor(ColorBoyaEnum.ROJO);
        } else if (muestra.getAlturaNivelMar() < -50 || muestra.getAlturaNivelMar() > 50) {
            boya.setLuzColor(ColorBoyaEnum.AMARILLO);
        } else {
            boya.setLuzColor(ColorBoyaEnum.VERDE);
        }
        repo.save(boya);
    }

    public boolean validarCoordenadas(Boya boya) {
        if((boya.getLatitudInstalacion() >= - 90 && boya.getLatitudInstalacion() <= 90) && (boya.getLongitudInstalacion() >= -90 && boya.getLongitudInstalacion() <= 90)){
            return true;
        }
        return false;
    }

   

   
}
