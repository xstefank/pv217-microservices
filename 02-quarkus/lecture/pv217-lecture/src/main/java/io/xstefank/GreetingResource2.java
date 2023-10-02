package io.xstefank;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello2")
public class GreetingResource2 {

    private AtomicInteger counter = new AtomicInteger(3);

    @Inject
    private CdiHello cdiHello;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println(counter.getAndIncrement());
        System.out.println("sadfsadfsadf");
        return "Hello from asdfljdsaflkjafds";
    }

    @POST
    @Path("/post")
    public void post(Avenger arg) {
        System.out.println("arg = " + arg);
    }

    @GET
    @Path("/avenger")
    public List<Avenger> getAvengers() {
        Avenger avenger = new Avenger();
        avenger.name = "Iron man";
        avenger.civilName= "Tony Stark";
        avenger.snapped = false;
        return List.of(avenger);
    }

    @GET
    @Path("/cdi")
    public String cdi() {
        return cdiHello.hello();
    }


}
