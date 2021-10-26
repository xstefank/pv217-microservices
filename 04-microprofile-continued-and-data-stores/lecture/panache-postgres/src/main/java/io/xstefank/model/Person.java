package io.xstefank.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public LocalDate birth;
    public Status status;

    public static Person findByName(String name){
        return find("name", name).firstResult();
    }

    public static List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public static void deleteStefs(){
        delete("name", "Stef");
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
