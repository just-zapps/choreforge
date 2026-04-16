package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.TaskInstance;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Repository
public class TaskRepository {

    private final Map<Long, TaskInstance> tasks = new HashMap<>();
    private Long nextId = 1L;

    public void saveAll(List<TaskInstance> taskList) {
        for (TaskInstance task : taskList) {
            if (!exists(task)) {
                task.setId(nextId++);
                tasks.put(task.getId(), task);
            }
        }
    }

    public Optional<TaskInstance> findById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public List<TaskInstance> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public boolean exists(TaskInstance candidate) {
        return tasks.values().stream().anyMatch(task ->
                task.getDate().equals(candidate.getDate())
                        && task.getTaskTemplate().getId().equals(candidate.getTaskTemplate().getId())
                        && task.getAssignedTo().getId().equals(candidate.getAssignedTo().getId())
                        && java.util.Objects.equals(task.getGroupName(), candidate.getGroupName())
        );
    }

    public List<TaskInstance> findByDate(LocalDate date) {
        return tasks.values().stream()
                .filter(task -> task.getDate().equals(date))
                .toList();
    }

    public List<TaskInstance> findByPlayerId(Long playerId) {
        return tasks.values().stream()
                .filter(task -> task.getAssignedTo().getId().equals(playerId))
                .toList();
    }
}
