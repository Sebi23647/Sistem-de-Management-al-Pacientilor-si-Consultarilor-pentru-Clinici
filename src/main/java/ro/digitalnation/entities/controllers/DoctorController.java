package ro.digitalnation.entities.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ro.digitalnation.apoiments.Appointment;
import ro.digitalnation.apoiments.AppointmentRepository;
import ro.digitalnation.entities.Doctor;
import ro.digitalnation.entities.repositories.DoctorRepository;
import ro.digitalnation.entities.repositories.PatientRepository;
import ro.digitalnation.management.Tratament;
import ro.digitalnation.management.TratamentRepository;

import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TratamentRepository tratamentRepository;
    @GetMapping("/doctorPages/addAppointment")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("doctors", doctorRepository.findAll());
        return "doctorPages/addAppointment";
    }

    @PostMapping("/doctorPages/addAppointment")
    public String addAppointment(@RequestParam Long patientId,
                                  @RequestParam Long doctorId,
                                  @RequestParam String appointmentDate,
                                  @RequestParam String status) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patientRepository.findById(patientId).orElse(null));
        appointment.setDoctor(doctorRepository.findById(doctorId).orElse(null));
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus(status);

        appointmentRepository.save(appointment);
        return "redirect:/doctorPages/viewAppointment?id=" + doctorId;
    }

    @GetMapping("/doctorPages/viewAppointment")
    public String viewAppointments(Model model, @RequestParam Long id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor != null) {
            List<Appointment> appointments = doctor.getAppointments();
            model.addAttribute("appointments", appointments);
            model.addAttribute("doctor", doctor);
        }
        return "doctorPages/viewAppointment";
    }
    @GetMapping("/doctorPages/deleteAppointment")
    public String deleteAppointment(@RequestParam Long appointmentId, @RequestParam Long doctorId) {
        appointmentRepository.deleteById(appointmentId);
        return "redirect:/doctorPages/viewAppointment?id=" + doctorId;
    }
    @GetMapping("/doctorPages/editAppointment")
    public String showEditAppointmentForm(@RequestParam Long appointmentId, Model model) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment != null) {
            model.addAttribute("appointment", appointment);
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("doctors", doctorRepository.findAll());
        } else {
            return "errorPage"; 
        }

        return "doctorPages/editAppointment";
    }

    @PostMapping("/doctorPages/editAppointment")
    public String editAppointment(@RequestParam Long appointmentId,
                                   @RequestParam Long patientId,
                                   @RequestParam Long doctorId,
                                   @RequestParam String appointmentDate,
                                   @RequestParam String status) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment != null) {
            appointment.setPatient(patientRepository.findById(patientId).orElse(null));
            appointment.setDoctor(doctorRepository.findById(doctorId).orElse(null));
            appointment.setAppointmentDate(appointmentDate);
            appointment.setStatus(status);

            appointmentRepository.save(appointment);
        }

        return "redirect:/doctorPages/viewAppointment?id=" + doctorId;
    }

    @GetMapping("/doctorPages/assignTreatment")
    public String showAssignTreatmentForm(@RequestParam Long appointmentId, Model model) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment == null) {
            return "errorPage"; 
        }

        model.addAttribute("appointment", appointment);
        return "doctorPages/assignTreatment";
    }
    @PostMapping("/doctorPages/assignTreatment")
    public String assignAndSaveTreatment(@RequestParam Long appointmentId,
                                          @RequestParam String description,
                                          @RequestParam double cost,
                                          @RequestParam String period,
                                          @RequestParam String diagnosis) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);

        if (appointment != null) {
            Tratament treatment = new Tratament(description, cost, period, diagnosis);
            Tratament savedTreatment = tratamentRepository.save(treatment);

            appointment.setTratament(savedTreatment);
            appointmentRepository.save(appointment);
        }

        return "redirect:/doctorPages/viewAppointment?id=" + (appointment != null ? appointment.getDoctor().getId() : "");
    }


}
