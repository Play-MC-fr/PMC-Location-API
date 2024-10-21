package fr.sukikui.pmc_location_api;

public enum RequestResponse
{
    SUCCESS(200, "Success", "§a"),
    INVALID_API_KEY(401, "Invalid API Key", "§c"),
    PLAYER_NOT_FOUND(404, "Player not found", "§c");

    public final int code;
    public final String message;
    public final String color;

    RequestResponse(int code, String message, String color)
    {
        this.code = code;
        this.message = message;
        this.color = color;
    }
}
