package org.acme;

import io.quarkus.runtime.ApplicationLifecycleManager;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

import java.util.UUID;

@Path("/")
public class GreetingResource {

    private final String id = "Node " + UUID.randomUUID();

    @GET
    @Path("/whereami")
    public String whereami() {
        try (Client client = ClientBuilder.newClient()) {
            return client.target("http://ip-api.com/json")
                .request().get().readEntity(String.class);
        }
    }

    @GET
    @Path("/whoami")
    public String whoami() {
        return "Id: " + id;
    }

    @GET
    @Path("/kill")
    public void kill() {
        System.exit(0);
    }
}
