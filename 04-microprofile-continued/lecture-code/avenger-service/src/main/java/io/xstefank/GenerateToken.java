package io.xstefank;

import io.smallrye.jwt.build.Jwt;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.HashSet;

@Path("/token")
public class GenerateToken {

    @GET
    public String main() {
        String token =
            Jwt.issuer("https://example.com/issuer")
                .upn("jdoe@quarkus.io")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .sign();
        return token;
    }
}
