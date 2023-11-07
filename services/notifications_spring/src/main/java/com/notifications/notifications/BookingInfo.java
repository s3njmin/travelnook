// package com.notifications.notifications;
// import lombok.*;
// import com.fasterxml.jackson.annotation.*;
// import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

// @ToString
// public class BookingInfo {
//     // public int hotel_id;
//     // public int room_id;
//     public int user_id;
//     public int booking_id;
//     public String user_email;
//     public String hotel_name;
//     public String room_description;
//     // public String status;
    
//     @JsonCreator
//     public BookingInfo( 
//             @JsonProperty("user_id") int user_id,
//             @JsonProperty("booking_id") int booking_id,
//             @JsonProperty("user_email") String user_email,
//             @JsonProperty("hotel_name") String hotel_name,
//             @JsonProperty("room_description") String room_description,
//             @JsonProperty("status") String status
//         ) {

//         this.user_id = user_id;
//         this.booking_id = booking_id;
//         this.user_email = user_email;
//         this.hotel_name = hotel_name;
//         this.room_description = room_description;
//         // this.status = status;
//     }
// }