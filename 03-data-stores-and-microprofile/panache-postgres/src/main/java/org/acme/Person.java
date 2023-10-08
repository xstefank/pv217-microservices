package org.acme;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
    public Status status;

    public static Person findByName(String name) {
        return Person.find("name", name).firstResult();
    }

//    public void setName(String name) {
//        this.name = name.toUpperCase();
//    }
}