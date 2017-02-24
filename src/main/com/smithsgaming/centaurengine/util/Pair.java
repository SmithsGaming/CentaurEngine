package smithsgaming.centaurengine.util;

/**
 * Created by Tim on 14/09/2016.
 */
public class Pair<V> {

    private V first, second;

    public Pair(V a, V b) {
        first = a;
        second = b;
    }

    public V getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

}