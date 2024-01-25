package de.tum.cit.ase.maze;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The Direction enum defines the possible directions in the MazeRunnerGame.
 * It is used to represent the direction of movement or orientation for various game elements like players and enemies.
 */
public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    private static final Random RANDOM = new Random();

    /**
     * Generates a random direction from the available directions.
     * This method is useful for assigning random movement or orientation to game elements.
     *
     * @return A randomly selected Direction value.
     */
    public static Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[RANDOM.nextInt(directions.length)];
    }

    /**
     * Generates a random direction, excluding a specified direction.
     * This method is particularly useful when you want to change the direction of a game element
     * to any direction other than its current one.
     *
     * @param excludeDirection The direction to be excluded from the random selection.
     * @return A randomly selected Direction value that is not the excluded direction.
     *         Returns null if all directions are excluded, which is an unlikely scenario.
     */
    public static Direction getRandomDirectionExcept(Direction excludeDirection) {
        List<Direction> values = Arrays.asList(Direction.values());
        Collections.shuffle(values); // Shuffle the list for randomness
        for (Direction dir : values) {
            if (dir != excludeDirection) {
                return dir;
            }
        }
        return null;
    }
}
