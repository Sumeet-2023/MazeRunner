package de.tum.cit.ase.maze.objects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Decoration {
    private Sprite boat;
    private TextureRegion water;
    public Decoration(){
        loadBoat();
        loadWater();
    }
    public void loadBoat(){
        Texture boatTexture = new Texture(Gdx.files.internal("Boat.png"));
        boat = new Sprite(boatTexture, 0, 0, 80, 32);
    }
    public void loadWater(){
        Texture waterTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        water = new TextureRegion(waterTexture, 4*24, 7*24, 24, 24);
    }

    public Sprite getBoat() {
        return boat;
    }

    public TextureRegion getWater() {
        return water;
    }
}
