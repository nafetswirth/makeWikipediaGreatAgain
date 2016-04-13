/**
 * Created by herby on 22/04/15.
 */
import controller.ArticleController;
import request.JSONResponseTransformer;
import routes.OptionsRoute;
import spark.Spark;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {

        staticFileLocation("/public");

        get("/articles", (req, res) -> ArticleController.getAll(req, res), new JSONResponseTransformer());

        get("/articles/:id", (req, res) -> ArticleController.getOne(req, res), new JSONResponseTransformer());

        post("/articles", (req, res) -> ArticleController.createOrUpdate(req, res), new JSONResponseTransformer());

        post("/articles/:id", (req, res) -> ArticleController.createOrUpdate(req, res), new JSONResponseTransformer());

        options("/articles", new OptionsRoute());

        options("/articles/:id", new OptionsRoute());

        delete("/articles/:id", (req, res) -> ArticleController.delete(req, res), new JSONResponseTransformer());
    }
}
