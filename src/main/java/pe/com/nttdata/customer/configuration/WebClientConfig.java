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

    @Bean(name = "accountClient")
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClient() { return WebClient.builder().baseUrl(accountUri); }

    @Bean(name = "movementClient")
    @LoadBalanced
    public WebClient.Builder movementWebClient() { return WebClient.builder().baseUrl("lb://movement-service/api/movements"); }

}
