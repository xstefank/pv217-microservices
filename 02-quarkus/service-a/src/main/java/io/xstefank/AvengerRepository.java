package io.xstefank;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class AvengerRepository {

    private int ids = 0;
    private final Map<Integer, Avenger> avengers = new HashMap<>();

    public Avenger addAvenger(Avenger avenger) {
        avenger.id = ++ids;
        avengers.put(avenger.id, avenger);
        return avenger;
    }

    public Avenger removeAvenger(int id) {
        return avengers.remove(id);
    }

    public Collection<Avenger> getAvengers() {
        return Collections.unmodifiableCollection(avengers.values());
    }

    @PostConstruct
    public void postConstruct() {
       Avenger ironMan = new Avenger();
       ironMan.name = "Iron Man";
       ironMan.civilName = "Tony Stark";
       ironMan.battleworld = false;
       addAvenger(ironMan);

       Avenger captainAmerica = new Avenger();
       captainAmerica.name = "Captain America";
       captainAmerica.civilName = "Steve Rogers";
       captainAmerica.battleworld = true;
       addAvenger(captainAmerica);

       Avenger thor = new Avenger();
       thor.name = "Thor";
       thor.civilName = "Thor Odinson";
       thor.battleworld = true;
       addAvenger(thor);
    }
}
