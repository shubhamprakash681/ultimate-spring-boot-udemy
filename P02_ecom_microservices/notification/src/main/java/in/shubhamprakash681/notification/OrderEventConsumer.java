package in.shubhamprakash681.notification;

import in.shubhamprakash681.notification.payload.order.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handleOrderEvents(OrderCreatedEvent orderEvent) {
        System.out.println("orderEvent: " + orderEvent);

        // Update database
        // Send Notification
        // Send emails
        // Generate Invoices
        // Send Seller Notifications
    }
}
