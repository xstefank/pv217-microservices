package org.acme.rest.client;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import jakarta.ws.rs.client.Client;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Mockito.when;

@QuarkusTest
public class ExtensionsResourceTest {

    @InjectMock
    @RestClient
    ExtensionsService mock;

    @BeforeEach
    public void setUp() {
        Extension extension = new Extension();
        extension.name = "test mock";
        when(mock.getById("test")).thenReturn(Set.of(extension));
    }

    @Test
    public void testExtensionsIdEndpoint() {
        given()
            .when().get("/extension/id/io.quarkus:quarkus-rest-client-reactive")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].id", is("io.quarkus:quarkus-rest-client-reactive"),
                "[0].name", is("REST Client Reactive"),
                "[0].keywords.size()", greaterThan(1),
                "[0].keywords", hasItem("rest-client"));
    }

    @Test
    public void testExtensionIdAsyncEndpoint() {
        given()
            .when().get("/extension/id-uni/io.quarkus:quarkus-rest-client-reactive")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].id", is("io.quarkus:quarkus-rest-client-reactive"),
                "[0].name", is("REST Client Reactive"),
                "[0].keywords.size()", greaterThan(1),
                "[0].keywords", hasItem("rest-client"));
    }

    @Test
    public void testMocked() {
        given()
            .when().get("/extension/id/test")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].name", is("test mock"));
    }
}