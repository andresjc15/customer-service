package pe.com.nttdata.customer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${config.endpoint.accounts}")
    private String accountUri;

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClient() { return WebClient.builder().baseUrl(accountUri); }
    /*
    @Bean
    @LoadBalanced
    public WebClient registerWebClient() { return WebClient.create(accountUri); }
    */

}
