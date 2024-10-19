package web.project.track.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.project.track.entity.DailyLog;
import web.project.track.entity.Student;
import web.project.track.service.DailyLogService;
import web.project.track.service.StudentUserService;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentUserController {

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private DailyLogService dailyLogService;

    // Show the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "student/login"; // Thymeleaf template for login
    }

    // Handle login form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Student student = studentUserService.authenticate(username, password);
        if (student != null) {
            session.setAttribute("loggedInStudent", student); // Store the student in session

            model.addAttribute("username", student.getUsername());
            model.addAttribute("groupId", student.getGroups()); // Assuming groups is the group ID
            model.addAttribute("studentId", student.getId()); // Assuming you have a method to get the student ID

            // Fetch logs and add them to the model
            List<DailyLog> logs = dailyLogService.getLogsByStudentId(student.getId());
            model.addAttribute("logs", logs);

            return "student/dashboard"; // Return to the student dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "student/login"; // Return to login with error message
        }
    }

    // Handle daily log submission
    @PostMapping("/submitDailyLog")
    public String submitLog(@RequestParam int studentId, @RequestParam String content, HttpSession session, Model model) {
        Student loggedInStudent = (Student) session.getAttribute("loggedInStudent");

        // Ensure the student is logged in and the session is valid
        if (loggedInStudent == null || loggedInStudent.getId() != studentId) {
            return "redirect:/student/login"; // Redirect to login if session is invalid
        }

        // Create and save the daily log
        DailyLog dailyLog = new DailyLog();
        dailyLog.setStudentId(studentId);
        dailyLog.setLogDate(LocalDate.now().toString()); // Set today's date
        dailyLog.setContent(content);
        dailyLogService.saveLog(dailyLog); // Save the log

        // Fetch the updated list of logs and update the model
        List<DailyLog> logs = dailyLogService.getLogsByStudentId(studentId);
        model.addAttribute("logs", logs);
        model.addAttribute("username", loggedInStudent.getUsername());
        model.addAttribute("groupId", loggedInStudent.getGroups());
        model.addAttribute("studentId", loggedInStudent.getId());

        return "student/dashboard"; // Return to the dashboard
    }
}
