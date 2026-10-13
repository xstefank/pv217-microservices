package io.xstefank;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestService {

    public String sayHello(String name) {
        return "Ahoj " + name;
    }
}
