package io.xstefank;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

    public String hello() {
        return "Hello from CDI bean";
    }
}
