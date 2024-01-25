package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import de.tum.cit.ase.maze.*;
import de.tum.cit.ase.maze.characters.Enemy;
import de.tum.cit.ase.maze.characters.Player;
import jdk.jshell.execution.Util;

import java.util.List;

/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {
    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private final String mapLevel;
    private final Sound keySound;
    private boolean hasKeyCollected = false;
    private Music backgroundMusic;
    private MapLoader mapLoader;
    private Player player;
    private EventHandler eventHandler;
    private EscMenuScreen escMenuScreen;
    private HUD hud;
    private boolean isPause = false;
    private float sinusInput = 0f;
    private final int tileSize = 32;
    private float aspectRatio;
    private int minTilesVisibleY;
    private String map1 = "maps//level-1.properties";
    private String map2 = "maps//level-2.properties";
    private String map3 = "maps//level-3.properties";
    private String map4 = "maps//level-4.properties";
    private String map5 = "maps//level-5.properties";

    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game,String mapLevel) {
        this.game = game;
        this.mapLevel=mapLevel;

        // Music
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

        // Create and configure the camera for the game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 1f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");


        // Creating new Instances of clsses
        mapLoader = new MapLoader(game, sinusInput,mapLevel);
        player = new Player(mapLoader.getPlayer_x(), mapLoader.getPlayer_y());
        eventHandler = new EventHandler(player, mapLoader, game, backgroundMusic);
        escMenuScreen = new EscMenuScreen(game,this);
        aspectRatio = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        minTilesVisibleY = 6;

        // Initializing HUD
        hud = new HUD(player, new ScreenViewport());
    }
    
    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {
        // Check for escape key press to go back to the menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPause = !isPause;
            if(isPause){
                pauseGame();
            }
            else {
                resumeGame();
            }
        }
        if(!isPause) {
            ScreenUtils.clear(0, 0, 0, 1); // Clear the screen

            // Setting the camera position
            /**
             * Math.max(player_x * 16, camera.viewportWidth / 2) gives the bigger value from players x position and half of viewport width.
             *
             * mapLoader.getMax_x() * 16 - camera.viewportWidth / 2 + 16) this is the maximum value till which the camera can move.
             *
             * Math.min(Math.max(player_x * 16, camera.viewportWidth / 2), mapLoader.getMax_x() * 16 - camera.viewportWidth / 2 + 16)
             * The whole statement give the minimum out of the above two statements.
             */
            camera.position.set(Math.min(Math.max(player.getX() * tileSize, camera.viewportWidth / 2), mapLoader.getMax_x() * tileSize - camera.viewportWidth / 2 + tileSize),
                    Math.min(Math.max(player.getY() * tileSize, camera.viewportHeight / 2), mapLoader.getMax_y() * tileSize - camera.viewportHeight / 2 + tileSize),
                    0);
            camera.update();

            // Setting the projection Matrix
            game.getSpriteBatch().setProjectionMatrix(camera.combined);
            sinusInput += delta;

            mapLoader.setSinusInput(sinusInput);

            // Setting the viewport such that it displays 12x8 tiles.

            camera.viewportHeight = tileSize * minTilesVisibleY;
            camera.viewportWidth = camera.viewportHeight * aspectRatio;

            // Handle Events
            eventHandler.handlePlayerMovements();
            eventHandler.handelPlayerObstacleInteraction(delta, hud);
            eventHandler.handlePlayerEnemyInteraction(delta, hud);
            eventHandler.handlePlayerHeartInteraction(hud);
            eventHandler.handelKey(hud);
            eventHandler.handelHeart(hud);
            eventHandler.handelWin();
            eventHandler.handelLose();

            playSound();

            // Rendering the Map
            game.getSpriteBatch().begin();

            if(mapLevel.equals(map1) || mapLevel.equals(map2) || mapLevel.equals(map3) || mapLevel.equals(map4)){
                mapLoader.loadMap1();
            } else if (mapLevel.equals(map5)) {
                mapLoader.loadMap5();
            } else {
                mapLoader.loadMap1();
            }

            if (player.getCurrentAnimationFrame() != null) {
                    game.getSpriteBatch().draw(player.getCurrentAnimationFrame(), player.getX() * 32, player.getY() * 32, 24, 48);
            }
            else {
                game.getSpriteBatch().draw(player.getDefaultFrame(), player.getX() * 32, player.getY() * 32, 24, 48);
            }
            game.getSpriteBatch().end();

            // Update and draw HUD
            hud.getStage().act(delta);

            hud.draw();
            player.update(Gdx.graphics.getDeltaTime());
            for (Enemy enemy : mapLoader.getEnemies())
            {
                enemy.handleEnemy(Gdx.graphics.getDeltaTime());
            }
        }
    }
    public void playSound(){
        if(player.getHasKey() && !hasKeyCollected){
            keySound.play();
            hasKeyCollected = true;
        }

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
        aspectRatio = (float) width / (float) height;
        camera.viewportHeight = tileSize * minTilesVisibleY;
        camera.viewportWidth = camera.viewportHeight * aspectRatio;

        camera.viewportWidth = Math.min(camera.viewportWidth, (mapLoader.getMax_x() - 2) * tileSize);
        camera.viewportHeight = Math.min(camera.viewportHeight, (mapLoader.getMax_y() - 2) * tileSize);
    }

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

    @Override
    public void dispose() {
        backgroundMusic.dispose();
        keySound.dispose();
    }
    public void pauseGame(){
        backgroundMusic.pause();
        game.setScreen(escMenuScreen);
    }
    public void resumeGame(){
        backgroundMusic.play();
        isPause=false;
        game.setScreen(this);
    }
}
