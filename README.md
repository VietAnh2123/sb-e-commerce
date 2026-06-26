# VA E-commerce

VA E-commerce is a demo e-commerce backend built with Spring Boot. It implements product, cart, order, and user management, with JWT authentication, role-based security and email notifications. It uses Spring Data JPA with MySQL, Cloudinary for image uploads, VNPAY/PayOS integrations for payments, OpenFeign for external services, WebSocket for real-time notifications, and OpenAPI (Swagger) for interactive API docs. The application is configured via environment variables declared in src/main/resources/application.properties.

Prerequisites:
- Java 21
- Maven
- MySQL (or provide JDBC URL via env)

Quick start:
1. Export required environment variables used in src/main/resources/application.properties (examples):
   - LOCAL_DB, LOCAL_DB_USERNAME, LOCAL_DB_PASSWORD
   - JWT_SECRET
   - CLOUD_NAME, API_KEY, API_SECRET
   - MAIL_USERNAME, MAIL_PASSWORD
   - VNPAY_TMN_CODE, VNPAY_SECRET_KEY
   - PAYOS_CLIENT_ID, PAYOS_API_KEY, PAYOS_CHECKSUM_KEY
   - GG_CLIENT_ID, GG_CLIENT_SECRET
2. Build: mvn clean package
3. Run: java -jar target/spring-shopping-cart-0.0.1-SNAPSHOT.jar
   or: mvn spring-boot:run

API docs (Swagger/OpenAPI): http://localhost:8080/shop/api-docs

# UI
 Updating....
