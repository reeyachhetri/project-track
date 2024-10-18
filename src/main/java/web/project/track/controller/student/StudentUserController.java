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

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentUserController {

    @Autowired
    private StudentUserService studentUserService;

    @Autowired
    private DailyLogService dailyLogService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "student/login"; // Thymeleaf template for login
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Student student = studentUserService.authenticate(username, password);
        if (student != null) {
            model.addAttribute("username", student.getUsername());
            model.addAttribute("groupId", student.getGroups()); // Assuming groups is the group ID
            model.addAttribute("studentId", student.getId()); // Assuming you have a method to get the student ID

            // Fetch logs and add them to the model
            List<DailyLog> logs = dailyLogService.getLogsByStudentId(student.getId());
            model.addAttribute("logs", logs);

            return "student/dashboard"; // Redirect to the student dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "student/login"; // Return to login with error message
        }
    }

    @PostMapping("/student/submitLog")
    public String submitLog(@RequestParam int studentId, @RequestParam String content, Model model) {
        DailyLog dailyLog = new DailyLog();
        dailyLog.setStudentId(studentId);
        dailyLog.setLogDate(LocalDate.now().toString()); // Set today's date
        dailyLog.setContent(content);
        dailyLogService.saveLog(dailyLog); // Save the log

        // After saving, fetch the updated list of logs
        List<DailyLog> logs = dailyLogService.getLogsByStudentId(studentId);
        model.addAttribute("logs", logs);

        // Optionally, you can also add other model attributes like username and groupId
        return "student/dashboard"; // Redirect to the dashboard
    }

    // You can add a method to view logs if needed, but it's covered in the login method.
}
