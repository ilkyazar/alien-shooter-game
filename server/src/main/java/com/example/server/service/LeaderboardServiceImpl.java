package com.example.server.service;

import com.example.server.model.Leaderboard;
import com.example.server.repository.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


/**
 * A comparator class to compare two Leaderboard entries. It is used for sorting an array of Leaderboards.
 * The object with higher score is regarded as bigger.
 */
class leaderboardComparator implements Comparator<Leaderboard> {
    @Override
    public int compare(Leaderboard entry1, Leaderboard entry2) {
        return entry2.getScore() - entry1.getScore();
    }
}


@Service
public class LeaderboardServiceImpl implements LeaderboardService {

    /** The LeaderboardRepository object establishes the connection
     * between database and web service. */
    @Autowired
    private LeaderboardRepository leaderboardRepository;

    //@PersistenceContext
    //private EntityManager entityManager;

    /**
     * This function gets all items in Leaderboard table and adds them to an array list.
     * We used the Comparator class to sort them by their scores in ascending order.
     * @return list of all leaderboard entries
     */
    @Override
    public List<Leaderboard> getAllLeaderboards() {

        List<Leaderboard> leaderboard = new ArrayList<>();

        this.leaderboardRepository.findAll().forEach(leaderboard::add);
        Collections.sort(leaderboard, new leaderboardComparator());

        return leaderboard;
    }

    /**
     * This function iterates through all leaderboard entries and checks if the date value is in the interval of
     * today and 7 days before. Today's and last week's dates are calculated by using Date and Calendar libraries.
     * @return list of last weeks leaderboard entries
     */
    @Override
    public List<Leaderboard> getWeeklyLeaderboard(){
        List<Leaderboard> allLeaderboards = this.getAllLeaderboards();
        List<Leaderboard> weeklyLeaderboard = new ArrayList<>();


        //Date todaysDate = new Date("yyyy-MM-dd HH:mm:ss");
        String todaysDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            Date todaysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todaysDateStr);
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, -7);
            Date oneWeekBefore = cal.getTime();


            for(Leaderboard leaderboard : allLeaderboards) {
                String dateStr = leaderboard.getDate();

                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                    if (date.before(todaysDate) && date.after(oneWeekBefore)) {
                        weeklyLeaderboard.add(leaderboard);
                    }
                }
                catch (Exception e) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Date error.");
                    System.out.println("-------------------------------------------------");
                }

            }
        }
        catch (Exception e) {
            return allLeaderboards;
        }

        return weeklyLeaderboard;
    }

    /**
     * This function iterates through all leaderboard entries and checks if the date value is in the interval of
     * today and 30 days before.
     * @return list of last months leaderboard entries
     */
    @Override
    public List<Leaderboard> getMonthlyLeaderboard(){
        List<Leaderboard> allLeaderboards = this.getAllLeaderboards();
        List<Leaderboard> monthlyLeaderboard = new ArrayList<>();


        //Date todaysDate = new Date("yyyy-MM-dd HH:mm:ss");
        String todaysDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            Date todaysDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(todaysDateStr);
            Calendar cal = new GregorianCalendar();
            cal.add(Calendar.DAY_OF_MONTH, -30);
            Date oneMonthBefore = cal.getTime();


            for(Leaderboard leaderboard : allLeaderboards) {
                String dateStr = leaderboard.getDate();

                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                    if (date.before(todaysDate) && date.after(oneMonthBefore)) {
                        monthlyLeaderboard.add(leaderboard);
                    }
                }
                catch (Exception e) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Date error.");
                    System.out.println("-------------------------------------------------");
                }

            }
        }
        catch (Exception e) {
            return allLeaderboards;
        }

        return monthlyLeaderboard;
    }

    /**Gets a leaderboard objects and inserts it to database.
     * @return the saved leaderboard object
     */
    @Override
    public Leaderboard addLeaderboardEntry(Leaderboard entry){
        return leaderboardRepository.save(entry);
    }


}
