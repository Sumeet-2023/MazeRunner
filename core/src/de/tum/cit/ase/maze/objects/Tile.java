package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Represents the floor of the game in MazeRunnerGame.
 * This class includes tiles Texture.
 */
public class Tile {
    private TextureRegion tile;
    private TextureRegion islandGrass;

    /**
     * Constructor of Tile initializes all the methods in Tile class.
     */
    public Tile(){
        loadTile();
        loadIslandGrass();
    }
    /**
     *Methods loads the TextureRegion/Sprite for the tile.
     */
    public void loadTile()
    {
        Texture tileTexture = new Texture(Gdx.files.internal("basictiles.png"));
        tile = new TextureRegion(tileTexture, 16, 8*16, 16, 16);
    }
    public void loadIslandGrass(){
        Texture tileTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandGrass = new TextureRegion(tileTexture, 0, 0, 27, 24);
    }
    /**
     * Getters for all the tile attributes.
     */
    public TextureRegion getTile() {
        return tile;
    }
    public TextureRegion getIslandGrass() {
        return islandGrass;
    }

}
