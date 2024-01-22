package de.tum.cit.ase.maze.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import de.tum.cit.ase.maze.Direction;

import java.util.List;

public class Ghost{
    private Animation<TextureRegion> ghostDownAnimation;
    public Ghost(){
        loadDownGhostAnimation();
    }
    private void loadDownGhostAnimation(){
        Texture walkSheet = new Texture(Gdx.files.internal("mobs.png"));
        int frameWidth = 16;
        int frameHeight = 16;
        int animationFrames = 3;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 6; col < animationFrames+6; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 4*frameHeight,frameWidth, frameHeight));
        }
        ghostDownAnimation = new Animation<>(0.08f, walkFrames);
    }
    public Animation<TextureRegion> getGhostDownAnimation() {
        return ghostDownAnimation;
    }
}
