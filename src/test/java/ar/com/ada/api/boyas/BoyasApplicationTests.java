package ar.com.ada.api.boyas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.boyas.entities.Boya;
import ar.com.ada.api.boyas.entities.Muestra;

import ar.com.ada.api.boyas.services.BoyaService;
import ar.com.ada.api.boyas.services.MuestraService;

@SpringBootTest
class BoyasApplicationTests {

	@Autowired
	BoyaService boyaService;

	@Autowired
	MuestraService muestraService;

	@Test
	void coordenadasMuestraInvalidas() {

		Muestra muestra = new Muestra();
		Muestra muestra2 = new Muestra();
		Muestra muestra3 = new Muestra();
		Muestra muestra4 = new Muestra();
		muestra.setLatitud(-91);
		muestra2.setLatitud(91);
		muestra3.setLongitud(-91);
		muestra4.setLongitud(91);
		assertFalse(muestraService.validarCoordenadas(muestra));
		assertFalse(muestraService.validarCoordenadas(muestra2));
		assertFalse(muestraService.validarCoordenadas(muestra3));
		assertFalse(muestraService.validarCoordenadas(muestra4));
	}

	@Test
	void coordenadasMuestraValidas() {

		Muestra muestra = new Muestra();
		Muestra muestra2 = new Muestra();
		Muestra muestra3 = new Muestra();
		Muestra muestra4 = new Muestra();
		muestra.setLatitud(-90);
		muestra2.setLatitud(90);
		muestra3.setLongitud(-90);
		muestra4.setLongitud(90);
		assertTrue(muestraService.validarCoordenadas(muestra));
		assertTrue(muestraService.validarCoordenadas(muestra2));
		assertTrue(muestraService.validarCoordenadas(muestra3));
		assertTrue(muestraService.validarCoordenadas(muestra4));

	}

	@Test
	void coordenadasBoyaInvalidas() {

		Boya boya = new Boya();
		Boya boya2 = new Boya();
		Boya boya3 = new Boya();
		Boya boya4 = new Boya();

		boya.setLatitudInstalacion(-91);
		boya2.setLatitudInstalacion(91);
		boya3.setLongitudInstalacion(-91);
		boya4.setLongitudInstalacion(91);

		assertFalse(boyaService.validarCoordenadas(boya));
		assertFalse(boyaService.validarCoordenadas(boya2));
		assertFalse(boyaService.validarCoordenadas(boya3));
		assertFalse(boyaService.validarCoordenadas(boya4));

	}

	@Test
	void coordenadasBoyaValidas() {

		Boya boya = new Boya();
		Boya boya2 = new Boya();
		Boya boya3 = new Boya();
		Boya boya4 = new Boya();

		boya.setLatitudInstalacion(-90);
		boya2.setLatitudInstalacion(90);
		boya3.setLongitudInstalacion(-90);
		boya4.setLongitudInstalacion(90);

		assertTrue(boyaService.validarCoordenadas(boya));
		assertTrue(boyaService.validarCoordenadas(boya2));
		assertTrue(boyaService.validarCoordenadas(boya3));
		assertTrue(boyaService.validarCoordenadas(boya4));

	}







}
