package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @RestClient
    ServiceBClient serviceBClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String messageFromServiceB = serviceBClient.hello("what-do-we-have-here");

        return "Hello from Service A! B says: " + messageFromServiceB;
    }
}
