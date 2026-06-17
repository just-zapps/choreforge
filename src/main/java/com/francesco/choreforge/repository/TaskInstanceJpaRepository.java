package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.TaskInstance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskInstanceJpaRepository extends JpaRepository<TaskInstance, Long> {

    List<TaskInstance> findByDate(LocalDate date);
    List<TaskInstance> findByAssignedTo_Id(Long playerId);
    List<TaskInstance> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
}
