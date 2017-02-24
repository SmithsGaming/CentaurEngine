package smithsgaming.centaurengine.util.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Tim on 18/02/2016.
 */
public class FastPriorityQueue<E extends FastPriorityQueueNode> implements IPriorityQueue<E> {

    private int nodeCount;
    private Object[] nodes;
    private long numNodesEverEnqueued;

    public FastPriorityQueue(int maxNodes) {

        if (maxNodes <= 0) {
            throw new ArrayIndexOutOfBoundsException(maxNodes);
        }
        nodeCount = 0;
        nodes = new Object[maxNodes + 1];
        numNodesEverEnqueued = 0;
    }

    @Override
    public void clear() {
        nodes = null;
        nodeCount = 0;
    }

    @Override
    public boolean contains(E node) {
        if (node == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (node.queueIndex < 0 || node.queueIndex >= nodes.length) {
            throw new ArrayIndexOutOfBoundsException("Node index has been corrupted. Has this node been manually changed or added to a different queue?");
        }
        return nodes[node.queueIndex] == node;
    }

    @Override
    public void enqueue(E node, double priority) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (nodeCount >= nodes.length - 1) {
            throw new UnsupportedOperationException("Queue is full, cannot add more nodes");
        }
        if (contains(node)) {
            throw new UnsupportedOperationException("Node is already part of the queue");
        }
        node.priority = priority;
        nodeCount++;
        nodes[nodeCount] = node;
        node.queueIndex = nodeCount;
        node.insertionIndex = numNodesEverEnqueued++;
        cascadeUp((E) nodes[nodeCount]);
    }

    private void swap(E a, E b) {
        nodes[a.queueIndex] = b;
        nodes[b.queueIndex] = a;
        int temp = a.queueIndex;
        a.queueIndex = b.queueIndex;
        b.queueIndex = temp;
    }

    private void cascadeUp(E node) {
        int parent = node.queueIndex / 2;
        while (parent >= 1) {
            E parentNode = (E) nodes[parent];
            if (hasHigherPriority(parentNode, node)) {
                break;
            }
            swap(node, parentNode);
            parent = node.queueIndex / 2;
        }
    }

    private void cascadeDown(E node) {
        E newParent;
        int finalQueueIndex = node.queueIndex;
        while (true) {
            newParent = node;
            int childLeftIndex = 2 * finalQueueIndex;
            if (childLeftIndex > nodeCount) {
                node.queueIndex = finalQueueIndex;
                nodes[finalQueueIndex] = node;
                break;
            }
            E childLeft = (E) nodes[childLeftIndex];
            if (hasHigherPriority(childLeft, newParent)) {
                newParent = childLeft;
            }
            int childRightIndex = childLeftIndex + 1;
            if (childRightIndex <= nodeCount) {
                E childRight = (E) nodes[childRightIndex];
                if (hasHigherPriority(childRight, newParent)) {
                    newParent = childRight;
                }
            }
            if (newParent != node) {
                nodes[finalQueueIndex] = newParent;
                int temp = newParent.queueIndex;
                newParent.queueIndex = finalQueueIndex;
                finalQueueIndex = temp;
            } else {
                node.queueIndex = finalQueueIndex;
                nodes[finalQueueIndex] = node;
                break;
            }
        }
    }

    private boolean hasHigherPriority(E higher, E lower) {
        return (higher.priority < lower.priority || (higher.priority == lower.priority && higher.insertionIndex < lower.insertionIndex));
    }

    @Override
    public E dequeue() {
        if (nodeCount <= 0) {
            throw new UnsupportedOperationException("Cannot dequeue from an empty Queue");
        }
        if (!isValidQueue()) {
            throw new UnsupportedOperationException("Queue has been corrupted (Did you update a node priority manually instead of calling UpdatePriority()? Or add the same node to two different queues?)");
        }
        E returnElement = (E) nodes[1];
        remove(returnElement);
        return returnElement;
    }

    public void resize(int maxNodes) {
        if (maxNodes <= 0) {
            throw new UnsupportedOperationException("Queue size cannot be smaller than 1");
        }
        if (maxNodes < nodeCount) {
            throw new UnsupportedOperationException("Called Resize(" + maxNodes + "), but current queue contains " + nodeCount + " nodes");
        }
        Object[] newArray = new Object[maxNodes + 1];
        int highestIndexToCopy = Math.min(maxNodes, nodeCount);
        for (int i = 1; i <= highestIndexToCopy; i++) {
            newArray[i] = nodes[i];
        }
        nodes = newArray;
    }

    @Override
    public E first() {
        if (nodeCount <= 0) {
            throw new UnsupportedOperationException("Cannot get the first node of an empty queue");
        }
        return (E) nodes[1];
    }

    @Override
    public void updatePriority(E node, double priority) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (!contains(node)) {
            throw new UnsupportedOperationException("Cannot call updatePriority() on a node which is not enqueued");
        }
        node.priority = priority;
        onNodeUpdated(node);
    }

    private void onNodeUpdated(E node) {
        int parentIndex = node.queueIndex / 2;
        E parentNode = (E) nodes[parentIndex];
        if (parentIndex > 0 && hasHigherPriority(node, parentNode)) {
            cascadeUp(node);
        } else {
            cascadeDown(node);
        }
    }

    @Override
    public void remove(E node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        if (!contains(node)) {
            throw new UnsupportedOperationException("Cannot call remove() on a node which is not enqueued");
        }
        if (node.queueIndex == nodeCount) {
            nodes[nodeCount] = null;
            nodeCount--;
            return;
        }
        E formerLastNode = (E) nodes[nodeCount];
        swap(node, formerLastNode);
        nodes[nodeCount] = null;
        nodeCount--;
        onNodeUpdated(formerLastNode);
    }

    @Override
    public int size() {
        return nodeCount;
    }

    public int maxSize() {
        return nodes.length - 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    public boolean isValidQueue() {
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] != null) {
                int childLeftIndex = 2 * i;
                if (childLeftIndex < nodes.length && nodes[childLeftIndex] != null && hasHigherPriority((E) nodes[childLeftIndex], (E) nodes[i])) {
                    return false;
                }
                int childRightIndex = childLeftIndex + 1;
                if (childRightIndex < nodes.length && nodes[childRightIndex] != null && hasHigherPriority((E) nodes[childRightIndex], (E) nodes[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    private class QueueIterator implements Iterator<E> {
        private int cursor;
        private final int end;

        public QueueIterator() {
            this.cursor = 1;
            this.end = FastPriorityQueue.this.maxSize();
        }

        @Override
        public boolean hasNext() {
            return cursor < end;
        }

        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) FastPriorityQueue.this.nodes[this.cursor++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
