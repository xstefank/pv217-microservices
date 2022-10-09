package org.acme.config;

import org.acme.model.Avenger;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue="!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    @ConfigProperty(name = "avenger")
    Avenger avenger;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("world") + suffix;
    }

    @GET
    @Path("/programmatically")
    public String programmatically() {
        return ConfigProvider.getConfig().getValue("greeting.message", String.class);
    }

    @GET
    @Path("/converter")
    @Produces(MediaType.APPLICATION_JSON)
    public Avenger getAvengerFromConfig() {
        return avenger;
    }
}