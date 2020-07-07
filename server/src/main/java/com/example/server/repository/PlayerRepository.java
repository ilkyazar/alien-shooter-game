package com.example.server.repository;

import com.example.server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PlayerRepository class performs the database operations.
 * It is extended from JpaRepository so it contains methods such as find, save and delete.*/
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
