package com.mycompany.retailbanking.app;

import com.mycompany.retailbanking.user.UserController;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 *
 * @author shria
 */
public class Main {

    public static void main(String[] args) {
          port(8082);
                   options("/*", (req, res) -> {
            String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                res.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        
        before((req, res) -> {
            
            String path = req.pathInfo();
            res.header("Access-Control-Allow-Origin", "http://localhost:8080");
            res.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS, HEAD");
            res.header("Access-Control-Allow-Headers", "Content-Type, X-MOCO-FLIGHT-TOKEN, Access-Control-Allow-Methods");
            res.header("Access-Control-Request-Headers", "X-MOCO-FLIGHT-TOKEN, Access-Control-Allow-Methods");
            res.header("Access-Control-Expose-Headers", "X-MOCO-FLIGHT-TOKEN, Access-Control-Allow-Methods");
        });
          
        path("/user", () ->
        {
            //view
            get("/:id",  (req, res) ->
            {
                return UserController.view(req, res);
            });
     
            //list
            get("/", (req, res) ->
            {
                return UserController.list(req, res);
            });
            
            //add
            post("/", (req, res) -> {
                return UserController.add(req, res);
            });
            
              //authenticate
            post("/authenticate", (req, res) -> {
                return UserController.login(req, res);
            });
            
            post("/transaction",(req,res)->){return }

        });
        
    }
}
