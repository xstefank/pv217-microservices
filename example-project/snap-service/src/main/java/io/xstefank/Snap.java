package io.xstefank;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.util.List;


/**
 * MUST be in the same package as the classes that access the public fields.
 * See https://github.com/quarkusio/quarkus/issues/12514.
 */
@MongoEntity
public class Snap extends PanacheMongoEntity {

    public Boolean snapped;
    public List<String> avengers;
}
