package io.xstefank;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
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

    @Inject
    ProcessingBean bean;

    @Inject
    Tracer tracer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(maxRetries = 10, delay = 1000)
    public String hello() {
        Log.info("Invocation #" + counter.getAndIncrement());

        Span span1 = tracer.spanBuilder("first-span")
            .setAttribute("custom-attribute", "value")
            .startSpan();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        span1.end();

        bean.process("test");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String messageFromServiceB = serviceBClient.hello("what-do-we-have-here");

        return "Hello from Service A! B says: " + messageFromServiceB;
    }
}
