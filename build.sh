mkdir executables
cd client
./mvnw clean package
cd ../server
./mvnw clean package
cd ../multiplayer-server
./mvnw clean package
cd ../
cp ./client/target/client_group1.jar ./executables/
cp ./multiplayer-server/target/multiplayerserver_group1.jar ./executables/
cp ./server/target/server_group1.war ./executables/