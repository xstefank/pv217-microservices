package com.acme;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.binder.BaseUnits;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Path("/example")
@ApplicationScoped
public class ExampleResource {

    private Random random = new Random();

    @Inject
    MeterRegistry registry;

    private List<String> gaugeList;
    private Gauge randomGauge;
    private Counter counter;
    private DistributionSummary summary;
    private Timer timer;

    @PostConstruct
    public void postConstruct() {
        gaugeList = registry.gaugeCollectionSize("gaugeList.size",
            Tags.of("listType", "String"), new ArrayList<>());

        randomGauge = Gauge.builder("random", this, ExampleResource::randomInt)
            .baseUnit("int") // optional
            .description("The peak live thread count...") // optional
            .tags("key", "value") // optional
            .register(registry);

//        counter = registry.counter("custom.counter", Tags.of("tag", "value"));
        counter = Counter.builder("my.counter")
            .baseUnit("whatevers")
            .register(registry);

//        summary = registry.summary("bytes.written", "protocol", "http");
        summary = DistributionSummary.builder("response.size")
            .baseUnit("bytes")            // optional
            .description("a description") // optional
            .tags("protocol", "http")     // optional
            .register(registry);

//        timer = registry.timer("fabric.selection", "primary", "blue");
        timer = Timer.builder("my.timer")
            .description("description ") // optional
            .tags("region", "test")      // optional
            .register(registry);
    }

    @GET
    @Path("/random")
    public int randomInt() {
        return random.nextInt();
    }

    @GET
    @Path("/gauge")
    public void gauge() {
        gaugeList.add(UUID.randomUUID().toString());
    }

    @GET
    @Path("/counter")
    public void counter() {
        counter.increment();
    }

    @GET
    @Path("/countedAnnotation")
    @Counted(value = "counted.method", extraTags = { "extra", "annotated" })
    public void countedAnnotation() {
        System.out.println("counting");
    }

    @GET
    @Path("/countedAnnotation2")
    @Counted(value = "counted.method", extraTags = { "extra", "annotated2" })
    public void countedAnnotation2() {
        System.out.println("counting");
    }

    @GET
    @Path("/summary")
    public void summary() {
        double v = random.nextDouble();
        System.out.println("v = " + v);
        summary.record(v);
    }


    @GET
    @Path("/timer")
    public void timer() {
        Timer.Sample sample = Timer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sample.stop(timer);
    }

    @GET
    @Path("/timerWrap")
    public void timerWrap() {
        timer.record(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @GET
    @Path("/timedMethod")
    @Timed(value = "call", extraTags = {"region", "test"})
    public void timedMethod() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }











    
    
}