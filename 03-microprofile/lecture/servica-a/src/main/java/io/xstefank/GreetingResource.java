package io.xstefank;

import io.micrometer.core.instrument.MeterRegistry;
import io.xstefank.model.Avenger;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class GreetingResource {

    @Inject
    MeterRegistry registry;

    @ConfigProperty(name = "my.message", defaultValue = "staticDefault")
    String message;

    @ConfigProperty(name = "avenger")
    Avenger avenger;

    @GET
    @Operation(summary = "My hello operation")
    @APIResponse(responseCode = "500", description = "I screwed up")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        registry.counter("my.counter").increment();
        return "Hello " + avenger;
    }

    @GET
    @Path("/program")
    public Iterable<String> program() {
        return ConfigProvider.getConfig().getPropertyNames();
    }

    @GET
    @Path("prime/{number}")
    public String checkIfPrime(long number) {
        if (number < 1) {
            registry.counter("example.prime.number", "type", "not-natural").increment();
            return "Only natural numbers can be prime numbers.";
        }
        if (number == 1 ) {
            registry.counter("example.prime.number", "type", "one").increment();
            return number + " is not prime.";
        }
        if (number == 2 || number % 2 == 0) {
            registry.counter("example.prime.number", "type", "even").increment();
            return number + " is not prime.";
        }

        if ( testPrimeNumber(number) ) {
            registry.counter("example.prime.number", "type", "prime").increment();
            return number + " is prime.";
        } else {
            registry.counter("example.prime.number", "type", "not-prime").increment();
            return number + " is not prime.";
        }
    }

    protected boolean testPrimeNumber(long number) {
        for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }


}