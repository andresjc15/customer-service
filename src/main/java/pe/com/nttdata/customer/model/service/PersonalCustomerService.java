package pe.com.nttdata.customer.model.service;

import pe.com.nttdata.customer.model.document.PersonalCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface PersonalCustomerService {

    Flux<PersonalCustomer> getAll();
    Mono<PersonalCustomer> save(PersonalCustomer personalCustomer) throws ExecutionException, InterruptedException;
    Mono<PersonalCustomer> update(PersonalCustomer personalCustomer);
    Mono<PersonalCustomer> delete(Long id);
    Mono<PersonalCustomer> findById(Long id);
    Mono<Boolean> existById(Long id);

}
