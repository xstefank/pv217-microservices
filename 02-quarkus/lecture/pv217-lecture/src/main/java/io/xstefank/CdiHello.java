package io.xstefank;

import jakarta.enterprise.context.RequestScoped;

import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class CdiHello {

    private AtomicInteger counter = new AtomicInteger(0);

    public String hello() {
        return "Hello asdf " + counter.getAndIncrement();
    }
}
