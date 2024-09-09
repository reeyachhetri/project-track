package web.project.track.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping("login")
    public String showLoginPage() {
        return "teacher/login";
    }

    @PostMapping("login")
    public String login(String username, String password, Model model) {
        if ("teacher".equals(username) && "teacher".equals(password)) {
            return "redirect:/teacher/home";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "login";
        }
    }


}
