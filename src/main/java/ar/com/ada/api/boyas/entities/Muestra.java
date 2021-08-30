package ar.com.ada.api.boyas.entities;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.com.ada.api.boyas.models.response.AnomaliaResponse;


@Entity
public class Muestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muestra_id")
    private Integer muestraId;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    @Column(name = "horario_muestra")
    private Date horarioMuestra;

    @Column(name = "matricula_embarcacion")
    private String matriculaEmbarcacion;

    private double longitud;

    private double latitud;

    @Column(name = "altura_nivel_del_mar")
    private double alturaNivelMar;


    

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
        this.boya.agregarMuestra(this);
    }

    public Date getHorarioMuestra() {
        return horarioMuestra;
    }

    public void setHorarioMuestra(Date horarioMuestra) {
        this.horarioMuestra = horarioMuestra;
    }

    public String getMatriculaEmbarcacion() {
        return matriculaEmbarcacion;
    }

    public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getAlturaNivelMar() {
        return alturaNivelMar;
    }

    public void setAlturaNivelMar(double alturaNivelMar) {
        this.alturaNivelMar = alturaNivelMar;
    }

    

    public enum TipoAlertaEnum {
        KAIJU(1), IMPACTO(2), SIN_ANOMALIAS(3);

        private final Integer value;

        
        private TipoAlertaEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoAlertaEnum parse(Integer id) {
            TipoAlertaEnum status = null; 

            for (TipoAlertaEnum item : TipoAlertaEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
}
