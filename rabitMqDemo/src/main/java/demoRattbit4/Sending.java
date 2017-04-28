package demoRattbit4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.vertx.core.Vertx;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

/**
 * Created by Ntex on 4/27/2017.
 */
public class Sending {
    private static final String EXCHANGE_NAME = "topic_logs";
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, handle -> {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost("server02.ntex.vn");
                factory.setUsername("manhvc");
                factory.setPassword("1@3Pikachu");
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
                //
                String queue = channel.queueDeclare("test2",false, false, false, null).getQueue();
                channel.queueBind(queue, EXCHANGE_NAME, "transfer.*.transaction.*");
                //
                String queue1 = channel.queueDeclare("test3",false, false, false, null).getQueue();
                channel.queueBind(queue1, EXCHANGE_NAME, "transfer.*.transaction.*");
                //
                String routingKey = "transfer.account.transaction.notify";
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                channel.basicPublish(EXCHANGE_NAME, routingKey, null, dtf.format(now).getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + routingKey + "':'" + dtf.format(now) + "'");
                channel.close();
                connection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ex) {
                ex.printStackTrace();
            }
        });
    }
}
