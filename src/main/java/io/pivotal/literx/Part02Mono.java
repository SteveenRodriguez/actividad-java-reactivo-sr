package io.pivotal.literx;

import reactor.core.publisher.Mono;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {

//========================================================================================

	// TODO Return an empty Mono
	Mono<String> emptyMono() {
		return Mono.empty();
	}

//========================================================================================

	// TODO Return a Mono that never emits any signal
	// Devuelve un Mono que nunca emite ninguna señal
	Mono<String> monoWithNoSignal() {
		return Mono.never();
	}

//========================================================================================

	// TODO Return a Mono that contains a "foo" value
	//Devuelve un Mono que contiene un valor "foo"
	Mono<String> fooMono() {
		return Mono.create(emisor -> emisor.success("foo"));
	}

//========================================================================================

	// TODO Create a Mono that emits an IllegalStateException
	// Crear un Mono que emita una IllegalStateException
	Mono<String> errorMono() {
		return Mono.error(new IllegalStateException());
	}

}
