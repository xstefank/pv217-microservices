package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GreetingProducer {

    private int counter = 0;

    public int inc() {
        return counter++;
    }
}
