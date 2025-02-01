# Dragmeclips

This project is a video upload and processing system built using **Spring Boot**, **Kafka**, **PostgreSQL**, and **FFmpeg**. It allows  users to upload videos, which are then processed asynchronously using Kafka and FFmpeg.

---

## **Prerequisites**

Before running the project, ensure you have the following installed:

1. **Docker** and **Docker Compose**:
   - [Install Docker](https://docs.docker.com/get-docker/)
   - [Install Docker Compose](https://docs.docker.com/compose/install/)

2. **Java 17** (for local development):
   - [Install Java 17](https://openjdk.org/projects/jdk/17/)

3. **Maven** (for building the project):
   - [Install Maven](https://maven.apache.org/install.html)

---

## **Setup and Run the Project**

### **1. Clone the Repository**
```bash
git clone https://github.com/farazabir/dragmeclips
cd dragmeclips
```

### **2. Build the Spring Boot Application**
```bash
mvn clean package
```

### **3. Update Environment Variables**
Edit the `application.properties` file to configure Kafka, PostgreSQL, and other settings:
```properties
spring.kafka.bootstrap-servers=localhost:9092
spring.datasource.url=jdbc:postgresql://localhost:5434/postgres
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### **4. Start the Application with Docker Compose**
Run the following command to start all services (Kafka, Zookeeper, PostgreSQL, Spring Boot app, and FFmpeg processor):
```bash
docker-compose up --build
```

### **5. Access the Application**
- The Spring Boot app will be available at: `http://localhost:8080`
- Use the `/videos/upload` endpoint to upload videos.

---

## **Project Structure**

- **Spring Boot App**: Handles video uploads and user authentication.
- **Kafka**: Manages asynchronous video processing tasks.
- **PostgreSQL**: Stores video metadata and user information.
- **FFmpeg Processor**: Processes videos (e.g., compression, thumbnail generation).

---

## **API Endpoints**

### **Upload a Video**
- **Endpoint**: `POST /videos/upload`
- **Request Body**:
  - `file`: video file.
  - `email`: email@email.com.
  - `userId`: userid.
- **Response**:
  - `Video uploaded and processing started. Video ID: <video_id>`

---

## **Environment Variables**

| Variable                  | Description                          | Default Value        |
|---------------------------|--------------------------------------|----------------------|
| `SPRING_DATASOURCE_URL`    | PostgreSQL connection URL            | `jdbc:postgresql://db:5432/postgres` |
| `SPRING_DATASOURCE_USERNAME` | PostgreSQL username                | `postgres`           |
| `SPRING_DATASOURCE_PASSWORD` | PostgreSQL password                | `postgres`           |
| `KAFKA_BOOTSTRAP_SERVERS`  | Kafka broker address                | `kafka:9092`         |

---

## **Troubleshooting**

1. **Kafka Fails to Start**:
   - Ensure `KAFKA_LISTENERS` and `KAFKA_ADVERTISED_LISTENERS` are correctly configured in `docker-compose.yml`.

2. **PostgreSQL Connection Issues**:
   - Verify the `spring.datasource.url` in `application.properties` matches the PostgreSQL container's port.

3. **FFmpeg Processor Fails**:
   - Check the `transcoder.py` script for syntax errors and ensure it uses Python 3.7+.
   ## need to make transcoder pipeline using ffmpeg
