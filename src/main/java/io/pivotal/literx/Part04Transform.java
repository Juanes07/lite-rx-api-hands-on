package io.pivotal.literx;

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
	Mono<User> capitalizeOne(Mono<User> mono) {
		Mono letterCapitalize = mono.map(user ->
				new User(user.getUsername().toUpperCase(),
						user.getFirstname().toUpperCase(),
						user.getLastname().toUpperCase()));
		return letterCapitalize;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName
	Flux<User> capitalizeMany(Flux<User> flux) {
		Flux letterCapitalize = flux.map(user -> new User(
				user.getUsername().toUpperCase(),
				user.getFirstname().toUpperCase(),
				user.getLastname().toUpperCase()));
		return letterCapitalize;
	}

//========================================================================================

	// TODO Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
	Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		Flux<User> userData = flux.flatMap(user -> asyncCapitalizeUser(user));
		return  userData;
	}

	Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}

}
