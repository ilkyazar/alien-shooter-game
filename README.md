This a simple 2D shooter game:

### IndiGO!

-------------------------------------------------
**Are you a gamer?**

Check out the User Manual from here >>> [USER_MANUAL.md][https://github.com/ilkyazar/alien-shooter-game/blob/master/USER_MANUAL.md]

--------------------------------------------------
There is registration and authentication functionality. Players can create accounts
using a unique username and sign in with their account information to access the game.

The game includes leaderboards. Players are listed by their scores in the game. Leaderboards for the most recent week (last 7 days) and the most recent month (last 30 days) are accessible. 

The game server implementation is under the [server](https://github.com/ilkyazar/alien-shooter-game/tree/master/server) folder. Also, a database is designed according to the requirements which is communicating with the game server. Database schema can be seen in [DB_schema.pdf](https://github.com/ilkyazar/alien-shooter-game/blob/master/server/DB_schema.pdf) and also in [DB_schema.png](https://github.com/ilkyazar/alien-shooter-game/blob/master/server/DB_schema.png).

The client implementation is under the [client](https://github.com/ilkyazar/alien-shooter-game/tree/master/client) folder.

For the multiplayer level, a multiplayer-server is implemented. It can be seen under the [multiplayer-server](https://github.com/ilkyazar/alien-shooter-game/tree/master/multiplayer-server) folder. Multiplayer server handles sessions of two clients (players).

Unit tests are provided under [PlayerTest](https://github.com/ilkyazar/alien-shooter-game/tree/master/server/src/test/java/com/example/playerTest) for Player and [LeaderboardTest](https://github.com/ilkyazar/alien-shooter-game/tree/master/server/src/test/java/com/example/leaderboardTest) for Leaderboard. 

Postman collection is provided under [group1.postman_collection.json](https://github.com/ilkyazar/alien-shooter-game/tree/master/server/group1.postman_collection.json).

Swagger documentation can be seen in:

http://localhost:8080/swagger-ui.html#/

JSON Response:

http://localhost:8080/v2/api-docs



For building the executables, please run: `bash build.sh` in the main directory.

The executable files for [server](https://github.com/ilkyazar/alien-shooter-game/blob/master/executables/server_group1.war), [client](https://github.com/ilkyazar/alien-shooter-game/blob/master/executables/client_group1.jar) and [multiplayer-server](https://github.com/ilkyazar/alien-shooter-game/blob/master/executables/multiplayerserver_group1.jar) will be created and placed under "executables" folder with names servergroup1.war, clientgroup1.jar and multiplayerservergroup1.jar respectively.


In order to play:
* First execute servergroup1.war that connects to the database and handles the web services API.
* Secondly, execute multiplayerservergroup1.jar that handles the communication between two players for multiplayer level. This server opens a new session for each pair of players.
* Finally run clientgroup1.jar to start the game. 


All in all, it will be:

bash build.sh

cd executables

java -jar server_group1.war

java -jar multiplayerserver_group1.jar

java -jar client_group1.jar

---------------------------------------

See [Wiki page][https://github.com/ilkyazar/alien-shooter-game/wiki/indiGO!---Alien-Shooter-Game] for more.

---------------------------------------

**AUTHORS:**

Dilsad Akkoyun

Ilkyaz Arabaci

---------------------------------------

**TECHNOLOGIES & TOOLS:**

JavaFX

MariaDB

Spring Boot

Apache Tomcat

[https://github.com/ilkyazar/alien-shooter-game/wiki/Term+Project+-+Group+1]: http://144.122.71.144:8080/ilkyaz.arabaci/group1/wiki/Term+Project+-+Group+1

[https://github.com/ilkyazar/alien-shooter-game/USER_MANUAL.md]: https://github.com/ilkyazar/alien-shooter-game/blob/master/USER_MANUAL.md
