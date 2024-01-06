package de.tum.cit.ase.maze.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tum.cit.ase.maze.Direction;
import de.tum.cit.ase.maze.MapLoader;
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

    public boolean canEnemyMove()
    {
        if (isWall(x + 0.2f, y) && direction == Direction.RIGHT) {
            return false;
        } else if (isWall(x, y + 0.2f) && direction == Direction.UP) {
            return false;
        } else if (isWall(x - 0.3f, y) && direction == Direction.LEFT) {
            return false;
        } else if (isWall(x, y - 0.3f) && direction == Direction.DOWN) {
            return false;
        }
        return true;
    }

    public boolean isWall(float x, float y)
    {
        for (List<Integer> coordinate : mapLoader.getWallCoordinates())
        {
            float wallX = coordinate.get(0);
            float wallY = coordinate.get(1);
            final float tolerance = 0.8f;
            if (Math.abs(x - wallX) < tolerance && Math.abs(y - wallY) < tolerance) {
                return true;
            }
        }
        return false;
    }

    public void handleEnemy(float deltaTime) {
        if (canEnemyMove())
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
