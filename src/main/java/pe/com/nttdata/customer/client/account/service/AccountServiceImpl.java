package pe.com.nttdata.customer.client.account.service;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.nttdata.customer.client.account.Account;
import pe.com.nttdata.customer.client.account.model.service.AccountService;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    //private final WebClient client;
    private final WebClient.Builder webClientBuilder;

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
