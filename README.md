project:
  name: Knowledge Base Application
  description: >
    A backend-focused Knowledge Base application built using Spring Boot
    that allows users to create, manage, and organize their personal knowledge
    entries securely. Each user owns their data, ensuring proper isolation
    and authorization. The project follows production-grade backend practices
    and is suitable as a portfolio project for backend or full-stack roles.

features:
  - User-based data isolation
  - CRUD operations for knowledge entries
  - RESTful API architecture
  - MySQL relational database integration
  - DTO-based request and response handling
  - Ownership and authorization validation
  - Layered architecture (Controller, Service, Repository)
  - API testing using Postman

tech_stack:
  language: Java 17
  framework: Spring Boot
  modules:
    - Spring Web
    - Spring Data JPA
    - Hibernate
  database: MySQL
  build_tool: Maven
  testing_tool: Postman

project_structure:
  src/main/java:
    base_package: com.example.knowledgebase
    layers:
      controller:
        - UserController.java
        - KnowledgeBaseController.java
      service:
        - UserService.java
        - KnowledgeBaseService.java
      repository:
        - UserRepository.java
        - KnowledgeBaseRepository.java
      entity:
        - User.java
        - KnowledgeBase.java
      dto:
        - KnowledgeBaseRequest.java
        - KnowledgeBaseResponse.java
      application:
        - KnowledgeBaseApplication.java

database_design:
  user:
    fields:
      - user_id: primary_key
      - username: string
      - email: string
      - password: string
  knowledge_base:
    fields:
      - entry_id: primary_key
      - topic: string
      - explanation: text
      - created_at: timestamp
      - user_id: foreign_key_to_user
  relationships:
    - one_user_has_many_knowledge_entries

authorization:
  rules:
    - Users can read only their own entries
    - Users can update only their own entries
    - Users can delete only their own entries
  validation_layer: service
  unauthorized_behavior: runtime_exception

api_endpoints:
  users:
    - method: POST
      path: /user
      description: Create a new user
    - method: GET
      path: /user/{id}
      description: Fetch user by ID
  knowledge_base_entries:
    - method: POST
      path: /user/{user_id}/knowledge_base
      description: Create a new knowledge entry
    - method: GET
      path: /user/{user_id}/knowledge_base
      description: Fetch all entries for a user
    - method: PUT
      path: /user/{user_id}/knowledge_base/{id}
      description: Update an existing entry
    - method: DELETE
      path: /user/{user_id}/knowledge_base/{id}
      description: Delete an entry

configuration:
  application_properties:
    datasource:
      url: jdbc:mysql://localhost:3306/knowledge_base
      username: root
      password: your_password
    jpa:
      ddl_auto: update
      show_sql: true
      dialect: MySQL8Dialect

running_application:
  steps:
    - clone_repository: git clone https://github.com/your-username/knowledge-base.git
    - navigate_to_project: cd knowledge-base
    - run_command: mvn spring-boot:run
  server:
    host: localhost
    port: 8080

testing:
  approach:
    - API testing via Postman
    - JSON request and response validation
    - Multi-user data isolation testing
    - Unauthorized access edge-case handling

future_enhancements:
  - JWT-based authentication
  - Role-based access control
  - Pagination and sorting
  - Search and tagging functionality
  - Redis caching
  - Dockerization
  - Frontend integration using Angular or React

project_objectives:
  - Learn real-world Spring Boot architecture
  - Gain strong understanding of relational databases
  - Implement authorization and ownership validation
  - Build a non-trivial backend application beyond tutorials

author:
  name: Vaibhav
  role: Backend Developer
  skills:
    - Java
    - Spring Boot

license:
  type: MIT
  description: Open-source and free to use under the MIT License
