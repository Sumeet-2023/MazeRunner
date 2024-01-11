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
    // 2nd Character Animation
    private Animation<TextureRegion> character2DownAnimation;
    private Animation<TextureRegion> character2RightAnimation;
    private Animation<TextureRegion> character2UpAnimation;
    private Animation<TextureRegion> character2LeftAnimation;
    private TextureRegion characterDown;
    private TextureRegion characterRight;
    private TextureRegion characterUp;
    private TextureRegion characterLeft;

    // 2nd Character TextureRegion
    private TextureRegion character2Down;
    private TextureRegion character2Right;
    private TextureRegion character2Up;
    private TextureRegion character2Left;
    //3rd Character Animation
    private Animation<TextureRegion> character3DownAnimation;
    private Animation<TextureRegion> character3RightAnimation;
    private Animation<TextureRegion> character3UpAnimation;
    private Animation<TextureRegion> character3LeftAnimation;

    //3rd Character Animation
    private TextureRegion character3Down;
    private TextureRegion character3Right;
    private TextureRegion character3Up;
    private TextureRegion character3Left;
    //4th Character
    private Animation<TextureRegion> character4DownAnimation;
    private Animation<TextureRegion> character4RightAnimation;
    private Animation<TextureRegion> character4UpAnimation;
    private Animation<TextureRegion> character4LeftAnimation;

    //3rd Character Animation
    private TextureRegion character4Down;
    private TextureRegion character4Right;
    private TextureRegion character4Up;
    private TextureRegion character4Left;

    private boolean isAnimating = false;
    private float animationTime = 0f;
    private Animation<TextureRegion> playerAnimation = null;
    private float x;
    private float y;
    private boolean hasKey = false;
    private TextureRegion defaultFrame;
    public Player(float x, float y){
        this.loadDownCharacterAnimation(); // Load Down character animation
        this.loadRightCharacterAnimation();
        this.loadUpCharacterAnimation();
        this.loadLeftCharacterAnimation();
        this.loadDownCharacter();
        this.loadRightCharacter();
        this.loadUpCharacter();
        this.loadLeftCharacter();
        // 2nd Character
        this.loadDownCharacter2Animation(); // Load Down character animation
        this.loadRightCharacter2Animation();
        this.loadUpCharacter2Animation();
        this.loadLeftCharacter2Animation();
        this.loadDownCharacter2();
        this.loadRightCharacter2();
        this.loadUpCharacter2();
        this.loadLeftCharacter2();
        // 3rd Character
        this.loadDownCharacter3Animation(); // Load Down character animation
        this.loadRightCharacter3Animation();
        this.loadUpCharacter3Animation();
        this.loadLeftCharacter3Animation();
        this.loadDownCharacter3();
        this.loadRightCharacter3();
        this.loadUpCharacter3();
        this.loadLeftCharacter3();

        this.loadDownCharacter4Animation(); // Load Down character animation
        this.loadRightCharacter4Animation();
        this.loadUpCharacter4Animation();
        this.loadLeftCharacter4Animation();
        this.loadDownCharacter4();
        this.loadRightCharacter4();
        this.loadUpCharacter4();
        this.loadLeftCharacter4();
        defaultFrame = character4Right;
        this.x = x;
        this.y = y;
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

    //2nd Character Animation
    public void loadDownCharacter2Animation() {
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));

        int frameWidth = 52;
        int frameHeight = 72;
        int animationFrames = 3;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0,frameWidth, frameHeight));
        }
        character2DownAnimation = new Animation<>(0.08f, walkFrames);
    }
    public void loadRightCharacter2Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,2*frameHeight,frameWidth,frameHeight));
        }
        character2RightAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadUpCharacter2Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,3*frameHeight,frameWidth,frameHeight));
        }
        character2UpAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadLeftCharacter2Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
        }
        character2LeftAnimation =new Animation<>(0.08f,walkFrames);
    }
    // 3rd Character
    public void loadDownCharacter3Animation() {
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));

        int frameWidth = 52;
        int frameHeight = 72;
        int animationFrames = 3;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 6; col < animationFrames+6; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 4*frameHeight,frameWidth, frameHeight));
        }
        character3DownAnimation = new Animation<>(0.08f, walkFrames);
    }
    public void loadLeftCharacter3Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,5*frameHeight,frameWidth,frameHeight));
        }
        character3LeftAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadRightCharacter3Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,6*frameHeight,frameWidth,frameHeight));
        }
        character3RightAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadUpCharacter3Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =6;col< animationFrame+6;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,7*frameHeight,frameWidth,frameHeight));
        }
        character3UpAnimation =new Animation<>(0.08f,walkFrames);
    }
    //4th Character
    public void loadDownCharacter4Animation() {
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));

        int frameWidth = 52;
        int frameHeight = 72;
        int animationFrames = 3;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 0; col < animationFrames; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 4*frameHeight,frameWidth, frameHeight));
        }
        character4DownAnimation = new Animation<>(0.08f, walkFrames);
    }
    public void loadLeftCharacter4Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame ;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,5*frameHeight,frameWidth,frameHeight));
        }
        character4LeftAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadRightCharacter4Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,6*frameHeight,frameWidth,frameHeight));
        }
        character4RightAnimation =new Animation<>(0.08f,walkFrames);
    }
    public void loadUpCharacter4Animation(){
        Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
        int frameWidth=52;
        int frameHeight=72;
        int animationFrame=3;
        Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
        for (int col =0;col< animationFrame;col++){
            walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,7*frameHeight,frameWidth,frameHeight));
        }
        character4UpAnimation =new Animation<>(0.08f,walkFrames);
    }

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
    public void loadDownCharacter2(){
        Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
        character2Down = new TextureRegion(downTexture, 0, 0, 52, 72);
    }
    public void loadLeftCharacter2(){
        Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
        character2Left = new TextureRegion(leftTexture, 0, 72, 52, 72);
    }
    public void loadRightCharacter2(){
        Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
        character2Right = new TextureRegion(rightTexture, 0, 2*72, 52, 72);
    }
    public void loadUpCharacter2(){
        Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
        character2Up = new TextureRegion(upTexture, 0, 3*72, 52, 72);
    }
    public void loadDownCharacter3(){
        Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
        character3Down = new TextureRegion(downTexture, 6*52, 4*72, 52, 72);
    }
    public void loadLeftCharacter3(){
        Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
        character3Left = new TextureRegion(leftTexture, 6*52, 5*72, 52, 72);
    }
    public void loadRightCharacter3(){
        Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
        character3Right = new TextureRegion(rightTexture, 6*52, 6*72, 52, 72);
    }
    public void loadUpCharacter3(){
        Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
        character3Up = new TextureRegion(upTexture, 6*52, 7*72, 52, 72);
    }
    public void loadDownCharacter4(){
        Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
        character4Down = new TextureRegion(downTexture, 0, 4*72, 52, 72);
    }
    public void loadLeftCharacter4(){
        Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
        character4Left = new TextureRegion(leftTexture, 0, 5*72, 52, 72);
    }
    public void loadRightCharacter4(){
        Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
        character4Right = new TextureRegion(rightTexture, 0, 6*72, 52, 72);
    }
    public void loadUpCharacter4(){
        Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
        character4Up = new TextureRegion(upTexture, 0, 7*72, 52, 72);
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
                animationTime = 0;
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

    public Animation<TextureRegion> getCharacter2DownAnimation() {
        return character2DownAnimation;
    }

    public Animation<TextureRegion> getCharacter2RightAnimation() {
        return character2RightAnimation;
    }

    public Animation<TextureRegion> getCharacter2UpAnimation() {
        return character2UpAnimation;
    }

    public Animation<TextureRegion> getCharacter2LeftAnimation() {
        return character2LeftAnimation;
    }

    public TextureRegion getCharacter2Down() {
        return character2Down;
    }

    public TextureRegion getCharacter2Right() {
        return character2Right;
    }

    public TextureRegion getCharacter2Up() {
        return character2Up;
    }

    public TextureRegion getCharacter2Left() {
        return character2Left;
    }

    public Animation<TextureRegion> getCharacter3DownAnimation() {
        return character3DownAnimation;
    }

    public Animation<TextureRegion> getCharacter3RightAnimation() {
        return character3RightAnimation;
    }

    public Animation<TextureRegion> getCharacter3UpAnimation() {
        return character3UpAnimation;
    }

    public Animation<TextureRegion> getCharacter3LeftAnimation() {
        return character3LeftAnimation;
    }


    public TextureRegion getCharacter3Down() {
        return character3Down;
    }

    public TextureRegion getCharacter3Right() {
        return character3Right;
    }

    public TextureRegion getCharacter3Up() {
        return character3Up;
    }

    public TextureRegion getCharacter3Left() {
        return character3Left;
    }

    public Animation<TextureRegion> getCharacter4DownAnimation() {
        return character4DownAnimation;
    }

    public Animation<TextureRegion> getCharacter4RightAnimation() {
        return character4RightAnimation;
    }

    public Animation<TextureRegion> getCharacter4UpAnimation() {
        return character4UpAnimation;
    }

    public Animation<TextureRegion> getCharacter4LeftAnimation() {
        return character4LeftAnimation;
    }

    public TextureRegion getCharacter4Down() {
        return character4Down;
    }

    public TextureRegion getCharacter4Right() {
        return character4Right;
    }

    public TextureRegion getCharacter4Up() {
        return character4Up;
    }

    public TextureRegion getCharacter4Left() {
        return character4Left;
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
}
