package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Avenger extends PanacheEntity {
    public String name;
    public String civilName;
    public boolean snapped;
}
