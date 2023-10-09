package io.xstefank;

public class Person {
    public String name;
    public String lastName;

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
    }
}
