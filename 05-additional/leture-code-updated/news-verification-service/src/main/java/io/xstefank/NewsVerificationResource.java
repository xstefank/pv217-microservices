package io.xstefank;

import org.jboss.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Random;

@Path("/verify")
public class NewsVerificationResource {

    private static final Logger LOG = Logger.getLogger(NewsVerificationResource.class);

    private final Random random = new Random();

    @POST
    //@Produces(MediaType.APPLICATION_JSON) -- not needed because resteasy-jackson auto assigns application/json media types
    public News verifyNews(String news) {
        boolean verified = random.nextBoolean();
        LOG.infof("News '%s' %s verified", news, verified ? "was" : "wasn't");

        return new News(news, verified);
    }
}
