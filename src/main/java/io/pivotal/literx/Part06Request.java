package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

	ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	// Cree un StepVerifier que solicite inicialmente todos los valores y espere que se reciban 4 valores
	StepVerifier requestAllExpectFour(Flux<User> flux) {
		return StepVerifier.create(flux).expectNextCount(4).expectComplete();
	}

//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER
	//  then requests another value and expects User.JESSE then stops verifying by cancelling the source
	// Cree un StepVerifier que inicialmente solicite 1 valor y espere User.SKYLER
	// luego solicita otro valor y espera User.JESSE luego deja de verificar cancelando la fuente
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
		return StepVerifier.create(flux,1).expectNext(User.SKYLER)
				.thenRequest(1).expectNext(User.JESSE).thenCancel();
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive
	//  Streams signals
	// Devuelve un flujo con todos los usuarios almacenados en el repositorio que imprime automáticamente
	// los registros de todos los reactivos Transmite señales
	Flux<User> fluxWithLog() {
		return repository.findAll().log();
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe,
	//  "firstname lastname" for all values and "The end!" on complete
	// Devuelve un Flux con todos los usuarios almacenados en el repositorio que imprime "Protagonizado:" al suscribirse,
	// "firstname lastname" para todos los valores y "¡Fin!" en completo
	Flux<User> fluxWithDoOnPrintln() {
		return repository
				.findAll()
				.doOnSubscribe(subscription -> System.out.println("Starring:"))
				.doOnNext(user -> System.out.println(user.getFirstname() + " " + user.getLastname()))
				.doOnComplete(() -> System.out.println("The end!"));
	}

}
