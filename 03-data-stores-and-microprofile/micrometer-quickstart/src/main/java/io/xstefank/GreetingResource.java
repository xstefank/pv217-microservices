package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.BaseUnits;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @Inject
    MeterRegistry meterRegistry;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted
    @Timed(value = "hello.timer", description = "Time taken to return hello world")
    public String hello() {
        meterRegistry.counter("hello").increment();
        meterRegistry.timer("hello.timer").record(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        List<String> list = meterRegistry.gaugeCollectionSize("fantastic.list",
            Tags.of("key", "value"),
            new ArrayList<>());
        list.add("hello");
        list.add("world");

        return "Hello from Quarkus REST";
    }
}
