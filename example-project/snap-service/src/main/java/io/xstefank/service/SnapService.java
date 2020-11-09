package io.xstefank.service;

import javax.enterprise.context.RequestScoped;
import java.util.Random;

@RequestScoped
public class SnapService {

    private Random random = new Random();

    public boolean shouldBeSnap(String avengerName) {
        if (avengerName.equals("Thanos")) {
            return true;
        }

        return random.nextBoolean();
    }
}
