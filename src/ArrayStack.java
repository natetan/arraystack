/**
 * Created by Yulong on 6/24/2016.
 *
 * First in, last out structure. Data at the end is the 'top'.
 * b[2, 5, 4]t
 */

import java.util.NoSuchElementException;

public class ArrayStack<E> {
    public static final int DEFAULT_CAPACITY = 10;

    private E[] stack;
    private int size;

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity can't be negative: " + capacity);
        }
        this.size = 0;
        this.stack = (E[])new Object[capacity];
    }

    private E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        return this.stack[index];
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.get(this.size - 1);
    }

    public E pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = this.get(this.size - 1);
        this.size--;
        return data;
    }

    public void push(E e) {
        this.stack[size] = e;
        this.size++;
    }

    public int size() {
        return this.size;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            String result = "t[" + this.get(this.size - 1);
            for (int i = this.size - 2; i >= 0; i--) {
                result += ", " + this.get(i);
            }
            return result + "]b";
        }
    }
}
