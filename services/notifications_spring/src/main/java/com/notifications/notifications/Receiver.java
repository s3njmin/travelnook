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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class Receiver {
    // private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private EmailSenderService service;

    @RabbitListener(queues = "Notification_service")
    public void receiveMessage(Message message) {
        try {
            // Extract Message Contents
            String routingKey = message.getMessageProperties().getReceivedRoutingKey();
            String body = new String(message.getBody(), StandardCharsets.UTF_8);
            
            // Convert string json object to JsonNode
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the JSON string into a JsonNode
            JsonNode root = objectMapper.readTree(body);

            // Extract the JSON content inside the "data" field
            JsonNode json = root.get("data");

            // routing key handling
            if (routingKey.equals("booking.confirm")) {
                handleBookingConfirmation(json);
            } else if (routingKey.equals("booking.hotelcancel")) {
                handleHotelCancellation(json);
            } else if (routingKey.equals("notification.pricechange")) {
                handlePriceChange(json);
            } else if (routingKey.equals("booking.refundsuccess")) {
                handleRefundSuccess(json);
            }

            // latch.countDown();

        } catch (Exception e) {
            System.out.println("Some Error Has Occurred");
            e.printStackTrace();
            // Todo: error handling
        }
    }
    
    public void handleBookingConfirmation(JsonNode json) {
        if (json != null) {
            String[] information = getJsonValues(json);
            try {
                service.sendConfirmationEmail(information[0], information[1], information[2]);
            } catch (Exception e) {
                System.out.println(information[0]);
                System.out.println(information[1]);
                System.out.println(information[2]);
            }
        } else {
            // Todo: error handling
            System.out.println("JSON is null");
        }
    }
    
    public void handleHotelCancellation(JsonNode json) {
        if (json != null) {
            String[] information = getJsonValues(json);
            service.sendCancellationEmail(information[0], information[1], information[2]);
        } else {
            // Todo: error handling
        }
    }
    
    public void handleRefundSuccess(JsonNode json) {
        if (json != null) {
            String[] information = getJsonValues(json);
            service.sendRefundSuccessEmail(information[0], information[1], information[2]);
        } else {
            // Todo: error handling
        }
    }
    
    public String[] getJsonValues(JsonNode json) throws NullPointerException {
        // if user email not avail, get it from user service
        String[] response = new String[3];
        
        try {
            response[0] = json.get("user_email").asText();
        } catch (NullPointerException e) {
            int user_id = Integer.parseInt(json.get("user_id").asText());
            response[0] = getEmail(user_id);
        }
        
        return response;
    }

    public void handlePriceChange(JsonNode json) {
        if (json != null) {
            String[] information = getJsonValuesPriceChange(json);
            String[] emails = getSubscribers(Integer.parseInt(information[0]), Float.parseFloat(information[1]));

            for (String email : emails) {
                service.sendPriceUpdateEmail(email, information[2], information[3], Float.parseFloat(information[1]));
            }

        } else {
            // Todo: error handling
        }
    }

    public String[] getJsonValuesPriceChange(JsonNode json) throws NullPointerException {
        // Read JSON
        String[] response = new String[4];
        response[0] = json.get("room_id").asText();
        response[1] = json.get("price_per_night").asText();
        response[2] = json.get("hotel_name").asText();
        response[3] = json.get("description").asText();

        return response;
    }

    // public CountDownLatch getLatch() {
    //     return latch;
    // }

    public static String getEmail(int user_id) throws JsonProcessingException {
        // Make an HTTP GET request to user service's API endpoint
        String userServiceUrl = "http://host.docker.internal:8080/api/v1/users/" + user_id + "/email";
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(userServiceUrl, String.class);
        // Convert string json object to JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse the JSON string into a JsonNode
        JsonNode json = objectMapper.readTree(response);

        return json.get("email").asText();
    }

    public static String[] getSubscribers(int room_id, float price) {
        // Make an HTTP GET request to user service's API endpoint
        String userServiceUrl = "http://host.docker.internal:8080/api/v1/subscriptions/room/" + room_id + "/price/" + price;
        RestTemplate restTemplate = new RestTemplate();

        String[] response = restTemplate.getForObject(userServiceUrl, String[].class);
        
        return response;
    }
}
