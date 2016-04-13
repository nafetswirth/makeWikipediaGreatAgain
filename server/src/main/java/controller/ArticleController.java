package controller;

import connection.RedisManager;
import models.Article;
import spark.Request;
import spark.Response;

import java.util.*;

import static spark.Spark.halt;

/**
 * Created by herby on 22/04/15.
 */
public class ArticleController
{
    private static RedisManager databaseMananger = new RedisManager();

    public static Article getOne(final Request req, final Response res) {
        final String id = req.params("id");
        final Map<String, String> articleMap = databaseMananger
                .connection().hgetAll("article:"+id);

        if (articleMap.get("id") == null) {
            halt(404, "No such article");
        }

        final Article article = new Article(articleMap);

        prepareResponse(res);

        return article;
    }

    public static Article createOrUpdate(final Request req, final Response res) {

        final String id = (req.params("id") == null) ? UUID.randomUUID().toString() : req.params("id");
        final Article article = new Article(id, req.queryParams("name"), req.queryParams("content"));
        final String response = databaseMananger.connection().hmset("article:"+ id, article.toMap());

        if(!response.equals("OK")) {
            halt(500);
        }

        prepareResponse(res);

        return article;
    }

    public static List<Article> getAll(final Request req, final Response res)  {
        final Set<String> keys = databaseMananger.connection().keys("article:*");

        final List<Article> articles = new ArrayList<>(keys.size());

        for (String key : keys) {
            final Map<String, String> articleMap = databaseMananger
                    .connection().hgetAll(key);

            final Article article = new Article(articleMap);
            articles.add(article);
        }

        prepareResponse(res);

        return articles;
    }

    public static Article delete(final Request req, final Response res) {
        final String id = req.params("id");

        final Article article = ArticleController.getOne(req, res);
        final Long response = databaseMananger.connection().del("article:"+ id);

        if(response.equals(0)) {
            halt(404);
        }

        prepareResponse(res);

        return article;
    }

    private static void prepareResponse(final Response res){
        res.type("application/json");
        res.header("Access-Control-Allow-Origin", "*");
    }
}
