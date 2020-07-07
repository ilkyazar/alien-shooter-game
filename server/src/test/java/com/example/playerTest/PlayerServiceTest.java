/*
package com.example.playerTest;

import com.example.server.ServerApplication;
import com.example.server.model.Player;
import com.example.server.service.PlayerService;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("testPlayerService")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlayerServiceTest {
    
    private PlayerService mock = org.mockito.Mockito.mock(PlayerService.class);

    @Autowired
    private PlayerService playerService;

    private Player newPlayer = new Player(0, "testplayer1", "pass1");
    private Player newPlayer2 = new Player(1, "testplayer2", "pass2");

    @Test
    public void aPlayerAddTest() {
        Mockito.when(mock.signupPlayer(newPlayer)).thenReturn(newPlayer);
        Player responseNewPlayer = playerService.signupPlayer(newPlayer);
        assertEquals(newPlayer, responseNewPlayer);
    }

    @Test
    public void bAuthenticatePlayerTest(){
        Mockito.when(mock.loginPlayer(newPlayer)).thenReturn(true);
        boolean canLogin = playerService.loginPlayer(newPlayer);
        Assert.assertEquals(true, canLogin);
    }

    @Test
    public void cAuthenticateWrongPassTest(){
        Player wrongPlayer = new Player(0, newPlayer.getUsername(), "wrongPassword");
        Mockito.when(mock.loginPlayer(wrongPlayer)).thenReturn(false);
        boolean canLogin = playerService.loginPlayer(wrongPlayer);
        Assert.assertEquals(false, canLogin);
    }

    @Test
    public void dFindNotExistingPlayerTest(){
        Mockito.when(mock.findPlayer(newPlayer2.getUsername())).thenReturn(null);
        Player player = playerService.findPlayer(newPlayer2.getUsername());
        Assert.assertEquals(null, player);
    }

    @Test
    public void eFindExistingPlayerTest(){                
        Mockito.when(mock.findPlayer(newPlayer.getUsername())).thenReturn(newPlayer);
        Player responsePlayer = playerService.findPlayer(newPlayer.getUsername());
        Assert.assertEquals(newPlayer.getUsername(), responsePlayer.getUsername());
    }

    @Test
    public void fDeletePlayerTest(){
        Player getPlayer = playerService.findPlayer(newPlayer.getUsername());

        Mockito.when(mock.deletePlayer(getPlayer.getPlayerId())).thenReturn(getPlayer);
        Player responsePlayer = playerService.deletePlayer(getPlayer.getPlayerId());
        Assert.assertEquals(getPlayer.getUsername(),responsePlayer.getUsername());
    }

    @Test
    public void gDeleteNonExistingPlayerTest(){
        doThrow(new EmptyResultDataAccessException(1)).when(mock).deletePlayer(newPlayer.getPlayerId());
    }

    @Test
    public void hUpdatePlayerTest(){
        playerService.signupPlayer(newPlayer2);
        int newPlayerId = playerService.findPlayer(newPlayer2.getUsername()).getPlayerId();
        Player newPlayer3 = new Player(newPlayerId, "testplayer3", "pass3");

        Mockito.when(mock.updatePlayer(newPlayer3)).thenReturn(newPlayer3);
        Player responsePlayer = playerService.updatePlayer(newPlayer3);
        playerService.deletePlayer(newPlayer3.getPlayerId());
        Assert.assertEquals(newPlayer3.getUsername(), responsePlayer.getUsername());
    }


    @Test
    public void iGetAllPlayersTest() {
        List<Player> allPlayers = playerService.getAllPlayers();
        Mockito.when(mock.getAllPlayers()).thenReturn(allPlayers);
        Assert.assertEquals(allPlayers.size(),playerService.getAllPlayers().size());
    }

}
*/