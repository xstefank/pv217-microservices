package org.acme.rest.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    public String name;
    public String alpha2Code;
    public String capital;
    public List<Currency> currencies;

    public static class Currency {
        public String code;
        public String name;
        public String symbol;
    }
}
