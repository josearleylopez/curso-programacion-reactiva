package com.trabajoclase.proyectouno;

import com.trabajoclase.proyectouno.models.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProyectoUnoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void listarClientesTest() {
		webTestClient.get()
				.uri("/api/clientes")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Cliente.class);
	}

	@Test
	public void crearClienteTest(){
		Cliente cliente = Cliente.builder()
				.dni("1089621000")
				.nombres("Emanuel")
				.apellidos("Lopez")
				.correoElectronico("a@a.com")
				.telefonoCelular("3212582525")
				.build();
		webTestClient.post()
				.uri("/api/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(cliente), Cliente.class)
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Cliente.class)
				.consumeWith(response -> {
					Cliente cliente1 = response.getResponseBody();
					Assertions.assertNotNull(cliente1.getId());
					Assertions.assertEquals(cliente.getDni(), cliente1.getDni());
					Assertions.assertEquals(cliente.getNombres(), cliente1.getNombres());
				});
	}

}
