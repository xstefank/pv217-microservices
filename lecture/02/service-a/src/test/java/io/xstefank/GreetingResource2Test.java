package io.xstefank;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResource2Test {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/hello2")
          .then()
             .statusCode(200)
             .body(is("[Hello from PV217, hello again]"));
    }

}