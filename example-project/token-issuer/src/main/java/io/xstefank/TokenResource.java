package io.xstefank;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.HashSet;

@Path("/token")
public class TokenResource {

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Counted(name = "tokens.generated.count")
    public String generateToken(TokenDetails tokenDetails) {

        String token =
            Jwt.issuer("https://www.fi.muni.cz/pv217/issuer")
                .upn(tokenDetails.upn)
                .groups(new HashSet<>(tokenDetails.claims != null ? Arrays.asList(tokenDetails.claims.split(",")) : Arrays.asList("User", "Admin")))
                .sign();

        return token;
    }
}
