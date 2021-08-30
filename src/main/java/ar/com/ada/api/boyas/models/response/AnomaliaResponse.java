package ar.com.ada.api.boyas.models.response;

import java.util.*;

import ar.com.ada.api.boyas.entities.Muestra.TipoAlertaEnum;

public class AnomaliaResponse {
    
    public Double alturaNivelDelMarActual;
    public Date horarioInicioAnomalia;
    public Date horarioInicioFinAnomalia;
    public TipoAlertaEnum tipoAlerta;
}
