package web.project.track.controller.student;

import web.project.track.entity.StudentUser;
import web.project.track.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentUserController {

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;  // Inject BCryptPasswordEncoder

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("studentUser", new StudentUser());
        return "student/register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("studentUser") StudentUser studentUser) {
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(studentUser.getPassword());
        studentUserService.registerStudent(studentUser.getUsername(), encodedPassword, studentUser.getName());
        return "redirect:/student/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "student/login";  // Updated path to avoid conflict with teacher login
    }

    @PostMapping("/login")
    public String loginStudent(@RequestParam String username, @RequestParam String password, Model model) {
        StudentUser studentUser = studentUserService.findByUsername(username);
        if (studentUser != null && passwordEncoder.matches(password, studentUser.getPassword())) {
            // Successful login
            return "redirect:/student/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "student/login"; // Ensure this path is correct
        }
    }


    @GetMapping("/dashboard")
    public String showDashboard() {
        return "student/dashboard";  // Updated path to avoid conflict with teacher dashboard
    }
}
