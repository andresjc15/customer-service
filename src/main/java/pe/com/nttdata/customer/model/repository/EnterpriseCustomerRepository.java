package pe.com.nttdata.customer.model.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.com.nttdata.customer.model.document.EnterpriseCustomer;

@Repository
public interface EnterpriseCustomerRepository extends ReactiveMongoRepository<EnterpriseCustomer, Long> {
}
