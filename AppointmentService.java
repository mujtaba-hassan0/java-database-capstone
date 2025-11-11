package com.smartclinic.service;

import com.smartclinic.model.Appointment;
import com.smartclinic.model.Doctor;
import com.smartclinic.model.Patient;
import com.smartclinic.repository.AppointmentRepository;
import com.smartclinic.repository.DoctorRepository;
import com.smartclinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // 1️⃣ Create a new appointment
    public Appointment createAppointment(Appointment appointment) {
        // Optional: Validate doctor and patient existence
        Optional<Doctor> doctor = doctorRepository.findById(appointment.getDoctor().getId());
        Optional<Patient> patient = patientRepository.findById(appointment.getPatient().getId());

        if (doctor.isEmpty() || patient.isEmpty()) {
            return null; // or throw an exception
        }

        appointment.setDoctor(doctor.get());
        appointment.setPatient(patient.get());
        return appointmentRepository.save(appointment);
    }

    // 2️⃣ Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 3️⃣ Get appointment by ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    // 4️⃣ Get appointments for a specific doctor
    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // 5️⃣ Get appointments for a specific patient
    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // 6️⃣ Get appointments for a doctor on a specific date
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }

    // 7️⃣ Update appointment
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Optional<Appointment> existing = appointmentRepository.findById(id);
        if(existing.isEmpty()) return null;

        Appointment appointment = existing.get();
        appointment.setDate(updatedAppointment.getDate());
        appointment.setTime(updatedAppointment.getTime());
        appointment.setNotes(updatedAppointment.getNotes());
        // Optionally update doctor or patient
        return appointmentRepository.save(appointment);
    }

    // 8️⃣ Delete appointment
    public boolean deleteAppointment(Long id) {
        Optional<Appointment> existing = appointmentRepository.findById(id);
        if(existing.isEmpty()) return false;

        appointmentRepository.delete(existing.get());
        return true;
    }
}
