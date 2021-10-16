package org.acme.config;


import org.eclipse.microprofile.config.spi.Converter;

public class AvengerConverter implements Converter<Avenger> {

    @Override
    public Avenger convert(String value) throws IllegalArgumentException, NullPointerException {
        String[] split = value.split(",");
        return new Avenger(split[0], split[1], Boolean.parseBoolean(split[2]));

    }
}
