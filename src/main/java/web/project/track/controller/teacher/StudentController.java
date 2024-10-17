package web.project.track.controller.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import web.project.track.entity.Student;
import web.project.track.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class StudentController {

    @Autowired
    private StudentService studentService; // Update service type if needed

    @GetMapping("/home")
    public String homePage(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("students", studentList);
        return "teacher/home"; // Thymeleaf template for home
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "teacher/add"; // Thymeleaf template for add
    }

    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teacher/add";
        }
        studentService.addStudent(student);
        return "redirect:/teacher/home"; // Redirect to home after addition
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "teacher/edit"; // Thymeleaf template for edit
        } else {
            return "redirect:/teacher/home";
        }
    }

    @PostMapping("/updatestudent")
    public String updateStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teacher/edit"; // Return to edit if there are errors
        }
        studentService.updateStudent(student);
        return "redirect:/teacher/home"; // Redirect to home after update
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "redirect:/teacher/home"; // Redirect after deletion
    }
}
