package ro.digitalnation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import ro.digitalnation.entities.Admin;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.Patient;
@Controller
public class HomePageController {
	@GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        Object user = session.getAttribute("user");
        
        if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            model.addAttribute("doctor", doctor);
            model.addAttribute("role", "doctor");
        } else if (user instanceof Admin) {
        	Admin admin=(Admin) user;
        	model.addAttribute("admin", admin); 
            model.addAttribute("role", "admin");
            
        } else if (user instanceof Patient) {
            Patient patient = (Patient) user;
            model.addAttribute("patient", patient);
            model.addAttribute("role", "patient");
        } else {
            return "redirect:/login";
        }
        
        return "home";
    }


}
