package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Door {
    private Sprite horizontalDoor;
    public Door(){
        loadHorizontalDoor();
    }
    public void loadHorizontalDoor(){
        Texture doorTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalDoor = new Sprite(doorTexture, 2*16, 6*16, 16, 16);
    }
    public Sprite getHorizontalDoor() {
        return horizontalDoor;
    }
}
