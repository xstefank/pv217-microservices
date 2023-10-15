package org.acme.opentelemetry;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CdiHello {

    @Inject
    Tracer tracer;

    @WithSpan
    public String hello(@SpanAttribute("PassedName") String name) {
        Span customSpan = tracer.spanBuilder("custom-span")
            .startSpan();

        // do work in custome span
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        customSpan.end();

        return "Hello " + name;
    }
}
