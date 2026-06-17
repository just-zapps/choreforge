package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.TaskTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTemplateJpaRepository extends JpaRepository<TaskTemplate, Long> {
}
