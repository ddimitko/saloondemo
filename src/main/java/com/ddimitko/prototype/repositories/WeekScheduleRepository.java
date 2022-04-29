package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.WeekSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekScheduleRepository extends JpaRepository<WeekSchedule, Long> {
}
