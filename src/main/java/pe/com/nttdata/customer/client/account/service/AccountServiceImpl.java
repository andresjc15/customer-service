package pe.com.nttdata.customer.client.account.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.customer.client.account.Account;
import pe.com.nttdata.customer.client.account.model.service.AccountService;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class AccountServiceImpl implements AccountService {

    @Qualifier("accountClient")
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Mono<Account> findById(Long id) {
        return webClientBuilder.build()
                .get()
                .uri("/{id}", Collections.singletonMap("id", id))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Account.class);
    }

}
