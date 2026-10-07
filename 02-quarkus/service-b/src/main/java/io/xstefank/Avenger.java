package io.xstefank;

public class Avenger {

    public int id;
    public String name;
    public String civilName;
    public boolean battleworld;

    @Override
    public String toString() {
        return "Avenger{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", civilName='" + civilName + '\'' +
            ", battleworld=" + battleworld +
            '}';
    }
}
