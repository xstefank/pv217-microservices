package io.xstefank;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class NewsDeserializer extends ObjectMapperDeserializer<News> {

    public NewsDeserializer() {
        super(News.class);
    }
}
