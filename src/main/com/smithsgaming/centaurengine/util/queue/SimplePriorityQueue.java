package smithsgaming.centaurengine.util.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tim on 18/02/2016.
 */
public class SimplePriorityQueue<E> implements IPriorityQueue<E> {

    private class SimpleNode extends FastPriorityQueueNode {

        protected E data;

        public SimpleNode(E data) {
            this.data = data;
        }

        public E getData() {
            return this.data;
        }
    }

    private final int INITIAL_QUEUE_SIZE = 11;
    private FastPriorityQueue<SimpleNode> queue;

    public SimplePriorityQueue() {
        queue = new FastPriorityQueue<>(INITIAL_QUEUE_SIZE);
    }

    public SimplePriorityQueue(int initialSize) {
        queue = new FastPriorityQueue<>(initialSize);
    }

    private SimpleNode getExistingNode(E item) {
        for (SimpleNode node : queue) {
            if (node.data.equals(item)) {
                return (SimpleNode) node;
            }
        }
        throw new UnsupportedOperationException("Item cannot be found in queue");
    }

    @Override
    public int size() {
        synchronized (queue) {
            return queue.size();
        }
    }

    @Override
    public E first() {
        synchronized (queue) {
            if (queue.size() <= 0) {
                throw new UnsupportedOperationException("Cannot call first() on an empty queue");
            }
            SimpleNode first = queue.first();
            return first.getData();
        }
    }

    @Override
    public void clear() {
        synchronized (queue) {
            queue.clear();
        }
    }

    @Override
    public boolean contains(E item) {
        synchronized (queue) {
            if (queue.size() < 1) {
                return false;
            }
            for (SimpleNode node : queue) {
                if (node != null && node.data != null && item != null && node.data.equals(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public E dequeue() {
        synchronized (queue) {
            if (queue.size() <= 0) {
                throw new UnsupportedOperationException("Cannot call dequeue() on an empty queue");
            }
            SimpleNode node = queue.dequeue();
            return node.data;
        }
    }

    @Override
    public void enqueue(E item, double priority) {
        synchronized (queue) {
            SimpleNode node = new SimpleNode(item);
            if (queue.size() == queue.maxSize()) {
                queue.resize(queue.maxSize() * 2 + 1);
            }
            queue.enqueue(node, priority);
        }
    }

    @Override
    public void remove(E node) {
        synchronized (queue) {
            queue.remove(getExistingNode(node));
        }
    }

    @Override
    public void updatePriority(E node, double priority) {
        synchronized (queue) {
            SimpleNode updateMe = getExistingNode(node);
            queue.updatePriority(updateMe, priority);
        }
    }

    @Override
    public Iterator<E> iterator() {
        List<E> queueData = new ArrayList<>();
        synchronized (queue) {
            for (SimpleNode node : queue) {
                queueData.add(node.data);
            }
        }
        return queueData.iterator();
    }

    public boolean isValidQueue() {
        synchronized (queue) {
            return queue.isValidQueue();
        }
    }
}
