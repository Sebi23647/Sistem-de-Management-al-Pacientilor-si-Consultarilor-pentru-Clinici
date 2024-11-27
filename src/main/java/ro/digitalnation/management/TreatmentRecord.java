package ro.digitalnation.management;

public class TreatmentRecord extends MedicalRecord {
    private String treatmentDescription;

    public TreatmentRecord(String recordId, String patientId, String doctorId, String date, String treatmentDescription) {
        super(recordId, patientId, doctorId, date);
        this.treatmentDescription = treatmentDescription;
    }

    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    @Override
    public String getDetails() {
        return "Treatment: " + treatmentDescription;
    }
    
}
