package com.cognizant.microservices.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient thirdPartyPaymentClient(@Value("${third-party.payment.url}") String thirdPartyPaymentUrl) {
        return WebClient.builder()
                .baseUrl(thirdPartyPaymentUrl)
                .build();
    }
}
