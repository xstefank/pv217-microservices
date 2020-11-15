package org.acme.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
public class FruitResource {

    @Inject
    @Channel("fruit-out")
    Publisher<Fruit> fruits;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Publisher<Fruit> stream() {
        return fruits;
    }

    // Create fruit

    @Inject
    @Channel("fruit-send")
    Emitter<Fruit> fruitEmitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String createFruit(Fruit fruit) {
        fruitEmitter.send(fruit);
        return "OK " + fruit.name;
    }
}
