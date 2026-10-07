package io.xstefank;

import io.smallrye.config.Config;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/hello")
public class GreetingResource {

    private final AtomicInteger counter2 = new AtomicInteger(0);

    @Inject
    TestService testService;

    @Inject
    Config config;

    @ConfigProperty(name = "test.prop")
    Optional<String> testProp;

    @GET
    @Path("/{id}/whatever")
//    @Produces(MediaType.TEXT_PLAIN)
    public List<String> hello(@QueryParam("name") String name,
                              @HeaderParam("my-header") String header,
                              @PathParam("id") int id) {
        System.out.println(testProp);
        System.out.println(config.getConfigValue("quarkus.log.console.darken"));
        System.out.println(testService.sayHello(name));
        System.out.println("Counter: " + counter2.incrementAndGet());
        System.out.println("name = " + name + ", header = " + header + ", id = " + id);
        return List.of("Hello from PV217", "hello again");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloSimple() {
        System.out.println("Service A called");
        return "Hello from PV217";
    }
}
