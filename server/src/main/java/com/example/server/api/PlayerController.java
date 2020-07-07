package com.example.server.api;

import com.example.server.model.Player;
import com.example.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents the Player Controller. The requests sent from REST web service are handled in controller methods
 * by calling the PlayerService methods. After the computation is made in PlayerService class, the value
 * is wrapped into a ResponseEntity and returned.
 */
@RequestMapping("api/player")
@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    /**
     * Inserts a player to the Player table with a post request. Players have unique usernames and ids.
     * @param a player object.
     * @return the inserted player.
     */
    @PostMapping("/signup")
    public ResponseEntity<Player> signupPlayer(@RequestBody Player player){
        return ResponseEntity.ok().body(this.playerService.signupPlayer(player));
    }

    /**
     * Updates the username and password of the player given by the playerId.
     * @return the updated player object*/
    @PostMapping("/update")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        return ResponseEntity.ok().body(this.playerService.updatePlayer(player));
    }

    /**
     * If the parameter is wrapped into a class, it can be sent in request body because they
     * can be represented as JSON (players for example). But in this method, parameter is
     * a string so it must be passed as a Path Variable.
     * Finds and returns the player with the given username.*/
    @RequestMapping(value = "/find/{username}", method = RequestMethod.GET)
    public ResponseEntity<Player> findPlayer(@PathVariable String username) {
        return ResponseEntity.ok().body(this.playerService.findPlayer(username));
    }

    /**
     * Checks if a player with given username exists.
     */

    @RequestMapping(value = "/checkPlayer/{username}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkPlayer(@PathVariable String username) {
        return ResponseEntity.ok().body(this.playerService.checkPlayer(username));
    }

    /**
     * Gets a player id and returns the username of that id.
     */
    @RequestMapping(value = "/getNamebyId/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getNamebyId(@PathVariable Integer id) {
        return ResponseEntity.ok().body(this.playerService.getNamebyId(id));
    }


    /**
     * Sends a get request to get all the player entries.
     * @return A response entity contains the list of players.
     */
    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<Player>> getAllPlayers(){
        return ResponseEntity.ok().body(this.playerService.getAllPlayers());
    }

    /**
     * This method finds the player with the given ID and deletes from Player table.
     * @return the deleted player.*/
    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Player> deletePlayer(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(this.playerService.deletePlayer(userId));
    }

    /**
     * Checks authentication for the given player.
     * @return true if username and password matches.*/
    @PostMapping("/login")
    public ResponseEntity<Boolean> loginPlayer(@RequestBody Player player){
        return ResponseEntity.ok().body(this.playerService.loginPlayer(player));
    }
}