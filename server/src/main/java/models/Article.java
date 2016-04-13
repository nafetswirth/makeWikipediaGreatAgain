package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by herby on 22/04/15.
 */
public class Article
{
    private final String id;
    private final String name;
    private final String content;

    public Article(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }

    public Article(Map<String,String> articleMap) {
        this(articleMap.get("id"), articleMap.get("name"), articleMap.get("content"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Map<String,String> toMap() {
        final Map<String,String> article = new HashMap<>();
        article.put("id", this.id);
        article.put("name", this.name);
        article.put("content", this.content);

        return article;
    }

    @Override
    public String toString()
    {
        return "Article{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
