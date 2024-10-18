package web.project.track.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.project.track.entity.DailyLog;
import web.project.track.repo.DailyLogRepository;
import web.project.track.service.DailyLogService;

import java.util.List;

@Service
public class DailyLogServiceImpl implements DailyLogService {

    @Autowired
    private DailyLogRepository dailyLogRepository;

    @Override
    public void saveLog(DailyLog dailyLog) {
        dailyLogRepository.save(dailyLog);
    }

    @Override
    public List<DailyLog> getLogsByStudentId(int studentId) {
        return dailyLogRepository.findByStudentId(studentId); // This should be implemented in the repository
    }
}
