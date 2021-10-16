package org.acme.security.jwt;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.jwt.Claim;
import io.quarkus.test.security.jwt.JwtSecurity;
import org.eclipse.microprofile.jwt.Claims;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TokenSecuredResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/secured/permit-all")
          .then()
             .statusCode(200)
             .body(is("hello + anonymous, isHttps: false, authScheme: null, hasJWT: false"));
    }

    @Test
    public void testSecuredEntpoint() {
        given()
            .when()
                .header("Authorization", "Bearer " + GenerateToken.generateToken()) // either set the token manually
                .get("/secured/roles-allowed")
            .then()
                .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = "Admin") // or you can use TestSecurity annotation
    @JwtSecurity(claims = {
        @Claim(key = "birthdate", value = "2021-10-16")
    })
    public void testSecuredEntpoint2() {
        given()
            .when()
            .get("/secured/roles-allowed")
            .then()
            .statusCode(200);
    }
}
