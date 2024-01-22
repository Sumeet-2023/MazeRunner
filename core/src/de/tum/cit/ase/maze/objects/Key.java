package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Key {
    private TextureRegion key;
    public Key(){
        loadKey();
    }
    public void loadKey(){
        Texture keyTexture = new Texture(Gdx.files.internal("keys_1_1.png"));
        key = new TextureRegion(keyTexture,16,16);
    }

    public TextureRegion getKey() {
        return key;
    }
}
