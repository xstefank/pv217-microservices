package org.acme.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {

    @ConfigProperty(name = "hello.message", defaultValue = "default2")
    String helloMessage;

    @ConfigProperty(name = "avenger")
    Avenger avenger;

    @Inject
    Config config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted
    @Timed(name = "myHelloTimer")
    @APIResponses(
        @APIResponse(responseCode = "404", name = "not found")
    )
    public String hello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + avenger;
    }
}
