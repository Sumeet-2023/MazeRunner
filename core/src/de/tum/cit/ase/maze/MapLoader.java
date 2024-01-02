package de.tum.cit.ase.maze;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private final Integer min_x=0;
    private final Integer min_y=0;
    private MazeRunnerGame game;
    private Wall wall;
    private Obstacle obstacles;
    private Enemy enemies;
    private Door doors;
    private Tile tiles;
    private Player player;
    private Key key;

    public MapLoader(MazeRunnerGame game, float sinusInput) {
        this.sinusInput = sinusInput;
        this.wall = new Wall();
        wall.loadHorizontalWall();
        wall.loadVerticalWall();
        wall.loadCornerWall();
        this.obstacles = new Obstacle();
        obstacles.loadSpikeAnimation();
        this.enemies = new Enemy();
        this.doors = new Door();
        doors.loadDoor();
        this.tiles = new Tile();
        this.game = game;
        this.key=new Key();
        key.loadHeartAnimation();
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
        map = Utils.readMap("maps\\level-1.properties");
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++)
        {
           for (int j = 0; j <= max_y; j++)
            {
                game.getSpriteBatch().draw(tiles.getTile(),i*16,j*16);
            }
        }
         game.getSpriteBatch().draw(key.getLife().getKeyFrame(sinusInput,true), 12*16,12*13,16,16);

        for (List<Integer> coordinates: map.keySet())
        {
            switch (map.get(coordinates))
            {
                case 0:
                    if (coordinates.get(1).equals(min_y) || coordinates.get(1).equals(max_y)){
                     game.getSpriteBatch().draw(wall.getHorizontalWall(), coordinates.get(0) * 16, coordinates.get(1) * 16, 16, 16);}
                    else if(coordinates.get(0).equals(min_x)){
                        wall.getVerticalWall().setPosition(coordinates.get(0)*16,coordinates.get(1)*16);
                         wall.getVerticalWall().setRotation(90);
                         wall.getVerticalWall().draw(game.getSpriteBatch());}
                    else if(coordinates.get(0).equals(max_x)){
                        wall.getVerticalWall().setPosition(coordinates.get(0)*16,coordinates.get(1)*16);
                        wall.getVerticalWall().setRotation(270);
                        wall.getVerticalWall().draw(game.getSpriteBatch());}
                    else{
                        game.getSpriteBatch().draw(wall.getTree(), coordinates.get(0) * 16, coordinates.get(1) * 16, 16, 16);}
                    if(coordinates.get(0).equals(min_x) && coordinates.get(1).equals(max_y) || coordinates.get(0).equals(max_x) && coordinates.get(1).equals(max_y)){
                        game.getSpriteBatch().draw(wall.getCornerWall(),coordinates.get(0)*16,coordinates.get(1)*16);}
                    if(coordinates.get(0)==2 && coordinates.get(1)==4 || coordinates.get(0)==13 && coordinates.get(1)==9){
                        game.getSpriteBatch().draw(wall.getWell(),coordinates.get(0)*16,coordinates.get(1)*16,16,16);
                    }
                     break;
                case 1:

                    break;
                case 2:
                    game.getSpriteBatch().draw(doors.getDoor(),coordinates.get(0)*16,coordinates.get(1)*16,16,16);
                    break;
                case 3:
                    if (coordinates.get(0) == 10 && coordinates.get(1) == 5 || coordinates.get(0) == 6 && coordinates.get(1) == 12){
                    game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput,true),coordinates.get(0)*16,coordinates.get(1)*16,16,16);}
                    else if( coordinates.get(0) == 11 && coordinates.get(1) == 12){
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput,true),coordinates.get(0)*16,coordinates.get(1)*16,16,16);
                    }
                    else {
                        game.getSpriteBatch().draw(obstacles.getFireAnimation().getKeyFrame(sinusInput,true),coordinates.get(0)*16,coordinates.get(1)*16);
                    }
                    break;
                case 4:

                    break;
                case 5:

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

    public Player getPlayer() {
        return player;
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
}
