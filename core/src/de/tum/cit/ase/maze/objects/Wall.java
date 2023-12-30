package de.tum.cit.ase.maze.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Wall {
    private Integer x;
    private Integer y;
    private TextureRegion horizontalWall;

    public Wall(Integer x, Integer y){
        this.x = x;
        this.y = y;
        Texture wallTexture = new Texture(Gdx.files.internal("basictiles.png"));
        horizontalWall = new TextureRegion(wallTexture, 16*4, 0, 16, 16);
    }
    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(horizontalWall, x * 16, y* 16);
    }
}
