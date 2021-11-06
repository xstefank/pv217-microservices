package io.xstefank;

import io.xstefank.model.Avenger;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Random;

@ApplicationScoped
public class AvengerService {

    private final List<String> firstNames = List.of("Iron", "Captain", "Black", "Winter", "Hulk", "Ant", "Spider", "Scarlet");
    private final List<String> secondNames = List.of("Man", "America", "Widow", "Soldier", "Falcon", "Knight", "Witch");

    private final Random random = new Random();

    public Avenger generateRandomAvenger() {
        String name = null;

        if (random.nextBoolean()) {
            name = random.nextBoolean() ? firstNames.get(random.nextInt(firstNames.size())) :
                secondNames.get(random.nextInt(secondNames.size()));
        } else {
            name = firstNames.get(random.nextInt(firstNames.size())) + " " + secondNames.get(random.nextInt(secondNames.size()));
        }

        return new Avenger(name, "Generated", true);
    }
}
