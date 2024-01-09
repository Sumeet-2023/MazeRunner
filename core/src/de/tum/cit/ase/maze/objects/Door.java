package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

public class Door {
    private Sprite horizontalDoor;
    private Animation<TextureRegion> HdoorOpenAnimation;
    private Animation<Sprite> VdoorOpenAnimation;
    public Door(){
        loadHorizontalDoor();
        loadHDoorOpenAnimation();
        loadVDoorOpenAnimation();
    }
    public void loadHDoorOpenAnimation(){
        Texture doorSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<TextureRegion> doorFrames=new Array<>(TextureRegion.class);
        for(int row=0;row<animationFrame;row++){
            doorFrames.add(new TextureRegion(doorSheet,0,row*16,16,16));
        }
        HdoorOpenAnimation = new Animation<>(0.4f,doorFrames);
    }
    public void loadVDoorOpenAnimation(){
        Texture doorSheet =new Texture(Gdx.files.internal("things.png"));
        int animationFrame=4;
        Array<Sprite> doorFrames = new Array<>(Sprite.class);

        for(int row=0;row<animationFrame;row++){
          Sprite frame = new Sprite(doorSheet,0,row*16,16,16);
          frame.setRotation(90);
          doorFrames.add(frame);
        }

        VdoorOpenAnimation = new Animation<>(0.4f,doorFrames);
    }
    public void loadHorizontalDoor(){
        Texture doorTexture = new Texture(Gdx.files.internal("things.png"));
        horizontalDoor = new Sprite(doorTexture, 1, 0, 16, 16);
    }

    public Sprite getHorizontalDoor() {
        return horizontalDoor;
    }

    public Animation<TextureRegion> getHdoorOpenAnimation() {
        return HdoorOpenAnimation;
    }

    public Animation<Sprite> getVdoorOpenAnimation() {
        return VdoorOpenAnimation;
    }
}
