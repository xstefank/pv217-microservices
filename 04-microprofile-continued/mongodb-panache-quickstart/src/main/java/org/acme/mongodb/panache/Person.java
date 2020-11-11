package org.acme.mongodb.panache;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

import java.time.LocalDate;
import java.util.List;

@MongoEntity(collection = "ThePerson")
public class Person extends PanacheMongoEntity {
    public String name;
    public LocalDate birth;
    public Status status;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteMartin(){
        delete("name", "Martin");
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", birth=" + birth +
            ", status=" + status +
            ", id=" + id +
            '}';
    }
}
