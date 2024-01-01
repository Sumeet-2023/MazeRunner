package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Door {
    private TextureRegion door;
    public Door(){
        loadDoor();
    }
    public void loadDoor(){
        Texture doorTexture = new Texture(Gdx.files.internal("things.png"));
        door = new TextureRegion(doorTexture, 1, 0, 16, 16);
    }

    public TextureRegion getDoor() {
        return door;
    }
}
