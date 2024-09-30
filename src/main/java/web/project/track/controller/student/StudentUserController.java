package web.project.track.controller.student;

import web.project.track.entity.StudentUser;
import web.project.track.service.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;  // Inject PasswordEncoder interface
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentUserController {

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder interface instead of BCryptPasswordEncoder

    // Display registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("studentUser", new StudentUser());
        return "student/register";  // Thymeleaf template for registration
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("studentUser") StudentUser studentUser) {
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(studentUser.getPassword());
        studentUserService.registerStudent(studentUser.getUsername(), encodedPassword, studentUser.getName());
        return "redirect:/student/login";  // Redirect to login page after registration
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "student/login";  // Thymeleaf template for login
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginStudent(@RequestParam String username, @RequestParam String password, Model model) {
        StudentUser studentUser = studentUserService.findByUsername(username); // Fetch user from the database


        if (studentUser != null && passwordEncoder.matches(password, studentUser.getPassword())) {
            return "redirect:/student/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");  // Error message
            return "student/login"; // Return to login page on failure
        }
    }

    // Display student dashboard
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "student/dashboard";
    }
}
