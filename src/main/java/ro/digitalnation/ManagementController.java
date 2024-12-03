package ro.digitalnation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.Patient;
import ro.digitalnation.management.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/management")
public class ManagementController {

    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<MedicalRecord> medicalRecords = new ArrayList<>();
    // Constructor pentru inițializare
    public ManagementController() {
        doctors.add(new Doctor("1", "Dr. Smith", "smith@hospital.com", "123456789", "Cardiology"));
        doctors.add(new Doctor("2", "Dr. Brown", "brown@hospital.com", "987654321", "Neurology"));

        patients.add(new Patient("1", "John Doe", "john.doe@gmail.com", "123456789", "No significant history"));
        patients.add(new Patient("2", "Jane Smith", "jane.smith@gmail.com", "987654321", "Diabetes Type 2"));
    }

    // ====== OPERAȚII PENTRU DOCTORI ======

    // GET: Afișează lista tuturor doctorilor
    @GetMapping("/doctors")
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctors);
        return "management/doctors/list";
    }

    // GET: Form pentru adăugarea unui nou doctor
    @GetMapping("/doctors/add")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor("", "", "", "", ""));
        return "management/doctors/add";
    }

    // POST: Adaugă un doctor nou
    @PostMapping("/doctors/add")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctors.add(doctor);
        return "redirect:/management/doctors";
    }

    // DELETE: Șterge un doctor pe baza ID-ului
    @PostMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable String id) {
        doctors.removeIf(doctor -> doctor.getId().equals(id));
        return "redirect:/management/doctors";
    }

    // ====== OPERAȚII PENTRU PACIENȚI ======

    // GET: Afișează lista tuturor pacienților
    @GetMapping("/patients")
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patients);
        return "management/patients/list";
    }

    // GET: Form pentru adăugarea unui nou pacient
    @GetMapping("/patients/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient("", "", "", "", ""));
        return "management/patients/add";
    }

    // POST: Adaugă un pacient nou
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute Patient patient) {
        patients.add(patient);
        return "redirect:/management/patients";
    }

    // DELETE: Șterge un pacient pe baza ID-ului
    @PostMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable String id) {
        patients.removeIf(patient -> patient.getId().equals(id));
        return "redirect:/management/patients";
    }
    
 // ====== OPERAȚII PENTRU INREGISTRĂRI MEDICALE ======

    // GET: Afișează lista tuturor înregistrărilor medicale
    @GetMapping("/medical-records")
    public String getAllMedicalRecords(Model model) {
        model.addAttribute("medicalRecords", medicalRecords);
        return "management/medical-records/list";
    }

    // GET: Form pentru adăugarea unui Medical Record
    @GetMapping("/medical-records/add")
    public String showAddMedicalRecordForm(Model model) {
        model.addAttribute("medicalRecord", new TreatmentRecord("", "", "", "", ""));
        model.addAttribute("doctors", doctors);
        return "management/medical-records/add";
    }

    // POST: Adaugă un Medical Record nou
    @PostMapping("/medical-records/add")
    public String addMedicalRecord(@RequestParam String doctorId,
                                   @RequestParam String patientName,
                                   @RequestParam String patientEmail,
                                   @RequestParam String patientPhoneNumber,
                                   @RequestParam String patientMedicalHistory,
                                   @RequestParam String date,
                                   @RequestParam String treatmentDescription) {

        // Găsim doctorul selectat
        Doctor selectedDoctor = doctors.stream()
                .filter(doctor -> doctor.getId().equals(doctorId))
                .findFirst()
                .orElse(null);

        if (selectedDoctor == null) {
            return "redirect:/management/medical-records/add?error=DoctorNotFound";
        }

        // Creăm un pacient nou și îl adăugăm în lista de pacienți
        String patientId = String.valueOf(patients.size() + 1);
        Patient newPatient = new Patient(patientId, patientName, patientEmail, patientPhoneNumber, patientMedicalHistory);
        patients.add(newPatient);

        // Creăm noul Medical Record
        String recordId = String.valueOf(medicalRecords.size() + 1);
        MedicalRecord newRecord = new TreatmentRecord(recordId, patientId, doctorId, date, treatmentDescription);
        medicalRecords.add(newRecord);

        return "redirect:/management/medical-records";
    }
}
