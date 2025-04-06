package com.trashpanda;
import com.trashpanda.ShareList.ShareListEntry;
import java.util.Arrays;
import com.trashpanda.ShareList.ShareListController;
import com.trashpanda.User.UserController;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Database.initializeDatabase();
        port(4567);

        options("/*", (request, response) -> {
            String requestHeaders = request.headers("Access-Control-Request-Headers");
            if (requestHeaders != null) {
                response.header("Access-Control-Allow-Headers", requestHeaders);
            }

            String requestMethod = request.headers("Access-Control-Request-Method");
            if (requestMethod != null) {
                response.header("Access-Control-Allow-Methods", requestMethod);
            }

            return "OK";
        });

        // Apply CORS headers globally
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*"); // Allow from any origin
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type, Authorization");
        });

        ShareListController.initializeRoutes();
        UserController.initializeRoutes();
    }
}
