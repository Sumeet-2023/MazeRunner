package de.tum.cit.ase.maze.characters;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Represents the player character in the MazeRunnerGame.
 * This class manages the player's animations, position, and state within the game.
 * It includes functionalities for loading character animations.
 */
public class Player {
    private Animation<TextureRegion> characterDownAnimation;
    private Animation<TextureRegion> characterRightAnimation;
    private Animation<TextureRegion> characterUpAnimation;
    private Animation<TextureRegion> characterLeftAnimation;
    private TextureRegion characterDown;
    private TextureRegion characterRight;
    private TextureRegion characterUp;
    private TextureRegion characterLeft;
    private boolean isAnimating = false;
    private float animationTime = 0f;
    private Animation<TextureRegion> playerAnimation = null;
    private float x;
    private float y;
    private boolean hasKey = false;
    private TextureRegion defaultFrame;
    private int heartCount = 3;

    /**
     * Constructs a Player object with specified initial position.
     * It loads various character animations and sets the default frame for the player.
     *
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     */
    public Player(float x, float y){
        this.loadDownCharacterAnimation(); // Load Down character animation
        this.loadRightCharacterAnimation();
        this.loadUpCharacterAnimation();
        this.loadLeftCharacterAnimation();
        this.loadDownCharacter();
        this.loadRightCharacter();
        this.loadUpCharacter();
        this.loadLeftCharacter();
        defaultFrame = characterRight;
        this.x = x;
        this.y = y;
    }

    /**
     * Loads the animation for the player character moving in a specific direction.
     * The method creates an animation from a sprite sheet, specifying frames for each movement direction.
     */
    public void loadDownCharacterAnimation() {
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));

        int frameWidth = 16;
        int frameHeight = 32;
        int animationFrames = 4;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0,frameWidth, frameHeight));
        }
        characterDownAnimation = new Animation<>(0.08f, walkFrames);
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
        characterRightAnimation =new Animation<>(0.08f,walkFrames);
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
        characterUpAnimation =new Animation<>(0.08f,walkFrames);
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
        characterLeftAnimation =new Animation<>(0.08f,walkFrames);
    }

    /**
     * Loads the static texture for the player character facing a specific direction.
     * These textures are used as default frames when the player is not moving.
     */
    public void loadDownCharacter(){
        Texture downTexture = new Texture(Gdx.files.internal("character.png"));
        characterDown = new TextureRegion(downTexture, 0, 0, 16, 32);
    }
    public void loadRightCharacter(){
        Texture rightTexture = new Texture(Gdx.files.internal("character.png"));
        characterRight = new TextureRegion(rightTexture, 0, 32, 16, 32);
    }
    public void loadUpCharacter(){
        Texture upTexture = new Texture(Gdx.files.internal("character.png"));
        characterUp = new TextureRegion(upTexture, 0, 2*32, 16, 32);
    }
    public void loadLeftCharacter(){
        Texture leftTexture = new Texture(Gdx.files.internal("character.png"));
        characterLeft = new TextureRegion(leftTexture, 0, 3*32, 16, 32);
    }

    /**
     * Starts an animation for the player character.
     * Resets the animation time and sets the animation flag to true.
     *
     * @param animation The Animation object to start.
     */
    public void startAnimation(Animation<TextureRegion> animation){
        this.playerAnimation = animation;
        this.animationTime = 0f;
        this.isAnimating = true;
    }

    /**
     * Updates the player's animation state.
     * Increments the animation time and stops the animation when it completes.
     *
     * @param sinusInput The time elapsed since the last frame, used to update the animation time.
     */
    public void update(float sinusInput){
        if (isAnimating){
            animationTime += sinusInput;
            if (animationTime >= playerAnimation.getAnimationDuration()){
                isAnimating = false;
                animationTime = 0;
            }
        }
    }

    /**
     * Retrieves the current frame of the player's animation.
     * Returns null if the player is not animating or if there's no current animation.
     *
     * @return The current TextureRegion frame of the animation, or null if not animating.
     */
    public TextureRegion getCurrentAnimationFrame() {
        if (isAnimating && playerAnimation != null) {
            return playerAnimation.getKeyFrame(animationTime, false);
        }
        return null;
    }

    /**
     * Getter and setter methods for the player's properties.
     * These include methods to get and set the player's position, key possession status, default frame, and heart count.
     */
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
    public TextureRegion getCharacterDown() {
        return characterDown;
    }
    public TextureRegion getCharacterRight() {
        return characterRight;
    }
    public TextureRegion getCharacterUp() {
        return characterUp;
    }
    public TextureRegion getCharacterLeft() {
        return characterLeft;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public boolean getHasKey() {
        return hasKey;
    }
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }
    public TextureRegion getDefaultFrame() {
        return defaultFrame;
    }
    public void setDefaultFrame(TextureRegion defaultFrame) {
        this.defaultFrame = defaultFrame;
    }
    public int getHeartCount() {
        return heartCount;
    }
    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }
}