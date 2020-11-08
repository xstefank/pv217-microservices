package org.acme.kafka;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class FruitDeserializer extends JsonbDeserializer<Fruit> {
    public FruitDeserializer(){
        // pass the class to the parent.
        super(Fruit.class);
    }
}
