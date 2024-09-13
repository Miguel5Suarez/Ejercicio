package com.ejercicio.ejercicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.repository.PagosRepository;
import com.ejercicio.ejercicio.service.PagosServiceImpl;

public class PagosServiceTest {
	@Mock
	private PagosRepository pagosRepository;

	@InjectMocks
	private PagosServiceImpl pagosService;
	private BigDecimal decimal = new BigDecimal("12.76");
	private Pagos pagos;
	private PagosEntity pagosEntity;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		pagos = new Pagos();
		pagos.setId(1L);
		pagos.setBeneficiario("Beneficiario");
		pagos.setCantidadProductos(5);
		pagos.setConcepto("Concepto");
		pagos.setDeudor("Deudor");
		pagos.setMonto(decimal);
		pagos.setEstatusPago("Estatus");

		pagosEntity = new PagosEntity();
		pagosEntity.setId(1L);
		pagosEntity.setBeneficiario("Beneficiario");
		pagosEntity.setCantidadProductos(5);
		pagosEntity.setConcepto("Concepto");
		pagosEntity.setDeudor("Deudor");
		pagosEntity.setMonto(decimal);
		pagosEntity.setEstatusPago("Estatus");

	}

	@Test
	void guardarTest() {

		when(pagosRepository.findById(1L)).thenReturn(Optional.empty());

		ResponseEntity<Pagos> response = pagosService.guardar(pagos);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pagos, response.getBody());
	}

	@Test
	void errorGuardarRepetidoTest() {

		when(pagosRepository.findById(1L)).thenReturn(Optional.of(pagosEntity));

		ResponseEntity<Pagos> response = pagosService.guardar(pagos);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals(pagos, response.getBody());
	}

	@Test
	void consultaTest() {

		when(pagosRepository.findAll()).thenReturn(List.of(pagosEntity));

		List<PagosEntity> response = pagosService.getPagos();
		assertNotNull(response);
	}

	@Test
	void consultaPorIdTest() {

		when(pagosRepository.findById(1L)).thenReturn(Optional.of(pagosEntity));

		Optional<PagosEntity> response = pagosService.getPagosById(1L);
		assertNotNull(response);
	}

	@Test
	void consultaPorIdVacioTest() {

		when(pagosRepository.findById(1L)).thenReturn(Optional.empty());

		Optional<PagosEntity> response = pagosService.getPagosById(1L);
		assertThat(response.isEmpty());
	}

	@Test

	void guadarTest() {

		pagosService.savePagos(pagosEntity);

		verify(pagosRepository).save(pagosEntity);

	}
}
