package pe.com.nttdata.customer.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.customer.model.document.PersonalCustomer;
import pe.com.nttdata.customer.model.request.PersonalCustomerRequest;
import pe.com.nttdata.customer.model.service.PersonalCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("${path.personal-costumers}")
@AllArgsConstructor
public class PersonalCustomerController {

    private static final Logger log = LoggerFactory.getLogger(PersonalCustomerController.class);

    private PersonalCustomerService personalCustomerService;

    @GetMapping
    public Flux<PersonalCustomer> getAll() { return personalCustomerService.getAll(); }

    @GetMapping("/{id}")
    public Mono<PersonalCustomer> getCustomer(@PathVariable Long id) {
        return personalCustomerService.findById(id);
    }

    @PostMapping
    public Mono<PersonalCustomer> register(@Valid @RequestBody PersonalCustomerRequest personalCustomerRequest)
            throws ExecutionException, InterruptedException {
        PersonalCustomer personalCustomer = new PersonalCustomer(personalCustomerRequest);
        return personalCustomerService.save(personalCustomer);
    }

    @PutMapping
    public Mono<PersonalCustomer> update(@Valid @RequestBody PersonalCustomerRequest personalCustomerRequest) {
        PersonalCustomer personalCustomer = new PersonalCustomer(personalCustomerRequest);
        return personalCustomerService.update(personalCustomer);
    }

    @DeleteMapping("/{id}")
    public Mono<PersonalCustomer> delete(@PathVariable Long id) {
        return personalCustomerService.delete(id);
    }
}
