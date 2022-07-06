package pe.com.nttdata.customer.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import pe.com.nttdata.customer.model.document.PersonalCustomer;
import pe.com.nttdata.customer.model.request.PersonalCustomerRequest;
import pe.com.nttdata.customer.model.service.PersonalCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${path.personal-costumers}")
@AllArgsConstructor
public class PersonalCustomerController {

    private static final Logger log = LoggerFactory.getLogger(PersonalCustomerController.class);

    private PersonalCustomerService personalCustomerService;

    @GetMapping
    @Operation(summary = "Get all Personal customer")
    public Flux<PersonalCustomer> getAll() { return personalCustomerService.getAll(); }

    @GetMapping("/{id}")
    @Operation(summary = "Get Personal customer by id")
    public Mono<PersonalCustomer> getCustomer(@PathVariable Long id) {
        return personalCustomerService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Register Personal customer")
    public Mono<PersonalCustomer> register(@Valid @RequestBody PersonalCustomerRequest personalCustomerRequest)
            throws ExecutionException, InterruptedException {
        PersonalCustomer personalCustomer = new PersonalCustomer(personalCustomerRequest);
        return personalCustomerService.save(personalCustomer);
    }

    @PutMapping
    @Operation(summary = "Update Personal customer")
    public Mono<PersonalCustomer> update(@Valid @RequestBody PersonalCustomerRequest personalCustomerRequest) {
        PersonalCustomer personalCustomer = new PersonalCustomer(personalCustomerRequest);
        return personalCustomerService.update(personalCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Personal customer by id")
    public Mono<PersonalCustomer> delete(@PathVariable Long id) {
        return personalCustomerService.delete(id);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Map<String, Object>> handleException(WebExchangeBindException e) {
        Map<String, Object> response = new HashMap<String, Object>();
        var errors = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        response.put("errors", errors);
        response.put("timestamp", new Date());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

}
