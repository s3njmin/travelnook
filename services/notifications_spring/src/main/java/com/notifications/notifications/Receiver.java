package com.notifications.notifications;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private EmailSenderService service;

    @RabbitListener(queues = "Notification_service")
    public void receiveMessage(Message message) throws java.io.IOException {

        // Extract Message Contents
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        String body = new String(message.getBody(), StandardCharsets.UTF_8);
        
        // Convert string json object to JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = null;

        try {
            // Parse the JSON string into a JsonNode
			JsonNode root = objectMapper.readTree(body);

			// Extract the JSON content inside the "data" field
			json = root.get("data");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Todo: error handling
        }

        // read JSON
        String email = json.get("user_email").asText();
        String hotel_name = json.get("hotel_name").asText();
        String room_description = json.get("description").asText();

        // if email is null (hotel side cancellation/confirmation), query user service
        if (email == null) {
            int user_id = json.get("")
        }

        // routing key handling
        if (routingKey.equals("booking.confirm")) {
            if (json != null) {
                service.sendConfirmationEmail(email, hotel_name, room_description);
            } else {
                // Todo: error handling
            }

        } else if (routingKey.equals("booking.hotelcancel")) {
            if (json != null) {
                service.sendCancellationEmail(email, hotel_name, room_description)
            } else {
                // Todo: error handling
            }

        } else if (routingKey.equals("notification.pricechange")) {
            // service.sendConfirmationEmail(email, hotel_name, room_description);
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public String getEmail() {

    }
}
