package de.tum.cit.ase.maze.characters;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player {
    private Animation<TextureRegion> characterDownAnimation;
    private Animation<TextureRegion> characterRightAnimation;
    private Animation<TextureRegion> characterUpAnimation;
    private Animation<TextureRegion> characterLeftAnimation;
    private boolean isAnimating = false;
    private float animationTime = 0f;
    private Animation<TextureRegion> playerAnimation = null;
    public Player(){
        this.loadDownCharacterAnimation(); // Load Down character animation
        this.loadRightCharacterAnimation();
        this.loadUpCharacterAnimation();
        this.loadLeftCharacterAnimation();
    }
    public void loadDownCharacterAnimation() {
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));

        int frameWidth = 16;
        int frameHeight = 32;
        int animationFrames = 4;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0, frameWidth, frameHeight));
        }
        characterDownAnimation = new Animation<>(0.1f, walkFrames);
    }
    public void loadRightCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
        }
        characterRightAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadUpCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight*2,frameWidth,frameHeight));
        }
        characterUpAnimation =new Animation<>(0.1f,walkFrames);
    }
    public void loadLeftCharacterAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
        int frameWidth=16;
        int frameHeight=32;
        int animationFrame=4;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight*3,frameWidth,frameHeight));
        }
        characterLeftAnimation =new Animation<>(0.1f,walkFrames);
    }

    public void startAnimation(Animation<TextureRegion> animation){
        this.playerAnimation = animation;
        this.animationTime = 0f;
        this.isAnimating = true;
    }

    public void update(float sinusInput){
        if (isAnimating){
            animationTime += sinusInput;
            if (animationTime >= playerAnimation.getAnimationDuration()){
                isAnimating = false;
            }
        }
    }

    public TextureRegion getCurrentAnimationFrame() {
        if (isAnimating && playerAnimation != null) {
            return playerAnimation.getKeyFrame(animationTime, false);
        }
        return null;
    }

    public boolean isAnimating() {
        return isAnimating;
    }

    public Animation<TextureRegion> getCharacterDownAnimation() {
        return characterDownAnimation;
    }

    public Animation<TextureRegion> getCharacterRightAnimation() {
        return characterRightAnimation;
    }

    public Animation<TextureRegion> getCharacterUpAnimation() {
        return characterUpAnimation;
    }

    public Animation<TextureRegion> getCharacterLeftAnimation() {
        return characterLeftAnimation;
    }
}
