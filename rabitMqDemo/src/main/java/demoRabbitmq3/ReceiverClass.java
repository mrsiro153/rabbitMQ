package demoRabbitmq3;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by Ntex on 4/26/2017.
 */
public class ReceiverClass {
    private static final String EXCHANGE_NAME = "task_queue";


    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername("manhvc");
            factory.setPassword("1@3Pikachu");
            factory.setHost("server02.ntex.vn");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            //channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
            //String queueName = channel.queueDeclare().getQueue();
            //channel.queueBind(queueName, EXCHANGE_NAME, "");
            channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                }
            };
            channel.basicConsume(EXCHANGE_NAME, true, consumer);
        }catch (Exception e){

        }
    }
}
