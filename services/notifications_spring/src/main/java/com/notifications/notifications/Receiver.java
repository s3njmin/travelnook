package com.notifications.notifications;

import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired
  private EmailSenderService service;

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");

    // try to send email if receiver is working
    String email = "hotel.book.updates@gmail.com";
    String hotel_name = "Marriot";
    String room_description = "Luxurious Double Suite";
    service.sendConfirmationEmail(email, hotel_name, room_description);

    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}