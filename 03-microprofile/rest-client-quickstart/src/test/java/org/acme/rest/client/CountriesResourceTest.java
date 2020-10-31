package org.acme.rest.client;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.ConfigProvider;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(WiremockCountries.class)
public class CountriesResourceTest {

    @Test
    public void testCountryNameEndpoint() {
        given()
            .when().get("/country/name/greece")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].alpha2Code", is("GR"),
                "[0].capital", is("Athens"),
                "[0].currencies.size()", is(1),
                "[0].currencies[0].name", is("Euro")
            );
    }

    @Test
    public void testCountryNameAsyncEndpoint() {
        given()
            .when().get("/country/name-async/greece")
            .then()
            .statusCode(200)
            .body("$.size()", is(1),
                "[0].alpha2Code", is("GR"),
                "[0].capital", is("Athens"),
                "[0].currencies.size()", is(1),
                "[0].currencies[0].name", is("Euro")
            );
    }

    @Test
    public void testCountryWireMockGREndpoint() {
        given()
            .when().get("/country/name/GR")
            .then()
            .statusCode(200)
            .body("$.size", is(1),
                "[0].capital", is("Αθήνα"),
                "[0].name", is("Ελλάδα")
            );
    }

}
