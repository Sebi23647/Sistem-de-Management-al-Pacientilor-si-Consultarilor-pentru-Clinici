package ro.digitalnation.entities;

public class Patient extends Person {
    private String medicalHistory;

    public Patient(String id, String name, String email, String phoneNumber, String medicalHistory) {
        super(id, name, email, phoneNumber);
        this.medicalHistory = medicalHistory;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String getRole() {
        return "Patient";
    }

	@Override
	public String toString() {
		return "Patient [medicalHistory=" + medicalHistory + "]";
	}
    
}
