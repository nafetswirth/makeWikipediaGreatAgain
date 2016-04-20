import connection.RedisManager;
import controller.ArticleController;
import request.JSONResponseTransformer;
import routes.OptionsRoute;

import static spark.Spark.*;

/**
 * @author Mohammed Derouich, Stefan Wirth
 */
public class Server {
    public static void main(String[] args) {

        staticFileLocation("/public");

        final ArticleController articleController = new ArticleController(
                new RedisManager("localhost")
        );

        get("/articles", (req, res) -> articleController.getAll(req, res), new JSONResponseTransformer());

        get("/articles/:id", (req, res) -> articleController.getOne(req, res), new JSONResponseTransformer());

        post("/articles", (req, res) -> articleController.createOrUpdate(req, res), new JSONResponseTransformer());

        post("/articles/:id", (req, res) -> articleController.createOrUpdate(req, res), new JSONResponseTransformer());

        options("/articles", new OptionsRoute());

        options("/articles/:id", new OptionsRoute());

        delete("/articles/:id", (req, res) -> articleController.delete(req, res), new JSONResponseTransformer());
    }
}
