package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tum.cit.ase.maze.MazeRunnerGame;
import com.badlogic.gdx.utils.Array;

public class Wall {
    private Sprite horizontalWall;
    private Sprite HOrangeWall;
    private TextureRegion cornerWall;
    private TextureRegion orangeCornerWall;
    private TextureRegion tree;
    private TextureRegion well;
    private TextureRegion stoneWall;

    public Wall(){
        loadHorizontalWall();
        loadHOrangeWall();
        loadOrangeCornerWall();
        loadCornerWall();
        loadTree();
        loadWell();
        loadStoneWall();
    }

    public void loadHorizontalWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalWall = new Sprite(wallTexture, 16 * 2, 0, 16, 16);
    }
    public void loadHOrangeWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        HOrangeWall = new Sprite(wallTexture, 16 * 6, 0, 16, 16);
    }
    public void loadCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        cornerWall = new TextureRegion(wallTexture, 16*3, 0, 16, 16);
    }
    public void loadOrangeCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        orangeCornerWall = new TextureRegion(wallTexture, 16*7, 0, 16, 16);
    }
    public void loadTree() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        tree = new TextureRegion(wallTexture, 16*4, 9*16, 16, 16);
    }
    public void loadWell() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        well = new TextureRegion(wallTexture, 16*7, 3*16, 16, 16);
    }
    public void loadStoneWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        stoneWall = new TextureRegion(wallTexture, 16 * 2, 7*16, 16, 16);
    }
    public Sprite getHorizontalWall() {
        return horizontalWall;
    }

    public Sprite getHOrangeWall() {
        return HOrangeWall;
    }

    public TextureRegion getCornerWall() {
        return cornerWall;
    }

    public TextureRegion getOrangeCornerWall() {
        return orangeCornerWall;
    }

    public TextureRegion getTree() {
        return tree;
    }

    public TextureRegion getWell() {
        return well;
    }

    public TextureRegion getStoneWall() {
        return stoneWall;
    }
}
