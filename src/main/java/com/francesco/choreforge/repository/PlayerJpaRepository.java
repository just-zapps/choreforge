package com.francesco.choreforge.repository;

import com.francesco.choreforge.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
}
