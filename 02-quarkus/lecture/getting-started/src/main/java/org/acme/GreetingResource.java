package org.acme;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "my.greeting")
    String greeting;


    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello " + greeting;
    }

    @POST
    @Path("/post")
    public String post(String input) {
        return greetingService.getHello(input);
    }

    @GET
    @Path("/avenger")
    public Avenger getAvenger() {
        Avenger avenger = new Avenger();
        avenger.name = "Tony Stark";
        avenger.nick = "Iron Man";
        avenger.power = 100;

        return avenger;
    }

    @POST
    @Path("/avenger")
    public Avenger avenger(Avenger avenger) {
        avenger.name = "My name";
        avenger.power = avenger.power + 10;

        return avenger;

    }

    @POST
    @Path("/avenger2")
    public void avenger(JsonNode json) {
        System.out.println(json.get("name"));
    }
}