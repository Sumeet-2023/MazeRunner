package de.tum.cit.ase.maze;

import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapLoader {
    private Map<List<Integer>, Integer> map;
    private float sinusInput;
    private float max_x;
    private float max_y;
    private  float player_x;
    private float player_y;
    private final float min_x = 0;
    private final float min_y = 0;
    private MazeRunnerGame game;
    private Wall wall;
    private Obstacle obstacles;
    private Enemy enemies;
    private Door doors;
    private Tile tiles;
    private Key key;

    private List<List<Integer>> wallCoordinates;

    public MapLoader(MazeRunnerGame game, float sinusInput) {
        this.game = game;
        this.sinusInput = sinusInput;

        // Select Map
        map = Utils.readMap("maps\\level-1.properties");

        // Wall
        this.wall = new Wall();
        wall.loadHorizontalWall();
        wall.loadVerticalWall();
        wall.loadCornerWall();
        wallCoordinates = new ArrayList<>();
        setWallCoordinates();

        // Obstacles
        this.obstacles = new Obstacle();
        obstacles.loadSpikeAnimation();

        // Enemies
        this.enemies = new Enemy();

        // Doors
        this.doors = new Door();
        doors.loadDoor();

        // Tiles
        this.tiles = new Tile();

        // Key
        this.key = new Key();
        key.loadHeartAnimation();


    }

    public void setPlayerStartingPos() {
        for (List<Integer> coordinates : map.keySet()) {
            if (map.get(coordinates) == 1) {
                player_x = coordinates.get(0);
                player_y = coordinates.get(1);
                break;
            }
        }
    }

    public void setWallCoordinates() {
        for (List<Integer> coordinates : map.keySet()) {
            if (map.get(coordinates) == 0)
            {
                List<Integer> data = new ArrayList<>();
                data.add(coordinates.get(0));
                data.add(coordinates.get(1));
                wallCoordinates.add(data);
            }
        }
    }


    public void setMaxXY(Map<List<Integer>, Integer> map) {
        max_x = 0;
        max_y = 0;
        for (List<Integer> key : map.keySet()) {
            if (key.get(0) > max_x)
                max_x = key.get(0);
            if (key.get(1) > max_y)
                max_y = key.get(1);
        }
    }

    public void loadMap1() {
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++) {
            for (int j = 0; j <= max_y; j++) {
                game.getSpriteBatch().draw(tiles.getTile(), i * 32, j * 32,32,32);
            }
        }
        game.getSpriteBatch().draw(key.getLife().getKeyFrame(sinusInput, true), 8 * 32, 7 * 32, 32, 32);
        for (List<Integer> coordinates : map.keySet()) {
            switch (map.get(coordinates)) {
                case 0:
                    if (coordinates.get(1)==min_y || coordinates.get(1)==max_y) {
                        game.getSpriteBatch().draw(wall.getHorizontalWall(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    } else if ( coordinates.get(0)==max_x ) {
                        wall.getVerticalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getVerticalWall().setSize(32,32);
                        wall.getVerticalWall().translateY(16);
                        wall.getVerticalWall().setRotation(270);
                        wall.getVerticalWall().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==min_x ) {
                        wall.getVerticalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getVerticalWall().setSize(32,32);
                        wall.getVerticalWall().translateX(16);
                        wall.getVerticalWall().setRotation(90);
                        wall.getVerticalWall().draw(game.getSpriteBatch());
                    }
                    else {
                        game.getSpriteBatch().draw(wall.getTree(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    if(coordinates.get(0)==max_x && coordinates.get(1)==max_y || coordinates.get(0)==0 && coordinates.get(1)==max_y){
                        game.getSpriteBatch().draw(wall.getCornerWall(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    if (coordinates.get(0) == 2 && coordinates.get(1) == 4 || coordinates.get(0) == 13 && coordinates.get(1) == 9) {
                        game.getSpriteBatch().draw(wall.getWell(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    break;
                case 1:
                    break;
                case 2:
                    game.getSpriteBatch().draw(doors.getDoor(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    break;
                case 3:
                    if (coordinates.get(0) == 10 && coordinates.get(1) == 5 || coordinates.get(0) == 6 && coordinates.get(1) == 12) {
                        game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    } else {
                        game.getSpriteBatch().draw(obstacles.getFireAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32,32,32);
                    }
                    break;
                case 4:

                    break;
                case 5:
                    game.getSpriteBatch().draw(key.getKey(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);

                    break;
            }
        }
    }


    public Obstacle getObstacles() {
        return obstacles;
    }

    public Enemy getEnemies() {
        return enemies;
    }

    public Door getDoors() {
        return doors;
    }


    public Tile getTiles() {
        return tiles;
    }

    public Key getKey() {
        return key;
    }

    public void setSinusInput(float sinusInput) {
        this.sinusInput = sinusInput;
    }


    // Getter for player starting pos


    public float getMax_x() {
        return max_x;
    }

    public float getMax_y() {
        return max_y;
    }

    public float getPlayer_x() {
        return player_x;
    }

    public float getPlayer_y() {
        return player_y;
    }

    public List<List<Integer>> getWallCoordinates() {
        return wallCoordinates;
    }
}


