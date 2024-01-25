package de.tum.cit.ase.maze;

import de.tum.cit.ase.maze.characters.Enemy;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides static methods for various operations such as reading map data from files,
 * checking movements and positions of characters within the game, and determining collisions with game elements.
 * Methods in this class are used to interact with game elements like walls, doors, and enemies.
 */
public class Utils {

    /**
     * Reads a map file and constructs a map representation from it.
     * The map file is expected to have lines with coordinates and values separated by "=".
     * Each line represents a key-value pair where the key is a list of two integers (x, y coordinates)
     * and the value is an integer.
     *
     * @param filePath The path of the file to be read.
     * @return A map where each key is a list of two integers representing coordinates,
     *         and the value is an integer associated with these coordinates.
     */
    public static Map<List<Integer>, Integer> readMap (String filePath) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        try{
            Path path = Paths.get(filePath);
            List<String> lines  = Files.readAllLines(path);
            for (String line : lines)
            {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String[] xy = parts[0].split(",");
                    int x = Integer.parseInt(xy[0].trim());
                    int y = Integer.parseInt(xy[1].trim());
                    int value = Integer.parseInt(parts[1].trim());
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(x);
                    coordinates.add(y);
                    map.put(coordinates, value);
                }else {
                    System.err.println("Invalid Map");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return (map);
    }

    /**
     * Determines if a character can move in a specified direction based on the current game state.
     * The method checks if the new position after moving would collide with walls, doors, or other characters.
     * It also considers whether the character has a key to open doors.
     *
     * @param characterX The current x-coordinate of the character.
     * @param characterY The current y-coordinate of the character.
     * @param direction The direction in which the character intends to move.
     * @param mapLoader An instance of MapLoader containing the current map layout.
     * @param hasKey Boolean indicating whether the character has a key.
     * @return True if the character can move in the specified direction without colliding, false otherwise.
     */
    public static boolean canCharacterMove(float characterX, float characterY, Direction direction, MapLoader mapLoader, boolean hasKey) {
        if (Math.abs(characterX -  mapLoader.getPlayer_x()) < 0.5  && Math.abs(characterY -  mapLoader.getPlayer_y()) < 0.5 && direction != Direction.RIGHT)
            return false;
        else if (isAtCoordinate(characterX + 0.2f, characterY, mapLoader.getWallCoordinates()) && direction == Direction.RIGHT) {
            return false;
        } else if (isAtCoordinate(characterX, characterY + 0.2f, mapLoader.getWallCoordinates()) && direction == Direction.UP) {
            return false;
        } else if (isAtCoordinate(characterX - 0.3f, characterY, mapLoader.getWallCoordinates()) && direction == Direction.LEFT) {
            return false;
        } else if (isAtCoordinate(characterX, characterY - 0.3f, mapLoader.getWallCoordinates()) && direction == Direction.DOWN) {
            return false;
        } else if (isAtCoordinate(characterX + 0.2f, characterY, mapLoader.getDoorCoordinates()) && direction == Direction.RIGHT && !hasKey) {
            return false;
        } else if (isAtCoordinate(characterX, characterY + 0.2f, mapLoader.getDoorCoordinates()) && direction == Direction.UP && !hasKey) {
            return false;
        } else if (isAtCoordinate(characterX - 0.3f, characterY, mapLoader.getDoorCoordinates()) && direction == Direction.LEFT && !hasKey) {
            return false;
        } else if (isAtCoordinate(characterX, characterY - 0.3f, mapLoader.getDoorCoordinates()) && direction == Direction.DOWN && !hasKey) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the specified coordinates correspond to a specified game element in the maze.
     * The method compares the given coordinates with a list of coordinates for the specified element.
     * The elements can be walls, doors, obstacles, or hearts.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @param coordinates A list of coordinates representing the positions of the specified game element.
     * @return True if the coordinates correspond to the specified game element, false otherwise.
     */
    public static boolean isAtCoordinate(float x, float y, List<List<Integer>> coordinates) {
        final float tolerance = 0.5f;
        for (List<Integer> coordinate : coordinates) {
            float wallX = coordinate.get(0);
            float wallY = coordinate.get(1);
            if (Math.abs(x - wallX) < tolerance && Math.abs(y - wallY) < tolerance) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified coordinates correspond to a heart in the game.
     * A heart represents a point where a player can regain health.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @param emptySpaceXY A list of integer coordinates representing the position of the heart.
     * @return True if the coordinates correspond to a heart, false otherwise.
     */
    public static boolean isHeart(float x, float y, List<Integer> emptySpaceXY){
        final float tolerance = 0.5f;

        float emptySpaceX = emptySpaceXY.get(0);
        float emptySpaceY = emptySpaceXY.get(1);
        if (Math.abs(x - emptySpaceX) < tolerance && Math.abs(y - emptySpaceY) < tolerance) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified coordinates correspond to an enemy's position in the game.
     * This method iterates through a list of enemies and checks if any enemy is at the given coordinates.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @param enemies A list of Enemy objects representing the enemies in the game.
     * @return True if the coordinates correspond to an enemy's position, false otherwise.
     */
    public static boolean isEnemy(float x, float y, @NotNull List<Enemy> enemies) {
        final float tolerance = 0.5f;
        for (Enemy enemy: enemies)
        {
            float enemyX = enemy.getX();
            float enemyY = enemy.getY();
            if (Math.abs(x - enemyX) < tolerance && Math.abs(y - enemyY) < tolerance) {
                return true;
            }
        }
        return false;
    }
}
