package io.xstefank.health;

import io.xstefank.client.TokenIssuerRESTClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class TokenIssuerHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    TokenIssuerRESTClient tokenIssuerRESTClient;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.builder()
            .name("Token issuer available");

        try {
            tokenIssuerRESTClient.callHealth();
            builder.up();
        } catch (Exception e) {
            builder.down()
                .withData("exception message", e.getMessage());
        }

        return builder.build();
    }
}
