package io.xstefank;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@Path("/avenger")
public class AvengerResource {
    @Inject
    MeterRegistry registry;

    @GET
    @Counted
    @Timed
    public List<Avenger> getAll() {
        return Avenger.listAll();
    }

    @POST
    @Transactional
    public Avenger createAvenger(Avenger avenger) {
        avenger.persist();
        avenger.battleworld = false;
        avenger.name = "martin";
        return avenger;
    }

    @Inject
    @RestClient
    AvengerGeneratorClient avengerGeneratorClient;

    @Inject
    @Channel("avenger-requests")
    Emitter<String> avengersEmitter;

    @GET
    @Path("/generate-avengers")
    @Transactional
    public List<Avenger> generateAvengers(@QueryParam("number") int number) {
        List<Avenger> generatedAvengers = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            avengersEmitter.send("Send me a new avenger");

            // ----------

//            Avenger avenger = avengerGeneratorClient.generateAvenger();
//            avenger.persist();
//            generatedAvengers.add(avenger);
        }
        return generatedAvengers;
    }

    @Incoming("avenger-responses")
    public void handleAvengerResponse(String response) {
        System.out.println("Received avenger response: " + response);
    }
}
