package web.project.track.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.project.track.entity.Teacher; // Import your Teacher entity
import web.project.track.service.TeacherService; // Import your TeacherService

@Controller
@RequestMapping("/teacher")
public class LoginController {

    @Autowired
    private TeacherService teacherService; // Inject the TeacherService

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    // Display login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "teacher/login"; // Thymeleaf template for login
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginTeacher(@RequestParam String username, @RequestParam String password, Model model) {
        Teacher teacher = teacherService.findByUsername(username); // Fetch user from the database

        if (teacher != null && passwordEncoder.matches(password, teacher.getPassword())) {
            return "redirect:/teacher/home"; // Redirect to home page on successful login
        } else {
            model.addAttribute("error", "Invalid username or password"); // Error message
            return "teacher/login"; // Return to login page on failure
        }
    }

    // Display registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("teacher", new Teacher()); // Add a new Teacher object to the model
        return "teacher/register"; // Thymeleaf template for registration
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerTeacher(@ModelAttribute("teacher") Teacher teacher) {
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword); // Set the encoded password
        teacherService.registerTeacher(teacher); // Save the teacher in the database
        return "redirect:/teacher/login"; // Redirect to login page after registration
    }

    // Display home page after successful login
    @GetMapping("/teacher/home")
    public String showHomePage() {
        return "teacher/home"; // Thymeleaf template for home
    }
}
