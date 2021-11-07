package io.xstefank.health;

import io.xstefank.client.AvengerGeneratorClient;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Readiness
@ApplicationScoped
public class AvengerGeneratorHealthCheck implements HealthCheck {

    @ConfigProperty(name = "avenger-generator-client/mp-rest/url")
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
