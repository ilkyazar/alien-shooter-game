package com.example.server.api;

import com.example.server.model.Leaderboard;
import com.example.server.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the Leaderboard Controller. The requests sent from REST web service are handled in controller methods
 * by calling the LeaderboardService methods. After the computation is made in LeaderboardService class, the value
 * is wrapped into a ResponseEntity and returned.
 */
@RequestMapping("api/leaderboard")
@RestController
public class LeaderboardController {
    @Autowired
    private LeaderboardService leaderboardService;

    /**
     * Sends a get request to get all the Leaderboard entries.
     * @return A response entity contains the list of leaderboards.
     */
    @GetMapping("/getAllLeaderboards")
    public ResponseEntity<List<Leaderboard>> getAllLeaderboards(){
        return ResponseEntity.ok().body(this.leaderboardService.getAllLeaderboards());
    }

    /**
     * Sends a get request to get the Leaderboard entries only from the last week.
     * @return A response entity contains the list of leaderboards.
     */
    @GetMapping("/leaderboardweekly")
    public ResponseEntity<List<Leaderboard>> getWeeklyLeaderboard() {
        return ResponseEntity.ok().body(this.leaderboardService.getWeeklyLeaderboard());
    }

    /**
     * Sends a get request to get the Leaderboard entries only from the last month.
     * @return A response entity contains the list of leaderboards.
     */
    @GetMapping("/leaderboardmonthly")
    public ResponseEntity<List<Leaderboard>> getMonthlyLeaderboard() {
        return ResponseEntity.ok().body(this.leaderboardService.getMonthlyLeaderboard());
    }

    /**
     * Inserts a Leaderboard entry to the Leaderboard table with a post request.
     * @param entry A Leaderboard object.
     * @return the inserted Leaderboard entry.
     */
    @PostMapping("/addLeaderboardEntry")
    public ResponseEntity<Leaderboard> addLeaderboardEntry(@RequestBody Leaderboard entry) {
        return ResponseEntity.ok().body(this.leaderboardService.addLeaderboardEntry(entry));
    }

}