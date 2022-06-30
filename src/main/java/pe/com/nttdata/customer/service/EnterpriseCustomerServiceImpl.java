package pe.com.nttdata.customer.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pe.com.nttdata.customer.model.document.Customer;
import pe.com.nttdata.customer.model.document.EnterpriseCustomer;
import pe.com.nttdata.customer.model.repository.EnterpriseCustomerRepository;
import pe.com.nttdata.customer.model.service.EnterpriseCustomerService;
import pe.com.nttdata.customer.util.SequenceGeneratorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class EnterpriseCustomerServiceImpl implements EnterpriseCustomerService {

    private static final Logger log = LoggerFactory.getLogger(EnterpriseCustomerServiceImpl.class);

    private final EnterpriseCustomerRepository enterpriseCustomerRepository;

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public Flux<EnterpriseCustomer> getAll() {
        log.info("Obteniendo clientes empresariales");
        return enterpriseCustomerRepository.findAll();
    }

    @Override
    public Mono<EnterpriseCustomer> save(EnterpriseCustomer enterpriseCustomer) throws ExecutionException, InterruptedException {
        enterpriseCustomer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
        enterpriseCustomer.setCreatedAt(new Date());
        enterpriseCustomer.setUpdatedAt(null);
        return enterpriseCustomerRepository.save(enterpriseCustomer);
    }

    @Override
    public Mono<EnterpriseCustomer> update(EnterpriseCustomer enterpriseCustomer) {
        return enterpriseCustomerRepository.findById(enterpriseCustomer.getId()).flatMap(c -> {
            c.setBusinessName(enterpriseCustomer.getBusinessName());
            c.setRuc(enterpriseCustomer.getRuc());
            c.setUpdatedAt(new Date());
            return enterpriseCustomerRepository.save(c);
        });
    }

    @Override
    public Mono<EnterpriseCustomer> delete(Long id) {
        return enterpriseCustomerRepository.findById(id).flatMap(c -> {
            c.setActive(false);
            c.setUpdatedAt(new Date());
            return enterpriseCustomerRepository.save(c);
        });
    }

    @Override
    public Mono<EnterpriseCustomer> findById(Long id) {
        return enterpriseCustomerRepository.findById(id);
    }

    @Override
    public Mono<Boolean> existById(Long id) {
        return enterpriseCustomerRepository.existsById(id);
    }
}
