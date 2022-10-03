package org.acme;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class GreetingService {

    private AtomicInteger counter = new AtomicInteger(0);

    public String getHello(String name) {
        return counter.getAndIncrement() + " Ahoj " + name;
    }
}
