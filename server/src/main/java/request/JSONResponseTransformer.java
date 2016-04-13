package request;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by herby on 12/04/16.
 */
public class JSONResponseTransformer implements ResponseTransformer {
    final Gson gson = new Gson();

    @Override
    public String render(Object o) throws Exception {
        return gson.toJson(o);
    }
}
