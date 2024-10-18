package web.project.track.service;

import web.project.track.entity.DailyLog;

import java.util.List;

public interface DailyLogService {
    void saveLog(DailyLog dailyLog);
    List<DailyLog> getLogsByStudentId(int studentId);
}
