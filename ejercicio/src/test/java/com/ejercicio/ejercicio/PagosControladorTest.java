package com.ejercicio.ejercicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.ejercicio.ejercicio.controlador.PagosControlador;
import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.service.PagosService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PagosControladorTest {
	@InjectMocks
	private PagosControlador pagosControlador;
	@Mock
	private PagosService pagosService;
	private Pagos pagos;
	private PagosEntity pagosEntity;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		pagos = new Pagos();
		pagos.setBeneficiario("Beneficiario");
		pagos.setCantidadProductos(1);
		pagos.setConcepto("Concepto");
		pagos.setDeudor("Deudor");
		pagos.setEstatusPago("Activo");
		pagos.setId(1L);
		pagos.setMonto(BigDecimal.valueOf(10.0));

		pagosEntity = new PagosEntity();
		pagosEntity.setId(1L);
		pagosEntity.setBeneficiario("Beneficiario");
		pagosEntity.setCantidadProductos(5);
		pagosEntity.setConcepto("Concepto");
		pagosEntity.setDeudor("Deudor");
		pagosEntity.setMonto(BigDecimal.valueOf(10.0));
		pagosEntity.setEstatusPago("Estatus");
	}

	@Test
	public void guadarTest() {
		ResponseEntity<Pagos> response = pagosControlador.guardar(pagos);
		assertNull(response);
	}

	@Test
	void consultaTest() {
		List<PagosEntity> response = pagosControlador.getPagos();
		assertNotNull(response);
	}

	@Test
	void consultaPorIdTest() {
		when(pagosService.getPagosById(1L)).thenReturn(Optional.of(pagosEntity));

		Optional<PagosEntity> response = pagosControlador.getPagosById(1L);
		assertNotNull(response);
	}

	@Test
	void consultaPorIdEmptyTest() {
		when(pagosService.getPagosById(1L)).thenReturn(Optional.empty());

		Optional<PagosEntity> response = pagosControlador.getPagosById(1L);
		assertThat(response.isEmpty());
	}

}
