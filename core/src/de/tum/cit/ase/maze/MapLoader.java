package de.tum.cit.ase.maze;

import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.characters.Ghost;
import de.tum.cit.ase.maze.objects.*;
import java.util.*;

/**
 * The MapLoader class is responsible for loading and managing the map data for the MazeRunnerGame.
 * It handles the layout of the game environment, including the placement of walls, obstacles, enemies, and other game elements.
 * The class also manages dynamic elements like the display state of keys and hearts.
 */
public class MapLoader {
    private final Map<List<Integer>, Integer> map ;
    private float sinusInput;
    private float max_x;
    private float max_y;
    private  float player_x;
    private float player_y;
    private final MazeRunnerGame game;
    private final Wall wall;
    private final Obstacle obstacles;
    private final Ghost ghost;
    private final Door doors;
    private final Tile tiles;
    private final Decoration decoration;
    private final Heart heart;
    private final Key key;
    private int keyX;
    private int keyY;
    private boolean displayKey = true;
    private final List<List<Integer>> wallCoordinates;
    private final List<List<Integer>> doorCoordinates;
    private final List<List<Integer>> obstacleCoordinates;
    private final List<Enemy> enemies;
    private final List<List<Integer>> heartCoordinates;
    private final int heartCoordinate1;
    private final int heartCoordinate2;
    private boolean displayHeart1 = true;
    private boolean displayHeart2 = true;

    /**
     * Constructs a MapLoader object for a specific level of the MazeRunnerGame.
     * It initializes various game elements like walls, obstacles, enemies, and keys based on the level data.
     *
     * @param game The main game object associated with the map.
     * @param sinusInput A float value used for animations within the map.
     * @param level The file path or identifier for the level data to be loaded.
     */
    public MapLoader(MazeRunnerGame game, float sinusInput,String level) {
        this.game = game;
        this.sinusInput = sinusInput;
        map = Utils.readMap(level);
        this.wall = new Wall();
        wallCoordinates = new ArrayList<>();
        this.obstacles = new Obstacle();
        obstacleCoordinates = new ArrayList<>();
        enemies = new ArrayList<>();
        ghost = new Ghost();
        this.doors = new Door();
        doorCoordinates = new ArrayList<>();
        this.tiles = new Tile();
        this.key = new Key();
        decoration = new Decoration();
        heart = new Heart();
        setCoordinateLists();
        heartCoordinates = new ArrayList<>();
        spaceCoordinate();
        Random random = new Random();
        heartCoordinate1 = random.nextInt(heartCoordinates.size());
        heartCoordinate2 = random.nextInt(heartCoordinates.size());
    }

    /**
     * Determines and sets the maximum x and y coordinates for the map based on the provided map data.
     *
     * @param map A Map with keys as a list of two integers (coordinates) and values as integers (tile types).
     */
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

    /**
     * Processes the map data to populate lists of different game elements like walls, doors, and obstacles.
     * It also sets the player's initial position and the position of the key on the map.
     */
    public void setCoordinateLists(){
        for (List<Integer> coordinates : map.keySet()) {
            switch (map.get(coordinates)){
                case 0:
                    List<Integer> wall = new ArrayList<>();
                    wall.add(coordinates.get(0));
                    wall.add(coordinates.get(1));
                    wallCoordinates.add(wall);
                    break ;
                case 1:
                    player_x = coordinates.get(0);
                    player_y = coordinates.get(1);
                    break;
                case 2:
                    List<Integer> door = new ArrayList<>();
                    door.add(coordinates.get(0));
                    door.add(coordinates.get(1));
                    doorCoordinates.add(door);
                    break;
                case 3:
                    List<Integer> obstacle = new ArrayList<>();
                    obstacle.add(coordinates.get(0));
                    obstacle.add(coordinates.get(1));
                    obstacleCoordinates.add(obstacle);
                    break;
                case 4:
                    Enemy enemy = new Enemy(coordinates.get(0), coordinates.get(1), 2f, Direction.getRandomDirection(), this);
                    enemies.add(enemy);
                    break;
                case 5:
                    keyX = coordinates.get(0);
                    keyY = coordinates.get(1);
                    break;

            }
        }
    }

    /**
     * Calculates and stores all possible coordinates for heart placements.
     * This method ensures that hearts are not placed on walls or obstacles.
     */
    public void spaceCoordinate(){
        setMaxXY(map);
        for (int i = 0; i <= max_x; i++) {
            for (int j = 0; j <= max_y; j++) {
                List<Integer> xy = new ArrayList<>();
                xy.add(i);
                xy.add(j);
                heartCoordinates.add(xy);
            }
        }
        for(List<Integer> wallXY : wallCoordinates){
            heartCoordinates.removeIf(xy -> xy.get(0).equals(wallXY.get(0))&& xy.get(1).equals(wallXY.get(1)));
        }
        for(List<Integer> obstacleXY : obstacleCoordinates){
            heartCoordinates.removeIf(xy -> xy.get(0).equals(obstacleXY.get(0))&& xy.get(1).equals(obstacleXY.get(1)));
        }
    }

