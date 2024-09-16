package com.ejercicio.ejercicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ejercicio.config.KafkaConfig;
import com.ejercicio.ejercicio.controlador.PagosControlador;
import com.ejercicio.ejercicio.entity.PagosEntity;
import com.ejercicio.ejercicio.modelo.Pagos;
import com.ejercicio.ejercicio.service.PagosService;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
@EmbeddedKafka
@ContextConfiguration(classes = { KafkaConfig.class })
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ProducerTest {

	@InjectMocks
	private PagosControlador pagosControlador;
	@Mock
	private PagosService pagosService;

	@Autowired
	KafkaTemplate templateMock;

	@Autowired
	private MockMvc mockMvc;

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
	void testSendEvent() {
		var dto = templateMock.send("pagos_topic", "A new event");
		assertThat(dto).isNotNull();
	}

	@Test
	void actualizar() throws Exception {
		int id = 22;
		String estatusPago = "Pendiente";
		ResultActions result = mockMvc.perform(patch("/pagos/modificar/{id}/{estatusPago}", id, estatusPago));
		assertThat(result).isNotNull();

	}

	@Test
	void actualizaEstatusNullTest() throws JsonProcessingException {
		Long id = 1L;
		String estatusPago = "Activo";

		Mockito.when(pagosService.estatusPorId(id)).thenReturn(null);

		ResponseEntity<?> response = pagosControlador.updatePagosById(id, estatusPago);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		assertEquals("No se encontró pago", response.getBody());
	}

}
