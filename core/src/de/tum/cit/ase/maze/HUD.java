package de.tum.cit.ase.maze;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.ase.maze.characters.Player;
import de.tum.cit.ase.maze.objects.Heart;
import de.tum.cit.ase.maze.objects.Key;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class HUD {
    private Player player;
    private Stage stage;
    private Viewport viewport;
    private Heart heart;
    private Key key;
    private Image keyImage;
    private List<Image> heartImages = new ArrayList<>();
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

        keyImage = new Image(key.getKey());
        keyImage.setScale(5);
        keyImage.setVisible(false);
        table.add(keyImage).pad(40);
        for (int i = 0; i < player.getHeartCount(); i++) {
            Image image = new Image(heart.getHeart());
            image.setScale(5);
            heartImages.add(image);
            table.add(image).pad(40);
        }
        // Adding HUD Elements
        table.pad(50);
        table.align(Align.topRight);
        stage.addActor(table);
    }

    public void draw() {
        stage.draw();
    }


    public void update()
    {
        if (player.getHasKey())
            keyImage.setVisible(true);

        for (int i = 0; i < heartImages.size(); i++) {
            Image heartImage = heartImages.get(2 - i);
            if (i < player.getHeartCount()) {
                heartImage.setDrawable(new SpriteDrawable(new Sprite(heart.getHeart())));
            } else {
                heartImage.setDrawable(new SpriteDrawable(new Sprite(heart.getEmptyHeart())));
            }
        }
    }

    public void animateKeyCollection() {
        keyImage.clearActions(); // Clear existing actions
        keyImage.addAction(Actions.sequence(
                Actions.scaleTo(7, 7, 0.3f), // Scale up
                Actions.scaleTo(5, 5, 0.3f)  // Scale down
        ));
    }

    public void animateHeartLoss(int heartIndex) {
        if (heartIndex >= 0 && heartIndex < heartImages.size()) {
            Image heartImage = heartImages.get(heartIndex);
            heartImage.clearActions(); // Clear existing actions
            heartImage.addAction(Actions.sequence(
                    Actions.scaleTo(7f, 7f, 0.3f), // Scale up
                    Actions.scaleTo(5f, 5f, 0.3f)  // Scale down
            ));
        }
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
