//package web.project.track.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/")
//
//public class FormController {
//    @GetMapping("teacher/add")
//    public String addStudentForm(Model model){
//        return "teacher/add";
//    }
//
//    @PostMapping("teacher/add")
//    public String addStudent(String studentName, String studentId, String group, String role, RedirectAttributes redirectAttributes) {
//        // Add success message to redirect attributes
//        redirectAttributes.addFlashAttribute("message", "Student added successfully!");
//
//        return "redirect:/teacher/home";
//    }
//
//    @GetMapping("teacher/home")
//    public String teacherHome(Model model) {
//        return "teacher/home";
//    }
//}
