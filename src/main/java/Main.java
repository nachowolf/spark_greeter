import services.Greeter;
import services.NameExtractor;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
         staticFiles.location("/public");

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

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "home.handlebars"));
        });

        post("/greet", (req, res) -> {
            NameExtractor nameExtractor = new NameExtractor(req.body());
            Greeter greeter = new Greeter();
            Map<String, Object> model = new HashMap<>();
            model.put("greetname", greeter.greet(nameExtractor.getName()));
            return new HandlebarsTemplateEngine().render(new ModelAndView(model, "home.handlebars"));
        });

        get("*", (req, res) -> {
            if(!req.pathInfo().startsWith("/static")){
                res.status(404);
                return "404 page not found";
            }
            return null;
        });
    }
}
