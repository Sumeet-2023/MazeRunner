package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.List;
import java.util.Map;

/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {

    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final BitmapFont font;

    private float sinusInput = 0f;

    private Map<List<Integer>, Integer> map;


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
        camera.zoom = 0.75f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");
    }


    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {
        // Check for escape key press to go back to the menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.goToMenu();
        }

        ScreenUtils.clear(0, 0, 0, 1); // Clear the screen

        camera.update(); // Update the camera

        sinusInput += delta;

        // Set up and begin drawing with the sprite batch
        game.getSpriteBatch().setProjectionMatrix(camera.combined);


        // Reading the Map
        String filePath = "maps\\level-1.properties";
        map = Utils.readMap(filePath);

        // Declaring TextureRegions & animations for require objects on the map
        TextureRegion wall = game.getWall();
        TextureRegion door = game.getDoor();
        Animation<TextureRegion> fire = game.getFireAnimation();

        // Rendering the Map
        game.getSpriteBatch().begin();
        for(List<Integer> key: map.keySet())
        {
            switch (map.get(key)){
                case 0: {
                    game.getSpriteBatch().draw(wall, key.get(0) * 16, key.get(1) * 16);
                    break ;
                }
                case 2: {
                    game.getSpriteBatch().draw(door, key.get(0) * 16, key.get(1) * 16);
                    break ;
                }
                case 3: {
                    game.getSpriteBatch().draw(
                            fire.getKeyFrame(sinusInput, true), key.get(0) * 16, key.get(1) * 16, 16, 16);
                    break ;
                }
            }
        }
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

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    // Additional methods and logic can be added as needed for the game screen
}
