package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @RestClient
    ServiceAClient serviceAClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 3, delay = 1000)
//    @Timeout(1)
    @Fallback(fallbackMethod = "helloFallback")
    public String hello() {
        System.out.println("Service B called");
        System.out.println(serviceAClient.whatever());
        return "Hello from Quarkus REST";
    }

    public String helloFallback() {
        return "Hello from Fallback";
    }
}
