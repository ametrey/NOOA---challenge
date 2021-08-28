package ar.com.ada.api.boyas.entities;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boya {

    @Id
    @Column(name = "boya_id")
    private Integer boyaId;

    @Column(name = "luz_color")
    private Integer luzColor;

    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;

    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;

    @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Muestra> muestras = new ArrayList<>();



    

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }

    public ColorBoyaEnum getLuzColor() {
       
        return ColorBoyaEnum.parse(luzColor);
    }

    public void setLuzColor(ColorBoyaEnum luzColor) {
        this.luzColor = luzColor.getValue();
    }

    public double getLongitudInstalacion() {
        return longitudInstalacion;
    }

    public void setLongitudInstalacion(double longitudInstalacion) {
        this.longitudInstalacion = longitudInstalacion;
    }

    public double getLatitudInstalacion() {
        return latitudInstalacion;
    }

    public void setLatitudInstalacion(double latitudInstalacion) {
        this.latitudInstalacion = latitudInstalacion;
    }

    public enum ColorBoyaEnum {
        ROJO(1), VERDE(2), AZUL(3), AMARILLO(4); 

        private final Integer value;

        
        private ColorBoyaEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static ColorBoyaEnum parse(Integer id) {
            ColorBoyaEnum status = null; // Default
            for (ColorBoyaEnum item : ColorBoyaEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }


    


    
}
