package pe.com.nttdata.customer.api;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.nttdata.customer.model.document.EnterpriseCustomer;
import pe.com.nttdata.customer.model.service.EnterpriseCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("${path.enterprise-costumer}")
@AllArgsConstructor
public class EnterpriseCustomerController {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseCustomerController.class);

    private final EnterpriseCustomerService enterpriseCustomerService;

    @GetMapping
    public Flux<EnterpriseCustomer> getAll() { return enterpriseCustomerService.getAll(); }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<EnterpriseCustomer>> getCustomer(@PathVariable Long id) {
        return enterpriseCustomerService.findById(id).map(customer -> {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(customer);
        }).onErrorResume(e -> {
            return Mono.just(ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null));
        });
    }

    @PostMapping
    public Mono<EnterpriseCustomer> register(@Valid @RequestBody EnterpriseCustomer enterpriseCustomer)
            throws ExecutionException, InterruptedException {
        return enterpriseCustomerService.save(enterpriseCustomer);
    }

    @PutMapping
    public Mono<EnterpriseCustomer> update(@Valid @RequestBody EnterpriseCustomer enterpriseCustomer) {
        return enterpriseCustomerService.update(enterpriseCustomer);
    }

    @DeleteMapping("/{id}")
    public Mono<EnterpriseCustomer> delete(@PathVariable Long id) {
        return enterpriseCustomerService.delete(id);
    }

}
