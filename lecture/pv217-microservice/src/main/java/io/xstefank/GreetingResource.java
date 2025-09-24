package io.xstefank;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import java.util.UUID;

@Path("/")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus PV217";
    }

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

    @Inject
    ChatModel chatModel;

    @GET
    @Path("/joke")
    public String joke() {
        return chatModel.chat("Tell me local joke for this city: " + whereami());
    }


}
