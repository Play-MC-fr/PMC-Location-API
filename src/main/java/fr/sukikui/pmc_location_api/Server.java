package fr.sukikui.pmc_location_api;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.javalin.json.JsonMapper;
import org.bukkit.Bukkit;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.get;

public class Server
{
    private static Javalin app;
    private static final String API_KEY = System.getenv("PMC_LOCATION_API_KEY");

    public static void start() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        app = Javalin.create(config -> {
            config.jsonMapper(new JsonMapper() {
                @Override
                public String toJsonString(Object obj, Type type) {
                    try {
                        return objectMapper.writeValueAsString(obj);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public <T> T fromJsonString(String json, Type targetType) {
                    try {
                        return objectMapper.readValue(json, objectMapper.constructType(targetType));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            config.router.mount(router -> {
                router.before(ctx -> {
                    String apiKey = ctx.header("X-API-Key");

                    Bukkit.broadcastMessage("API Key: " + API_KEY);
                    Bukkit.broadcastMessage("Given API Key: " + apiKey);

                    if (apiKey == null || !apiKey.equals(API_KEY)) {
                        throw new UnauthorizedResponse("Invalid API Key");
                    }
                });
            }).apiBuilder(() -> get("/", ctx -> ctx.result("PMC Location API")));
        }).start(7070);

        app.get("/api/location/{name}", Server::getPlayerLocation);
    }

    public static void stop() {
        app.stop();
    }

    private static void getPlayerLocation(Context ctx) {
        String name = ctx.pathParam("name");
        PlayerLocation location = PlayerLocation.getFromName(name);

        if (location != null) {
            ctx.json(location);
        } else {
            ctx.status(404).result("Player not found");
        }
    }
}