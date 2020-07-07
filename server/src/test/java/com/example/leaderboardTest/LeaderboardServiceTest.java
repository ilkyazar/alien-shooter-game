/*package com.example.leaderboardTest;

import com.example.server.ServerApplication;
import com.example.server.model.Leaderboard;
import com.example.server.service.LeaderboardService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.StringBuffer;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.FieldPosition;
import java.util.GregorianCalendar;


@ActiveProfiles("testLeaderboardService")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeaderboardServiceTest {
    private LeaderboardService mock = org.mockito.Mockito.mock(LeaderboardService.class);

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private PlayerService playerService;

    private Player newPlayer = new Player(0, "testplayer", "testpassword");

    @Test
    public void aAddLeaderboardTest() {
        playerService.signupPlayer(newPlayer);

        int playerId = playerService.findPlayer(newPlayer.getUsername()).getPlayerId();
        Leaderboard newLb1 = new Leaderboard(0, playerId, 100, "2020-03-25 14:49:11");
        Mockito.when(mock.addLeaderboardEntry(newLb1)).thenReturn(newLb1);
        Leaderboard responseLb = leaderboardService.addLeaderboardEntry(newLb1);
        Assert.assertEquals(newLb1, responseLb);
    }

    @Test
    public void bWeeklyLeaderboardTest(){

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, - 3);
        Date dateInWeek = cal.getTime();

        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.format(dateInWeek, stringBuffer, new FieldPosition(0));
        String date = stringBuffer.toString();

        try{
            List<Leaderboard> weekList = leaderboardService.getWeeklyLeaderboard();
            int playerId = playerService.findPlayer(newPlayer.getUsername()).getPlayerId();
            Leaderboard newLb1 = new Leaderboard(0, playerId, 100, date);
            leaderboardService.addLeaderboardEntry(newLb1);

            Assert.assertEquals(weekList.size()+1, leaderboardService.getWeeklyLeaderboard().size());
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    @Test
    public void cMonthlyLeaderboardTest(){

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, - 12);
        Date dateInMonth = cal.getTime();

        StringBuffer stringBuffer = new StringBuffer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.format(dateInMonth, stringBuffer, new FieldPosition(0));
        String date = stringBuffer.toString();

        try{
            List<Leaderboard> monthList = leaderboardService.getMonthlyLeaderboard();
            int playerId = playerService.findPlayer(newPlayer.getUsername()).getPlayerId();
            Leaderboard newLb1 = new Leaderboard(0, playerId, 200, date);
            leaderboardService.addLeaderboardEntry(newLb1);
            List<Leaderboard> responseBoard = leaderboardService.getMonthlyLeaderboard();

            playerService.deletePlayer(playerId);

            Assert.assertEquals(monthList.size()+1, responseBoard.size());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
*/