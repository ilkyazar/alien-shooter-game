package com.example.server.repository;

import com.example.server.model.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The LeaderboardRepository class performs database operations
 * It is extended from JpaRepository so it contains methods such as find, save and delete.*/
@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {
}
