package demoRabbitmq3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.vertx.core.Vertx;

/**
 * Created by Ntex on 4/26/2017.
 */
public class SendingClass {
    private static final String EXCHANGE_NAME = "task_queue";
    static int dem = 0;
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

                //channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
                //channel.queueDeclare(EXCHANGE_NAME,false,false,false,null);
                String message = getMessage(args) + "    " + dem;
                dem++;
                channel.basicPublish("", EXCHANGE_NAME, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");

                channel.close();
                connection.close();
            } catch (Exception e) {
            }
        });
    }

    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "info: Hello World!";
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
