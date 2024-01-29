package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates static obstacles in the MazeRunnerGame.
 * It includes functionalities for loading obstacle animations.
 */

public class Obstacle {
     private Animation<TextureRegion> spikeAnimation;
     private Animation<TextureRegion> fireAnimation;
     private Animation<TextureRegion> flameAnimation;
     private Animation<TextureRegion> poisonAnimation;

    /**
     * Constructor initializes all the methods created for obstacles animation.
     */
    public Obstacle(){
         this.loadSpikeAnimation();
         this.loadFireAnimation();
         this.loadFlameAnimation();
         this.loadPoisonAnimation();
    }

    /**
     * Methods loads the animations for the obstacle from respective files.
     */
     public void loadSpikeAnimation(){
         Texture spikeSheet =new Texture(Gdx.files.internal("things.png"));
         int animationFrame=3;
        Array<TextureRegion> spikeFrames=new Array<>(TextureRegion.class);
         for(int col=6;col<animationFrame+6;col++){
             spikeFrames.add(new TextureRegion(spikeSheet,col*16,6*16,16,16));
         }
         for(int col=8;col>=6;col--){
             spikeFrames.add(new TextureRegion(spikeSheet,col*16,6*16,16,16));
         }

         spikeAnimation=new Animation<>(0.4f,spikeFrames);
     }
    public void loadFireAnimation() {
        Texture fireSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrame = 7;

        Array<TextureRegion> fireFrames = new Array<>(TextureRegion.class);

        for (int col = 4; col < animationFrame + 4; col++){
            fireFrames.add(new TextureRegion(fireSheet, col * frameWidth, 3 * frameHeight, frameWidth, frameHeight));
        }
        fireAnimation = new Animation<>(0.1f, fireFrames);
    }
    public void loadFlameAnimation() {
        Texture flameSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 32;
        int frameHeight = 32;
        int animationFrame = 9;

        Array<TextureRegion> flameFrames = new Array<>(TextureRegion.class);

        for (int col = 8; col < animationFrame +8; col++){
            flameFrames.add(new TextureRegion(flameSheet, col * frameWidth, frameHeight, frameWidth, frameHeight));
        }
        flameAnimation = new Animation<>(0.1f, flameFrames);
    }
    public void loadPoisonAnimation() {
        Texture poisonSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 32;
        int frameHeight = 32;
        int animationFrame = 8;

        Array<TextureRegion> poisonFrames = new Array<>(TextureRegion.class);

        for (int col = 2; col < animationFrame +2; col++){
            poisonFrames.add(new TextureRegion(poisonSheet, col * frameWidth, 2 * frameHeight, frameWidth, frameHeight));
        }

        poisonAnimation = new Animation<>(0.2f, poisonFrames);
    }

    /**
     * Getters for all the obstacle attribute.
     */

    public Animation<TextureRegion> getSpikeAnimation() {
        return spikeAnimation;
    }

    public Animation<TextureRegion> getFireAnimation() {
        return fireAnimation;
    }

    public Animation<TextureRegion> getFlameAnimation() {
        return flameAnimation;
    }

    public Animation<TextureRegion> getPoisonAnimation() {
        return poisonAnimation;
    }
}
