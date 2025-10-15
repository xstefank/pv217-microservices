package io.xstefank;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProcessingBean {

    @WithSpan
    public String process(String value) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Processed value: " + value;
    }
}
