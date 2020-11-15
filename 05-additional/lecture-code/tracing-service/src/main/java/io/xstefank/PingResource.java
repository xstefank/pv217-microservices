package io.xstefank;

import io.opentracing.Tracer;
import io.smallrye.opentracing.SmallRyeClientTracingFeature;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/ping")
public class PingResource {

    @Inject
    TracingBean tracingBean;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return tracingBean.getHello();
    }

    @Inject
    Tracer tracer;

    @GET
    @Path("/call2")
    public String call2() {
        Client client = ClientBuilder.newBuilder()
            .register(new SmallRyeClientTracingFeature(tracer))
            .build();

        return client
            .target("http://localhost:8081/ping")
            .request()
            .get()
            .readEntity(String.class);
    }

    @Inject
    @RestClient
    Service2Clent service2Clent;

    @GET
    @Path("call2-mp")
    public String call2MP() {
        return service2Clent.hello();
    }
}
