package ro.digitalnation.entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ro.digitalnation.apoiments.Appointment;
import ro.digitalnation.entities.Patient;
import ro.digitalnation.management.Tratament;
import ro.digitalnation.apoiments.AppointmentRepository;
import ro.digitalnation.entities.repositories.PatientRepository;
import ro.digitalnation.management.TratamentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PatientController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping("/patientPages/appointmentTreatment/{id}")
    public String viewAppointmentTreatment(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null && appointment.getTratament() != null) {
            model.addAttribute("treatment", appointment.getTratament());
            return "patientPages/appointmentTreatment"; 
        }
        model.addAttribute("errorMessage", "Tratamentul nu este disponibil pentru această programare.");
        return "/patientPages/viewAppointments"; 
    }
    
    @GetMapping("/patientPages/viewAppointments")
    public String viewAppointments(@RequestParam Long patientId, Model model) {
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());
        model.addAttribute("appointments", appointments);
        return "patientPages/viewAppointments";
    }

    @GetMapping("patientPages/viewTreatments")
    public String viewTreatments(@RequestParam Long patientId, Model model) {
        List<Appointment> appointments = appointmentRepository.findAll()
                .stream()
                .filter(appointment -> appointment.getPatient().getId().equals(patientId))
                .collect(Collectors.toList());

        List<Tratament> treatments = appointments.stream()
                .map(Appointment::getTratament)
                .filter(tratament -> tratament != null) 
                .collect(Collectors.toList());

        model.addAttribute("treatments", treatments);
        return "patientPages/viewTreatments"; 
    }


}
