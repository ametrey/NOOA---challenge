package ar.com.ada.api.boyas.entities;

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




    public List<Anomalia> traerAnomalias(){
        return null;

        
        
    }
}
