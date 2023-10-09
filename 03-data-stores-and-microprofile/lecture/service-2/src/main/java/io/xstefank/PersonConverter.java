package io.xstefank;

import org.eclipse.microprofile.config.spi.Converter;


public class PersonConverter implements Converter<Person> {
    @Override
    public Person convert(String s) throws IllegalArgumentException, NullPointerException {
        Person person = new Person();
        String[] split = s.split(",");
        person.name = split[0];
        person.lastName = split[1];
        return person;
    }
}
