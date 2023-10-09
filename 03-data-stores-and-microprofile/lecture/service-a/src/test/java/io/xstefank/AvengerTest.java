package io.xstefank;

import io.quarkus.test.junit.QuarkusTest;
import io.xstefank.model.Avenger;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
public class AvengerTest {

    @BeforeEach
    @Transactional
    public void beforeEach() {
        Avenger avenger = new Avenger();
        avenger.name = UUID.randomUUID().toString();
        avenger.civilName = "civil " + UUID.randomUUID().toString();
        avenger.snapped = false;

        avenger.persist();
    }

    @Test
    public void assertOne() {
        Assertions.assertEquals(1, Avenger.count());
    }

    @AfterEach
    @Transactional
    public void cleanUp() {
        Avenger.deleteAll();
    }
}
