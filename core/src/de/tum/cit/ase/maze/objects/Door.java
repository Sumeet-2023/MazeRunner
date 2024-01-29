package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This Class contains door texture (at exit coordinates) for MazeRunnerGame.
 */
public class Door {
    private Sprite horizontalDoor;

    /**
     * Constructor initializes method which stores door Texture.
     */
    public Door(){
        loadHorizontalDoor();
    }

    /**
     * Method loads the door Texture in horizontalDoor.
     */
    public void loadHorizontalDoor(){
        Texture doorTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalDoor = new Sprite(doorTexture, 2*16, 6*16, 16, 16);
    }

    /**
     * Getter for horizontalDoor attribute.
     */
    public Sprite getHorizontalDoor() {
        return horizontalDoor;
    }
}
