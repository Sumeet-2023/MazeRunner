package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Heart {
    private TextureRegion heart;
    private Animation<TextureRegion> life;
    private TextureRegion emptyHeart;

    public Heart(){
        loadTextureRegions();
        loadHeartAnimation();
    }

    private void loadTextureRegions(){
        Texture heartSheet = new Texture(Gdx.files.internal("objects.png"));
        this.heart = new TextureRegion(heartSheet, 4 * 16, 0 * 16, 16, 16);
        this.emptyHeart = new TextureRegion(heartSheet, 8 * 16, 0 * 16, 16, 16);
    }
    public void loadHeartAnimation() {
        Texture heartSheet = new Texture(Gdx.files.internal("objects.png"));

        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrame = 4;

        Array<TextureRegion> heartFrames = new Array<>(TextureRegion.class);

        for (int col = 0; col < animationFrame; col++){
            heartFrames.add(new TextureRegion(heartSheet, col * frameWidth, 3 * frameHeight, frameWidth, frameHeight));
        }
        life = new Animation<>(0.2f,heartFrames);
    }

    public TextureRegion getHeart() {
        return heart;
    }
    public TextureRegion getEmptyHeart() {
        return emptyHeart;
    }
    public Animation<TextureRegion> getLife() {
        return life;
    }
}
