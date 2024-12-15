package ro.digitalnation.authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ro.digitalnation.entities.Admin;
import ro.digitalnation.entities.Patient;
import ro.digitalnation.entities.repositories.AdminRepository;
import ro.digitalnation.entities.repositories.PatientRepository;

//Adauga  verificare cod admin
// verificare corectitudine parola
// 
@Controller
public class RegisterController {

    private static final String ADMIN_CODE = "4321"; // De adaugat end point pentru schimbare cod admin de catre admini existenti

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO()); 
        return "authentication/register"; 
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterDTO registerDTO, Model model) {

        if (ADMIN_CODE.equals(registerDTO.getCodAdmin())) {
            Admin admin = new Admin();
            admin.setNume(registerDTO.getNume());
            admin.setPrenume(registerDTO.getPrenume());
            admin.setEmail(registerDTO.getEmail());
            admin.setPassword(registerDTO.getPassword()); 
            admin.setPhoneNumber(registerDTO.getPhoneNumber());
            System.out.println("IN ADMIN");
            adminRepository.save(admin);
            model.addAttribute("message", "Admin înregistrat cu succes!");
        } else {
            Patient patient = new Patient();
            patient.setNume(registerDTO.getNume());
            patient.setPrenume(registerDTO.getPrenume());
            patient.setEmail(registerDTO.getEmail());
            patient.setPassword(registerDTO.getPassword()); // Recomandat: criptează parola!
            patient.setPhoneNumber(registerDTO.getPhoneNumber());
           
            patientRepository.save(patient);
            model.addAttribute("message", "Pacient înregistrat cu succes!");
        }

        return "redirect:/login"; 
    }
}
