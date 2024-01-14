package de.tum.cit.ase.maze;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.ase.maze.characters.Player;
import de.tum.cit.ase.maze.objects.Heart;
import de.tum.cit.ase.maze.objects.Key;

import javax.swing.text.View;

public class HUD {
    private Player player;
    private Stage stage;
    private Viewport viewport;
    private Heart heart;
    private Key key;

    public HUD(Player player, Viewport viewport) {
        this.player = player;
        this.viewport = viewport;
        this.heart = new Heart();
        this.key = new Key();
        stage = new Stage(viewport);
        setUpHud();
    }

    public void setUpHud(){
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        for (int i = 0; i < player.getHeartCount(); i++) {
            Image image = new Image(heart.getHeart());
            image.setScale(5);
            table.add(image).pad(40);
        }
        // Adding HUD Elements
        table.pad(60);
        table.align(Align.topRight);
        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }

    public void update() {
        stage.clear();

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        if (player.getHasKey())
        {
            Image image = new Image(key.getKey());
            image.setScale(5);
            table.add(image).pad(40);
        }
        for (int i = 0; i < 3 - player.getHeartCount(); i++){
            Image image = new Image(heart.getEmptyHeart());
            image.setScale(5);
            table.add(image).pad(40);
        }
        for (int i = 0; i < player.getHeartCount(); i++) {
            Image image = new Image(heart.getHeart());
            image.setScale(5);
            table.add(image).pad(40);
        }

        // Adding HUD Elements
        table.pad(60);
        table.align(Align.topRight);
        stage.addActor(table);

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
