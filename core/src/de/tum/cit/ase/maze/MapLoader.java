package de.tum.cit.ase.maze;

import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.characters.Player;
import de.tum.cit.ase.maze.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapLoader {
    private Map<List<Integer>, Integer> map;
    private float sinusInput;

    private Integer max_x;
    private Integer max_y;
    
    private List<Wall> walls;
    private List<Obstacle> obstacles;
    private List<Enemy> enemies;
    private List<Door> doors;
    private List<Tile> tiles;
    private Player player;
    private Key key;

    public MapLoader(float sinusInput) {
        this.sinusInput = sinusInput;
        this.walls = new ArrayList<Wall>();
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.tiles = new ArrayList<>();
    }

    public void setMaxXY(Map<List<Integer>, Integer> map)
    {
        max_x = 0;
        max_y = 0;
        for (List<Integer> key: map.keySet()){
            if (key.get(0) > max_x)
                max_x = key.get(0);
            if (key.get(1) > max_y)
                max_y = key.get(1);
        }
    }

    public void loadMap1()
    {
        map = Utils.readMap("maps\\level-3.properties");
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++)
        {
            for (int j = 0; j <= max_y; j++)
            {
                tiles.add(new Tile());
            }
        }

        for (List<Integer> coordinates: map.keySet())
        {
            switch (map.get(coordinates))
            {
                case 0:
                    walls.add(new Wall(coordinates.get(0), coordinates.get(1)));
                    break;
                case 1:
                    player = new Player();
                    break;
                case 2:
                    doors.add(new Door());
                    break;
                case 3:
                    obstacles.add(new Obstacle());
                    break;
                case 4:
                    enemies.add(new Enemy());
                    break;
                case 5:
                    key = new Key();
                    break;
            }
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public Player getPlayer() {
        return player;
    }

    public Key getKey() {
        return key;
    }

    public void setSinusInput(float sinusInput) {
        this.sinusInput = sinusInput;
    }
}
