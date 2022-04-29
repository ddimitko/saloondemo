package com.ddimitko.prototype.repositories;

import com.ddimitko.prototype.objects.DaySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayScheduleRepository extends JpaRepository<DaySchedule, Long> {

}
