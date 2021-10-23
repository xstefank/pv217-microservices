package io.xstefank;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class PersonRepositoryTest {

    // original repository connecting to test H2 database
    @Inject
    PersonRepository personRepository;

    @Test
    @Transactional
    public void testPersonPersist() {
        Person p = new Person();
        p.setName("Test");
        p.setBirth(LocalDate.now());
        p.setStatus(Status.Alive);

        Assertions.assertEquals(0, personRepository.count());

        personRepository.persist(p);

        Assertions.assertEquals(1, personRepository.count());
        Person found = personRepository.findById(p.getId());
        Assertions.assertEquals(p.getName(), found.getName());
        Assertions.assertEquals(p.getBirth(), found.getBirth());
        Assertions.assertEquals(p.getStatus(), found.getStatus());

        personRepository.deleteById(p.getId());

        Assertions.assertEquals(0, personRepository.count());
    }

}
