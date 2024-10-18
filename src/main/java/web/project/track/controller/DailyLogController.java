package web.project.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import web.project.track.entity.DailyLog;
import web.project.track.service.DailyLogService;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes({"studentId", "username", "groupId"}) // Store these attributes in the session
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    // Method to display the dashboard
    @GetMapping("/student/dashboard")
    public String dashboard(Model model) {
        Integer studentId = (Integer) model.getAttribute("studentId");

        if (studentId == null) {
            return "redirect:/student/login"; // Redirect to login if studentId is not in the session
        }

        // Retrieve logs for the specific student
        List<DailyLog> logs = dailyLogService.getLogsByStudentId(studentId);
        model.addAttribute("logs", logs);

        // Model attributes should be available due to @SessionAttributes
        return "student/dashboard"; // Ensure this matches the path to your dashboard template
    }

    // Method to submit a daily log
    @PostMapping("/student/submitLog")
    public String submitLog(@RequestParam String content, Model model) {
        Integer studentId = (Integer) model.getAttribute("studentId");

        if (studentId == null) {
            return "redirect:/student/login"; // Redirect to login if studentId is not in the session
        }

        DailyLog dailyLog = new DailyLog();
        dailyLog.setStudentId(studentId);
        dailyLog.setLogDate(LocalDate.now().toString());
        dailyLog.setContent(content);

        // Save the daily log
        dailyLogService.saveLog(dailyLog);

        // Redirect back to the dashboard after submission
        return "redirect:/student/dashboard"; // Redirect without parameters as studentId is in the session
    }
}
