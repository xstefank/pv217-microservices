package io.xstefank;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import java.util.Random;

@ApplicationScoped
@Liveness
public class TestHealthCheck implements HealthCheck {

    private Random random = new Random();

    @Override
    public HealthCheckResponse call() {
        if (random.nextBoolean()) {
            return HealthCheckResponse.up("my-super-health-check");
        }
        return HealthCheckResponse.builder()
            .name("my-super-health-check")
            .withData("exception", "put exception here")
            .down()
            .build();
    }
}
