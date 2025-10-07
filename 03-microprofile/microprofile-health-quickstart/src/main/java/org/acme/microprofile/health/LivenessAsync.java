import io.smallrye.health.api.AsyncHealthCheck;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.HealthCheckResponse;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@Liveness
@ApplicationScoped
public class LivenessAsync implements AsyncHealthCheck {

    @Override
    public Uni<HealthCheckResponse> call() {
        return Uni.createFrom().item(HealthCheckResponse.up("liveness-reactive"))
            .onItem().delayIt().by(Duration.ofMillis(10));
    }
}