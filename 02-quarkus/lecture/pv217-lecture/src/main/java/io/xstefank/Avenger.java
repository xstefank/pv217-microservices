package io.xstefank;

public class Avenger {

    public String civilName;
    public String name;
    public boolean snapped;

    @Override
    public String toString() {
        return "Avenger{" +
            "civilName='" + civilName + '\'' +
            ", name='" + name + '\'' +
            ", snapped=" + snapped +
            '}';
    }
}
