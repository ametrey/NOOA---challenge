package ar.com.ada.api.boyas.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.ada.api.boyas.entities.Muestra.TipoAlertaEnum;

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
        Anomalia impacto = new Anomalia();
        Anomalia kaiju = new Anomalia();

        for (int i = 0; i < muestras.size(); i++) {

            if (i < muestras.size() - 1 && (diferenciaAltura(muestras.get(i).getAlturaNivelMar(),
                    muestras.get(i + 1).getAlturaNivelMar()) > 500)) {
                impacto.setHorarioInicioAnomalia(muestras.get(i).getHorarioMuestra());
                impacto.setHorarioInicioFinAnomalia(muestras.get(i + 1).getHorarioMuestra());
                impacto.setAlturaNivelDelMarActual(muestras.get(i + 1).getAlturaNivelMar());
                impacto.setTipoAlerta(TipoAlertaEnum.IMPACTO);
                anomalias.add(impacto);

            }
        }

        for (int i = 0; i < muestras.size(); i++) {

            if (i < muestras.size() - 1
                    && (minutos(muestras.get(i).getHorarioMuestra(), muestras.get(i + 1).getHorarioMuestra()) > 10
                            && diferenciaAltura(muestras.get(i).getAlturaNivelMar(),
                                    muestras.get(i + 1).getAlturaNivelMar()) == 200)) {
                kaiju.setHorarioInicioAnomalia(muestras.get(i).getHorarioMuestra());
                kaiju.setHorarioInicioAnomalia(muestras.get(i + 1).getHorarioMuestra());
                kaiju.setAlturaNivelDelMarActual(muestras.get(i + 1).getAlturaNivelMar());
                kaiju.setTipoAlerta(TipoAlertaEnum.KAIJU);
                anomalias.add(kaiju);

            }
        }

        return anomalias;

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

        for (int i = 0; i < muestras.size(); i++) {

        }
        return false;

    }

    public Long minutos(Date primerFecha, Date segundaFecha) {
        

        Long resultado = segundaFecha.getTime() - primerFecha.getTime();
        resultado = resultado / (1000 * 60) % 60;

        return resultado;
    }
}
