# Curb Chat

A real-time chat application built with Spring Boot and React, featuring WebSocket communication for instant messaging.

## Tech Stack

### Backend
- Java 21
- Spring Boot 3.1.5
- WebSocket (STOMP)
- Docker
- Azure Container Registry
- Azure App Service

### Frontend
- React
- Vite
- SockJS Client
- STOMP.js
- Vercel

## Features
- Real-time messaging
- User join/leave notifications
- Clean, modern UI
- Cross-device compatibility
- Containerized backend
- Automated deployments

## Local Development

### Backend
```bash
# Clone the repository
git clone https://github.com/sdhrrr/yappy.git
cd yappy

# Run using Maven
./mvnw spring-boot:run

# Or build and run with Docker
docker build -t chatapp .
docker run -p 8080:8080 chatapp


I would really appreciate any contributions or bug fixes to this project :)
