package smithsgaming.centaurengine.util;

import smithsgaming.centaurengine.util.maths.Vector2i;

import java.util.Random;

/**
 * Created by Tim on 02/09/2016.
 */
public enum Direction {

    NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0),
    NORTH_EAST(1, 1), SOUTH_EAST(1, -1), SOUTH_WEST(-1, -1), NORTH_WEST(-1, 1);

    private Vector2i step;

    Direction(int x, int y) {
        this.step = new Vector2i(x, y);
    }

    public Vector2i getDirection() {
        return new Vector2i(step);
    }

    public int getX() {
        return step.x;
    }

    public int getY() {
        return step.y;
    }

    /**
     * @return NORTH, EAST, SOUTH or WEST
     */
    public static Direction getRandomNormal() {
        Random random = new Random();
        return Direction.values()[random.nextInt(4)];
    }

}