    /**
     * Loads and renders the any map layout.
     * This method draws tiles, walls, doors, enemies, and other elements based on their coordinates.
     * It also handles the animation and display state of dynamic elements like hearts.
     */
    public void loadMapGeneral() {
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++) {
            for (int j = 0; j <= max_y; j++) {
                game.getSpriteBatch().draw(tiles.getTile(), i * 32, j * 32,32,32);
            }
        }
        if(displayHeart1) {
            game.getSpriteBatch().draw(heart.getLife().getKeyFrame(sinusInput, true), heartCoordinates.get(heartCoordinate1).get(0) * 32, heartCoordinates.get(heartCoordinate1).get(1) * 32, 32, 32);
        }
        if(displayHeart2){
            game.getSpriteBatch().draw(heart.getLife().getKeyFrame(sinusInput, true), heartCoordinates.get(heartCoordinate2).get(0) * 32, heartCoordinates.get(heartCoordinate2).get(1) * 32, 32, 32);
        }

        for (List<Integer> coordinates : map.keySet()) {
            float min_x = 0;
            float min_y = 0;
            switch (map.get(coordinates)) {
                case 0:
                    if (coordinates.get(1)== min_y || coordinates.get(1)==max_y) {
                        game.getSpriteBatch().draw(wall.getHorizontalWall(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    } else if ( coordinates.get(0)==max_x ) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateY(16);
                        wall.getHorizontalWall().setRotation(270);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)== min_x) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateX(16);
                        wall.getHorizontalWall().setRotation(90);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
                    }
                    else {
                        game.getSpriteBatch().draw(decoration.getTree(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    if(coordinates.get(0)==max_x && coordinates.get(1)==max_y || coordinates.get(0)==0 && coordinates.get(1)==max_y){
                        game.getSpriteBatch().draw(wall.getCornerWall(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    break;
                case 1:
                    game.getSpriteBatch().draw(decoration.getStaircase(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);

                    break;
                case 2:
                    if(coordinates.get(0)== min_x || coordinates.get(0)==max_x){
                        doors.getHorizontalDoor().setPosition(coordinates.get(0)*32,coordinates.get(1)*32);
                        doors.getHorizontalDoor().setSize(32,32);
                        doors.getHorizontalDoor().translateX(16);
                        doors.getHorizontalDoor().setRotation(90);
                        doors.getHorizontalDoor().draw(game.getSpriteBatch());
                    }
                    else {
                        game.getSpriteBatch().draw(doors.getHorizontalDoor(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    break;
                case 3:
                    if(coordinates.get(0)%2==0 && coordinates.get(1)%2==0) {
                        game.getSpriteBatch().draw(obstacles.getFireAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==1 && coordinates.get(1)%2==0){
                        game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==0 && coordinates.get(1)%2==1){
                        game.getSpriteBatch().draw(obstacles.getPoisonAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==1 && coordinates.get(1)%2==1){
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 36, 36);
                    }
                    else {
                        game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    break;
                case 4:
                    for (Enemy enemy: enemies)
                    {

                        enemy.drawEnemy(game.getSpriteBatch(), sinusInput, ghost.getGhostDownAnimation());
                    }
                    break;
                case 5:
                    if (displayKey)
                        game.getSpriteBatch().draw(key.getKey(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);

                    break;
            }
        }
    }

    /**
     * Loads and renders the fifth map layout.
     * Similar to loadMapGeneral, it draws various elements like tiles, walls, doors, but with a different theme and layout.
     * It also manages the animation and display of dynamic elements.
     */
    public void loadMap5(){
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++) {
            for (int j = 0; j <= max_y; j++) {
                game.getSpriteBatch().draw(tiles.getIslandGrass(), i * 32, j * 32,32,32);
            }
        }
        for(List<Integer> exitXY : doorCoordinates) {
            game.getSpriteBatch().draw(decoration.getWater(), exitXY.get(0) * 32, exitXY.get(1) * 32, 32, 32);
        }
        if(displayHeart1) {
            game.getSpriteBatch().draw(heart.getLife().getKeyFrame(sinusInput, true), heartCoordinates.get(heartCoordinate1).get(0) * 32, heartCoordinates.get(heartCoordinate1).get(1) * 32, 32, 32);
        }
        if(displayHeart2){
            game.getSpriteBatch().draw(heart.getLife().getKeyFrame(sinusInput, true), heartCoordinates.get(heartCoordinate2).get(0) * 32, heartCoordinates.get(heartCoordinate2).get(1) * 32, 32, 32);
        }
        for (List<Integer> coordinates : map.keySet()) {
            switch (map.get(coordinates)) {
                case 0:
                    if ( coordinates.get(1)==max_y) {
                        game.getSpriteBatch().draw(wall.getIslandWallUp(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    } else if (coordinates.get(1)==0) {
                        game.getSpriteBatch().draw(wall.getIslandWallDown(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if (coordinates.get(0)==0) {
                        game.getSpriteBatch().draw(wall.getIslandWallLeft(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if (coordinates.get(0)==max_x) {
                        game.getSpriteBatch().draw(wall.getIslandWallRight(), coordinates.get(0) * 32, coordinates.get(1) * 32, 38, 32);
                    }
                    else {
                        if(coordinates.get(0)%2==0 && coordinates.get(1)%2==0 || coordinates.get(0)%2==1 && coordinates.get(1)%2==1) {
                            game.getSpriteBatch().draw(wall.getStoneWall(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                        } else if (coordinates.get(0)%2==1 && coordinates.get(1)%2==0 || coordinates.get(0)%2==0 && coordinates.get(1)%2==1) {
                            game.getSpriteBatch().draw(wall.getWood(), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);

                        }
                    }
                    if( coordinates.get(0)==0 && coordinates.get(1)==max_y){
                        game.getSpriteBatch().draw(wall.getIslandCornerWallUpLeft(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    if(coordinates.get(0)==max_x && coordinates.get(1)==max_y ){
                        game.getSpriteBatch().draw(wall.getIslandCornerWallUpRight(),coordinates.get(0)*32,coordinates.get(1)*32,56,32);
                    }
                    if(coordinates.get(0)==0 && coordinates.get(1)==0 ){
                        game.getSpriteBatch().draw(wall.getIslandCornerWallDownLeft(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    if(coordinates.get(0)==max_x && coordinates.get(1)==0 ){
                        game.getSpriteBatch().draw(wall.getIslandCornerWallDownRight(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                    }
                    break;
                case 1:
                    break;
                case 2:
                     if (coordinates.get(0)==max_x) {
                         game.getSpriteBatch().draw(decoration.getBoat(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
                     }
                    else {
                         decoration.getBoat().setPosition(coordinates.get(0)*32,coordinates.get(1)*32);
                         decoration.getBoat().setSize(32,32);
                         decoration.getBoat().setRotation(270);
                         decoration.getBoat().translateX(-24);
                         decoration.getBoat().translateY(-24);
                         decoration.getBoat().draw(game.getSpriteBatch());
                    }
                    break;
                case 3:
                    if(coordinates.get(0)%2==0 && coordinates.get(1)%2==0) {
                        game.getSpriteBatch().draw(obstacles.getFireAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==1 && coordinates.get(1)%2==0){
                        game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==0 && coordinates.get(1)%2==1){
                        game.getSpriteBatch().draw(obstacles.getPoisonAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else if(coordinates.get(0)%2==1 && coordinates.get(1)%2==1){
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                    else {
                        game.getSpriteBatch().draw(obstacles.getSpikeAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
                    }
                case 4:
                    for (Enemy enemy: enemies)
                    {

                        enemy.drawEnemy(game.getSpriteBatch(), sinusInput, ghost.getGhostDownAnimation());
                    }
                    break;
                case 5:
                    if (displayKey)
                        game.getSpriteBatch().draw(key.getKey(),coordinates.get(0)*32,coordinates.get(1)*32,28,32);

                    break;
            }
        }
    }

    /**
     * Sets the sinus input value, used for animations within the map.
     *
     * @param sinusInput The new sinus input value.
     */
    public void setSinusInput(float sinusInput) {
        this.sinusInput = sinusInput;
    }

    /**
     * Getter methods for various properties of the map.
     * These include maximum dimensions, player's position, coordinates of different elements, and state of dynamic items.
     * Each method returns the respective value or list of values.
     */
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
    public List<List<Integer>> getDoorCoordinates() {
        return doorCoordinates;
    }
    public List<List<Integer>> getObstacleCoordinates() {
        return obstacleCoordinates;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    public int getKeyX() {
        return keyX;
    }
    public int getKeyY() {
        return keyY;
    }
    public List<List<Integer>> getHeartCoordinates() {
        return heartCoordinates;
    }
    public int getHeartCoordinate1() {
        return heartCoordinate1;
    }
    public int getHeartCoordinate2() {
        return heartCoordinate2;
    }

    /**
     * Setter methods for controlling the display state of keys and hearts on the map.
     * @param displayKey Boolean indicating whether the item (key/heart) should be displayed.
     */
    public void setDisplayKey(boolean displayKey) {
        this.displayKey = displayKey;
    }
    public void setDisplayHeart1(boolean displayHeart) {
        this.displayHeart1 = displayHeart;
    }
    public void setDisplayHeart2(boolean displayHeart2) {
        this.displayHeart2 = displayHeart2;
    }
}


