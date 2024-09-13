package com.ejercicio.ejercicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.ejercicio.config.KafkaConfig;

@SpringBootTest
@EmbeddedKafka
@ContextConfiguration(classes = { KafkaConfig.class })
@AutoConfigureMockMvc
public class ProducerTest {

	@Autowired
	KafkaTemplate templateMock;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSendEvent() {
		var dto = templateMock.send("pagos_topic", "A new event");
		assertThat(dto).isNotNull();
	}

	@Test
	void actualizar() throws Exception {
		int id = 22;
		String estatusPago = "Pendiente";
		// Act
		ResultActions result = mockMvc.perform(patch("/pagos/modificar/{id}/{estatusPago}", id, estatusPago));
		assertThat(result).isNotNull();

	}

}
