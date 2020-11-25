package io.xstefank.health;

import io.xstefank.client.SnapRESTClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class SnapServiceHealthCheck implements HealthCheck {

    @Inject
    @RestClient
    SnapRESTClient snapRESTClient;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.builder()
            .name("Snap service available");

        try {
            snapRESTClient.callHealth();
            builder.up();
        } catch (Exception e) {
            builder.down()
                .withData("exception message", e.getMessage());
        }

        return builder.build();
    }
}
