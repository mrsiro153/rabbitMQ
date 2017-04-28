package pk_receive;

import com.rabbitmq.client.*;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MainReceive {
    private final static String QUEUE_NAME = "test1";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("manhvc");
        factory.setPassword("1@3Pikachu");
        factory.setHost("server02.ntex.vn");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
