package io.xstefank;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class AvenerGenerator {

    public void startup(@Observes StartupEvent event) {
        System.out.println("test");
    }
}
