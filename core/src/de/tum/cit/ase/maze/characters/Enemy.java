package de.tum.cit.ase.maze.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.tum.cit.ase.maze.Direction;
import de.tum.cit.ase.maze.MapLoader;
import de.tum.cit.ase.maze.Utils;

/**
 * Represents an enemy character in the MazeRunnerGame.
 * The Enemy class is responsible for managing the position, movement, and rendering of enemy characters within the game.
 * Enemies move within the map and change direction when encountering obstacles.
 */
public class Enemy {
    protected float x, y;
    protected float speed;
    protected Direction direction;
    private final MapLoader mapLoader;

    /**
     * Constructs an Enemy object with a specified position, speed, direction, and map context.
     *
     * @param x The initial x-coordinate of the enemy.
     * @param y The initial y-coordinate of the enemy.
     * @param speed The movement speed of the enemy.
     * @param direction The initial direction of movement for the enemy.
     * @param mapLoader The MapLoader instance, used for determining valid movement within the game map.
     */
    public Enemy(float x, float y, float speed, Direction direction, MapLoader mapLoader) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
        this.mapLoader = mapLoader;
    }

    /**
     * Draws the enemy character on the screen using the provided sprite batch and animation.
     * The method calculates the correct frame of the animation based on the given sinus input.
     *
     * @param spriteBatch The SpriteBatch used for drawing.
     * @param sinusInput A float value representing the time elapsed, used for determining the current animation frame.
     * @param animation The animation object containing the enemy's sprite frames.
     */
    public void drawEnemy(SpriteBatch spriteBatch, float sinusInput, Animation<TextureRegion> animation) {
        spriteBatch.draw(animation.getKeyFrame(sinusInput,true), x * 32, y * 32, 32, 32);
    }

    /**
     * Updates the enemy's position and direction based on the game state.
     * The enemy will move in its current direction if possible or change to a new direction when encountering obstacles.
     *
     * @param deltaTime The time in seconds since the last frame, used for calculating movement.
     */
    public void handleEnemy(float deltaTime) {
        if (Utils.canCharacterMove(x, y, direction, mapLoader, false))
            moveStraight(deltaTime, direction);
        else
            setNewDirection();
    }

    /**
     * Moves the enemy straight in its current direction.
     * The distance moved is based on the enemy's speed and the time elapsed since the last update.
     *
     * @param deltaTime The time in seconds since the last frame, used for calculating movement.
     * @param direction The direction in which the enemy is currently moving.
     */
    public void moveStraight(float deltaTime, Direction direction) {
        if (direction == Direction.RIGHT) {
            x += (float) (0.2 * speed * deltaTime);
        } else if (direction == Direction.LEFT) {
            x -= (float) 0.2 * speed * deltaTime;
        } else if (direction == Direction.DOWN) {
            y -= (float) 0.2 * speed * deltaTime;
        } else if (direction == Direction.UP) {
            y += (float) 0.2 * speed * deltaTime;
        }
    }

    /**
     * Changes the enemy's direction to a new random direction, excluding the current direction.
     * This method is called when the enemy encounters an obstacle in its path.
     */
    public void setNewDirection() {
        Direction oldDirection = direction;
        direction = Direction.getRandomDirectionExcept(oldDirection);
    }

    /**
     * Getter methods for the enemy's current position.
     * getX() returns the current x-coordinate, and getY() returns the current y-coordinate.
     * @return The current x-coordinate or y-coordinate of the enemy.
     */
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
}
