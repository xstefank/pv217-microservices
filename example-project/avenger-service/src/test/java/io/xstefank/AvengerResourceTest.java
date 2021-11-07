package io.xstefank;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.xstefank.entity.Avenger;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AvengerResourceTest {

    @Test
    public void testAvengerGet() {
        given()
            .when().get("/avenger")
            .then()
            .statusCode(200)
            .body("size()", is(4))
            .body("name", containsInAnyOrder("Iron Man", "Captain America", "Black Widow", "Spider-man"))
            .body("civilName", containsInAnyOrder("Tony Stark", "Steve Rogers", "Natasha Romanoff", "Peter Parker"))
            .body("snapped", containsInAnyOrder(false, false, false, true));
    }

    @Test
    public void testCreateDeleteAvenger() {
        Avenger avenger = new Avenger("Test name", "Test civilName", true);

        Object id = given()
            .body(avenger)
            .contentType("application/json")
            .when().post("/avenger/create")
            .then()
            .statusCode(201)
            .body("name", equalTo("Test name"))
            .body("civilName", equalTo("Test civilName"))
            .body("snapped", equalTo(true))
            .extract().path("id");

        given()
            .when().delete("/avenger/" + id + "/delete")
            .then()
            .statusCode(200)
            .body("name", equalTo("Test name"))
            .body("civilName", equalTo("Test civilName"))
            .body("snapped", equalTo(true))
            .body("id", equalTo(id));
    }

}
