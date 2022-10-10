package io.xstefank;

import io.xstefank.model.Avenger;
import org.eclipse.microprofile.config.spi.Converter;

public class AvengerConverter implements Converter<Avenger> {
    @Override
    public Avenger convert(String s) throws IllegalArgumentException, NullPointerException {
        String[] split = s.split(",");
        return new Avenger(split[0], split[1], Boolean.parseBoolean(split[2]));
    }
}
