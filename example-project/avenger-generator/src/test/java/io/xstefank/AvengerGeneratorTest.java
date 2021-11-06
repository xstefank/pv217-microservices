package io.xstefank;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class AvengerGeneratorTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when().get("/avenger/generate")
            .then()
            .statusCode(200)
            .body("name", notNullValue())
            .body("civilName", equalTo("Generated"))
            .body("snapped", equalTo(true));
    }

}
