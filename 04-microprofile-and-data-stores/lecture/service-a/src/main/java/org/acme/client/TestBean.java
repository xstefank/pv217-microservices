package org.acme.client;

import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestBean {

    @WithSpan("test hello")
    public String getHello(@SpanAttribute("test param") String name) {
        return "Ahoj " + name;
    }
}
