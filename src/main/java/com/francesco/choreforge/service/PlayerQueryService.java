package com.francesco.choreforge.service;

import com.francesco.choreforge.model.Player;
import com.francesco.choreforge.repository.PlayerJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerQueryService {

    private final PlayerJpaRepository playerJpaRepository;

    public PlayerQueryService(PlayerJpaRepository playerJpaRepository) {
        this.playerJpaRepository = playerJpaRepository;
    }

    public List<Player> findAll() {
        return playerJpaRepository.findAll();
    }
}