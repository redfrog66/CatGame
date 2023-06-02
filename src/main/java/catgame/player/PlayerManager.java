package catgame.player;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class PlayerManager {
    private static final String JSON_FILE_PATH = "players.json";

    /**
     *
     * @param player This method expects a player which it can save into a list we read in from the json,
     *               then write the extended list back into the json.
     */
    public void addPlayer(Player player) {
        // Read the existing player data from JSON file
        List<Player> players = readPlayersFromJson();

        // Add the new player to the list
        players.add(player);

        // Serialize the updated player list to JSON
        String jsonData = serializePlayersToJson(players);

        // Write the JSON data back to the file
        writeJsonToFile(jsonData);
    }
    private List<Player> readPlayersFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read the existing JSON file and deserialize its content
            return objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<Player>>() {
            });
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list if file reading fails
        }
    }
    private String serializePlayersToJson(List<Player> players) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Serialize the player list to JSON
            return objectMapper.writeValueAsString(players);
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
            return ""; // Return an empty string if serialization fails
        }
    }

    private void writeJsonToFile(String jsonData) {
        try {
            // Write the JSON data back to the file
            FileWriter fileWriter = new FileWriter(JSON_FILE_PATH);
            fileWriter.write(jsonData);
            fileWriter.close();
        } catch (IOException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

    /**
     *
     * @return Either returns the very last player on the list, or returns nothing, because the list is empty.
     */
    public Player getLastAddedPlayer() {
        List<Player> players = readPlayersFromJson();
        if (!players.isEmpty()) {
            return players.get(players.size() - 1);
        }
        return null;
    }

    public Player getBestPlayer() {
        //Changing the data to Int
        List<Player> players = readPlayersFromJson();
        for (int i = 0; i < players.size(); i++) {
            players.get(i).steps = toInt(String.valueOf(players.get(i).steps));

        Player bestPlayer = new Player();
        for (int j = 0; j < players.size(); j++) {
            if
        }
        }
        return null;
    }

}
