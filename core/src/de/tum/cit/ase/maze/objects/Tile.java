package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    private TextureRegion tile;
    private TextureRegion islandGrass;

    public Tile(){
        loadTile();
        loadIslandGrass();
    }

    public void loadTile()
    {
        Texture tileTexture = new Texture(Gdx.files.internal("basictiles.png"));
        tile = new TextureRegion(tileTexture, 16, 8*16, 16, 16);
    }
    public void loadIslandGrass(){
        Texture tileTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandGrass = new TextureRegion(tileTexture, 0, 0, 27, 24);
    }

    public TextureRegion getTile() {
        return tile;
    }
    public TextureRegion getIslandGrass() {
        return islandGrass;
    }

}
