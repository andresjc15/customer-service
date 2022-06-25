package pe.com.nttdata.customer.model.service;

import pe.com.nttdata.customer.model.document.EnterpriseCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface EnterpriseCustomerService {

    Flux<EnterpriseCustomer> getAll();
    Mono<EnterpriseCustomer> save(EnterpriseCustomer enterpriseCustomer) throws ExecutionException, InterruptedException;
    Mono<EnterpriseCustomer> update(EnterpriseCustomer enterpriseCustomer);
    Mono<EnterpriseCustomer> delete(Long id);
    Mono<EnterpriseCustomer> findById(Long id);
    Mono<Boolean> existById(Long id);

}
