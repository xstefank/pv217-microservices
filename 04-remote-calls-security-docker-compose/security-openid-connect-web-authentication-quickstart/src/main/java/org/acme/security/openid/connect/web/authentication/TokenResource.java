package org.acme.security.openid.connect.web.authentication;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.RefreshToken;

@Path("/tokens")
public class TokenResource {

    /**
     * Injection point for the ID token issued by the OpenID Connect provider
     */
    @Inject
    @IdToken
    JsonWebToken idToken;

    /**
     * Injection point for the access token issued by the OpenID Connect provider
     */
    @Inject
    JsonWebToken accessToken;

    /**
     * Injection point for the refresh token issued by the OpenID Connect provider
     */
    @Inject
    RefreshToken refreshToken;

    /**
     * Returns the tokens available to the application.
     * This endpoint exists only for demonstration purposes.
     * Do not expose these tokens in a real application.
     *
     * @return an HTML page containing the tokens available to the application.
     */
    @GET
    @Produces("text/html")
    public String getTokens() {
        StringBuilder response = new StringBuilder().append("<html>")
            .append("<body>")
            .append("<ul>");


        Object userName = this.idToken.getClaim(Claims.preferred_username);

        if (userName != null) {
            response.append("<li>username: ").append(userName.toString()).append("</li>");
        }

        Object scopes = this.accessToken.getClaim("scope");

        if (scopes != null) {
            response.append("<li>scopes: ").append(scopes.toString()).append("</li>");
        }

        response.append("<li>refresh_token: ").append(refreshToken.getToken() != null).append("</li>");

        return response.append("</ul>").append("</body>").append("</html>").toString();
    }

    @GET
    @Path("/admin-only")
    @RolesAllowed("admin")
    public String getAdminMessage() {
        return "If you see this message, you are an admin.";
    }
}