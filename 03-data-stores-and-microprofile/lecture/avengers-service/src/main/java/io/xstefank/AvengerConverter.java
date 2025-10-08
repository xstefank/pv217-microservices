package io.xstefank;

import org.eclipse.microprofile.config.spi.Converter;

public class AvengerConverter implements Converter<Avenger> {
    @Override
    public Avenger convert(String value) throws IllegalArgumentException, NullPointerException {
        String[] split = value.split(",");
        Avenger avenger = new Avenger();
        avenger.name = split[0];
        avenger.civilName = split[1];
        avenger.snapped = Boolean.parseBoolean(split[2]);
        return avenger;
    }
}
