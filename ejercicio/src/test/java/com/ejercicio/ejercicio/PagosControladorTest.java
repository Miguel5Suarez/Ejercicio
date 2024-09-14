package com.ejercicio.ejercicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejercicio.ejercicio.controlador.PagosControlador;
import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.repository.PagosRepository;
import com.ejercicio.ejercicio.service.PagosService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PagosControladorTest {
	@InjectMocks
	private PagosControlador pagosControlador;
	@Mock
	private PagosService pagosService;
	@Mock
	private PagosRepository pagosRepository;

	private Pagos pagos;
	private PagosEntity pagosEntity;
	String estatus = "estatus";

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
	void consultaEstatusTest() {
		Long id = 1L;
		String estatusPago = "Pagado";
		ResponseEntity<String> estatus = new ResponseEntity<>(estatusPago, HttpStatus.OK);

		when(pagosService.getStatusById(Mockito.anyLong())).thenReturn(estatus);

		ResponseEntity<String> response = pagosControlador.getStatusById(id);

		assertEquals(estatus, response);
	}
}
