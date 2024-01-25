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
import java.util.ArrayList;
import java.util.List;

/**
 * The HUD (Heads-Up Display) class manages the game's on-screen display elements in MazeRunnerGame.
 * This includes displaying the player's hearts and keys. The class updates and animates these elements
 * based on game events such as key collection and heart loss.
 */
public class HUD {
    private final Player player;
    private final Stage stage;
    private final Heart heart;
    private final Key key;
    private Image keyImage;
    private final List<Image> heartImages = new ArrayList<>();

    /**
     * Constructs a HUD instance for the game.
     * Initializes the player's hearts and key and sets up the stage for the HUD elements.
     *
     * @param player The player character whose status is to be displayed on the HUD.
     * @param viewport The viewport in which the HUD elements are to be displayed.
     */
    public HUD(Player player, Viewport viewport) {
        this.player = player;
        this.heart = new Heart();
        this.key = new Key();
        stage = new Stage(viewport);
        setUpHud();
    }

    /**
     * Sets up the HUD elements on the game screen.
     * This method creates and positions the key and heart images, aligning them at the top-right of the screen.
     */
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

    /**
     * Draws the HUD elements on the screen.
     * This method should be called in the game's render loop to continuously update the HUD's appearance.
     */
    public void draw() {
        stage.draw();
    }

    /**
     * Updates the HUD elements based on the player's current status.
     * This includes displaying the key if the player has it, and updating the heart icons to reflect current health.
     */
    public void update() {
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

    /**
     * Animates the key image when the player collects a key.
     * The animation involves scaling the key image up and then down to highlight its collection.
     */
    public void animateKeyCollection() {
        keyImage.clearActions(); // Clear existing actions
        keyImage.addAction(Actions.sequence(
                Actions.scaleTo(7, 7, 0.3f), // Scale up
                Actions.scaleTo(5, 5, 0.3f)  // Scale down
        ));
    }

    /**
     * Animates a heart image when the player loses a heart.
     * The specified heart image is scaled up and then down to indicate the loss.
     *
     * @param heartIndex The index of the heart in the heartImages list to be animated.
     */
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

    /**
     * Returns the Stage object where HUD elements are displayed.
     * This can be used to add or interact with other elements on the same stage.
     *
     * @return The Stage object used by this HUD.
     */
    public Stage getStage() {
        return stage;
    }
}
