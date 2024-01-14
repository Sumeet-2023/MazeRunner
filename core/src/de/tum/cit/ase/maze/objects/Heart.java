package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Heart {
    private TextureRegion heart;
    private TextureRegion emptyHeart;

    public Heart(){
        loadTextureRegions();
    }

    private void loadTextureRegions(){
        Texture heartSheet = new Texture(Gdx.files.internal("objects.png"));
        this.heart = new TextureRegion(heartSheet, 4 * 16, 0 * 16, 16, 16);
        this.emptyHeart = new TextureRegion(heartSheet, 8 * 16, 0 * 16, 16, 16);
    }

    public TextureRegion getHeart() {
        return heart;
    }

    public TextureRegion getEmptyHeart() {
        return emptyHeart;
    }

}
