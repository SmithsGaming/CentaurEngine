package smithsgaming.centaurengine.util.maths;

import java.math.BigDecimal;

/**
 * Created by Tim on 17/02/2016.
 */
public class Maths {

    public static int mag(double x) {
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            return -1;
        }
        return 1;
    }

    public static int clamp(int x, int min, int max) {
        if (x <= min) {
            return min;
        }
        if (x >= max) {
            return max;
        }
        return x;
    }

    public static float clamp(float x, float min, float max) {
        if (x <= min) {
            return min;
        }
        if (x >= max) {
            return max;
        }
        return x;
    }

    public static double clamp(double x, double min, double max) {
        if (x <= min) {
            return min;
        }
        if (x >= max) {
            return max;
        }
        return x;
    }

    public static float round(float target, int precision) {
        BigDecimal bd = new BigDecimal(target);
        BigDecimal rounded = bd.setScale(precision, BigDecimal.ROUND_HALF_UP);
        return rounded.floatValue();
    }
}
