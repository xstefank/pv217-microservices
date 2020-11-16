package io.xstefank;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class NewsDeserializer extends JsonbDeserializer<News> {

    public NewsDeserializer() {
        super(News.class);
    }
}
