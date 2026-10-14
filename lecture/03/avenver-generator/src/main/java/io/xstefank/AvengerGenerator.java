package io.xstefank;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@Path("/avenger")
public class AvengerGenerator {

    @Inject
    Tracer tracer;

    @GET
    @Path("/generate")
    public Avenger generateAvenger() {
        sleep();
        Avenger avenger = new Avenger();
        avenger.name = "GENERATED";
        avenger.civilName = "GENERATED";
        avenger.battleworld = false;
        return avenger;
    }

    @WithSpan("my-custom-sleep")
    public void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Span span = tracer.spanBuilder("inner-sleep").startSpan();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        span.end();

    }

    @Incoming("avenger-requests")
    @Outgoing("avenger-responses")
    public String handleAvengerRequest(String request) {
        System.out.println("Received avenger request: " + request);
        return "Response to: " + request;
    }
}
