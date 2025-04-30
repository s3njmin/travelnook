Travelnook seeks to implify the process of finding and reserving accommodations for travelers, addressing challenges like information overload, price and amenity comparisons, and security.

![Screenshot 2025-04-30 at 3 18 15 PM](https://github.com/user-attachments/assets/1e106225-9311-4178-9a59-e44591a1cc2a)

It consists of the following microservices:
- API Gateway (NGINX with jwt authentication), query aggregation
- Payments (Powered by Stripe API)
- Booking (Handles CRUD operations for Hotel Bookings)
- Hotel (Handles CRUD operations on Hotel Listings)
- Search (Uses AWS ElasticSearch to locate hotel bookings)

More information on the app's usage scenarios may be found in this slide deck: https://docs.google.com/presentation/d/1xl01n-aDvpIo24rxvQe8JCopidVQMrTybDKNnVEBcx0/edit?usp=sharing


## 🚀 Running with Docker Compose
To start the application using Docker Compose:
docker-compose up --build
