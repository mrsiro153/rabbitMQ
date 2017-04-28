package demoRabbitmq2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import io.vertx.core.Vertx;


/**
 * Created by Ntex on 4/26/2017.
 */
public class SendingClass {
    private static final String TASK_QUEUE_NAME = "task_queue";
    private final static String username = "manhvc";
    private final static String pass = "1@3Pikachu";
    private final static String host = "server02.ntex.vn";
    static int dme = 0;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, handle -> {
            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setUsername("manhvc");
                factory.setPassword("1@3Pikachu");
                factory.setHost("server02.ntex.vn");
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

                String message = getMessage(args) + "   " + dme;
                dme++;
                channel.basicPublish("", TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");

                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
