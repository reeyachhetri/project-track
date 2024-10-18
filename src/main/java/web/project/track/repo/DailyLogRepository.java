package web.project.track.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import web.project.track.entity.DailyLog;

public interface DailyLogRepository extends JpaRepository<DailyLog, Integer> {
    // You can add custom query methods if needed
}
