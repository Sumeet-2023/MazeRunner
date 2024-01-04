package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Door {
    private TextureRegion door;
    private Animation<TextureRegion> doorOpenAnimation;
    public Door(){
        loadDoor();
        loadDoorOpenAnimation();
    }
    public void loadDoorOpenAnimation(){
        Texture doorSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<TextureRegion> doorFrames=new Array<>(TextureRegion.class);
        for(int row=0;row<animationFrame;row++){
            doorFrames.add(new TextureRegion(doorSheet,0,row*16,16,16));
        }
        doorOpenAnimation = new Animation<>(0.4f,doorFrames);
    }
    public void loadDoor(){
        Texture doorTexture = new Texture(Gdx.files.internal("things.png"));
        door = new TextureRegion(doorTexture, 1, 0, 16, 16);
    }

    public TextureRegion getDoor() {
        return door;
    }

    public Animation<TextureRegion> getDoorOpenAnimation() {
        return doorOpenAnimation;
    }
}
