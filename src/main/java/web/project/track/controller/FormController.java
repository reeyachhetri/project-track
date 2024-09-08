package web.project.track.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FormController {
    @GetMapping("home")
    public String teacherHome(Model model){
        return "teacher/home";
    }

    @PostMapping("teacher/add")
    public String addStudent(String studentName, String studentId, String group, String role, Model model) {

        model.addAttribute("message", "Student added successfully!");

        return "teacher/home";
    }
}