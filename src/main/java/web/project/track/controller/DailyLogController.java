package web.project.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.project.track.entity.DailyLog;
import web.project.track.service.DailyLogService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DailyLogController {

    @Autowired
    private DailyLogService dailyLogService;

    @PostMapping("/student/submitLog")
    public String submitLog(@RequestParam int studentId, @RequestParam String content) {
        DailyLog dailyLog = new DailyLog();
        dailyLog.setStudentId(studentId);
        dailyLog.setLogDate(LocalDate.now().toString());
        dailyLog.setContent(content);
        dailyLogService.saveLog(dailyLog);
        return "redirect:/student/dashboard?studentId=" + studentId; // Redirect back to dashboard with studentId
    }

    @GetMapping("/student/dashboard")
    public String dashboard(@RequestParam int studentId, Model model) {
        List<DailyLog> logs = dailyLogService.getLogsByStudentId(studentId);
        model.addAttribute("logs", logs);
        model.addAttribute("studentId", studentId);
        model.addAttribute("username", "Student Name"); // Replace with actual username from session
        model.addAttribute("groupId", "Group ID"); // Replace with actual group ID from session
        return "student/dashboard"; // Ensure this matches the path to your dashboard template
    }

    @GetMapping("/student/logs")
    public String viewLogs(@RequestParam int studentId, Model model) {
        model.addAttribute("logs", dailyLogService.getLogsByStudentId(studentId));
        return "studentLogs"; // Ensure you create a corresponding template for this
    }
}
