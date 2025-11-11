package com.smartclinic.repository;

import com.smartclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Find a patient by email (useful for login or verification)
    Optional<Patient> findByEmail(String email);

    // Optional: search by name
    Optional<Patient> findByName(String name);
}
