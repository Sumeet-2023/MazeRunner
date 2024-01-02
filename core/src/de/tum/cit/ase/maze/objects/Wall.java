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
    private TextureRegion horizontalWall;
    private Sprite verticalWall;
    private TextureRegion cornerWall;
    private Animation<TextureRegion> flame;
    private TextureRegion tree;
    private TextureRegion well;

    public Wall(){
        loadHorizontalWall();
        loadVerticalWall();
        loadCornerWall();
        loadFlame();
        loadTree();
        loadWell();
    }

    public void loadHorizontalWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalWall = new TextureRegion(wallTexture, 16 * 2, 0, 16, 16);
    }
    public void loadVerticalWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        verticalWall = new Sprite(wallTexture,16*2,0,16,16);
    }
    public void loadCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        cornerWall = new TextureRegion(wallTexture, 16*3, 0, 16, 16);
    }
    public void loadFlame(){
        Texture flameSheet =new Texture(Gdx.files.internal("basictiles.png"));
        int animationFrame=2;
        Array<TextureRegion> flameFrames=new Array<>(TextureRegion.class);
        for(int col=4;col<animationFrame+4;col++){
            flameFrames.add(new TextureRegion(flameSheet,col*16,7*16,16,16));
        }
        flame=new Animation<>(0.6f,flameFrames);
    }
    public void loadTree() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        tree = new TextureRegion(wallTexture, 16*4, 9*16, 16, 16);
    }
    public void loadWell() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        well = new TextureRegion(wallTexture, 16*7, 3*16, 16, 16);
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

    public Animation<TextureRegion> getFlame() {
        return flame;
    }

    public TextureRegion getTree() {
        return tree;
    }

    public TextureRegion getWell() {
        return well;
    }
}
