package io.xstefank.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class AvengerGeneratorHealthCheck implements HealthCheck {

    @ConfigProperty(name = "quarkus.rest-client.avenger-generator-client.url")
    String url;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder builder = HealthCheckResponse.builder()
            .name("Avenger generator available");

        Client client = null;

        try {
            client = ClientBuilder.newClient();
            Response response = client.target(url).path("/q/health")
                .request().get();
            response.close();
            builder.up();
        } catch (Exception e) {
            builder.down()
                .withData("exception message", e.getMessage());
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return builder.build();
    }
}