package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.entity.Person;
import org.acme.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonResourceTest {

    @Inject
    PersonRepository personRepository;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/person/basics")
          .then()
             .statusCode(200)
             .body(is("Hello from Panache with repository"));

        Assertions.assertEquals(1, personRepository.count());
    }

}