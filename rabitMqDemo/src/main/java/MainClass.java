import com.google.gson.Gson;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import org.json.XML;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Ntex on 4/25/2017.
 */
public class MainClass {

    public static void main(String[] args) {

        test1();
    }

    public static void test() {
        String s ="";
        for (int i = 0; i < 17; i++) {
            int randomNumOrChar = ThreadLocalRandom.current().nextInt(1, 3);
            if(randomNumOrChar==1){
                //1 is number
                int randomNum = ThreadLocalRandom.current().nextInt(0, 10);
                s+=randomNum;
            }else {
                int randomChar = ThreadLocalRandom.current().nextInt(97, 122 + 1);
                char c= (char) randomChar;
                s+=c;
            }
        }
    }
    public static void test1(){
    }
}
