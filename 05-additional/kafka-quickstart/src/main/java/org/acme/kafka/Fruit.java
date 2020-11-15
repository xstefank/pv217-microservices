package org.acme.kafka;

public class Fruit {

    public String name;
    public int price;

    public Fruit() {
    }

    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
            "name='" + name + '\'' +
            ", price=" + price +
            '}';
    }
}
