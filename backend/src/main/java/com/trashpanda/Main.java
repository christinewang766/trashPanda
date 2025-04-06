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
        try {
            // 🔸 1. Create sample share list
            Item tomato = new Item("tomatoes", ItemCategory.VEGETABLE, ItemQuantityType.COUNT);
            Item rice = new Item("rice", ItemCategory.GRAIN, ItemQuantityType.CUP);

            ShareListEntry e1 = new ShareListEntry("piyusha", tomato, 3, null);
            ShareListEntry e2 = new ShareListEntry("christine", rice, 1, null);

            // 🔸 2. Call the fetcher
            String recipesJson = RecipeFetcher.getRecipesFromShareList(Arrays.asList(e1, e2));

            // 🔸 3. Print result
//            System.out.println("Received recipes:");
//            System.out.println(recipesJson);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
