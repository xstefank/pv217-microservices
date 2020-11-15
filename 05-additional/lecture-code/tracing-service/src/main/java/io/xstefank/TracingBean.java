package io.xstefank;

import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TracingBean {

    @Traced
    public String getHello() {
        return "Hello";
    }
}
