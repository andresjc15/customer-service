package pe.com.nttdata.customer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.com.nttdata.customer.model.document.PersonalCustomer;
import pe.com.nttdata.customer.model.repository.PersonalCustomerRepository;
import pe.com.nttdata.customer.model.service.PersonalCustomerService;
import pe.com.nttdata.customer.util.SequenceGeneratorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class PersonalCustomerServiceImpl implements PersonalCustomerService {

    private static final Logger log = LoggerFactory.getLogger(PersonalCustomerServiceImpl.class);

    private final PersonalCustomerRepository personalCustomerRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<PersonalCustomer> getAll() {
        return personalCustomerRepository.findAll();
    }

    @Override
    public Mono<PersonalCustomer> save(PersonalCustomer personalCustomer)
            throws ExecutionException, InterruptedException {
        personalCustomer.setId(sequenceGeneratorService.generateSequence(PersonalCustomer.SEQUENCE_NAME));
        personalCustomer.setActive(true);
        personalCustomer.setCreatedAt(new Date());
        personalCustomer.setUpdatedAt(null);
        return personalCustomerRepository.save(personalCustomer);
    }

    @Override
    public Mono<PersonalCustomer> update(PersonalCustomer personalCustomer) {
        return personalCustomerRepository.findById(personalCustomer.getId()).flatMap(c -> {
            c.setName(personalCustomer.getName());
            c.setLastName(personalCustomer.getLastName());
            c.setAddress(personalCustomer.getAddress());
            c.setDni(personalCustomer.getDni());
            c.setUpdatedAt(new Date());
            return personalCustomerRepository.save(personalCustomer);
        });
    }

    @Override
    public Mono<PersonalCustomer> delete(Long id) {
        return personalCustomerRepository.findById(id).flatMap(c -> {
            c.setActive(false);
            c.setUpdatedAt(new Date());
            return personalCustomerRepository.save(c);
        });
    }

    @Override
    public Mono<PersonalCustomer> findById(Long id) { return personalCustomerRepository.findById(id); }

    @Override
    public Mono<Boolean> existById(Long id) { return personalCustomerRepository.existsById(id); }
}
