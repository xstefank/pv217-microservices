package org.acme.config.custom;

public class MicroProfileCustomValue {

    private final int number;

    public MicroProfileCustomValue(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "MicroProfileCustomValue{" +
            "number=" + number +
            '}';
    }
}
