package io.xstefank;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ping")
public class PingResource {

    @GET
    @Path("/json-p")
    public String jsonp() {

        JsonArray jsonArray = Json.createArrayBuilder()
            .add(1)
            .add(2)
            .add(3)
            .build();

        JsonObject json = Json.createObjectBuilder()
            .add("name", "value")
            .add("array", jsonArray)
            .build();

        System.out.println(json);
        
        delimeter();

        String jsonValue = json.getString("name");
        JsonArray array = json.getJsonArray("array");
        JsonValue jsonValue1 = array.get(2);
        System.out.println(jsonValue1);

        return "hello";
    }


    @GET
    @Path("/json-b")
    @Produces(MediaType.TEXT_PLAIN)
    public String jsonb() {
        JsonbConfig jsonbConfig = new JsonbConfig();
        jsonbConfig.setProperty(JsonbConfig.FORMATTING, true);
        Jsonb jsonb = JsonbBuilder.create(jsonbConfig);

        List<Student> students = List.of(new Student("Luke", 21), new Student("Leia", 21));
        String pv217 = jsonb.toJson(new Course("PV217", 30, students));
        System.out.println(pv217);

        delimeter();

        Course course = jsonb.fromJson(pv217, Course.class);
        System.out.println(course);

        return "hello";
    }

    private void delimeter() {
        System.out.println("===========");
    }
}
