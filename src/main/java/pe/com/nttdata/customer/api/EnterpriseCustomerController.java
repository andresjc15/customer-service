package pe.com.nttdata.customer.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import pe.com.nttdata.customer.client.account.model.service.AccountService;
import pe.com.nttdata.customer.model.document.EnterpriseCustomer;
import pe.com.nttdata.customer.model.request.EnterpriseCustomerRequest;
import pe.com.nttdata.customer.model.service.EnterpriseCustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${path.enterprise-costumers}")
@AllArgsConstructor
public class EnterpriseCustomerController {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseCustomerController.class);

    private final EnterpriseCustomerService enterpriseCustomerService;

    private final AccountService accountService;

    @GetMapping
    @Operation(summary = "Get all Enterprise customer")
    public Mono<ResponseEntity<Flux<EnterpriseCustomer>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(enterpriseCustomerService.getAll())
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Enterprise customer by id")
    public Mono<ResponseEntity<EnterpriseCustomer>> getCustomer(@PathVariable Long id) {
        return enterpriseCustomerService.findById(id)
                .map(customer -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(customer))
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(null)));
    }

    @PostMapping
    @Operation(summary = "Register Enterprise customer")
    public Mono<ResponseEntity<Map<String, Object>>> register(@Valid @RequestBody EnterpriseCustomerRequest enterpriseCustomerRequest)
            throws ExecutionException, InterruptedException {
        EnterpriseCustomer enterpriseCustomer = new EnterpriseCustomer(enterpriseCustomerRequest);
        Map<String, Object> response = new HashMap<String, Object>();
        return enterpriseCustomerService.save(enterpriseCustomer)
                .map(customer -> {
                    response.put("enterpriseCustomer", customer);
                    return ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(response);
                })
                .onErrorResume(error -> {
                    return Mono.just(error).cast(WebExchangeBindException.class)
                            .flatMap(e -> Mono.just(e.getFieldErrors()))
                            .flatMapMany(Flux::fromIterable)
                            .map(fieldError -> "El campo "+fieldError.getField() + " " + fieldError.getDefaultMessage())
                            .collectList()
                            .flatMap(list -> {
                                response.put("errors", list);
                                response.put("timestamp", new Date());
                                response.put("status", HttpStatus.BAD_REQUEST.value());
                                return Mono.just(ResponseEntity.badRequest().body(response));
                            });
                });
    }

    @PutMapping
    @Operation(summary = "Update Enterprise customer")
    public Mono<EnterpriseCustomer> update(@Valid @RequestBody EnterpriseCustomerRequest enterpriseCustomerRequest) {
        EnterpriseCustomer enterpriseCustomer = new EnterpriseCustomer(enterpriseCustomerRequest);
        return enterpriseCustomerService.update(enterpriseCustomer);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Enterprise customer by id")
    public Mono<EnterpriseCustomer> delete(@PathVariable Long id) {
        return enterpriseCustomerService.delete(id);
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
