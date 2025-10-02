package org.acme;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("/v1")
public class GreetingResource {

//    @Inject
    GreetingService service;

    public GreetingResource(GreetingService service) {
        this.service = service;
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Log.info("From bean " + service.greeting("Quarkuasdfass"));
        return "Hello from Quarkus REST dsafdsaf";
    }

    @Path("/avenger/{id}")
    @POST
    public List<Avenger> getAvengers(
        @QueryParam("query") String query,
        @HeaderParam("Content-Type") String contentType,
        @PathParam("id") String identifier, Avenger avenger) {
        Log.error("SADFDSAFSADF " + identifier);
        Log.error("SADFDSAFSADF " + query);
        Log.error("SADFDSAFSADF " + contentType);
        avenger.name = "Zombie " + avenger.name;
        return List.of(avenger);
    }
}
