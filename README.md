📚 Knowledge Base

A production-grade Spring Boot backend for managing personal knowledge with strict user ownership and authorization.

Built to mirror real-world enterprise backend practices, not tutorial shortcuts.

🚀 Features

🔐 User-based data isolation

🧩 Full CRUD lifecycle for knowledge entries

🧠 DTO-driven request & response models

🗄️ MySQL relational persistence

🧱 Clean layered architecture

🛡️ Ownership validation at service layer

🧪 API testing with Postman

🛠 Tech Stack

Language: Java 17

Framework: Spring Boot

Web: Spring Web

Persistence: Spring Data JPA, Hibernate

Database: MySQL

Build Tool: Maven

Testing: Postman

📂 Project Structure
src/main/java
└── com.example.knowledgebase
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    └── KnowledgeBaseApplication.java

🗄 Database Design
User

user_id (PK)

username

email

password

Knowledge Entry

entry_id (PK)

topic

explanation

created_at

user_id (FK → User)

➡️ One user can own multiple knowledge entries.

🔐 Authorization Model

Users can only access their own data

Validation handled in the service layer

Unauthorized access throws controlled exceptions

This mirrors authorization logic used in production systems before adding JWT/OAuth.

🔄 API Endpoints
User APIs
Method	Endpoint	Description
POST	/user	Create a new user
GET	/user/{id}	Fetch user by ID
Knowledge Base APIs
Method	Endpoint	Description
POST	/user/{user_id}/knowledge_base	Create a new knowledge entry
GET	/user/{user_id}/knowledge_base	Fetch all entries for a user
PUT	/user/{user_id}/knowledge_base/{id}	Update an existing entry
DELETE	/user/{user_id}/knowledge_base/{id}	Delete an existing entry
⚙️ Configuration

application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/knowledge_base
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Running the Application
git clone https://github.com/your-username/knowledge-base.git
cd knowledge-base
mvn spring-boot:run


Server runs at:

http://localhost:8080

🧪 Testing

Postman used for API testing

Multi-user data isolation verified

Unauthorized access scenarios tested

📈 Roadmap

JWT authentication

Role-based access control

Pagination & sorting

Search and tagging

Redis caching

Docker support

🎯 Why This Project

Practice real backend architecture

Learn relational database modeling

Implement ownership-based authorization

Build something beyond CRUD tutorials

👤 Author

Vaibhav
Backend Developer — Java & Spring Boot

📜 License

MIT License
