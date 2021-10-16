package org.acme.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "greeting.message")
    String message;

    @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
    String suffix;

    @ConfigProperty(name = "greeting.name")
    Optional<String> name;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message + " " + name.orElse("world") + suffix;
    }

    @GET
    @Path("/programmatic")
    public String programmaticMessage() {
        return new NotCDIBean().getConfigMessage();
    }

    // Config mapping example

    @Inject
    PV217Config pv217Config;

    @GET
    @Path("/mapping")
    public void printMapping() {
        System.out.println(pv217Config.room());
        System.out.println(pv217Config.time());
    }

    // Converter example

    @ConfigProperty(name = "avenger")
    Avenger avenger;

    @GET
    @Path("/avenger")
    public void printAvenger() {
        System.out.println(avenger.toString());
    }
}
