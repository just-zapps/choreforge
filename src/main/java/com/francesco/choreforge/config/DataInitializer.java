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

    @Bean
    CommandLineRunner initPlayers(PlayerJpaRepository playerJPARepository, TaskTemplateJpaRepository taskTemplateJpaRepository) {
        return args -> {
            if (playerJPARepository.count() == 0) {
                playerJPARepository.save(new Player(1L, "Player1"));
                playerJPARepository.save(new Player(2L, "Player2"));
            }
            if (taskTemplateJpaRepository.count() == 0) {
                taskTemplateJpaRepository.save(new TaskTemplate(1L, "Clean toilet", 10, 5));
                taskTemplateJpaRepository.save(new TaskTemplate(2L, "Clean sink", 8, 4));
                taskTemplateJpaRepository.save(new TaskTemplate(3L, "Empty bin", 5, 2));
                taskTemplateJpaRepository.save(new TaskTemplate(4L, "Tidy desk", 6, 3));
                taskTemplateJpaRepository.save(new TaskTemplate(5L, "Take out trash", 4, 2));
                taskTemplateJpaRepository.save(new TaskTemplate(6L, "Wash dinner dishes", 5, 2));
                taskTemplateJpaRepository.save(new TaskTemplate(7L, "Cook dinner", 7, 3));
            }
        };
    }
}
