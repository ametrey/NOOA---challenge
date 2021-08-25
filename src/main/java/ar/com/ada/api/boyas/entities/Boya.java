package ar.com.ada.api.boyas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Boya {

    @Id
    @Column(name = "boya_id")
    private Integer boyaId;

    @Column(name = "luz_color")
    private String luzColor;

    @Column(name = "longitud_instalacion")
    private double longitudInstalacion;

    @Column(name = "latitud_instalacion")
    private double latitudInstalacion;



    

    public Integer getBoyaId() {
        return boyaId;
    }

    public void setBoyaId(Integer boyaId) {
        this.boyaId = boyaId;
    }

    public String getLuzColor() {
        return luzColor;
    }

    public void setLuzColor(String luzColor) {
        this.luzColor = luzColor;
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

        // NOTE: Enum constructor tiene que estar en privado
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
