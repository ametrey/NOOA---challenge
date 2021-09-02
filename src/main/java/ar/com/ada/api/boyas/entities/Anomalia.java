package ar.com.ada.api.boyas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.ada.api.boyas.entities.Muestra.TipoAlertaEnum;
import ar.com.ada.api.boyas.models.request.TipoAlerta;

public class Anomalia {

    private Double alturaNivelDelMarActual;
    private Date horarioInicioAnomalia;
    private Date horarioInicioFinAnomalia;
    private TipoAlertaEnum tipoAlerta;

    public Double getAlturaNivelDelMarActual() {
        return alturaNivelDelMarActual;
    }

    public void setAlturaNivelDelMarActual(Double alturaNivelDelMarActual) {
        this.alturaNivelDelMarActual = alturaNivelDelMarActual;
    }

    public Date getHorarioInicioAnomalia() {
        return horarioInicioAnomalia;
    }

    public void setHorarioInicioAnomalia(Date horarioInicioAnomalia) {
        this.horarioInicioAnomalia = horarioInicioAnomalia;
    }

    public Date getHorarioInicioFinAnomalia() {
        return horarioInicioFinAnomalia;
    }

    public void setHorarioInicioFinAnomalia(Date horarioInicioFinAnomalia) {
        this.horarioInicioFinAnomalia = horarioInicioFinAnomalia;
    }

    public TipoAlertaEnum getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(TipoAlertaEnum tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public List<Anomalia> traerAnomalias(List<Muestra> muestras) {

        List<Anomalia> anomalias = new ArrayList<>();
        Anomalia anomalia = new Anomalia();

        for (int i = 0; i < muestras.size(); i++) {

            if (i < muestras.size() - 1 && (diferenciaAltura(muestras.get(i).getAlturaNivelMar(),
                    muestras.get(i + 1).getAlturaNivelMar()) > 500)) {
                anomalia.setHorarioInicioAnomalia(muestras.get(i).getHorarioMuestra());
                anomalia.setHorarioInicioAnomalia(muestras.get(i + 1).getHorarioMuestra());
                anomalia.setAlturaNivelDelMarActual(muestras.get(i + 1).getAlturaNivelMar());
                anomalia.setTipoAlerta(TipoAlertaEnum.IMPACTO);
                anomalias.add(anomalia);

            }
        }

        for (int i = 0; i < muestras.size(); i++) {

            if (i < muestras.size() - 1
                    && (minutos(muestras.get(i).getHorarioMuestra(), muestras.get(i + 1).getHorarioMuestra()) > 10
                            && diferenciaAltura(muestras.get(i).getAlturaNivelMar(),
                                    muestras.get(i + 1).getAlturaNivelMar()) == 200)) {
                anomalia.setHorarioInicioAnomalia(muestras.get(i).getHorarioMuestra());
                anomalia.setHorarioInicioAnomalia(muestras.get(i + 1).getHorarioMuestra());
                anomalia.setAlturaNivelDelMarActual(muestras.get(i + 1).getAlturaNivelMar());
                anomalia.setTipoAlerta(TipoAlertaEnum.KAIJU);
                anomalias.add(anomalia);
                
            }
        }

        return anomalias;

    }

    // epic bonus

    /*
     * public TipoAlertaEnum alertaAnomalia(List<Muestra> muestras){
     * if(esAlertaKaiju(muestras)){ return TipoAlertaEnum.KAIJU; }
     * if(esAlertaImpacto(muestras)){ return TipoAlertaEnum.IMPACTO; } return
     * TipoAlertaEnum.SIN_ANOMALIAS; }
     */

    /*
     * public Boolean esAlertaImpacto(int i, List<Muestra> muestras) {
     * 
     * if (i < muestras.size() - 1 &&
     * (diferenciaAltura(muestras.get(i).getAlturaNivelMar(), muestras.get(i +
     * 1).getAlturaNivelMar()) > 500)) { return true; } return false;
     * 
     * }
     */

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

        for (int i = 0; i < muestras.size(); i++) {

        }
        return false;

    }

    public Long minutos(Date primerFecha, Date segundaFecha) {
        /*
         * Long result = primerFecha.getTime() - segundaFecha.getTime(); int
         * diferenciaDias = Math.abs((int) ((primerFecha.getTime() -
         * segundaFecha.getTime()) / (1000 * 60 * 60 * 24))); Long diferenciaHoras =
         * (primerFecha.getTime() - segundaFecha.getTime()) / (60 * 60 * 1000);
         */
        // Long diferenciaMinutos = Math.abs((primerFecha.getTime() -
        // segundaFecha.getTime()) / (60 * 1000));

        Long resultado = segundaFecha.getTime() - primerFecha.getTime();
        resultado = resultado / (1000 * 60) % 60;

        return resultado;
    }
}
