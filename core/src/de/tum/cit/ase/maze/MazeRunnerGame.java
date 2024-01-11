package de.tum.cit.ase.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.tum.cit.ase.maze.screens.*;
import games.spooky.gdx.nativefilechooser.NativeFileChooser;

/**
 * The MazeRunnerGame class represents the core of the Maze Runner game.
 * It manages the screens and global resources like SpriteBatch and Skin.
 */
public class MazeRunnerGame extends Game {
    private Music backgroundMusic;
    private MapLoader mapLoader;
    // Screens
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private CreditScreen creditScreen;
    private EscMenuScreen escMenuScreen;
    private SelectMapScreen selectMapScreen;

    // Sprite Batch for rendering
    private SpriteBatch spriteBatch;
    private Sprite sprite;

    // UI Skin
    private Skin skin;


    /**
     * Constructor for MazeRunnerGame.
     *
     * @param fileChooser The file chooser for the game, typically used in desktop environment.
     */
    public MazeRunnerGame(NativeFileChooser fileChooser) {
        super();
    }

    /**
     * Called when the game is created. Initializes the SpriteBatch and Skin.
     */
    @Override
    public void create() {

        spriteBatch = new SpriteBatch(); // Create SpriteBatch
        skin = new Skin(Gdx.files.internal("craft/craftacular-ui.json")); // Load UI skin
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("MainMenuMusic.mp3"));
        backgroundMusic.setVolume(0.8f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        goToMenu(); // Navigate to the menu screen


    }

    /**
     * Switches to the menu screen.
     */
    public void goToMenu() {
        this.setScreen(new MenuScreen(this));// Set the current screen to MenuScreen
        backgroundMusic.play();
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
        if (escMenuScreen != null) {
            escMenuScreen.dispose(); // Dispose the game screen if it exists
            escMenuScreen = null;
        }

    }

    /**
     * Switches to the game screen.
     */
    public void goToGame(String level) {

        this.setScreen(new GameScreen(this,level));// Set the current screen to GameScreen
        backgroundMusic.pause();
        if (menuScreen != null) {
            menuScreen.dispose(); // Dispose the menu screen if it exists
            menuScreen = null;
        }
    }

    public void goToEscMenu() {
        this.setScreen(new EscMenuScreen(this,gameScreen)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
        if (menuScreen != null) {
            menuScreen.dispose(); // Dispose the menu screen if it exists
            menuScreen = null;
        }

    }
    public void goToSelectMap() {
        this.setScreen(new SelectMapScreen(this)); // Set the current screen to MenuScreen
    }
    public void goToEscSelectMap() {
        this.setScreen(new EscSelectMapScreen(this)); // Set the current screen to MenuScreen
    }


    /**
     * Switches to the Credits screen.
     */
    public void goToCredits()
    {
        this.setScreen(new CreditScreen(this));
        if (menuScreen != null){
            menuScreen.dispose();
            menuScreen = null;
        }
    }
    public void goToLooseScreen() {
        this.setScreen(new LooseScreen(this)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
    }
    public void goToWinScreen() {
        this.setScreen(new WinScreen(this)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
    }

    /**
     * Loads the character animation from the character.png file.
     */

    /**
     * Cleans up resources when the game is disposed.
     */
    @Override
    public void dispose() {
        getScreen().hide(); // Hide the current screen
        getScreen().dispose(); // Dispose the current screen
        spriteBatch.dispose(); // Dispose the spriteBatch
        skin.dispose(); // Dispose the skin
        backgroundMusic.dispose();
    }

    // Getter methods
    public Skin getSkin() {
        return skin;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
