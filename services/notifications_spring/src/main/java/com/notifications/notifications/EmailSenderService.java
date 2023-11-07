package com.notifications.notifications;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.*;
import org.springframework.beans.factory.annotation.*;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String toEmail, String hotel_name, String room_description) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hotel.book.updates@gmail.com");
        message.setTo(toEmail);
        message.setText("Congratulations!\nYour booking for the " + room_description + " at the " + hotel_name + " has been successful!");
        message.setSubject("Booking Confirmed!");

        mailSender.send(message);
    }

    public void sendCancellationEmail(String toEmail, String hotel_name, String room_description) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hotel.book.updates@gmail.com");
        message.setTo(toEmail);
        message.setText("Congratulations!\nYour booking for the " + room_description + " at the " + hotel_name + " has been successfully cancelled!\nYou will receive an email when your refund has been processed.");
        message.setSubject("Booking Cancelled!");

        mailSender.send(message);
    }

    public void sendPriceUpdateEmail(String toEmail, String hotel_name, String room_description, float price) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hotel.book.updates@gmail.com");
        message.setTo(toEmail);
        message.setText("Omg!\nThe " + room_description + " at the " + hotel_name + " is now listed for only $" + price + " a night!\nBook it now before it is snatched up!");
        message.setSubject("Price Update");

        mailSender.send(message);
    }

    public void sendRefundSuccessEmail(String toEmail, String hotel_name, String room_description) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hotel.book.updates@gmail.com");
        message.setTo(toEmail);
        message.setText("Congratulations!\nYour refund for the " + room_description + " at the " + hotel_name + " has been successfully processed!");
        message.setSubject("Price Update");

        mailSender.send(message);
    }
}
