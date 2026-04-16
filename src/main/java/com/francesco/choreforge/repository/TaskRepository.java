package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.TaskInstance;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {

    private final Map<Long, TaskInstance> tasks = new HashMap<>();

    public void saveAll(List<TaskInstance> taskList) {
        for (TaskInstance task : taskList) {
            tasks.put(task.getId(), task);
        }
    }

    public Optional<TaskInstance> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<TaskInstance> findAll() {
        return new ArrayList<>(tasks.values());
    }
}
