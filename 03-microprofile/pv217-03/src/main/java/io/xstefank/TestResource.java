package io.xstefank;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestResource {

    @ConfigProperty(name = "pv217.message")
    String message;

    @GET
    @Path("/message")
    @Counted
    @Timed(name = "timeMessage")
    public String getMessage() {
        return message;
    }

    @GET
    @Path("/json-p")
    public String jsonP() {
        JsonObject json = Json.createObjectBuilder()
            .add("name", "Black Widow")
            .add("civilName", "Natasha")
            .add("snapped", "false")
            .build();

        System.out.println(json);

        System.out.println(json.getString("name"));

        return "hello";
    }

    @POST
    @Path("/create-avenger-p")
    @Operation(description = "creates avenger")
    @APIResponse(responseCode = "404", description = "Error")
    public String createAvengerP(JsonObject json) {
        System.out.println(json);

        return json.getString("name", "N/A");
    }

    @GET
    @Path("/json-b")
    public String jsonB() {
        Jsonb jsonb = JsonbBuilder.create();

        Avenger ironMan = getAvenger();

        String json = jsonb.toJson(ironMan);
        System.out.println(json);

        Avenger newAvenger = jsonb.fromJson(json, Avenger.class);
        System.out.println(newAvenger);

        return "hello";
    }

    @GET
    @Path("produce-avenger")
    @Produces(MediaType.APPLICATION_JSON)
    public Avenger produceAvenger() {
        return getAvenger();
    }

    @POST
    @Path("create-avenger")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createAvenger(Avenger avenger) {
        System.out.println(avenger);

        return avenger.name;
    }

    private Avenger getAvenger() {
        Avenger ironMan = new Avenger();
        ironMan.name = "Iron Man";
        ironMan.civilName = "Tony Stark";
        ironMan.snapped = false;
        return ironMan;
    }
}
