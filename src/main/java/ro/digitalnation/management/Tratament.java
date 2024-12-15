package ro.digitalnation.management;

import jakarta.persistence.*;

@Entity
@Table(name = "tratament")
public class Tratament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private double cost;

    @Column(name = "period", nullable = false)
    private String period; // Format: e.g., "2 weeks", "1 month"

    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    public Tratament() {
        // Default constructor for JPA
    }

    public Tratament(String description, double cost, String period, String diagnosis) {
        this.description = description;
        this.cost = cost;
        this.period = period;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Tratament{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", period='" + period + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
