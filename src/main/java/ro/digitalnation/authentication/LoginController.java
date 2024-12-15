package ro.digitalnation.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import ro.digitalnation.entities.Admin;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.Patient;
import ro.digitalnation.entities.repositories.AdminRepository;
import ro.digitalnation.entities.repositories.DoctorRepository;
import ro.digitalnation.entities.repositories.PatientRepository;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;  

    @GetMapping({"/", "/login"})
    public String showLoginForm(Model model) {
        model.addAttribute("user", new LoginDTO()); 
        return "authentication/login"; 
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDTO loginDTO, Model model, HttpSession session) {
        Optional<Admin> adminOptional = adminRepository.findByEmail(loginDTO.getEmail());
        Optional<Patient> patientOptional = patientRepository.findByEmail(loginDTO.getEmail());
        Optional<Doctor> doctorOptional = doctorRepository.findByEmail(loginDTO.getEmail()); // Căutăm și doctorul

        if (adminOptional.isPresent() && adminOptional.get().getPassword().equals(loginDTO.getPassword())) {
            session.setAttribute("user", adminOptional.get()); 
            return "redirect:/home"; 
        }
        
        if (patientOptional.isPresent() && patientOptional.get().getPassword().equals(loginDTO.getPassword())) {
            session.setAttribute("user", patientOptional.get());
            return "redirect:/home"; 
        }

        if (doctorOptional.isPresent() && doctorOptional.get().getPassword().equals(loginDTO.getPassword())) {
            Doctor doctor = doctorOptional.get(); 
            session.setAttribute("user", doctor); 

            session.setAttribute("doctorSpecialty", doctor.getSpecialty());
            session.setAttribute("doctorSalary", doctor.getSalary());

            return "redirect:/home"; 
        }

        model.addAttribute("message", "Email sau parolă incorecte!"); 
        return "authentication/login"; 
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/login"; 
    }
}
