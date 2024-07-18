
### email-service 
```markdown
# Email Service for Subscriber Management System (SMS)

## Overview

The Email Service is responsible for sending emails asynchronously in the Subscriber Management System (SMS). It uses RabbitMQ for messaging to handle email requests efficiently.

## Architecture

- **Spring Boot**: The core framework used to build the Email Service.
- **RabbitMQ**: For messaging and handling email requests asynchronously.

## Features

- **Asynchronous Email Sending**: Sends emails asynchronously using RabbitMQ.
- **Message Queuing**: Handles email requests through message queues for efficient processing.



## Configuration

Configuration for the Email Service is done through `application.yml` or `application.properties`. Below is an example configuration in `application.yml`:

```yaml
server:
  port: 8092

spring:
  application:
    name: email-service

rabbitmq:
  host: localhost
  port: 5671
  username: guest
  password: guest
