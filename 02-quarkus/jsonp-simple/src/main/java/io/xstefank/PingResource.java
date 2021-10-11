package io.xstefank;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/ping")
public class PingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String consumeJSON(JsonObject jsonObject) {
        System.out.println(jsonObject.toString());
        System.out.println(jsonObject.getString("name"));
        System.out.println(Arrays.toString(jsonObject.getJsonArray("skills").toArray()));

        return jsonObject.toString();
    }
}
