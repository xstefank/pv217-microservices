package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "my.hello", defaultValue = "default-hello")
    String hello;

    @ConfigProperty(name = "my.avenger")
    Avenger avengerFromConfig;

    @Inject
    MeterRegistry registry;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    @Counted
    @Timed(extraTags = {"asdf", "sadfsadf"})
    public String hello() {
        registry.counter("my.hello", "service", "avenger").increment();
        Avenger avenger = new Avenger();
        avenger.name = "iron man";
        avenger.civilName = "tony stark";
        avenger.snapped = false;

        avenger.persist();

        Log.info("Avenger from config: " + avengerFromConfig);
        return "Hello from Quarkus REST " + hello;
    }

    @GET
    @Path("/all")
    public List<Avenger> all() {
        return Avenger.listAll();
    }


}
