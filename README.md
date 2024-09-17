# Social Media Platform

## Project Description

This project is a space-themed social media platform designed to enable users to interact and share content seamlessly. It includes features such as user registration, posting and reacting to content, chatting with friends, managing friendships, and administrative controls for user management. The platform provides a secure and efficient environment for social interaction, ensuring user data is managed and protected effectively. It aims to deliver a comprehensive social networking experience with real-time communication and user engagement, all set within an exciting and visually appealing space-themed interface.

## Functional Requirements

### User Management

1. **User Registration**:

   - Users can create an account by providing necessary details such as username, password, email, etc.
   - The system should validate the user input and store user details in the database.

2. **User Login**:

   - Users can log in using their credentials (username and password).
   - The system should authenticate users and create a session/token for logged-in users.

3. **Profile Management**:

   - Users can view and edit their profile information.
   - Users can upload a profile picture.

4. **Friendship Management**:
   - Users can send, accept, and reject friend requests.
   - Users can view their list of friends.
   - Users can remove friends from their list.

### Post Management

5. **Creating Posts**:

   - Users can create new posts with text and images.
   - The system should store the posts and make them available on the user’s feed.

6. **Viewing Posts**:

   - Users can view posts on their feed from friends and themselves.
   - The system should display posts in a chronological or relevant order.

7. **Interacting with Posts**:

   - Users can like, dislike, and comment on posts.
   - The system should update the post’s interaction counts and store comments.

8. **Deleting Posts**:
   - Users can delete their own posts.
   - The system should remove the post and all associated interactions from the feed.

### Chat Management

9. **Sending Messages**:

   - Users can send messages to their friends.
   - The system should store messages and ensure they are delivered in real-time.

10. **Receiving Messages**:

    - Users can receive messages from friends in real-time.

11. **Chat History**:
    - Users can view the history of conversations with their friends.
    - The system should store and retrieve past messages.

### Administrative Functions

12. **User Administration**:

    - Admins can ban or unban users.

13. **Content Moderation**:
    - Admins can delete posts or comments that violate community guidelines.

## Non-Functional Requirements

### Performance

1. **Response Time**:
   - The system should respond to user actions within 2 seconds under normal load conditions.
2. **Throughput**:
   - The system should handle at least 1000 requests per second to accommodate peak usage times.

### Scalability

3. **Horizontal Scalability**:
   - The system should support horizontal scaling to handle increased load by adding more instances of services.
4. **Elasticity**:
   - The system should automatically scale up or down based on the current load.

### Security

5. **Access Control**:
   - The system should enforce role-based access control to ensure only authorized users can perform certain actions.

### Usability

6. **User Interface**:
   - The user interface should be intuitive and easy to navigate.
7. **Accessibility**:
   - The system should comply with accessibility standards to ensure it is usable by people with disabilities.

### Maintainability

8. **Code Quality**:
   - The codebase should follow best practices and be well-documented to facilitate maintenance.
9. **Modularity**:
   - The system should be modular to allow for easy updates and addition of new features.

### Monitoring and Logging

10. **Monitoring**:
    - The system should have robust monitoring in place to track performance, detect issues, and alert administrators.
11. **Logging**:
    - The system should log all significant events and errors to aid in debugging and monitoring.

### Interoperability

12. **API Standards**:
    - The system should adhere to common API standards to facilitate integration with other systems.
13. **Compatibility**:
    - The system should be compatible with various devices and browsers to ensure a wide range of users can access it.

## Architecture

The social media platform is built using a microservices architecture, leveraging modern technologies to ensure scalability, reliability, and maintainability. The architecture is designed to handle a large number of users and interactions efficiently while allowing for independent development, deployment, and scaling of services.

### Microservices Overview

The platform comprises several microservices, each responsible for specific functionality:

