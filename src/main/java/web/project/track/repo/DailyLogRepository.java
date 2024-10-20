package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.DailyLog;
import java.util.List;

public interface DailyLogRepository extends JpaRepository<DailyLog, Integer> {
    List<DailyLog> findByStudentId(int studentId);
}
