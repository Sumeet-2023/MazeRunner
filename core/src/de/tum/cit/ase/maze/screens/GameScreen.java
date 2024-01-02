package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import de.tum.cit.ase.maze.MapLoader;
import de.tum.cit.ase.maze.MazeRunnerGame;
import de.tum.cit.ase.maze.Utils;
import de.tum.cit.ase.maze.objects.Wall;

import java.util.List;
import java.util.Map;

/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {
    private Music backgroundMusic;

    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final BitmapFont font;

    private float sinusInput = 0f;

    private MapLoader mapLoader;



    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game) {
        this.game = game;

        // Create and configure the camera for the game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 0.5f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");
        mapLoader = new MapLoader(game, sinusInput);
    }


    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {
        // Check for escape key press to go back to the menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

            backgroundMusic.dispose();
            game.goToEscMenu();
        }
        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen

        camera.update(); // Update the camera

        sinusInput += delta;
        mapLoader.setSinusInput(sinusInput);
        // Set up and begin drawing with the sprite batch
        game.getSpriteBatch().setProjectionMatrix(camera.combined);


        // Rendering the Map
        game.getSpriteBatch().begin();
            mapLoader.loadMap1();
        game.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
    }

    // Additional methods and logic can be added as needed for the game screen
}
