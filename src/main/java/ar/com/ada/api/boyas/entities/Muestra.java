package ar.com.ada.api.boyas.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.print.attribute.standard.DateTimeAtCreation;

@Entity
public class Muestra {

    @Id
    @Column(name = "muestra_id")
    private Integer muestraId;

    @Column(name = "boya_id")
    private Integer boyaId;

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

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
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

}
