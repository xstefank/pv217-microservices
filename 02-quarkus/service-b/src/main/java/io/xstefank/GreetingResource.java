package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @RestClient
    AvengerClient avengerClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 5, delay = 3000)
    public String hello() {
        System.out.println("Trying to call service-a...");
        return "Hello from Service B, here are your avengers: \n" + avengerClient.getAvengers();
    }
}
