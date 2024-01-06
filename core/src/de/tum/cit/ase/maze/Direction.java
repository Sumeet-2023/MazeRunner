package de.tum.cit.ase.maze;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN;

    private static final Random RANDOM = new Random();

    public static Direction getRandomDirection() {
        Direction[] directions = Direction.values();
        return directions[RANDOM.nextInt(directions.length)];
    }
    public static Direction getRandomDirectionExcept(Direction excludeDirection) {
        List<Direction> values = Arrays.asList(Direction.values());
        Collections.shuffle(values); // Shuffle the list for randomness

        // Iterate over the shuffled list and return the first direction not equal to the excluded one
        for (Direction dir : values) {
            if (dir != excludeDirection) {
                return dir;
            }
        }

        return null; // In case all directions are excluded, which shouldn't happen in this context
    }
}
