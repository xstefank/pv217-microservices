package io.xstefank.health;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class TriggerHealthCheck implements HealthCheck {

    private boolean up = true;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.builder().name("trigger-hc");
        if (up) {
            builder.up();
        } else {
            builder.down();
        }
        return builder.build();
    }

    public void toggleUp() {
        up = !up;
    }
}
