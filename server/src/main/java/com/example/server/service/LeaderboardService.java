package com.example.server.service;

import com.example.server.model.Leaderboard;

import java.util.List;

/**
 * This is the interface for LeaderboardService.
 * All the methods are implemented in LeaderboardServiceImpl class.
 */
public interface LeaderboardService {
    List<Leaderboard> getAllLeaderboards();
    List<Leaderboard> getWeeklyLeaderboard();
    List<Leaderboard> getMonthlyLeaderboard();
    Leaderboard addLeaderboardEntry(Leaderboard entry);
}
