package ro.digitalnation.entities;

public class Doctor extends Person {
    private String specialty;

    public Doctor(String id, String name, String email, String phoneNumber, String specialty) {
        super(id, name, email, phoneNumber);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String getRole() {
        return "Doctor";
    }

	@Override
	public String toString() {
		return "Doctor [specialty=" + specialty + "]";
	}
    
}
