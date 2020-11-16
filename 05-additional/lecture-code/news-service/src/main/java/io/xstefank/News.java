package io.xstefank;

public class News {

    public String text;
    public boolean verified;

    @Override
    public String toString() {
        return "News{" +
            "text='" + text + '\'' +
            ", verified=" + verified +
            '}';
    }
}
