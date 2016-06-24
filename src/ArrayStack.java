/**
 *  Yulong Tan
 *  6.24.16
 *
 * Array implementation of a stack. First in, last out structure
 */

import java.util.NoSuchElementException;

public class ArrayStack<E> {
    public static final int DEFAULT_CAPACITY = 10; // default capacity

    private E[] stack;
    private int size;

    // Constructs a new ArrayStack with default capacity
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    // Constructs a new ArrayStack with specified capacity.
    // If capacity is negative, throws an IllegalArgumentException.
    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity can't be negative: " + capacity);
        }
        this.size = 0;
        this.stack = (E[])new Object[capacity];
    }

    // Ensures that the capcacity of array
    private void ensureCapacity() {
        if (this.size() >= this.stack.length) {
            E[] newArray = (E[])new Object[this.stack.length * 2];
            for (int i = 0; i < this.stack.length; i++) {
                newArray[i] = this.stack[i];
            }
            this.stack = newArray;
        }
    }

    // Returns the data at the specified index. If index is less than 0 or is
    // greater than or equal to the size, throws an IllegalArgumentException.
    private E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        return this.stack[index];
    }

    // Returns true if the stack is empty and false otherwise.
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Returns the data at the 'top' of the stack. If the stack is empty,
    // it throws a NoSuchElementException.
    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.get(this.size - 1);
    }

    // Returns and removes the data at the 'top' of the stack. Size is decreased.
    // If the stack is empty, throws a NoSuchElementException.
    public E pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        E data = this.get(this.size - 1);
        this.size--;
        return data;
    }

    // Adds given data to the stack. Size is increased.
    public void push(E e) {
        this.ensureCapacity();
        this.stack[this.size] = e;
        this.size++;
    }

    // Returns the size of the stack.
    public int size() {
        return this.size;
    }

    // Returns a string representation of the stack. Top and bottom are specified.
    // Data is inside brackets, with each element separated by commas.
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
