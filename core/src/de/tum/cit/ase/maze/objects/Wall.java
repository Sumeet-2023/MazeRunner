package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tum.cit.ase.maze.MazeRunnerGame;

public class Wall {
    private TextureRegion horizontalWall;
    private Sprite verticalWall;
    private TextureRegion cornerWall;

    public Wall(){
        loadHorizontalWall();
        loadVerticalWall();
        loadCornerWall();
    }

    public void loadHorizontalWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalWall = new TextureRegion(wallTexture, 16 * 4, 0, 16, 16);
    }
    public void loadVerticalWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        verticalWall = new Sprite(wallTexture,16*4,0,16,16);
    }
    public void loadCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        cornerWall = new TextureRegion(wallTexture, 16*7, 0, 16, 16);
    }

    public TextureRegion getHorizontalWall() {
        return horizontalWall;
    }

    public Sprite getVerticalWall() {
        return verticalWall;
    }

    public TextureRegion getCornerWall() {
        return cornerWall;
    }
}