1. **Eureka Server**: Central service registry for service discovery and load balancing.
2. **API Gateway**: Acts as a single entry point for all client requests, routing them to the appropriate microservices. It also handles security through Spring Security.
3. **Administration Service**: Manages administrative tasks such as banning users.
4. **Chat Service**: Handles real-time messaging between users.
5. **Post Service**: Manages user posts, including creation, deletion, reactions, comments, and tracking unviewed posts.
6. **User Service**: Handles user-related operations such as friend requests, and account management.

### Key Architectural Components

#### Service Discovery

- **Eureka Server** is used for service discovery. Each microservice registers itself with the Eureka Server, enabling dynamic discovery and load balancing.

#### API Gateway

- **Spring Cloud Gateway** is used as the API Gateway. It routes client requests to the appropriate microservices and secures endpoints using **Spring Security**.

#### Inter-Service Communication

- **OpenFeign** is used for declarative REST client creation, facilitating communication between microservices.

#### Containerization and Orchestration

- **Docker** is used to containerize microservices, ensuring consistency across different environments.
- **Docker Compose** is used for defining and running multi-container Docker applications during development.
- **Kubernetes** orchestrates the Docker containers, providing automated deployment, scaling, and management of containerized applications.

#### Monitoring and Observability

- **Prometheus** is used for collecting metrics from microservices.
- **Grafana** is used for visualizing metrics and creating dashboards for real-time monitoring.
- **Zipkin** is used for distributed tracing, enabling the tracking of requests as they flow through the microservices.

#### CI/CD

- **GitHub Actions** is used for Continuous Integration and Continuous Deployment (CI/CD). Automated pipelines are set up for building, testing, and deploying code changes.

#### Security

- **Spring Security** is used to secure the API Gateway and other sensitive endpoints, ensuring that only authenticated and authorized users can access the system.

### Data Flow

1. **Client Request**: A client sends a request to the platform, which is received by the API Gateway.
2. **Routing**: The API Gateway routes the request to the appropriate microservice based on the endpoint.
3. **Service Interaction**: Microservices communicate with each other as needed using OpenFeign to fulfill the request.
4. **Database Interaction**: Microservices interact with the MySQL database to retrieve or store data.
5. **Response**: The response from the microservice is routed back through the API Gateway to the client.

### Advantages of the Architecture

- **Scalability**: Each microservice can be scaled independently based on its load.
- **Fault Isolation**: Failures in one microservice do not affect others, enhancing system reliability.
- **Technology Diversity**: Different microservices can use different technologies or programming languages if needed.
- **Independent Development and Deployment**: Teams can develop, test, and deploy microservices independently, speeding up the development lifecycle.

This architecture ensures that the platform can efficiently handle a growing user base, adapt to changing requirements, and maintain high performance and availability.

![jee_workflow](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/5f9a385b-d17d-47e5-8d62-c0cb0ffa8004)

## Class Diagram

Here is our class diagram for the social media platform project. The diagram illustrates the structure of the system by showing the system's classes, their attributes, and the relationships among the objects. This visual representation helps in understanding how different components interact with each other, providing a clear overview of the system's design.

![image](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/af8a5d6e-6c7a-4512-82b5-54c6217ebee0)

## Application Mockup Images

To provide a visual representation of the social media platform's user interface and user experience, we have included a set of application mockup images. These mockups illustrate the design and layout of key screens and features within the platform, offering a glimpse into how users will interact with the application.

### Login :

![login](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/f14392cd-fa21-4391-b181-f8e24b66642c)

### Register :

![register](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/63b7ff53-a2e0-464d-8897-f72674a734d0)

### Feed :

![feed](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/de806ffa-f935-4025-ad76-bc40adcb8ca1)

### Profile :

![profile](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/4b2dd1ce-727c-4060-b595-e9292ef6fbf4)

### Chat page :

![chat](https://github.com/m-elhamlaoui/Social-Media-Platform/assets/135556638/f58c9fd5-a890-49f3-80f6-8a7c7eea7fa8)
