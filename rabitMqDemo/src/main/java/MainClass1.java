import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

/**
 * Created by Ntex on 4/25/2017.
 */
public class MainClass1 {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(10, id -> {
            HttpClient client = vertx.createHttpClient();
            client.get(9100,"localhost","/bank_simulation/user/195663639621", response -> {
                response.bodyHandler(handler->{
                    System.out.println(handler.toString());
                });
            }).putHeader("content-type", "application/json").end();
        });
    }
}
