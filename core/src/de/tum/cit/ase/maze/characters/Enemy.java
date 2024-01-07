package de.tum.cit.ase.maze.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tum.cit.ase.maze.Direction;
import de.tum.cit.ase.maze.MapLoader;
import de.tum.cit.ase.maze.Utils;
import de.tum.cit.ase.maze.objects.Wall;

import java.util.List;

public class Enemy {
    protected float x, y;
    protected float speed;
    protected Direction direction;
    private float stateTime = 0;
    private MapLoader mapLoader;

    public Enemy(float x, float y, float speed, Direction direction, MapLoader mapLoader) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.mapLoader = mapLoader;
    }


    public void drawEnemy(SpriteBatch spriteBatch, float sinusInput, Animation<TextureRegion> animation)
    {
        spriteBatch.draw(animation.getKeyFrame(sinusInput,true), x * 32, y * 32, 32, 32);
    }



    public void handleEnemy(float deltaTime) {
        if (Utils.canCharacterMove(x, y, direction, mapLoader, false))
            moveStraight(deltaTime, direction);
        else
            setNewDirection();
        stateTime += deltaTime;
    }

    public void moveStraight(float deltaTime, Direction direction)
    {
        if (direction == Direction.RIGHT) {
            x += 0.2 * speed * deltaTime;
        } else if (direction == Direction.LEFT) {
            x -= 0.2 * speed * deltaTime;
        } else if (direction == Direction.DOWN) {
            y -= 0.2 * speed * deltaTime;
        } else if (direction == Direction.UP) {
            y += 0.2 * speed * deltaTime;
        }
    }

    public void setNewDirection()
    {
        Direction oldDirection = direction;
        direction = Direction.getRandomDirectionExcept(oldDirection);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public Direction getDirection() {
        return direction;
    }
}
