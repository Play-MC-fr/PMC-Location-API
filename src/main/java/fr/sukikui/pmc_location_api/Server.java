package fr.sukikui.pmc_location_api;

import io.javalin.Javalin;
import io.javalin.http.Context;


public class Server
{
    private static Javalin app;
    private static final String API_KEY = System.getenv("PMC_LOCATION_API_KEY");

    public static void start()
    {
        app = Javalin.create().start(7070);

        app.before(ctx -> {
            String apiKey = ctx.header("X-API-Key");
            if (apiKey == null || !apiKey.equals(API_KEY)) {
                ctx.status(403).result("Forbidden: Invalid API Key");
            }
        });

        app.get("/players/:name/location", Server::getPlayerLocation);
    }

    public static void stop()
    {
        app.stop();
    }

    private static void getPlayerLocation(Context ctx)
    {
        String name = ctx.pathParam("name");
        PlayerLocation location = PlayerLocation.getFromName(name);

        if (location != null)
            ctx.json(location);
        else
            ctx.status(404);
    }
}
