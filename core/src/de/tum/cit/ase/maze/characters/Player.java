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
    public Player(float x, float y,String level){
        this.loadDownCharacterAnimation(level); // Load Down character animation
        this.loadRightCharacterAnimation(level);
        this.loadUpCharacterAnimation(level);
        this.loadLeftCharacterAnimation(level);
        this.loadDownCharacter(level);
        this.loadRightCharacter(level);
        this.loadUpCharacter(level);
        this.loadLeftCharacter(level);

        defaultFrame = characterRight;
        this.x = x;
        this.y = y;
    }
    public void loadDownCharacterAnimation(String level) {
        if(level.equals("maps//level-1.properties")) {

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
            characterDownAnimation = new Animation<>(0.08f, walkFrames);
        } else if (level.equals("maps//level-2.properties")) {

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
            characterDownAnimation = new Animation<>(0.08f, walkFrames);
        }
        else if(level.equals("maps//level-3.properties")){

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
            characterDownAnimation = new Animation<>(0.08f, walkFrames);
        } else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties") ) {

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
            characterDownAnimation = new Animation<>(0.08f, walkFrames);
        }
        else {
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
            characterDownAnimation = new Animation<>(0.08f, walkFrames);
        }

    }
    public void loadRightCharacterAnimation(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight, frameWidth, frameHeight));
            }
            characterRightAnimation = new Animation<>(0.08f, walkFrames);
        }
        else if(level.equals("maps//level-2.properties")){
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,2*frameHeight,frameWidth,frameHeight));
            }
            characterRightAnimation =new Animation<>(0.08f,walkFrames);
        } else if (level.equals("maps//level-3.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =6;col< animationFrame+6;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,6*frameHeight,frameWidth,frameHeight));
            }
            characterRightAnimation =new Animation<>(0.08f,walkFrames);
        } else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,6*frameHeight,frameWidth,frameHeight));
            }
            characterRightAnimation =new Animation<>(0.08f,walkFrames);
        }
        else{
            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight, frameWidth, frameHeight));
            }
            characterRightAnimation = new Animation<>(0.08f, walkFrames);
        }
    }
    public void loadUpCharacterAnimation(String level){
        if(level.equals("maps//level-1.properties")) {

            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight * 2, frameWidth, frameHeight));
            }
            characterUpAnimation = new Animation<>(0.08f, walkFrames);
        } else if (level.equals("maps//level-2.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,3*frameHeight,frameWidth,frameHeight));
            }
            characterUpAnimation =new Animation<>(0.08f,walkFrames);
        } else if (level.equals("maps//level-3.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =6;col< animationFrame+6;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,7*frameHeight,frameWidth,frameHeight));
            }
            characterUpAnimation =new Animation<>(0.08f,walkFrames);
        } else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,7*frameHeight,frameWidth,frameHeight));
            }
            characterUpAnimation =new Animation<>(0.08f,walkFrames);
        }
        else {
            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight * 2, frameWidth, frameHeight));
            }
            characterUpAnimation = new Animation<>(0.08f, walkFrames);
        }
    }
    public void loadLeftCharacterAnimation(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight * 3, frameWidth, frameHeight));
            }
            characterLeftAnimation = new Animation<>(0.08f, walkFrames);
        } else if (level.equals("maps//level-2.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,frameHeight,frameWidth,frameHeight));
            }
            characterLeftAnimation =new Animation<>(0.08f,walkFrames);
        }
        else if(level.equals("maps//level-3.properties")){
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =6;col< animationFrame+6;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,5*frameHeight,frameWidth,frameHeight));
            }
            characterLeftAnimation =new Animation<>(0.08f,walkFrames);
        }
        else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture walkSheet = new Texture(Gdx.files.internal("motw.png"));
            int frameWidth=52;
            int frameHeight=72;
            int animationFrame=3;
            Array<TextureRegion> walkFrames= new Array<>(TextureRegion.class);
            for (int col =0;col< animationFrame ;col++){
                walkFrames.add(new TextureRegion(walkSheet,col*frameWidth,5*frameHeight,frameWidth,frameHeight));
            }
            characterLeftAnimation =new Animation<>(0.08f,walkFrames);
        }
        else {
            Texture walkSheet = new Texture(Gdx.files.internal("character.png"));
            int frameWidth = 16;
            int frameHeight = 32;
            int animationFrame = 4;
            Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);
            for (int col = 0; col < animationFrame; col++) {
                walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, frameHeight * 3, frameWidth, frameHeight));
            }
            characterLeftAnimation = new Animation<>(0.08f, walkFrames);
        }
    }

    public void loadDownCharacter(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture downTexture = new Texture(Gdx.files.internal("character.png"));
            characterDown = new TextureRegion(downTexture, 0, 0, 16, 32);
        }
        else if(level.equals("maps//level-2.properties")){
            Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
            characterDown = new TextureRegion(downTexture, 0, 0, 52, 72);
        }
        else if(level.equals("maps//level-3.properties")){
            Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
            characterDown = new TextureRegion(downTexture, 6*52, 4*72, 52, 72);
        }
        else if(level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")){
            Texture downTexture = new Texture(Gdx.files.internal("motw.png"));
            characterDown = new TextureRegion(downTexture, 0, 4*72, 52, 72);
        }
        else{
            Texture downTexture = new Texture(Gdx.files.internal("character.png"));
            characterDown = new TextureRegion(downTexture, 0, 0, 16, 32);
        }
    }
    public void loadRightCharacter(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture rightTexture = new Texture(Gdx.files.internal("character.png"));
            characterRight = new TextureRegion(rightTexture, 0, 32, 16, 32);
        } else if (level.equals("maps//level-2.properties")) {
            Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
            characterRight = new TextureRegion(rightTexture, 0, 2*72, 52, 72);
        }
        else if (level.equals("maps//level-3.properties")) {
            Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
            characterRight = new TextureRegion(rightTexture, 6*52, 6*72, 52, 72);
        }
        else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture rightTexture = new Texture(Gdx.files.internal("motw.png"));
            characterRight = new TextureRegion(rightTexture, 0, 6*72, 52, 72);
        }
        else {
            Texture rightTexture = new Texture(Gdx.files.internal("character.png"));
            characterRight = new TextureRegion(rightTexture, 0, 32, 16, 32);
        }

    }
    public void loadUpCharacter(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture upTexture = new Texture(Gdx.files.internal("character.png"));
            characterUp = new TextureRegion(upTexture, 0, 2 * 32, 16, 32);
        } else if (level.equals("maps//level-2.properties")) {
            Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
            characterUp = new TextureRegion(upTexture, 0, 3*72, 52, 72);
        }
        else if (level.equals("maps//level-3.properties")) {
            Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
            characterUp = new TextureRegion(upTexture, 6*52, 7*72, 52, 72);
        }
        else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture upTexture = new Texture(Gdx.files.internal("motw.png"));
            characterUp = new TextureRegion(upTexture, 0, 7*72, 52, 72);
        }
        else {
            Texture upTexture = new Texture(Gdx.files.internal("character.png"));
            characterUp = new TextureRegion(upTexture, 0, 2 * 32, 16, 32);
        }
    }
    public void loadLeftCharacter(String level){
        if(level.equals("maps//level-1.properties")) {
            Texture leftTexture = new Texture(Gdx.files.internal("character.png"));
            characterLeft = new TextureRegion(leftTexture, 0, 3 * 32, 16, 32);
        }
        else if (level.equals("maps//level-2.properties")) {
            Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
            characterLeft = new TextureRegion(leftTexture, 0, 72, 52, 72);
        }
        else if (level.equals("maps//level-3.properties")) {
            Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
            characterLeft = new TextureRegion(leftTexture, 6*52, 5*72, 52, 72);
        }
        else if (level.equals("maps//level-4.properties") || level.equals("maps//level-5.properties")) {
            Texture leftTexture = new Texture(Gdx.files.internal("motw.png"));
            characterLeft = new TextureRegion(leftTexture, 0, 5*72, 52, 72);
        }
        else
        {
            Texture leftTexture = new Texture(Gdx.files.internal("character.png"));
            characterLeft = new TextureRegion(leftTexture, 0, 3 * 32, 16, 32);
        }
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
