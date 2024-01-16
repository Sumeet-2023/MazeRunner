package de.tum.cit.ase.maze;

import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.objects.Obstacle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    /**
     * Reading the file and mapping the data into key and values and returning it.
     * @param filePath String containing the filePath of the file to be read.
     * @return Map with a list of two integers [x, y] as key and an integer (value) as value
     */
    public static Map<List<Integer>, Integer> readMap (String filePath)
    {
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

    public static boolean canCharacterMove(float characterX, float characterY, Direction direction, MapLoader mapLoader, boolean hasKey)
    {
        if (Math.abs(characterX -  mapLoader.getPlayer_x()) < 0.5  && Math.abs(characterY -  mapLoader.getPlayer_y()) < 0.5 && direction != Direction.RIGHT)
            return false;
        else if (isWall(characterX + 0.2f, characterY, mapLoader.getWallCoordinates()) && direction == Direction.RIGHT) {
            return false;
        } else if (isWall(characterX, characterY + 0.2f, mapLoader.getWallCoordinates()) && direction == Direction.UP) {
            return false;
        } else if (isWall(characterX - 0.3f, characterY, mapLoader.getWallCoordinates()) && direction == Direction.LEFT) {
            return false;
        } else if (isWall(characterX, characterY - 0.3f, mapLoader.getWallCoordinates()) && direction == Direction.DOWN) {
            return false;
        } else if (isDoor(characterX + 0.2f, characterY, mapLoader.getDoorCoordinates()) && direction == Direction.RIGHT && !hasKey) {
            return false;
        } else if (isDoor(characterX, characterY + 0.2f, mapLoader.getDoorCoordinates()) && direction == Direction.UP && !hasKey) {
            return false;
        } else if (isDoor(characterX - 0.3f, characterY, mapLoader.getDoorCoordinates()) && direction == Direction.LEFT && !hasKey) {
            return false;
        } else if (isDoor(characterX, characterY - 0.3f, mapLoader.getDoorCoordinates()) && direction == Direction.DOWN && !hasKey) {
            return false;
        }
        return true;
    }

    public static boolean isWall(float x, float y, List<List<Integer>> wallCoordinates)
    {
        final float tolerance = 0.5f;
        for (List<Integer> coordinate : wallCoordinates) {
            float wallX = coordinate.get(0);
            float wallY = coordinate.get(1);
            if (Math.abs(x - wallX) < tolerance && Math.abs(y - wallY) < tolerance) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDoor(float x, float y, List<List<Integer>> doorCoordinates)
    {
        final float tolerance = 0.5f;
        for (List<Integer> coordinate : doorCoordinates) {
            float doorX = coordinate.get(0);
            float doorY = coordinate.get(1);
            if (Math.abs(x - doorX) < tolerance && Math.abs(y - doorY) < tolerance) {
                return true;
            }
        }
        return false;
    }
    public static boolean isObstacle(float x, float y, List<List<Integer>> obstacleCoordinates){
        final float tolerance = 0.4f;
        for (List<Integer> coordinate : obstacleCoordinates) {
            float obstacleX = coordinate.get(0);
            float obstacleY = coordinate.get(1);
            if (Math.abs(x - obstacleX) < tolerance && Math.abs(y - obstacleY) < tolerance) {
                return true;
            }
        }
        return false;
    }


    public static boolean isEnemy(float x, float y, List<Enemy> enemies)
    {
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
