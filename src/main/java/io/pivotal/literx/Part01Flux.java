package io.pivotal.literx;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

//========================================================================================

	// TODO Return an empty Flux
	Flux<String> emptyFlux() {
		return Flux.empty();
	}

//========================================================================================

	// TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
	//Devuelve un Flux que contiene 2 valores "foo" y "bar" sin usar una matriz o una colección
	Flux<String> fooBarFluxFromValues() {
		return Flux.create(emisor -> { //interfaz FluxSink, metodo next, recibe objeto y no devuelve nada
			emisor.next("foo");
			emisor.next("bar");
			emisor.complete(); // completamos el contrato con la interfaz FluxSink
		});
	}

//========================================================================================

	// TODO Create a Flux from a List that contains 2 values "foo" and "bar"
	// Cree un Flux a partir de una lista que contenga 2 valores "foo" y "bar"
	Flux<String> fooBarFluxFromList() {
		List<String> listaDeValores = new ArrayList<String>();
		listaDeValores.add("foo");
		listaDeValores.add("bar");
		return Flux.fromIterable(listaDeValores);
	}

//========================================================================================

	// TODO Create a Flux that emits an IllegalStateException
	//Crear un Flux que emita una IllegalStateException
	Flux<String> errorFlux() {
		return Flux.error(new IllegalStateException());
	}

//========================================================================================

		// TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
		//Crea un Flux que emita valores crecientes de 0 a 9 cada 100ms
	Flux<Long> counter() {
		return Flux.range(0,10).map(a -> new Long(a)).delayElements(Duration.ofMillis(100));
	}

}
