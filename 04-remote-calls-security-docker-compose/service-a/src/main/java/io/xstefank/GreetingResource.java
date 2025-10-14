package io.xstefank;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello")
public class GreetingResource {

    private static AtomicInteger counter = new AtomicInteger(0);

    @RestClient
    ServiceBClient serviceBClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 10, delay = 1000)
    public String hello() {
        Log.info("Invocation #" + counter.getAndIncrement());

        String messageFromServiceB = serviceBClient.hello("what-do-we-have-here");

        return "Hello from Service A! B says: " + messageFromServiceB;
    }
}
