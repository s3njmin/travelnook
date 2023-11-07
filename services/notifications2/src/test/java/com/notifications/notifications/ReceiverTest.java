package com.notifications.notifications;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class ReceiverTest {
    // setup - mock producer
        // mock queue
        // mock topic exchange, bind
        // mock routing key bindings to exchange
    
    // run receiver listener

    // testing - consumption, error handling
        // booking.confirm with everything OK
        // without email but with user_id the rest OK
        // invalid params -> throw InvalidParaError
            // without either -> ERROR
            // with invalid id -> ERROR
            // without room_description -> ERROR

        // booking.hotelcancel with everything OK
        // without email but with user_id the rest OK
        // invalid params -> throw InvalidParaError
            // without either -> ERROR
            // with invalid id -> ERROR
            // without room_description -> ERROR

        // booking.refundsuccess with everything OK
        // without email but with user_id the rest OK
        // invalid params -> throw InvalidParaError
            // without either -> ERROR
            // with invalid id -> ERROR
            // without room_description -> ERROR

        // notification.pricechange with room_id, price_per_night, hotel_name, description -> OK
            //  
}
