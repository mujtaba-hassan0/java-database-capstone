package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // 1️⃣ Get all prescriptions
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    // 2️⃣ Get prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        if (prescription == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(prescription);
    }

    // 3️⃣ Get prescriptions by patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatient(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    // 4️⃣ Add new prescription
    @PostMapping
    public ResponseEntity<Prescription> addPrescription(@RequestBody Prescription prescription) {
        Prescription savedPrescription = prescriptionService.addPrescription(prescription);
        return ResponseEntity.ok(savedPrescription);
    }

    // 5️⃣ Update prescription
    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable Long id, @RequestBody Prescription prescription) {
        Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescription);
        if (updatedPrescription == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedPrescription);
    }

    // 6️⃣ Delete prescription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        boolean deleted = prescriptionService.deletePrescription(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }
}
