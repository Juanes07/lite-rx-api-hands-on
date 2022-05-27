package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

    // TODO Create a Flux of user from Flux of username, firstname and lastname.
    Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
        Flux<User> combineList = Flux.zip(usernameFlux,firstnameFlux,lastnameFlux)
                .map(data -> new User(data.getT1(),data.getT2(),data.getT3()));
        return combineList;
    }

//========================================================================================

    // TODO Return the mono which returns its value faster
    Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
        Mono<User> fasterMono = Mono.first(mono1,mono2);
        return fasterMono;
    }

//========================================================================================

    // TODO Return the flux which returns the first value faster
    Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
        Flux<User> fasterFlux = Flux.first(flux1,flux2);
        return fasterFlux;
    }

//========================================================================================

    // TODO Convert the input Flux<User> to a Mono<Void> that represents the complete signal of the flux
    Mono<Void> fluxCompletion(Flux<User> flux) {
        Mono<Void> input = flux.then();
        return input;
    }

//========================================================================================

    // TODO Return a valid Mono of user for null input and non null input user (hint: Reactive Streams do not accept null values)
    Mono<User> nullAwareUserToMono(User user) {
        Mono<User> mono = Mono.justOrEmpty(user);
        return mono;
    }

//========================================================================================

    // TODO Return the same mono passed as input parameter, expect that it will emit User.SKYLER when empty
    Mono<User> emptyToSkyler(Mono<User> mono) {
        Mono<User> user = mono.defaultIfEmpty(User.SKYLER);
        return user;
    }

//========================================================================================

    // TODO Convert the input Flux<User> to a Mono<List<User>> containing list of collected flux values
    Mono<List<User>> fluxCollection(Flux<User> flux) {
        Mono<List<User>> list = flux.collectList();
        return list;
    }

}
