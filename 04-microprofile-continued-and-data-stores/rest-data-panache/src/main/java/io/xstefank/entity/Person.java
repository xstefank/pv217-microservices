package io.xstefank.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {

    public String name;
    public LocalDate birth;
    public Status status;
}
