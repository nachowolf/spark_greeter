import services.Extractor;
import services.GreetDatabase;
import services.Greeter;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static services.DatabaseConnectionType.*;
import static spark.Spark.*;

public class Main {

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            GreetDatabase.setDatabaseConnectionType(HEROKU);
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }

        GreetDatabase.setDatabaseConnectionType(LOCAL);
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
         staticFiles.location("/public");
         AtomicReference<String> greet = new AtomicReference<>("");
        System.out.println("started on port: " + getHerokuAssignedPort());
//         new GreetDatabase().clear();

//        path("/route", () -> {
////            before("/*", (q, a) -> log.info("Received route call"));
//            path("/email", () -> {
//                get("/add", (req, res) -> {
//                    count.getAndIncrement();
//                    res.redirect("/route/email/boop");
//                    return null;
//                });
//                put("/change", (req, res) -> "email updated");
//                delete("/delete", (req, res) -> "email deleted");
//                get("/boop", (req, res) -> "counter: " + count.toString());
//            });
//        });


        get("/reset", (req, res) -> {
            new GreetDatabase().clear();
            greet.set("");
            res.redirect("/");
            return "";
        });


        get("/", (req, res) -> {
            GreetDatabase gdb = new GreetDatabase();
            Map<String, Object> model = new HashMap<>();
            Map<String, Integer> users = gdb.getUsers();
            model.put("greetname", greet.toString());
            model.put("userlist", users);
            model.put("totalgreets", gdb.getTotalGreets());
            model.put("totalusers", gdb.getTotalUsers());
            greet.set("");
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "home.handlebars"));
        });

      

//        post("/greet", (req, res) -> {
//            GreetDatabase gdb = new GreetDatabase();
//            Extractor extractor = new Extractor(req.body());
//            Greeter greeter = new Greeter();
//            Map<String, Integer> users = gdb.getUsers();
//            Map<String, Object> model = new HashMap<>();
//            model.put("greetname", greeter.greet(extractor));
//            model.put("userlist", users);
//            model.put("totalusers", gdb.getTotalGreets());
//            model.put("totalgreets", gdb.getTotalUsers());
//            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "home.handlebars"));
//        });

        post("/greet", (req, res) -> {
            Extractor extractor = new Extractor(req.body());
            Greeter greeter = new Greeter();
            greet.set(greeter.greet(extractor));

            res.redirect("/");
            return "";

        });

        get("/delete/:user", (req, res) -> {
             new GreetDatabase().delete( req.params("user"));
             res.redirect("/");
             return "";
        });

        get("*", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            if(!req.pathInfo().startsWith("/static")){
                res.status(404);
                return new HandlebarsTemplateEngine().render(
                        new ModelAndView(model, "404.handlebars"));
            }
            return null;
        });
    }
}
