package request;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * @author Mohammed Derouich, Stefan Wirth
 */
public class JSONResponseTransformer implements ResponseTransformer {
    final Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
