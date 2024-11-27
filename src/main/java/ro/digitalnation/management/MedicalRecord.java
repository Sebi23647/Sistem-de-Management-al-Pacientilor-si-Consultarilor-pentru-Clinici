package ro.digitalnation.management;

public abstract class MedicalRecord {
    private String recordId;
    private String patientId;
    private String doctorId;
    private String date;

    public MedicalRecord(String recordId, String patientId, String doctorId, String date) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDate() {
        return date;
    }

    public abstract String getDetails();
    
}
