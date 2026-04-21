package com.francesco.choreforge.config;

import com.francesco.choreforge.model.Player;
import com.francesco.choreforge.repository.PlayerJPARepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initPlayers(PlayerJPARepository playerJPARepository) {
        return args -> {
            if (playerJPARepository.count() == 0) {
                playerJPARepository.save(new Player(1L, "Player1"));
                playerJPARepository.save(new Player(2L, "Player2"));
            }
        };
    }
}
