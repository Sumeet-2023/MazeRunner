package de.tum.cit.ase.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import de.tum.cit.ase.maze.screens.CreditScreen;
import de.tum.cit.ase.maze.screens.GameScreen;
import de.tum.cit.ase.maze.screens.MenuScreen;
import games.spooky.gdx.nativefilechooser.NativeFileChooser;

/**
 * The MazeRunnerGame class represents the core of the Maze Runner game.
 * It manages the screens and global resources like SpriteBatch and Skin.
 */
public class MazeRunnerGame extends Game {
    // Screens
    private MenuScreen menuScreen;
    private GameScreen gameScreen;
    private CreditScreen creditScreen;

    // Sprite Batch for rendering
    private SpriteBatch spriteBatch;
    private Sprite sprite;

    // UI Skin
    private Skin skin;

    // Character animation downwards
    private Animation<TextureRegion> characterDownAnimation;

    // Fire Animation
    private Animation<TextureRegion> fireAnimation;

    // Door TextureRegions
    private TextureRegion door;

    // Wall TextureRegions
    private TextureRegion wall;

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
        this.loadCharacterAnimation(); // Load character animation
        this.loadFireAnimation(); // Load fire animation
        this.loadDoor(); // Load door TextureRegion
        this.loadWall(); // Load wall TextureRegion

        // Play some background music
        // Background sound
        Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        goToMenu(); // Navigate to the menu screen
    }

    /**
     * Switches to the menu screen.
     */
    public void goToMenu() {
        this.setScreen(new MenuScreen(this)); // Set the current screen to MenuScreen
        if (gameScreen != null) {
            gameScreen.dispose(); // Dispose the game screen if it exists
            gameScreen = null;
        }
    }

    /**
     * Switches to the game screen.
     */
    public void goToGame() {
        this.setScreen(new GameScreen(this)); // Set the current screen to GameScreen
        if (menuScreen != null) {
            menuScreen.dispose(); // Dispose the menu screen if it exists
            menuScreen = null;
        }
    }

    /**
     * Switches to the Credits screen.
     */
    public void goToCredits()
    {
        this.setScreen(new CreditScreen(this));
        if (creditScreen != null){
            creditScreen.dispose();
            creditScreen = null;
        }
    }

    /**
     * Loads the character animation from the character.png file.
     */
    private void loadCharacterAnimation() {
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));

        int frameWidth = 16;
        int frameHeight = 32;
        int animationFrames = 4;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0, frameWidth, frameHeight));
        }

        characterDownAnimation = new Animation<>(0.1f, walkFrames);
    }


    /**
     * Loads the fire animation from the objects.png file.
     */
    public void loadFireAnimation() {
        Texture fireSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrame = 7;

        Array<TextureRegion> fireFrames = new Array<>(TextureRegion.class);

        for (int col = 4; col < animationFrame + 4; col++){
            fireFrames.add(new TextureRegion(fireSheet, col * frameWidth, 3 * frameHeight, frameWidth, frameHeight));
        }
        fireAnimation = new Animation<>(0.1f, fireFrames);
    }

    // TODO: Create methods for loading animation for different direction of character.

    // TODO: Create method for loading animation for different direction of different enemies.

    // TODO: Create method for loading animation for door opening;


    public void loadWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        wall = new TextureRegion(wallTexture, 1, 0, 16, 16);
    }

    public void loadDoor() {
        Texture doorTexture = new Texture(Gdx.files.internal("things.png"));
        door = new TextureRegion(doorTexture, 1, 0, 16, 16);
    }

    // TODO: Create method for Loading TextureRegion for Key.

    // TODO: Create methods for Loading TextureRegion for different directions of walls.
    /**
     * Cleans up resources when the game is disposed.
     */
    @Override
    public void dispose() {
        getScreen().hide(); // Hide the current screen
        getScreen().dispose(); // Dispose the current screen
        spriteBatch.dispose(); // Dispose the spriteBatch
        skin.dispose(); // Dispose the skin
    }

    // Getter methods
    public Skin getSkin() {
        return skin;
    }

    public Animation<TextureRegion> getCharacterDownAnimation() {
        return characterDownAnimation;
    }
    public Animation<TextureRegion> getFireAnimation() { return fireAnimation; }
    public TextureRegion getDoor() { return door; }
    public TextureRegion getWall() { return wall; }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
