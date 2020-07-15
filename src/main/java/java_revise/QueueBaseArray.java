package java_revise;

/**
 * @author ScXin
 * @date 9/9/2019 1:05 AM
 */
public class QueueBaseArray<T> {
    private T[] quque;
    private int head;
    private int tail;
    private int size;
    private int count;

    public QueueBaseArray(int size) {
        this.size = size;
        quque = (T[]) new Object[size];
        this.head = 0;
        this.tail = 0;
    }

    public boolean enQueue(T t) {
        if (count == size) {
            return false;
        }
        quque[tail++ % size] = t;
        count++;
        return true;
    }

    public T outQueue() {
        if (count == 0) {
            return null;
        }
        count--;
        return quque[head-- % (size)];
    }

    public T peek() {
        if (count == 0) {
            return null;
        }
        return quque[head % size];
    }

    public boolean isFull() {
        return count == size;
    }

    public boolean isEmpty() {
        return count == 0;
    }

}
