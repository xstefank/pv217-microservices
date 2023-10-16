package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello")
@ApplicationScoped
public class GreetingResource {

    public AtomicInteger counter = new AtomicInteger(0);

    @Inject
    @RestClient
    ServiceBClient serviceBClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 5)
    @Fallback(fallbackMethod = "fallbackMethod")
    public String hello() {
        System.out.println("Invocation #" + counter.incrementAndGet());
        System.out.println(serviceBClient.counter());
        return serviceBClient.helloFromB();
    }

    public String fallbackMethod() {
        return "Fallback string";
    }
}
