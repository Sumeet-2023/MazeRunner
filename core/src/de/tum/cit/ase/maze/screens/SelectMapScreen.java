package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
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

import java.util.List;
import java.util.Map;

public class SelectMapScreen implements Screen {
    private final Stage stage;
    private MazeRunnerGame game;
    public SelectMapScreen(MazeRunnerGame game) {
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
        TextButton map0 = new TextButton("Level 1", game.getSkin());
        table.add(map0).width(300).pad(10).row();
        TextButton map1 = new TextButton("Level 2", game.getSkin());
        table.add(map1).width(300).pad(10).row();
        TextButton map2 = new TextButton("Level 3", game.getSkin());
        table.add(map2).width(300).pad(10).row();
        TextButton map3 = new TextButton("Level 4", game.getSkin());
        table.add(map3).width(300).pad(10).row();
        TextButton map4 = new TextButton("Level 5", game.getSkin());
        table.add(map4).width(300).pad(10).row();
        TextButton cancel = new TextButton("Back", game.getSkin());
        table.add(cancel).width(300).pad(10).row();

        map0.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGame(1);
            }
        });
        map1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGame(2);
            }
        });
        map2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGame(3);
            }
        });
        map3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGame(4);
            }
        });
        map4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToGame(5);
            }
        });

        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { game.goToMenu();
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
        // Dispose of the stage when screen is disposed
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
