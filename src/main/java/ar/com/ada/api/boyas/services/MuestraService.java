package ar.com.ada.api.boyas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;

import ar.com.ada.api.boyas.repositories.MuestraRepository;

@Service
public class MuestraService{

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public List<Muestra> traerMuestrasPorBoyaId(Integer idBoya) {
        
        Boya boya = boyaService.buscarBoyaPorId(idBoya);
        
        
        
        return boya.getMuestras();
    }

    public Boya getBoya(Integer id){
        
        Boya boya = boyaService.buscarBoyaPorId(id);

        return boya;
        
    }

    public Muestra crearMuestra(Integer boyaId, String horario, String matricula, double latitud, double longitud, double alturaNivelDelMar) throws ParseException {

        Muestra muestra = new Muestra();
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(horario);
        muestra.setBoyaId(boyaService.buscarBoyaPorId(boyaId));
        muestra.setHorarioMuestra(date);
        muestra.setMatriculaEmbarcacion(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setAlturaNivelMar(alturaNivelDelMar);

        repo.save(muestra);

        return muestra;
    }

    public void eliminarMuestra(Muestra muestra) {

        repo.delete(muestra);
    }

    public Muestra traerMuestrasPorId(Integer id) {
        
        return repo.getById(id);
    }

    
    public List<Boya> traerBoyasPorColor(ColorBoyaEnum color) {
        
        return boyaService.traerBoyasPorColor(color);
    }

    public Muestra traerMuestraMinimaProBoyaId(Integer idBoya) {

        Boya boya = boyaService.buscarBoyaPorId(idBoya);
        Muestra muestraMin = boya.getMuestras().get(0);
        
        for(Muestra muestra : boya.getMuestras()){

            if(muestra.getAlturaNivelMar() < muestraMin.getAlturaNivelMar()){
                muestraMin = muestra;
            }

        }
        return muestraMin;
    }

    public void ResetearColor(Boya boya) {

       boya.setLuzColor(ColorBoyaEnum.AZUL);
    }

    public Muestra buscarMuestraPorId(Integer id) {
        
        Optional<Muestra> resultado = repo.findById(id);
        Muestra muestra = null;

        if (resultado.isPresent())
            muestra = resultado.get();

        return muestra;
    }
    
}
