package org.acme.mongodb.panache;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.panache.common.Sort;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.util.List;

@MongoEntity(collection="ThePerson")
public class Person extends PanacheMongoEntity {
    public String name;

    // will be persisted as a 'birth' field in MongoDB
    @BsonProperty("birth")
    public LocalDate birthDate;

    public Status status;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteLoics(){
        delete("name", "Loïc");
    }

    public static List<Person> findOrdered() {
        return findAll(Sort.by("lastname", "firstname")).list();
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", birthDate=" + birthDate +
            ", status=" + status +
            ", id=" + id +
            '}';
    }
}
