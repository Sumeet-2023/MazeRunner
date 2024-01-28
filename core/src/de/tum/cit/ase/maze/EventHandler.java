package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import de.tum.cit.ase.maze.characters.Player;

/**
 * The EventHandler class is responsible for handling events and interactions within the MazeRunnerGame.
 * This includes player movements, interactions with objects like keys, obstacles, enemies, and hearts,
 * as well as determining win or lose conditions. It utilizes various utility methods from the Utils class
 * to determine the state and outcomes of these interactions.
 */
public class EventHandler {
    private final Player player;
    private final MapLoader mapLoader;
    private final MazeRunnerGame game;
    private final Music backgroundMusic;
    private float timeOnObstacle = 0f;
    private boolean isOnObstacle = false;
    private boolean isOnEnemy = false;
    private boolean isOnHeart1 = false;
    private boolean isOnHeart2 = false;
    private float timeOnEnemy = 0f;

    /**
     * Constructs an EventHandler object with necessary game components.
     *
     * @param player The player character of the game.
     * @param mapLoader The object responsible for loading and managing the game map.
     * @param game The main game object that controls the game's screens and states.
     * @param backgroundMusic The background music to be played during the game.
     */
    public EventHandler(Player player, MapLoader mapLoader, MazeRunnerGame game, Music backgroundMusic) {
        this.player = player;
        this.mapLoader = mapLoader;
        this.game = game;
        this.backgroundMusic = backgroundMusic;
    }

    /**
     * Handles the movement of the player based on keyboard input.
     * This method checks if the player can move in the desired direction and updates the player's position.
     * It also handles animation based on the direction of movement.
     */
    public void handlePlayerMovements() {
        float animationSpeed = 3;
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.LEFT, mapLoader, player.getHasKey())) {
                player.setX(player.getX() - (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterLeftAnimation());
            }
            player.setDefaultFrame(player.getCharacterLeft());
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.RIGHT, mapLoader, player.getHasKey())) {
                player.setX(player.getX() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterRightAnimation());
            }
            player.setDefaultFrame(player.getCharacterRight());
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.UP, mapLoader, player.getHasKey())) {
                player.setY(player.getY() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterUpAnimation());
            }
            player.setDefaultFrame(player.getCharacterUp());
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.DOWN, mapLoader, player.getHasKey())) {
                player.setY(player.getY() - (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterDownAnimation());
            }
            player.setDefaultFrame(player.getCharacterDown());
        }
    }

    /**
     * Handles interactions with the key in the game.
     * Checks if the player has reached the key's coordinates and updates the game state accordingly.
     * This includes setting the player's 'hasKey' status and updating the HUD.
     *
     * @param hud The game's Heads-Up Display (HUD) to be updated on key collection.
     */
    public void handelKey(HUD hud) {
        if (Math.abs(player.getX() -  mapLoader.getKeyX()) < 0.5  && Math.abs(player.getY() - mapLoader.getKeyY()) < 0.5) {
            player.setHasKey(true);
            mapLoader.setDisplayKey(false);
            hud.update();
            hud.animateKeyCollection();
        }
    }

    /**
     * Handles interactions between the player and obstacles.
     * This method updates the player's health and HUD when the player encounters an obstacle.
     * It uses a timer to inflict damage at specific intervals.
     *
     * @param deltaTime The time passed since the last frame, used for timing interactions.
     * @param hud The HUD to update the player's health display.
     */
    public void handelPlayerObstacleInteraction(float deltaTime, HUD hud) {
        if (Utils.isAtCoordinate(player.getX(), player.getY(), mapLoader.getObstacleCoordinates())){
            if (!isOnObstacle){
                player.setHeartCount(player.getHeartCount() - 1);
                isOnObstacle = true;
                timeOnObstacle = 0f;
                hud.update();
                hud.animateHeartLoss(2 - player.getHeartCount());
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnObstacle >= 3.0f){
                    player.setHeartCount(player.getHeartCount() - 1);
                    timeOnObstacle = 0f;
                    hud.update();
                    hud.animateHeartLoss(2 - player.getHeartCount());
                }
            }
        }
        else {
            isOnObstacle = false;
            timeOnObstacle = 0f;
        }
    }

    /**
     * Handles interactions between the player and enemies.
     * This method updates the player's health when encountering an enemy and uses a timer to control damage intervals.
     * The HUD is updated to reflect changes in the player's health.
     *
     * @param deltaTime The time passed since the last frame, used for timing interactions.
     * @param hud The HUD to update the player's health display.
     */
    public void handlePlayerEnemyInteraction(float deltaTime, HUD hud) {
        if (Utils.isEnemy(player.getX(), player.getY(), mapLoader.getEnemies())){
            if (!isOnEnemy){
                player.setHeartCount(player.getHeartCount() - 1);
                isOnEnemy = true;
                timeOnObstacle = 0f;
                hud.update();
                hud.animateHeartLoss(2 - player.getHeartCount());
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnEnemy >= 3.0f){
                    player.setHeartCount(player.getHeartCount() - 1);
                    timeOnEnemy = 0f;
                    hud.update();
                    hud.animateHeartLoss(2 - player.getHeartCount());

                }
            }
        }
        else {
            isOnEnemy = false;
            timeOnEnemy = 0f;
        }
    }

    /**
     * Handles interactions between the player and heart pickups.
     * This method checks if the player has reached the heart's location and updates the player's health and HUD.
     * Hearts increase the player's health.
     *
     * @param hud The HUD to be updated when a heart is collected.
     */
    public void handlePlayerHeartInteraction(HUD hud){
            if (Utils.isHeart(player.getX(), player.getY(), mapLoader.getHeartCoordinates().get(mapLoader.getHeartCoordinate1()))) {
                if (!isOnHeart1) {
                    if (player.getHeartCount() < 3) {
                        player.setHeartCount(player.getHeartCount() + 1);
                        isOnHeart1 = true;
                        mapLoader.setDisplayHeart1(false);
                        hud.update();
                    }
                }
            } else if (Utils.isHeart(player.getX(), player.getY(), mapLoader.getHeartCoordinates().get(mapLoader.getHeartCoordinate2()))) {
                if (!isOnHeart2) {
                    if (player.getHeartCount() < 3) {
                        player.setHeartCount(player.getHeartCount() + 1);
                        isOnHeart2 = true;
                        mapLoader.setDisplayHeart2(false);
                        hud.update();
                    }
                }
            }
    }

    /**
     * Handles the lose condition of the game.
     * This method is called when the player's health reaches zero, triggering the transition to the lose screen
     * and disposing of the background music.
     */
    public void handelLose() {
        if (player.getHeartCount() == 0)
        {
            game.goToLoseScreen();
            backgroundMusic.dispose();
        }
    }

    /**
     * Handles the win condition of the game.
     * Checks if the player has reached the door with the key and transitions to the win screen while disposing
     * of the background music.
     */
    public void handelWin() {
        if (Utils.isAtCoordinate(player.getX(), player.getY(), mapLoader.getDoorCoordinates()) && player.getHasKey())
        {
            game.goToWinScreen();
            backgroundMusic.dispose();
        }
    }
}