package web.project.track.controller.teacher;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.project.track.entity.Student;

@Controller
@RequestMapping("/teacher")
public class StudentController {

    private List<Student> studentList = new ArrayList<>();
    private int idCounter = 1;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("students", studentList);
        return "teacher/home";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "teacher/add";
    }

    @PostMapping("/addstudent")
    public String addStudent(Student student) {
        student.setId(idCounter++);
        studentList.add(student);
        return "redirect:/teacher/home";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                model.addAttribute("student", student);
                break;
            }
        }
        return "teacher/edit";
    }

    @PostMapping("/updatestudent")
    public String updateStudent(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == student.getId()) {
                studentList.set(i, student);
                break;
            }
        }
        return "redirect:/teacher/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentList.removeIf(student -> student.getId() == id);
        return "redirect:/teacher/home";
    }
}
