package io.xstefank;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonPersitenceTest {

    @Test
    @Transactional
    public void testPersonPersist() {
        Person p = new Person();
        p.name = "Test";
        p.birth = LocalDate.now();
        p.status = Status.Alive;

        Assertions.assertEquals(0, Person.count());
        p.persist();

        Assertions.assertEquals(1, Person.count());
        Person found = Person.findById(p.id);
        Assertions.assertEquals(p.name, found.name);
        Assertions.assertEquals(p.birth, found.birth);
        Assertions.assertEquals(p.status, found.status);

        Person.deleteById(p.id);
        Assertions.assertEquals(0, Person.count());
    }
}
