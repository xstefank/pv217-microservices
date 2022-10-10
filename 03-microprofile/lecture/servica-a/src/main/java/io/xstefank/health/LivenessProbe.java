package io.xstefank.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessProbe implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("test-hc")
            .withData("key", "value")
            .build();
    }
}
