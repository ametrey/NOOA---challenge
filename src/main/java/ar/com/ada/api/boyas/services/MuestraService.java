package ar.com.ada.api.boyas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;
import ar.com.ada.api.boyas.entities.Muestra.TipoAlertaEnum;
import ar.com.ada.api.boyas.repositories.MuestraRepository;


@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;

    @Autowired
    BoyaService boyaService;

    public List<Muestra> traerMuestrasPorBoyaId(Integer idBoya) {

        Boya boya = boyaService.buscarBoyaPorId(idBoya);

        return boya.getMuestras();
    }

    public Boya getBoya(Integer id) {

        Boya boya = boyaService.buscarBoyaPorId(id);

        return boya;

    }

    public Muestra crearMuestra(Integer boyaId, String horario, String matricula, double latitud, double longitud,
            double alturaNivelDelMar) throws ParseException {

        Muestra muestra = new Muestra();
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(horario);
        muestra.setBoya(boyaService.buscarBoyaPorId(boyaId));
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

        for (Muestra muestra : boya.getMuestras()) {

            if (muestra.getAlturaNivelMar() < muestraMin.getAlturaNivelMar()) {
                muestraMin = muestra;
            }

        }
        return muestraMin;
    }

    
    public Muestra buscarMuestraPorId(Integer id) {

        Optional<Muestra> resultado = repo.findById(id);
        Muestra muestra = null;

        if (resultado.isPresent())
            muestra = resultado.get();

        return muestra;
    }

    // epic bonus
//
    public TipoAlertaEnum alertaAnomalia(List<Muestra> muestras){
        if(esAlertaKaiju(muestras)){
            return TipoAlertaEnum.KAIJU;
        }
        if(esAlertaImpacto(muestras)){
            return TipoAlertaEnum.IMPACTO;
        }
        return TipoAlertaEnum.SIN_ANOMALIAS;
    }

    public Boolean esAlertaImpacto(List<Muestra> muestras) {

        for (int i = 0; i < muestras.size(); i++) {

            if (i < muestras.size() - 1 && (diferenciaAltura(muestras.get(i).getAlturaNivelMar(),
                    muestras.get(i + 1).getAlturaNivelMar()) > 500)) {
                return true;
            }

        }
        return false;

    }

    public Double diferenciaAltura(Double primerNum, Double segundoNum) {

        Double resultado = null;

        if (primerNum >= 0 && segundoNum >= 0) {
            resultado = Math.abs(primerNum - segundoNum);
        }
        if ((primerNum <= 0 && segundoNum >= 0) || (segundoNum <= 0 && primerNum >= 0)) {
            resultado = Math.abs(primerNum) + Math.abs(segundoNum);
        }
        if (primerNum < 0 && segundoNum < 0) {
            resultado = Math.abs(Math.abs(primerNum) - Math.abs(segundoNum));
        }

        return resultado;

    }

    public Boolean esAlertaKaiju(List<Muestra> muestras) {


        //if (now.getTime() - previous.getTime() >= 20*60*1000) 

        for (int i = 0; i < muestras.size(); i++) {
        
            if (i < muestras.size() - 1 && (minutos(muestras.get(i).getHorarioMuestra(), muestras.get(i+1).getHorarioMuestra()) > 10 && diferenciaAltura(muestras.get(i).getAlturaNivelMar(), muestras.get(i+1).getAlturaNivelMar()) >= 200)){
                return true;
            }
        }
        return false;

    }

    public Long minutos (Date primerFecha, Date segundaFecha){
        /*Long result = primerFecha.getTime() - segundaFecha.getTime();
        int diferenciaDias = Math.abs((int) ((primerFecha.getTime() - segundaFecha.getTime()) / (1000 * 60 * 60 * 24)));
        Long diferenciaHoras = (primerFecha.getTime() - segundaFecha.getTime()) / (60 * 60 * 1000);*/
        Long diferenciaMinutos = Math.abs((primerFecha.getTime() - segundaFecha.getTime()) / (60 * 1000));
       

        Long resultado = segundaFecha.getTime() - primerFecha.getTime();
        resultado = resultado / (1000*60) % 60;
        
        return resultado;
    }

}
