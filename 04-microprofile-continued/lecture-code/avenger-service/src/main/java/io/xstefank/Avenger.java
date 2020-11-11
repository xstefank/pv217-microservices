package io.xstefank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Avenger extends PanacheEntity {

    public String name;
    public String civilName;
    public boolean snapped;

    @Override
    public String toString() {
        return "Avenger{" +
            "name='" + name + '\'' +
            ", civilName='" + civilName + '\'' +
            ", snapped=" + snapped +
            ", id=" + id +
            '}';
    }
}
