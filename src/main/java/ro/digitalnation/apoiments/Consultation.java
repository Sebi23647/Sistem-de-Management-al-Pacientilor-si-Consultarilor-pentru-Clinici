package ro.digitalnation.apoiments;

public class Consultation extends Appointment {
    private String notes;

    public Consultation(String appointmentId, String patientId, String doctorId, String appointmentDate, String status, String notes) {
        super(appointmentId, patientId, doctorId, appointmentDate, status);
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String getAppointmentType() {
        return "Consultation";
    }
}

