package ro.digitalnation.apoiments;

import jakarta.persistence.*;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.Patient;
import ro.digitalnation.management.Tratament;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne
    @JoinColumn(name = "treatment_id", nullable = true)
    private Tratament tratament;

    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Column(name = "status", nullable = false)
    private String status;

    public Appointment() {
        // Default constructor for JPA
    }

    public Appointment(Patient patient, Doctor doctor, Tratament tratament, String appointmentDate, String status) {
        this.patient = patient;
        this.doctor = doctor;
        this.tratament = tratament;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Tratament getTratament() {
        return tratament;
    }

    public void setTratament(Tratament tratament) {
        this.tratament = tratament;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", tratament=" + tratament +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
