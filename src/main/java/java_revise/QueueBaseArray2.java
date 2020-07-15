package java_revise;

/**
 * @author ScXin
 * @date 9/24/2019 3:55 PM
 */
public class QueueBaseArray2<T> {
    T[] queue;
    private static int maxSize;
    private int head = 0;
    private int tail = 0;
    private int count = 0;
    public QueueBaseArray2(int maxSize) {
        this.maxSize = maxSize;
        queue = (T[]) new Object[maxSize];
    }
    public boolean push(T t) {
        if (count == maxSize) {
            return false;
        }
        queue[(tail++) % maxSize] = t;
        return true;
    }
    public T poll() {
        if (count == 0) {
            return null;
        }
        T t = queue[head++ % maxSize];
        count--;
        return t;
    }
    public T peek() {
        if (count == maxSize) {
            return null;
        }
        T t = queue[head % maxSize];
        count--;
        return t;
    }
    public boolean isEmpty() {
        return count == 0;
    }
}
