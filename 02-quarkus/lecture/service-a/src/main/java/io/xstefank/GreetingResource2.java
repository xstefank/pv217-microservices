package io.xstefank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello2")
public class GreetingResource2 {

    private final AtomicInteger counter2 = new AtomicInteger(0);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<String> hello() {
        System.out.println("Counter: " + counter2.incrementAndGet());
        return List.of("Hello from PV217", "hello again");
    }
}
