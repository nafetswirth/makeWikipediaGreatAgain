package routes;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by herby on 12/04/16.
 */
public class OptionsRoute implements Route {
    @Override
    public Object handle(final Request req, final Response res) throws Exception {
        res.status(200);
        res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.header("Access-Control-Allow-Origin", "*");
        return "OK";
    }
}
