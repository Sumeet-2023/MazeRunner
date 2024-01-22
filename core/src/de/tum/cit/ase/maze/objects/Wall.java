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
    private TextureRegion cornerWall;
    private TextureRegion well;
    private TextureRegion stoneWall;
    private TextureRegion islandCornerWallUpLeft;
    private TextureRegion islandCornerWallUpRight;
    private TextureRegion islandCornerWallDownLeft;
    private TextureRegion islandCornerWallDownRight;
    private TextureRegion islandWallDown;
    private TextureRegion islandWallUp;
    private TextureRegion islandWallRight;
    private TextureRegion islandWallLeft;
    private TextureRegion wood;



    public Wall(){
        loadHorizontalWall();
        loadCornerWall();
        loadWell();
        loadStoneWall();
        loadIslandCornerWallUL();
        loadIslandCornerWallUR();
        loadIslandCornerWallDL();
        loadIslandCornerWallDR();
        loadIslandWallUp();
        loadIslandWallDown();
        loadIslandWallRight();
        loadIslandWallLeft();
        loadWood();
    }

    public void loadHorizontalWall()
    {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalWall = new Sprite(wallTexture, 16 * 2, 0, 16, 16);
    }
    public void loadCornerWall() {
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        cornerWall = new TextureRegion(wallTexture, 16 * 3, 0, 16, 16);
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
    public void loadIslandCornerWallUL(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandCornerWallUpLeft = new TextureRegion(wallTexture, 0, 3*24, 27, 24);
    }
    public void loadIslandCornerWallUR(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandCornerWallUpRight = new TextureRegion(wallTexture, 7*24 + 10 , 3*24, 24, 24);
    }
    public void loadIslandCornerWallDL(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandCornerWallDownLeft = new TextureRegion(wallTexture, 0, 6*24, 27, 24);
    }
    public void loadIslandCornerWallDR(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandCornerWallDownRight = new TextureRegion(wallTexture, 2*27+20, 6*24, 27, 24);
    }
    public void loadIslandWallUp(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandWallUp = new TextureRegion(wallTexture, 27, 3*24, 27, 20);
    }
    public void loadIslandWallDown(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandWallDown = new TextureRegion(wallTexture, 27, 6*24, 27, 24);
    }
    public void loadIslandWallRight(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandWallRight = new TextureRegion(wallTexture, 3*27 , 5*24, 27, 24);
    }
    public void loadIslandWallLeft(){
        Texture wallTexture = new Texture(Gdx.files.internal("Island Tileset.png"));
        islandWallLeft = new TextureRegion(wallTexture, 0, 4*24, 20, 24);
    }
    public  void loadWood(){
        Texture wallTexture = new Texture(Gdx.files.internal("decor.png"));
        wood = new TextureRegion(wallTexture, 24, 2*24, 24, 24);
    }

    public Sprite getHorizontalWall() {
        return horizontalWall;
    }

    public TextureRegion getCornerWall() {
        return cornerWall;
    }

    public TextureRegion getWell() {
        return well;
    }

    public TextureRegion getStoneWall() {
        return stoneWall;
    }

    public TextureRegion getIslandCornerWallUpLeft() {
        return islandCornerWallUpLeft;
    }

    public TextureRegion getIslandCornerWallUpRight() {
        return islandCornerWallUpRight;
    }

    public TextureRegion getIslandCornerWallDownLeft() {
        return islandCornerWallDownLeft;
    }

    public TextureRegion getIslandCornerWallDownRight() {
        return islandCornerWallDownRight;
    }

    public TextureRegion getIslandWallDown() {
        return islandWallDown;
    }

    public TextureRegion getIslandWallUp() {
        return islandWallUp;
    }

    public TextureRegion getIslandWallRight() {
        return islandWallRight;
    }

    public TextureRegion getIslandWallLeft() {
        return islandWallLeft;
    }

    public TextureRegion getWood() {
        return wood;
    }
}
