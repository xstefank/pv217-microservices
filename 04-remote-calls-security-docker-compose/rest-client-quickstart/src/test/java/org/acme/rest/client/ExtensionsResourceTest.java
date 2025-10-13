package org.acme.rest.client;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
public class ExtensionsResourceTest {

    @Test
    public void testExtensionsIdEndpoint() {
        given()
            .when().get("/extension/id/io.quarkus:quarkus-rest-client")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].id", is("io.quarkus:quarkus-rest-client"),
                "[0].name", is("REST Client"),
                "[0].keywords.size()", greaterThan(1),
                "[0].keywords", hasItem("rest-client"));
    }
}