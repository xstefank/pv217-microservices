package io.xstefank;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotUsed {

    public String sayHello(String name) {
        return "Ahoj " + name;
    }
}
