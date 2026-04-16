package com.francesco.choreforge.service;

import com.francesco.choreforge.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    public Player assignNextPlayer(List<Player> players, int playerIndex) {
        return players.get(playerIndex % players.size());
    }
}
