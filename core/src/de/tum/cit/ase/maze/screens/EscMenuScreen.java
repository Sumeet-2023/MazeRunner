package de.tum.cit.ase.maze.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.ase.maze.MazeRunnerGame;

/**
 * The EscMenuScreen displays the screen when esc button is pressed i.e game is in pause state.
 */
public class EscMenuScreen implements Screen{
    private final Stage stage;
    private GameScreen gameScreen;

    /**
     * Constructor for EscMenuScreen. Sets up the camera, viewport, stage, and UI elements.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public EscMenuScreen(MazeRunnerGame game,GameScreen gameScreen) {
        this.gameScreen=gameScreen;
        var camera = new OrthographicCamera();
        camera.zoom = 1.0f; // Set camera zoom for a closer view

        Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
        stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements

        Table table = new Table(); // Create a table for layout
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table); // Add the table to the stage

        Image backgroundImage = new Image(new Texture(Gdx.files.internal("MainMenuImage.jpg")));
        table.setBackground(backgroundImage.getDrawable());
        // Create and add a button to go to the game screen
        TextButton continueGame = new TextButton("Continue", game.getSkin());
        table.add(continueGame).width(300).pad(10).row();
        TextButton selectMap = new TextButton("Play New Map", game.getSkin());
        table.add(selectMap).width(300).pad(10).row();
        TextButton exitGame = new TextButton("Exit", game.getSkin());
        table.add(exitGame).width(300).pad(10).row();

        continueGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { gameScreen.resumeGame();
            }
        });
        selectMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { game.goToSelectMap();
            }
        });


        exitGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToMenu();

            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f,0.2f,0.2f,1);  // Set the Color to Dark Grey
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Update the stage
        stage.draw(); // Draw the stage
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true); // Update the stage viewport on resize
    }

    @Override
    public void dispose() {
        // Dispose of the stage when screen is disposed.
        stage.dispose();
    }

    @Override
    public void show() {
        // Set the input processor so the stage can receive input events
        Gdx.input.setInputProcessor(stage);
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
