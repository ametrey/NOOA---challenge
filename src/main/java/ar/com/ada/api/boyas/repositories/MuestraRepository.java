package ar.com.ada.api.boyas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;
import ar.com.ada.api.boyas.entities.Boya.ColorBoyaEnum;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Integer>{

    Boya traerBoyaPorColor(ColorBoyaEnum color);
    
}
