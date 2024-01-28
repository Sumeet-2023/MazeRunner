package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Screen;
import de.tum.cit.ase.maze.MazeRunnerGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

 /**
 * The WinScreen class is responsible for displaying the Win screen if player reaches the door safely after collecting the keys.
 * It extends the LibGDX Screen class and sets up the UI components for WinScreen class.
 */
public class WinScreen implements Screen {
    private final MazeRunnerGame game;
    private Music backgroundMusic;
    private final BitmapFont font;
    private final Stage stage;

    /**
     * Constructor for WinScreen. Sets up the camera, viewport, stage,backgroundMusic and UI elements.
     * @param game The main game class, used to access global resources and methods.
     */
    public WinScreen(MazeRunnerGame game){
        this.game = game;
        var camera = new OrthographicCamera();
        camera.zoom = 1.0f;
        font = game.getSkin().getFont("font");
        Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
        stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements
        Table table = new Table(); // Create a table for layout
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table); // Add the table to the stage

        Label label = new Label("Click Space for Main Menu!",game.getSkin());
        table.bottom().add(label);

        Image backgroundImage = new Image(new Texture(Gdx.files.internal("WinImage.png")));
        table.setBackground(backgroundImage.getDrawable());

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("LooseWinMusic.wav"));
        backgroundMusic.setVolume(0.8f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }
    @Override
    public void show() {
        // Set the input processor so the stage can receive input events
         Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            backgroundMusic.dispose();
            game.goToMenu();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Update the stage
        stage.draw(); // Draw the stage

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override
    public void dispose() {
         // Dispose of the stage,backgroundMusic when screen is disposed
         stage.dispose();
         backgroundMusic.dispose();
    }

   // The following methods are part of the Screen interface but are not used in this screen.
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
