package ro.digitalnation.entities;

import jakarta.persistence.*;
import ro.digitalnation.apoiments.Appointment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends User {

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Patient() {
        // Default constructor for JPA
    }

    public Patient(String id, String name, String email, String phoneNumber,List<Appointment> appointments) {
        super();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "appointments=" + appointments +
                "} " + super.toString();
    }
}
