package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@QuarkusTest
public class PersonEntityTest {

    @BeforeEach
    @Transactional
    public void testPersonPersist() {
        Person person = new Person();
        person.name = "test";
        person.birth = LocalDate.now();
        person.status = Status.Alive;

        person.persist();
    }

    @Test
    public void testPersist() {
        Assertions.assertEquals(1, Person.count());
    }

    @AfterEach
    @Transactional
    public void cleanup() {
        Person.deleteAll();
    }
}
