package io.xstefank.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Avenger extends PanacheEntity {

    public String name;
    public String civilName;
    public boolean snapped;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<Avenger> listByStatus(boolean status) {
        return Avenger.list("status", status);
    }

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
