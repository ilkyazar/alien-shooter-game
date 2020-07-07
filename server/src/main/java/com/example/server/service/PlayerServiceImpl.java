package com.example.server.service;

import com.example.server.model.Player;
import com.example.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    //@PersistenceContext
    //private EntityManager entityManager;

    /**
     * Gets a player object, encodes the password string using apache.commons.codec library.
     * Saves the player to repository.
     * @return saved player object*/
    @Override
    public Player signupPlayer(Player player) {

        String passwordToEncode = player.getPassword();
        Base64 base64 = new Base64();
        String encodedPassword = new String(base64.encode(passwordToEncode.getBytes()));

        player.setPassword(encodedPassword);
        return playerRepository.save(player);
    }

    /**
     * Get all player entries from the database.
     * @return list of players*/
    @Override
    public List<Player> getAllPlayers() {
        return this.playerRepository.findAll();
    }

    /**
     * Finds the player given by the id. Creates the new encoded password and
     * updates username and password of that player.
     * @return updated player object.*/
    @Override
    public Player updatePlayer(Player player){

        Optional<Player> playerDb = this.playerRepository.findById(player.getPlayerId());
        Player playerUpdate = playerDb.get();

        playerUpdate.setUsername(player.getUsername());

        String passwordToEncode = player.getPassword();
        Base64 base64 = new Base64();
        String encodedPassword = new String(base64.encode(passwordToEncode.getBytes()));

        playerUpdate.setPassword(encodedPassword);
        return this.playerRepository.save(playerUpdate);
    }

    /**
     * Finds the player by given id and deletes from Player Repository.
     * @return deleted player object.*/
    @Override
    public Player deletePlayer(Integer id) {
        Optional<Player> playerDb = this.playerRepository.findById(id);
        Player player = playerDb.get();

        this.playerRepository.delete(player);
        return player;
    }

    /**
     * Gets a username string, iterates through list of all players and finds the
     * player object with the given username.
     * @return a player object */
    @Override
    public Player findPlayer(String username) {

        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);

        for(Player player : players) {
            if(player.getUsername().equals(username)){
                return player;
            }
        }
        return null;
    }

    /**
     * Player with the given id is found in the repository
     * and its username is returned.
     */

    @Override
    public String getNamebyId(Integer id){
        Optional<Player> playerDb = this.playerRepository.findById(id);
        Player player = playerDb.get();
        return player.getUsername();
    }

    /**
     * Gets a username and returns true if there is a player with that username.
     */
    @Override
    public boolean checkPlayer(String username) {

        List<Player> players = new ArrayList<>();
        playerRepository.findAll().forEach(players::add);

        for(Player player : players) {
            if(player.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }


    /**
     * Note:
     * Here we just need username and password for authentication.
     * But in controller (API), there can be one @RequestBody for request, so they should be wrapped in an object.
     * Gets username and finds the encoded password from database. Decodes the password and compares with the given password.
     * @return true if the passwords match.
     * */
    @Override
    public boolean loginPlayer(Player player){

        if(checkPlayer(player.getUsername())){
            Player getPlayer = this.findPlayer(player.getUsername());
        
            String encodedPassword = getPlayer.getPassword();
            Base64 base64 = new Base64();

            String decodedPassword = new String(base64.decode(encodedPassword.getBytes()));

            if(decodedPassword.equals(player.getPassword())){
                return true;
            }
        }
        
        return false;
    }
}
