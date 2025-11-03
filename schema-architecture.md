# Smart Clinic App – Schema Architecture

## 1. Architecture Summary

The Smart Clinic application follows a **three-tier architecture** consisting of the **Presentation Layer (Frontend)**, **Application Layer (Backend)**, and **Data Layer (Databases)**.  
The frontend interacts with the backend through **RESTful APIs** exposed by **Spring Boot controllers** that follow the **Model-View-Controller (MVC)** pattern.  
The **Application Layer** manages business logic and data flow using **Spring Services**, while the **Data Layer** uses **Spring Data JPA** for relational data stored in **MySQL** and **Spring Data MongoDB** for non-relational data such as logs or unstructured documents.  
This modular design promotes scalability, maintainability, and clean separation of concerns.

---

## 2. Numbered Flow – Request/Response Cycle

1. **User Interaction (Frontend):**  
   The user interacts with the web application’s frontend (built with HTML, CSS, and JavaScript or a frontend framework).  
   Example: A user submits a “Book Appointment” form.

2. **HTTP Request to Backend:**  
   The frontend sends an HTTP request (usually via `fetch()` or Axios) to the **Spring Boot REST API endpoint** — e.g., `POST /api/appointments`.

3. **Controller Layer (Presentation Logic):**  
   The request reaches the corresponding **Spring MVC Controller**, which receives the incoming data as a **DTO (Data Transfer Object)** and validates it.

4. **Service Layer (Business Logic):**  
   The controller delegates processing to the **Service Layer**, which contains the core business rules.  
   For example, it verifies doctor availability and appointment slot conflicts.

5. **Repository Layer (Data Access):**  
   The service calls **Repository interfaces** that use **Spring Data JPA** or **Spring Data MongoDB** to interact with databases.  
   - MySQL is used for structured clinic data (patients, doctors, appointments).  
   - MongoDB is used for non-relational data (logs, analytics, or audit trails).

6. **Database Operations:**  
   The repositories execute **CRUD operations** on MySQL tables or MongoDB collections through ORM frameworks, abstracting SQL or BSON queries.

7. **Data Return from Databases:**  
   The databases return the required results (e.g., confirmation of saved appointment or retrieved patient record) to the repository layer.

8. **Response Assembly in Service Layer:**  
   The service layer processes the returned data, applies any transformation or formatting, and prepares the response object.

9. **Controller Sends HTTP Response:**  
   The controller sends an HTTP response (usually JSON) back to the frontend, including a success message or the requested data.
https://docs.github.com/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax
10. **Frontend Updates UI:**  
    The frontend receives the JSON response, updates the user interface dynamically (e.g., shows a “Booking Confirmed” message), and completes the user’s interaction.
