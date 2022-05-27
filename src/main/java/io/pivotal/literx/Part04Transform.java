package io.pivotal.literx;

import ch.qos.logback.core.helpers.Transform;
import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Locale;

/**
 * Learn how to transform values.
 *
 * @author Sebastien Deleuze
 */
public class Part04Transform {

//========================================================================================

	// TODO Capitalize the user username, firstname and lastname
	// Escriba con mayúscula el nombre de usuario, nombre y apellido del usuario
	Mono<User> capitalizeOne(Mono<User> mono) {
		return mono.map(user -> new User(
				user.getUsername().toUpperCase(),
				user.getFirstname().toUpperCase(),
				user.getLastname().toUpperCase()));
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map(user -> new User(
				user.getUsername().toUpperCase(),
				user.getFirstname().toUpperCase(),
				user.getLastname().toUpperCase()));
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		return flux.flatMap(this::asyncCapitalizeUser);
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
