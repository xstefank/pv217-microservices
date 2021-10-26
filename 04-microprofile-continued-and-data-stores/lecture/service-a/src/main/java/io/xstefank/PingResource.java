package io.xstefank;

import io.opentracing.Tracer;
import io.smallrye.opentracing.SmallRyeClientTracingFeature;
import io.smallrye.opentracing.SmallRyeTracingDynamicFeature;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingResource {

    @Inject
    Tracer tracer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        Client client = null;
        try {
            ClientBuilder clientBuilder = ClientBuilder.newBuilder();
            clientBuilder.register(new SmallRyeClientTracingFeature(tracer));
            client = clientBuilder.build();
            Response response = client
                .target("http://localhost:8081/ping")
                .request()
                .get();

            if (response.getStatus() != 200) {
                throw new RuntimeException("unexpected status code");
            }

            return response.readEntity(String.class);
        } finally {
            if (client != null) {
                client.close();
            }
        }
    }

    @Inject
    @RestClient
    ServiceBClient serviceBClient;

    @GET
    @Path("/mp")
    public String getWithMP() {
        return serviceBClient.ping();
    }

    @Inject
    FailingBean failingBean;

    @GET
    @Path("/ft")
    @Retry(maxRetries = 1)
    @Fallback(fallbackMethod = "ftFallback")
    public String ft() {
        return failingBean.maybeHello();
    }

    public String ftFallback() {
        return "fallback";
    }


}
