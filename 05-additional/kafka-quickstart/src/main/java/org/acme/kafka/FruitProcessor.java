package org.acme.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

/**
 * A bean consuming data from the "fruit-in" Kafka topic and applying some price conversion.
 * The result is pushed to the "fruit-out" stream.
 */
@ApplicationScoped
public class FruitProcessor {

    private static final double CONVERSION_RATE = 0.88;

    @Incoming("fruit-in")
    @Outgoing("fruit-out")
    @Broadcast
    public Fruit process(Fruit fruit) {
        fruit.price = (int) (fruit.price * CONVERSION_RATE);
        return fruit;
    }

}
