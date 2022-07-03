package pe.com.nttdata.customer.client.account.model.service;

import pe.com.nttdata.customer.client.account.Account;
import reactor.core.publisher.Mono;

public interface AccountService {

    public Mono<Account> findById(Long id);

}
