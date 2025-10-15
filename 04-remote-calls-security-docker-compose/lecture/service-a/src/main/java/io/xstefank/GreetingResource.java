package io.xstefank;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.quarkus.logging.Log;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    private static int counter = 0;

    @RestClient
    ServiceBClient serviceBClient;

    @Inject
    ProcessingBean bean;

    @Inject
    Tracer tracer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 3, delay = 10)
    @Fallback(fallbackMethod = "fallbackHello")
    public String hello() {
        Log.info("Invocation #" + ++counter);
        Log.info("Processing " + bean.process("test-value"));

        Span span = tracer.spanBuilder("my-span")
            .setAttribute("test-key", "test-value")
            .startSpan();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        span.end();

        String result = serviceBClient.hello("test-value-from-a");


        return "Hello from Service A, B says: " + result;
    }

    public String fallbackHello() {
        return "This is a fallback";
    }

//    @PermitAll
    @RolesAllowed("admin")
    @GET
    @Path("/secret")
    public String secret() {
        return "Admin only secret";
    }


}
