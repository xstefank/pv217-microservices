package org.acme.converter;

import org.acme.model.Avenger;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.Scanner;

public class AvengerConverter implements Converter<Avenger> {
    @Override
    public Avenger convert(String s) throws IllegalArgumentException, NullPointerException {
        String[] split = s.split(";");

        return new Avenger(split[0], split[1], Boolean.parseBoolean(split[2]));
    }
}
