package org.acme.config.custom;

import org.eclipse.microprofile.config.spi.Converter;

public class MicroProfileCustomValueConverter implements Converter<MicroProfileCustomValue> {
    @Override
    public MicroProfileCustomValue convert(String value) {
        return new MicroProfileCustomValue(Integer.parseInt(value));
    }
}
