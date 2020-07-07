package com.example.client;

import com.example.client.constants.DBConstants;
import com.example.client.model.LeaderboardEntry;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class provides the communication with the rest API of the server.
 * Each function connects to server url and sends a request to API.
 * Results are used for signup, login and leaderboard operations.
 */

public class WebService {

    public static boolean canUserLogin(String username, String password) {
        String url = DBConstants.webServiceURL + DBConstants.playerAPI + "/login";
        String json = "{ \"id\": \"" + 0 + "\", \"username\": \"" + username + "\", \"password\": \""+ password +"\" }";

        String loginResult = connectDB("POST", url, json);
        return loginResult.equals("true");
    }

    public static boolean isUsernameAvailable(String username){
        String url = DBConstants.webServiceURL + DBConstants.playerAPI + "/checkPlayer/" + username;
        String usernameResult = connectDB("GET", url, null);
        System.out.println(usernameResult);
        return usernameResult.equals("false");
    }

    public static void registerUser(String username, String password){
        String url = DBConstants.webServiceURL + DBConstants.playerAPI + "/signup";
        String json = "{ \"id\": \"" + 0 + "\", \"username\": \"" + username + "\", \"password\": \""+ password +"\" }";
        connectDB("POST", url, json);
    }

    public static int getIdbyUsername(String username){
        String url = DBConstants.webServiceURL + DBConstants.playerAPI + "/find/" + username;
        String result = connectDB("GET", url, null);
        JSONObject jsonObj = new JSONObject(result);
        return jsonObj.getInt("playerId");

    }

    public static String getNamebyId(int id){
        String url = DBConstants.webServiceURL + DBConstants.playerAPI + "/getNamebyId/" + id;
        String result = connectDB("GET", url, null);
        return result;
    }


    public static List<LeaderboardEntry> getWeeklyLeaderboard(){
        String url = DBConstants.webServiceURL + DBConstants.leaderboardAPI + "/leaderboardweekly";
        String lbResult = connectDB("GET", url, null);
        return parseLeaderboard(lbResult);

    }

    public static List<LeaderboardEntry> getMonthlyLeaderboard(){
        String url = DBConstants.webServiceURL + DBConstants.leaderboardAPI + "/leaderboardmonthly";
        String lbResult = connectDB("GET", url, null);
        return parseLeaderboard(lbResult);
    }

    public static List<LeaderboardEntry> getAllLeaderboard(){
        String url = DBConstants.webServiceURL + DBConstants.leaderboardAPI + "/getAllLeaderboards";
        String lbResult = connectDB("GET", url, null);
        return parseLeaderboard(lbResult);
    }

    public static List<LeaderboardEntry> parseLeaderboard(String lbResult) {
        List<LeaderboardEntry> entries = new ArrayList<>();

        try{
            JSONArray jsonArray = new JSONArray(lbResult);
            
            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                LeaderboardEntry newEntry = new LeaderboardEntry(
                    jsonObj.getInt("playerId"),
                    getNamebyId(jsonObj.getInt("playerId")),
                    jsonObj.getInt("score"),
                    jsonObj.getString("date"));
                
                entries.add(newEntry);
            }
            return entries;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void addToLeaderboard(int playerId, int score){
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String url = DBConstants.webServiceURL + DBConstants.leaderboardAPI + "/addLeaderboardEntry";
        String json = "{ \"leaderboardId\": \"" + 0 + "\", \"playerId\": \"" + playerId + "\", \"score\": \""+ score +"\", \"date\": \""+ dateStr +"\" }";
        connectDB("POST", url, json);

    }

    private static String connectDB(String method, String urlStr, String jsonData) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod(method);

            if (method.equals("POST")){
                OutputStream os = conn.getOutputStream();
                os.write(jsonData.getBytes(StandardCharsets.UTF_8));
                os.close();
            }

            InputStream inpStream = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(inpStream, "UTF-8");
            inpStream.close();
            conn.disconnect();
            return result;

        } catch (Exception e) {
            System.out.println(e);
            return "Could not connect to the database.";
        }
    }
}