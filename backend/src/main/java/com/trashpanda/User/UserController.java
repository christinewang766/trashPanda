package com.trashpanda.User;

import com.google.gson.Gson;

import java.util.Map;

import static spark.Spark.*;

public class UserController {
    public static void initializeRoutes() {

        get("/check-username/:username", (req, res) -> {
            String username = req.params(":username");
            UserService service = new UserService();

            boolean exists = service.checkIfProfileExists(username);
            System.out.println(exists);
            res.type("application/json");
            return "{\"exists\": " + exists + "}";
        });


        post("/verify-login", (req, res) -> {
            String body = req.body();
            Gson gson = new Gson();
            Map<String, String> loginData = gson.fromJson(body, Map.class);

            String username = loginData.get("username");
            String password = loginData.get("password");

            UserService service = new UserService();
            boolean isValid = service.verifyUsernameAndPassword(username, password);

            res.type("text/plain");
            return String.valueOf(isValid);
        });

        post("/create-profile", (req, res) -> {
            Gson gson = new Gson();
            Map<String, Object> data = gson.fromJson(req.body(), Map.class);

            String username = (String) data.get("username");
            String firstname = (String) data.get("firstname");
            String lastname = (String) data.get("lastname");
            String contact = (String) data.get("contact");
            String password = (String) data.get("password");
            int radius = ((Double) data.get("radius")).intValue();
            double latitude = (Double) data.get("latitude");
            double longitude = (Double) data.get("longitude");

            UserService service = new UserService();
            boolean success = service.createNewProfile(username, firstname, lastname, contact, password, radius, latitude, longitude);

            res.type("application/json");
            return gson.toJson(Map.of("success", success));
        });
    }
}
