package de.tum.cit.ase.maze;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.ase.maze.objects.Key;

import javax.swing.text.View;

public class HUD {
    private int hearts;
    private boolean key;
    private Stage stage;
    private Viewport viewport;

    public HUD(int hearts, boolean key, Viewport viewport) {
        this.hearts = hearts;
        this.key = key;
        this.viewport = viewport;
        stage = new Stage(viewport);
        setUpHud();
    }

    public void setUpHud(){
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Key key1 = new Key();
        Image image = new Image(key1.getKey());
        image.setScale(6);
        // Adding HUD Elements
        table.padTop(80);
        table.add(image);

        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public void update() {

    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
