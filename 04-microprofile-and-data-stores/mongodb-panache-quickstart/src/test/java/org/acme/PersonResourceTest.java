package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.internal.common.assertion.Assertion;
import org.acme.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/persons/basics")
            .then()
            .statusCode(200)
            .body(is("Hello from Mongo Panache"));

        Assertions.assertEquals(1, Person.count());
    }

}