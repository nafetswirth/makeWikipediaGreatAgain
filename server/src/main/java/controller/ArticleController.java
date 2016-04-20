package controller;

import connection.RedisManager;
import models.Article;
import spark.Request;
import spark.Response;

import java.util.*;

import static spark.Spark.halt;

/**
 * @author Mohammed Derouich, Stefan Wirth
 */
public class ArticleController {

    private final RedisManager databaseManager;

    public ArticleController(final RedisManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Article getOne(final Request req, final Response res) {
        final String id = req.params("id");
        final Map<String, String> articleMap = databaseManager
                .connection().hgetAll("article:"+id);

        if (articleMap.get("id") == null) {
            halt(404, "No such article");
        }

        final Article article = new Article(articleMap);

        prepareResponse(res);

        return article;
    }

    public Article createOrUpdate(final Request req, final Response res) {

        final String id = (req.params("id") == null) ? UUID.randomUUID().toString() : req.params("id");
        final Article article = new Article(id, req.queryParams("name"), req.queryParams("content"));
        final String response = databaseManager.connection().hmset("article:"+ id, article.toMap());

        if(!response.equals("OK")) {
            halt(500);
        }

        prepareResponse(res);

        return article;
    }

    public List<Article> getAll(final Request req, final Response res)  {
        final Set<String> keys = databaseManager.connection().keys("article:*");

        final List<Article> articles = new ArrayList<>(keys.size());

        for (String key : keys) {
            final Map<String, String> articleMap = databaseManager
                    .connection().hgetAll(key);

            final Article article = new Article(articleMap);
            articles.add(article);
        }

        prepareResponse(res);

        return articles;
    }

    public Article delete(final Request req, final Response res) {
        final String id = req.params("id");

        final Article article = getOne(req, res);
        final Long response = databaseManager.connection().del("article:"+ id);

        if(response.equals(0)) {
            halt(404);
        }

        prepareResponse(res);

        return article;
    }

    private void prepareResponse(final Response res){
        res.type("application/json");
        res.header("Access-Control-Allow-Origin", "*");
    }
}
