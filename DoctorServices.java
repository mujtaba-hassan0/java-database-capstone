package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Doctor;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    // Add new doctor
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update doctor info
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        Optional<Doctor> existing = doctorRepository.findById(id);
        if(existing.isEmpty()) return null;

        Doctor doctor = existing.get();
        doctor.setName(updatedDoctor.getName());
        doctor.setSpecialty(updatedDoctor.getSpecialty());
        doctor.setEmail(updatedDoctor.getEmail());
        doctor.setPhone(updatedDoctor.getPhone());
        return doctorRepository.save(doctor);
    }

    // Delete doctor
    public boolean deleteDoctor(Long id) {
        Optional<Doctor> existing = doctorRepository.findById(id);
        if(existing.isEmpty()) return false;

        doctorRepository.delete(existing.get());
        return true;
    }

    // Get appointments for a doctor
    public List<Appointment> getAppointmentsForDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Search doctors by specialty
    public List<Doctor> searchBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyContainingIgnoreCase(specialty);
    }
}
