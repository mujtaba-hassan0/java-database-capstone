# Smart Clinic — Schema Design

This document describes the database schema design for the Smart Clinic app.  
We use **MySQL (relational)** for structured, transactional data (patients, doctors, appointments, admin) and **MongoDB (document)** for unstructured or semi-structured records (prescriptions, logs, feedback) that benefit from flexible schemas and nested fields.

---

## MySQL Database Design

**Design notes / rationale (short):**
- Use MySQL for consistency, transactions, joins, and strong constraints (e.g., appointment consistency, FK enforcement).  
- Normalize core entities (patients, doctors, appointments) to avoid duplication and ensure referential integrity.

### Table: `patients`
```sql
-- Patients table: stores registered patients
CREATE TABLE patients (
    patient_id BIGINT AUTO_INCREMENT PRIMARY KEY,      
    email VARCHAR(255) NOT NULL UNIQUE,                
    password_hash VARCHAR(255) NOT NULL,               
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    date_of_birth DATE,
    gender ENUM('Male','Female','Other') DEFAULT 'Other',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- Indexes: unique on email ensures no duplicate accounts


-- Doctors table: stores doctor profiles 
CREATE TABLE doctors (
    doctor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(150) NOT NULL,
    contact_number VARCHAR(20),
    bio TEXT,
    years_experience SMALLINT UNSIGNED DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE,                    
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

Table: appointments
sql


-- Appointments: connects patients and doctors with a timeslot
CREATE TABLE appointments (
    appointment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status ENUM('SCHEDULED','COMPLETED','CANCELLED','NO_SHOW') NOT NULL DEFAULT 'SCHEDULED',
    reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    CONSTRAINT chk_time_order CHECK (end_time > start_time)
);

-- Indexes to help common lookups:
CREATE INDEX idx_appointments_doctor_start ON appointments (doctor_id, start_time);
CREATE INDEX idx_appointments_patient_start ON appointments (patient_id, start_time);
Table: admins
sql

-- Admins: platform administrators
CREATE TABLE admins (
    admin_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(200),
    role ENUM('SUPER_ADMIN','ADMIN') DEFAULT 'ADMIN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**MongoDB Collection Design**

Use MongoDB for flexible nested documents (prescriptions with multiple medicines, audit logs, or feedback). This avoids complex joins and is good for append-heavy or schema-evolving data.

Chosen collection: prescriptions

Justification: Prescriptions often include arrays of medications with dosage instructions and optional attachments or notes. The document model maps naturally to this structure and can evolve without schema migrations.

```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
  }
}
```


