package ro.digitalnation.entities;

import jakarta.persistence.*;
import ro.digitalnation.apoiments.Appointment;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor extends User {
	@Column(name = "specialty")
    private String specialty;
	
    @Column(name = "salary")
    private double salary;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String id, String name, String email, String phoneNumber, String specialty, double salary) {
        super();
        this.specialty = specialty;
        this.salary = salary;
    }

    public Doctor() {
        // Default constructor for JPA
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialty='" + specialty + '\'' +
                ", salary=" + salary +
                ", appointments=" + appointments +
                '}';
    }
}
