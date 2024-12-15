
package ro.digitalnation.entities.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ro.digitalnation.apoiments.Appointment;
import ro.digitalnation.apoiments.AppointmentRepository;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.repositories.DoctorRepository;
import ro.digitalnation.management.Tratament;
import ro.digitalnation.management.TratamentRepository;

@Controller
public class AdminController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TratamentRepository tratamentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    
    @GetMapping("/adminPages/addDoctor")
    public String addDoctorPage(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "adminPages/addDoctor";
    }

    @PostMapping("/addDoctor")
    public String addDoctor(@ModelAttribute Doctor doctor, Model model) {
        doctorRepository.save(doctor);
        model.addAttribute("message", "Doctorul a fost adăugat cu succes!");
        return "adminPages/addDoctor";
    }

    @GetMapping("/adminPages/searchDoctor")
    public String searchDoctorPage(Model model) {
        model.addAttribute("doctors", null);
        return "adminPages/searchDoctor";
    }

    @PostMapping("/searchDoctor")
    public String searchDoctor(@ModelAttribute("nume") String nume, Model model) {
        List<Doctor> doctors = doctorRepository.findByNume(nume);
        model.addAttribute("doctors", doctors);
        return "adminPages/searchDoctor";
    }

    @GetMapping("/doctorDetails/{id}")
    public String doctorDetails(@PathVariable Long id, Model model) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        model.addAttribute("doctor", doctor);
        return "adminPages/doctorDetails";
    }


    @GetMapping("/adminPages/appointmentsHistory")
    public String appointmentsHistoryPage() {
        return "adminPages/appointmentsHistory";
    }

    @GetMapping("/appointmentsHistory")
    public String appointmentsHistoryPage(Model model) {
        List<Appointment> appointments = appointmentRepository.findAll();
        model.addAttribute("appointments", appointments);
        return "adminPages/appointmentsHistory";
    }

    @GetMapping("adminPages/treatmentsHistory")
    public String treatmentsHistoryPage() {
        return "adminPages/treatmentsHistory";
    }
    
    
    @GetMapping("/treatmentsHistory")
    public String treatmentsHistoryPage(Model model) {
        List<Tratament> treatments = tratamentRepository.findAll();
        model.addAttribute("treatments", treatments);
        return "adminPages/treatmentsHistory";
    }

    @GetMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable Long id, Model model) {
        doctorRepository.deleteById(id);
        model.addAttribute("message", "Doctorul a fost șters cu succes!");
        return "redirect:/adminPages/searchDoctor"; 
    }

}
