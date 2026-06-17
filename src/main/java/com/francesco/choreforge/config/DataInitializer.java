package com.francesco.choreforge.config;

import com.francesco.choreforge.model.Player;
import com.francesco.choreforge.model.TaskTemplate;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import com.francesco.choreforge.repository.TaskTemplateJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private void saveTemplateIfMissing(TaskTemplateJpaRepository repository,
                                       Long id,
                                       String name,
                                       int points,
                                       int penalty) {
        if (!repository.existsById(id)) {
            repository.save(new TaskTemplate(id, name, points, penalty));
        }
    }

    private void savePlayerIfMissing(PlayerJpaRepository repository,
                                     Long id,
                                     String name) {
        if (!repository.existsById(id)) {
            repository.save(new Player(id, name));
        }
    }

    @Bean
    CommandLineRunner initPlayers(PlayerJpaRepository playerJpaRepository, TaskTemplateJpaRepository taskTemplateJpaRepository) {
        return args -> {

            savePlayerIfMissing(playerJpaRepository, 1L, "Player1");
            savePlayerIfMissing(playerJpaRepository, 2L, "Player2");

            saveTemplateIfMissing(taskTemplateJpaRepository, 1L, "Clean toilet", 10, 5);
            saveTemplateIfMissing(taskTemplateJpaRepository, 2L, "Clean sink", 8, 4);
            saveTemplateIfMissing(taskTemplateJpaRepository, 3L, "Empty bin", 5, 2);
            saveTemplateIfMissing(taskTemplateJpaRepository, 4L, "Tidy desk", 6, 3);
            saveTemplateIfMissing(taskTemplateJpaRepository, 5L, "Take out plastic", 4, 2);
            saveTemplateIfMissing(taskTemplateJpaRepository, 6L, "Wash dishes", 5, 2);

        };
    }
}
