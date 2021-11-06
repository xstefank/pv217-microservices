package io.xstefank.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Avenger extends PanacheEntity {

    public String name;
    public String civilName;
    public Boolean snapped;

    public Avenger() {
    }

    public Avenger(String name, String civilName, Boolean snapped) {
        this.name = name;
        this.civilName = civilName;
        this.snapped = snapped;
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

    public void merge(Avenger update) {
        if (update.name != null) {
            this.name = update.name;
        }
        if (update.civilName != null) {
            this.civilName = update.civilName;
        }
        if (update.snapped != null) {
            this.snapped = update.snapped;
        }
    }
}
