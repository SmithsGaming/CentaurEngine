package smithsgaming.centaurengine.util.queue;

/**
 * Created by Tim on 18/02/2016.
 */
public interface IPriorityQueue<E> extends Iterable<E> {

    void enqueue(E node, double priority);

    E dequeue();

    void clear();

    boolean contains(E node);

    void remove(E node);

    void updatePriority(E node, double priority);

    E first();

    int size();

}
