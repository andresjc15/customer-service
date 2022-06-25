package pe.com.nttdata.customer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.com.nttdata.customer.model.document.PersonalCustomer;
import pe.com.nttdata.customer.model.repository.PersonalCustomerRepository;
import pe.com.nttdata.customer.model.service.PersonalCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class PersonalCustomerServiceImpl implements PersonalCustomerService {

    private static final Logger log = LoggerFactory.getLogger(PersonalCustomerServiceImpl.class);

    private final PersonalCustomerRepository personalCustomerRepository;

    @Override
    public Flux<PersonalCustomer> getAll() {
        return null;
    }

    @Override
    public Mono<PersonalCustomer> save(PersonalCustomer personalCustomer) throws ExecutionException, InterruptedException {
        return null;
    }

    @Override
    public Mono<PersonalCustomer> update(PersonalCustomer personalCustomer) {
        return null;
    }

    @Override
    public Mono<PersonalCustomer> delete(Long id) {
        return null;
    }

    @Override
    public Mono<PersonalCustomer> findById(Long id) {
        return null;
    }

    @Override
    public Mono<Boolean> existById(Long id) {
        return null;
    }
}
