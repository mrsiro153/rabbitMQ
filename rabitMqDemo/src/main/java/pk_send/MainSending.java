package pk_send;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MainSending {
    private final static String QUEUE_NAME = "hello";
    private final static String username = "manhvc";
    private final static String pass="1@3Pikachu";
    private final static String host="server02.ntex.vn";

    public static int dem =0;
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, handle -> {

            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("manhvc");
            factory.setPassword("1@3Pikachu");
            factory.setHost("server02.ntex.vn");
            try {
                Connection conn = factory.newConnection();
                Channel channel = conn.createChannel();
                //
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);
                String message = "Hello World!    "+dem;
                channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
                System.out.println(" [x] Sent '" + message+ "'");
                dem++;
                channel.close();
                conn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ecx) {
                ecx.printStackTrace();
            }
        });

    }
}
