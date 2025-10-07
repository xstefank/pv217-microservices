package org.acme.config;

public class Avenger {

    String name;
    String civilName;
    boolean zombie;

    public Avenger(String name, String civilName, boolean zombie) {
        this.name = name;
        this.civilName = civilName;
        this.zombie = zombie;
    }

    @Override
    public String toString() {
        return "Avenger{" +
            "name='" + name + '\'' +
            ", civilName='" + civilName + '\'' +
            ", zombie=" + zombie +
            '}';
    }
}
