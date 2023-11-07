package com.notifications.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootApplication
@ComponentScan(basePackages = "com.notifications.notifications")
public class NotificationsApplication {
	// static final String topicExchangeName = "notification.topic";
	// static final String queueName = "notifications_queue";
	// static final String queueName2 = "Notification_service";

	// // initialize queue
	// @Bean
	// Queue queue() {
	// 	return new Queue(queueName, true);
	// }

	// // initialize topic exchange
	// @Bean
	// TopicExchange exchange() {
	// 	return new TopicExchange(topicExchangeName);
	// }

	// // create bindings for each routing key
	// @Bean
	// Binding bindingBookingConfirm(Queue queue, TopicExchange exchange) {
	// 	return BindingBuilder.bind(queue).to(exchange).with("booking.confirm");
	// }
	// @Bean
	// Binding bindingBookingCancel(Queue queue, TopicExchange exchange) {
	// 	return BindingBuilder.bind(queue).to(exchange).with("booking.cancel");
	// }
	// @Bean
	// Binding bindingBookingHotelCancel(Queue queue, TopicExchange exchange) {
	// 	return BindingBuilder.bind(queue).to(exchange).with("booking.hotelcancel");
	// }
	// @Bean
	// Binding bindingNotificationPriceChange(Queue queue, TopicExchange exchange) {
	// 	return BindingBuilder.bind(queue).to(exchange).with("notification.pricechange");
	// }

	// create message listener container
	// @Bean
	// SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
	// 	SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	// 	container.setConnectionFactory(connectionFactory);
	// 	container.setQueueNames(queueName2);
	// 	container.setMessageListener(listenerAdapter);
	// 	return container;
	// }

	// // initialize listenerAdapter instance using custom Receiver class
	// @Bean
	// MessageListenerAdapter listenerAdapter(Receiver receiver) {
	// 	return new MessageListenerAdapter(receiver, "receiveMessage");
	// }

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(NotificationsApplication.class, args);

		// BookingInfo myObject = new BookingInfo(1, 100, "hotel.book.updates@gmail.com", "The Fullerton",  "Penthouse", "Booked");
		// ObjectMapper objectMapper = new ObjectMapper();
		// String jsonString = null;

		// try {
		// 	jsonString = objectMapper.writeValueAsString(myObject);
		// 	System.out.println(jsonString);
		// } catch (JsonProcessingException e) {
		// 	e.printStackTrace();
		// }

		// String jsonString = "{\"data\": {\"user_email\": \"hotel.book.updates@gmail.com\", \"hotel_name\": \"The Fullerton\", \"room_description\": \"Penthouse\", \"room_id\": 2}}";

		// byte[] byteObject = null;
		// try {
		// 	byteObject = jsonString.getBytes("utf-8");
		// 	System.out.println(byteObject);
		// } catch (java.io.UnsupportedEncodingException e) {
		// 	e.printStackTrace();
		// }

		// String body = new String(byteObject, StandardCharsets.UTF_8);
		// System.out.println(body);

		// ObjectMapper objectMapper = new ObjectMapper();
		// try {
		// 	// Parse the JSON string into a JsonNode
		// 	JsonNode root = objectMapper.readTree(body);
		// 	// Extract the JSON content inside the "data" field
		// 	JsonNode jsonData = root.get("data");
			
		// 	// BookingInfo bookingMessage = objectMapper.readValue(jsonData, BookingInfo.class);
		// 	System.out.println(jsonData.get("user_email"));
		// } catch (JsonProcessingException e) {
		// 	e.printStackTrace();
		// }
		
  	}
}
