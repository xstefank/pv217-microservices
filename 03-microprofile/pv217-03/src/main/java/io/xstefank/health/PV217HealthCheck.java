package io.xstefank.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class PV217HealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        // verify your state

        return HealthCheckResponse.named("PV217 HC")
            .down()
            .withData("excepton", "value")
            .build();
    }
}
