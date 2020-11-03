package io.xstefank;

public class Avenger {
    public String name;
    public String civilName;
    public boolean snapped;

    @Override
    public String toString() {
        return "Avenger{" +
            "name='" + name + '\'' +
            ", civilName='" + civilName + '\'' +
            ", snapped=" + snapped +
            '}';
    }
}
