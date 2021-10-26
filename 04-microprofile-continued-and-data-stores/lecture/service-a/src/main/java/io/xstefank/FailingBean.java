package io.xstefank;

import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class FailingBean {

    private Random random = new Random();

    @Traced
    public String maybeHello() {
        System.out.println("Calling maybe hello");
        if (random.nextBoolean()) {
            return "Hello";
        } else {
            throw new RuntimeException();
        }
    }
}
