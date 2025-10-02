package org.acme;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;

import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class GreetingService {

    private AtomicLong counter = new AtomicLong();

    public String greeting(String name) {
        Log.error("counter: " + counter.incrementAndGet());
        Log.error("instance: " + this);
        return "Hello " + name + " from GreetingService";
    }
}
