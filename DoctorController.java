package com.smartclinic.controller;

import com.smartclinic.model.Doctor;
import com.smartclinic.model.Appointment;
import com.smartclinic.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 1️⃣ Get all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // 2️⃣ Get doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if(doctor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(doctor);
    }

    // 3️⃣ Add new doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    // 4️⃣ Update doctor info
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
        if(updatedDoctor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedDoctor);
    }

    // 5️⃣ Delete doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        boolean deleted = doctorService.deleteDoctor(id);
        if(!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    // 6️⃣ Get appointments for a doctor
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable Long id) {
        List<Appointment> appointments = doctorService.getAppointmentsForDoctor(id);
        if(appointments == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(appointments);
    }

    // Optional: Search doctors by specialty
    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(@RequestParam String specialty) {
        List<Doctor> doctors = doctorService.searchBySpecialty(specialty);
        return ResponseEntity.ok(doctors);
    }
}
