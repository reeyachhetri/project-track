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
import web.project.track.service.impl.StudentServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;


    @GetMapping("/home")
    public String homePage(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("students", studentList);
        return "teacher/home";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "teacher/add";
    }


    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teacher/add";
        }
        studentService.addStudent(student);
        return "redirect:/teacher/home";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "teacher/edit";
        } else {
            return "redirect:/teacher/home";
        }
    }


    @PostMapping("/updatestudent")
    public String updateStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teacher/edit";
        }
        studentService.updateStudent(student);
        return "redirect:/teacher/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "redirect:/teacher/home";
    }
}