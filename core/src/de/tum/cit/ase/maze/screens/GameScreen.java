package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.tum.cit.ase.maze.*;
import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.characters.Player;

/**
 * The GameScreen class represents the main gameplay screen of the MazeRunnerGame.
 * It is responsible for rendering the game world, managing game logic, handling user input, and updating game elements.
 * This screen includes the game's map, player character, enemies, and HUD elements.
 */
public class GameScreen implements Screen {
    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final String mapLevel;
    private final Sound keySound;
    private boolean hasKeyCollected = false;
    private final Music backgroundMusic;
    private final MapLoader mapLoader;
    private final Player player;
    private final EventHandler eventHandler;
    private final EscMenuScreen escMenuScreen;
    private final HUD hud;
    private boolean isPause = false;
    private float sinusInput = 0f;
    private final int tileSize = 32;
    private float aspectRatio;
    private final int minTilesVisibleY;
    private final String map1 = "maps//level-1.properties";
    private final String map2 = "maps//level-2.properties";
    private final String map3 = "maps//level-3.properties";
    private final String map4 = "maps//level-4.properties";
    private final String map5 = "maps//level-5.properties";

    /**
     * Constructs a GameScreen for the MazeRunnerGame.
     * Initializes the game elements, sets up the background music, and prepares the camera for the game view.
     *
     * @param game The main game class, used to access global resources and methods.
     * @param mapLevel The identifier for the level to be loaded in this game screen.
     */
    public GameScreen(MazeRunnerGame game,String mapLevel) {
        this.game = game;
        this.mapLevel=mapLevel;
        if(mapLevel.equals(map1) || mapLevel.equals(map2) || mapLevel.equals(map5)) {
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Map1Music.ogg"));
            backgroundMusic.setVolume(0.2f);
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
        else if(mapLevel.equals(map3) || mapLevel.equals(map4)){
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Map3Music.mp3"));
            backgroundMusic.setVolume(0.2f);
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
        else {
            backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Map1Music.ogg"));
            backgroundMusic.setVolume(0.2f);
            backgroundMusic.setLooping(true);
            backgroundMusic.play();
        }
        this.keySound = Gdx.audio.newSound(Gdx.files.internal("keyPickup.mp3"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 1f;
        mapLoader = new MapLoader(game, sinusInput,mapLevel);
        player = new Player(mapLoader.getPlayer_x(), mapLoader.getPlayer_y());
        eventHandler = new EventHandler(player, mapLoader, game, backgroundMusic);
        escMenuScreen = new EscMenuScreen(game,this);
        aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        minTilesVisibleY = 6;
        hud = new HUD(player, new ScreenViewport());
    }

    /**
     * Renders the game screen and updates all game elements.
     * This method is responsible for the main game loop. It handles user input, updates the game state,
     * and renders the game elements to the screen.
     * The camera's position is dynamically adjusted based on the player's position to ensure the player
     * remains in view while respecting the boundaries of the map. The viewport is set to display a fixed
     * number of tiles vertically (defined by 'minTilesVisibleY'), with the horizontal tiles determined by
     * the aspect ratio of the screen. This approach maintains a consistent view across different screen sizes.
     *
     * @param delta The time in seconds since the last render. Used for animations and game state updates.
     */
    @Override
    public void render(float delta) {
        // Handle escape key for pausing the game
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPause = !isPause;
            if(isPause){
                pauseGame();
            }
            else {
                resumeGame();
            }
        }

        // Continue only if the game is not paused
        if(!isPause) {
            ScreenUtils.clear(0, 0, 0, 1);

            // Calculate camera position
            // First, determine the central position based on player's position and viewport size
            // Limit this position to be within the boundaries of the map
            camera.position.set(
                    Math.min(
                            Math.max(player.getX() * tileSize, camera.viewportWidth / 2),
                            mapLoader.getMax_x() * tileSize - camera.viewportWidth / 2 + tileSize
                    ),
                    Math.min(Math.max(player.getY() * tileSize, camera.viewportHeight / 2),
                            mapLoader.getMax_y() * tileSize - camera.viewportHeight / 2 + tileSize
                    ),
                    0
            );
            camera.update();

            // Setting the projection Matrix
            game.getSpriteBatch().setProjectionMatrix(camera.combined);
            sinusInput += delta;

            mapLoader.setSinusInput(sinusInput);

            // Update the viewport dimensions
            camera.viewportHeight = tileSize * minTilesVisibleY;
            camera.viewportWidth = camera.viewportHeight * aspectRatio;

            // Handle game events and interactions
            eventHandler.handlePlayerMovements();
            eventHandler.handelPlayerObstacleInteraction(delta, hud);
            eventHandler.handlePlayerEnemyInteraction(delta, hud);
            eventHandler.handlePlayerHeartInteraction(hud);
            eventHandler.handelKey(hud);
            eventHandler.handelWin();
            eventHandler.handelLose();

            // Play sound if key is collected
            playSound();

            // Begin sprite batch and render the map
            game.getSpriteBatch().begin();

            if(mapLevel.equals(map1) || mapLevel.equals(map2) || mapLevel.equals(map3) || mapLevel.equals(map4)){
                mapLoader.loadMapGeneral();
            } else if (mapLevel.equals(map5)) {
                mapLoader.loadMap5();
            } else {
                mapLoader.loadMapGeneral();
            }

            // Draw the player
            if (player.getCurrentAnimationFrame() != null) {
                    game.getSpriteBatch().draw(player.getCurrentAnimationFrame(), player.getX() * 32, player.getY() * 32, 24, 48);
            }
            else {
                game.getSpriteBatch().draw(player.getDefaultFrame(), player.getX() * 32, player.getY() * 32, 24, 48);
            }
            game.getSpriteBatch().end();

            // Update and draw the HUD
            hud.getStage().act(delta);
            hud.draw();

            // Update player and enemy states
            player.update(Gdx.graphics.getDeltaTime());
            for (Enemy enemy : mapLoader.getEnemies())
            {
                enemy.handleEnemy(Gdx.graphics.getDeltaTime());
            }
        }
    }

    /**
     * Plays the key collection sound when the player picks up a key.
     * This method ensures the sound is played only once upon key collection.
     */
    public void playSound(){
        if(player.getHasKey() && !hasKeyCollected){
            keySound.play();
            hasKeyCollected = true;
        }

    }

    /**
     * Adjusts the camera's viewport when the screen size changes.
     * This method ensures that the game view remains consistent across different screen sizes.
     *
     * @param width The new width of the screen.
     * @param height The new height of the screen.
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
        aspectRatio = (float) width / (float) height;
        camera.viewportHeight = tileSize * minTilesVisibleY;
        camera.viewportWidth = camera.viewportHeight * aspectRatio;

        camera.viewportWidth = Math.min(camera.viewportWidth, (mapLoader.getMax_x() - 2) * tileSize);
        camera.viewportHeight = Math.min(camera.viewportHeight, (mapLoader.getMax_y() - 2) * tileSize);
    }

    /**
     * Lifecycle methods for the screen.
     * These methods are called by the LibGDX framework to handle screen state changes such as pausing, resuming, showing, and hiding the screen.
     */
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    /**
     * Cleans up resources when the screen is no longer needed.
     * This includes disposing of the background music and sound effects to free up memory.
     */
    @Override
    public void dispose() {
        backgroundMusic.dispose();
        keySound.dispose();
    }

    /**
     * Pauses the game and switches to the escape menu screen.
     * This method is called when the player presses the escape key.
     */
    public void pauseGame(){
        backgroundMusic.pause();
        game.setScreen(escMenuScreen);
    }

    /**
     * Resumes the game from the pause state and returns to the game screen from the escape menu.
     */
    public void resumeGame(){
        backgroundMusic.play();
        isPause=false;
        game.setScreen(this);
    }
}
