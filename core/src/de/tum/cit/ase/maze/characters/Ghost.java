package de.tum.cit.ase.maze.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import de.tum.cit.ase.maze.Direction;

import java.util.List;

public class Ghost{
    private Animation<TextureRegion> ghostDownAnimation;
    private Animation<TextureRegion> ghostRightAnimation;
    private Animation<TextureRegion> ghostUpAnimation;
    private Animation<TextureRegion> ghostLeftAnimation;
    private TextureRegion ghostRight;
    private TextureRegion ghostUp;
    private TextureRegion ghostLeft;
    private TextureRegion ghostDown;




    public Ghost(){
        this.loadRightGhostAnimation();
        this.loadDownGhostAnimation();
        this.loadLeftGhostAnimation();
        this.loadUpGhostAnimation();
        this.loadDownGhost();
        this.loadRightGhost();
        this.loadLeftGhost();
        this.loadUpGhost();

    }



    private void loadDownGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrames = 3;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 6; col < animationFrames+6; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 4*frameHeight,frameWidth, frameHeight));
        }
        ghostDownAnimation = new Animation<>(0.08f, walkFrames);
    }
    public void loadRightGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,6*frameHeight,frameWidth,frameHeight));
        }
        ghostRightAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadUpGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,7*frameHeight,frameWidth,frameHeight));
        }
        ghostUpAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadLeftGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth=16;
        int frameHeight=16;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,5*frameHeight,frameWidth,frameHeight));
        }
        ghostLeftAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadDownGhost(){
        Texture downTexture = new Texture(Gdx.files.internal("mobs.png"));
        ghostDown = new TextureRegion(downTexture, 6*16, 4*16, 16, 16);
    }
    public void loadRightGhost(){
        Texture rightTexture = new Texture(Gdx.files.internal("mobs.png"));
        ghostRight = new TextureRegion(rightTexture, 6*16, 6*16, 16, 16);
    }
    public void loadUpGhost(){
        Texture upTexture = new Texture(Gdx.files.internal("mobs.png"));
        ghostUp = new TextureRegion(upTexture, 6*16, 7*16, 16, 16);
    }
    public void loadLeftGhost(){
        Texture leftTexture = new Texture(Gdx.files.internal("mobs.png"));
        ghostLeft = new TextureRegion(leftTexture, 6*16, 5*16, 16, 16);
    }



    public Animation<TextureRegion> getGhostDownAnimation() {
        return ghostDownAnimation;
    }

    public Animation<TextureRegion> getGhostRightAnimation() {
        return ghostRightAnimation;
    }

    public Animation<TextureRegion> getGhostUpAnimation() {
        return ghostUpAnimation;
    }

    public Animation<TextureRegion> getGhostLeftAnimation() {
        return ghostLeftAnimation;
    }

    public TextureRegion getGhostRight() {
        return ghostRight;
    }

    public TextureRegion getGhostUp() {
        return ghostUp;
    }

    public TextureRegion getGhostLeft() {
        return ghostLeft;
    }

    public TextureRegion getGhostDown() {
        return ghostDown;
    }
}
