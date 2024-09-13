package com.ejercicio.ejercicio;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.ejercicio.ejercicio.service.PagosService;

@SpringBootTest
class EjercicioApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test 
	void getPagos() {
		PagosService pagosService = Mockito.mock(PagosService.class);
	}

}
