package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.characters.Ghost;
import de.tum.cit.ase.maze.objects.*;

import java.util.*;

import static com.badlogic.gdx.math.MathUtils.random;

public class MapLoader {

    // Map
    private Map<List<Integer>, Integer> map ;

    // Attributes (Variables)
    private float sinusInput;

    // Maximum & Minimum value of x and y of the map
    private float max_x;
    private float max_y;
    private final float min_x = 0;
    private final float min_y = 0;

    // Coordinates of player's starting point
    private  float player_x;
    private float player_y;

    // Attributes (Class Instances)
    private MazeRunnerGame game;
    private Wall wall;
    private Obstacle obstacles;
    private Ghost ghost;
    private Door doors;
    private Tile tiles;

    // Key and its position
    private Key key;
    private int keyX;
    private int keyY;
    private boolean displayKey = true;

    // Attributes Coordinate lists
    private List<List<Integer>> wallCoordinates;
    private List<List<Integer>> doorCoordinates;
    private List<List<Integer>> obstacleCoordinates;
    private List<Enemy> enemies;


    public MapLoader(MazeRunnerGame game, float sinusInput,String level) {
        this.game = game;
        this.sinusInput = sinusInput;

        // Select Map
         map = Utils.readMap(level);

        // Wall
        this.wall = new Wall();
        wallCoordinates = new ArrayList<>();

        // Obstacles
        this.obstacles = new Obstacle();
        obstacleCoordinates = new ArrayList<>();
        obstacleCoordinates = new ArrayList<>();

        // Enemies
        enemies = new ArrayList<>();
        ghost = new Ghost();

        // Doors
        this.doors = new Door();
        doorCoordinates = new ArrayList<>();

        // Tiles
        this.tiles = new Tile();

        // Key
        this.key = new Key();

        // Store coordinates
        setCoordinateLists();

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
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateY(16);
                        wall.getHorizontalWall().setRotation(270);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());

                    }
                    else if (coordinates.get(0)==min_x ) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateX(16);
                        wall.getHorizontalWall().setRotation(90);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
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
                    if(coordinates.get(0)==min_x){
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
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
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
    public void loadMap2(){
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
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateY(16);
                        wall.getHorizontalWall().setRotation(270);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==min_x ) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateX(16);
                        wall.getHorizontalWall().setRotation(90);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
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
                    if(coordinates.get(0)==min_x ){
                        doors.getHorizontalDoor().setPosition(coordinates.get(0)*32,coordinates.get(1)*32);
                        doors.getHorizontalDoor().setSize(32,32);
                        doors.getHorizontalDoor().translateX(16);
                        doors.getHorizontalDoor().setRotation(90);
                        doors.getHorizontalDoor().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==max_x) {
                        doors.getHorizontalDoor().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        doors.getHorizontalDoor().setSize(32, 32);
                        doors.getHorizontalDoor().translateY(16);
                        doors.getHorizontalDoor().setRotation(270);
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
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
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
    public void loadMap3(){
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
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateY(16);
                        wall.getHorizontalWall().setRotation(270);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==min_x ) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateX(16);
                        wall.getHorizontalWall().setRotation(90);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
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
                    if(coordinates.get(0)==min_x){
                        doors.getHorizontalDoor().setPosition(coordinates.get(0)*32,coordinates.get(1)*32);
                        doors.getHorizontalDoor().setSize(32,32);
                        doors.getHorizontalDoor().translateX(16);
                        doors.getHorizontalDoor().setRotation(90);
                        doors.getHorizontalDoor().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==max_x) {
                        doors.getHorizontalDoor().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        doors.getHorizontalDoor().setSize(32, 32);
                        doors.getHorizontalDoor().translateY(16);
                        doors.getHorizontalDoor().setRotation(270);
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
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
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
    public void loadMap4(){
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
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateY(16);
                        wall.getHorizontalWall().setRotation(270);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==min_x ) {
                        wall.getHorizontalWall().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        wall.getHorizontalWall().setSize(32,32);
                        wall.getHorizontalWall().translateX(16);
                        wall.getHorizontalWall().setRotation(90);
                        wall.getHorizontalWall().draw(game.getSpriteBatch());
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
                    if(coordinates.get(0)==min_x){
                        doors.getHorizontalDoor().setPosition(coordinates.get(0)*32,coordinates.get(1)*32);
                        doors.getHorizontalDoor().setSize(32,32);
                        doors.getHorizontalDoor().translateX(16);
                        doors.getHorizontalDoor().setRotation(90);
                        doors.getHorizontalDoor().draw(game.getSpriteBatch());
                    }
                    else if (coordinates.get(0)==max_x) {
                        doors.getHorizontalDoor().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        doors.getHorizontalDoor().setSize(32, 32);
                        doors.getHorizontalDoor().translateY(16);
                        doors.getHorizontalDoor().setRotation(270);
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
                        game.getSpriteBatch().draw(obstacles.getFlameAnimation().getKeyFrame(sinusInput, true), coordinates.get(0) * 32, coordinates.get(1) * 32, 32, 32);
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
    public void loadMap5(){
        setMaxXY(map);

        for (int i = 0; i <= max_x; i++) {
            for (int j = 0; j <= max_y; j++) {
                game.getSpriteBatch().draw(tiles.getIslandGrass(), i * 32, j * 32,32,32);
            }
        }

        game.getSpriteBatch().draw(key.getLife().getKeyFrame(sinusInput, true), 8 * 32, 7 * 32, 32, 32);
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
                        doors.getHorizontalDoor().setPosition(coordinates.get(0) * 32, coordinates.get(1) * 32);
                        doors.getHorizontalDoor().setSize(32, 32);
                        doors.getHorizontalDoor().translateY(16);
                        doors.getHorizontalDoor().setRotation(270);
                        doors.getHorizontalDoor().draw(game.getSpriteBatch());
                    }
                    else {
                        game.getSpriteBatch().draw(doors.getHorizontalDoor(),coordinates.get(0)*32,coordinates.get(1)*32,32,32);
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
                        game.getSpriteBatch().draw(key.getKey(),coordinates.get(0)*32,coordinates.get(1)*32,28,32);

                    break;
            }
        }
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

    // getters for coordinate lists
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
// Getters for key starting values

    public int getKeyX() {
        return keyX;
    }

    public int getKeyY() {
        return keyY;
    }

    public void setDisplayKey(boolean displayKey) {
        this.displayKey = displayKey;
    }

    public boolean isDisplayKey() {
        return displayKey;
    }
}


