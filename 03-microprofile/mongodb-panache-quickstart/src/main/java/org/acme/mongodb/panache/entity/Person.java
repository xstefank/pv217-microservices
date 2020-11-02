package org.acme.mongodb.panache.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

//@MongoEntity(collection="ThePerson")
public class Person extends PanacheMongoEntity {
    public String name;
    public LocalDate birth;
    public Status status;
}
