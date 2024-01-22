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
    private TextureRegion islandTree;
    private TextureRegion tree;
    private TextureRegion staircase;
    public Decoration(){
        loadBoat();
        loadWater();
        loadTree();
        loadIslandTree();
        loadStaircase();
    }
    public void loadBoat(){
        Texture boatTexture = new Texture(Gdx.files.internal("Boat.png"));
        boat = new Sprite(boatTexture, 0, 0, 80, 32);
    }
    public void loadWater(){
        Texture waterTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        water = new TextureRegion(waterTexture, 4*24, 7*24, 24, 24);
    }
    public void loadIslandTree(){
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        islandTree = new TextureRegion(wallTexture, 16*6, 3*16, 16, 16);
    }
    public void loadTree() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        tree = new TextureRegion(wallTexture, 16*4, 9*16, 16, 16);
    }
    public void loadStaircase() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        staircase = new TextureRegion(wallTexture, 16, 7*16, 16, 16);
    }
    public Sprite getBoat() {
        return boat;
    }

    public TextureRegion getWater() {
        return water;
    }

    public TextureRegion getIslandTree() {
        return islandTree;
    }

    public TextureRegion getTree() {
        return tree;
    }

    public TextureRegion getStaircase() {
        return staircase;
    }
}
