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
        if (this.size >= this.stack.length) {
            E[] newArray = (E[])new Object[this.stack.length * 2];
            for (int i = 0; i < this.stack.length; i++) {
                newArray[i] = this.stack[i];
            }
            this.stack = newArray;
        }
    }

    // Returns true if this stack is equal to the given stack.
    public boolean equals(ArrayStack other) {
        if (this.size != other.size) {
            return false;
        } else { // size is the same
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(other.get(i))) {
                    return false;
                }
            }
            return true;
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
        return this.size == 0;
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

    // Sorts the stack, from top to bottom
    public void sort() {
        if (this.size > 1) {
            int size1 = this.size / 2;
            int size2 = this.size - size1;
            ArrayStack half1 = new ArrayStack();
            ArrayStack half2 = new ArrayStack();
            for (int i = 0; i < size1; i++) {
                half1.push(this.pop());
            }
            for (int i = 0; i < size2; i++) {
                half2.push(this.pop());
            }
            half1.sort();
            half2.sort();
            this.mergeSort(this, half1, half2);
        }
    }

    private void mergeSort(ArrayStack result, ArrayStack half1, ArrayStack half2) {
        while (!half1.isEmpty() && !half2.isEmpty()) {
            if (((Comparable) half1.peek()).compareTo(half2.peek()) <= 0) {
                result.push(half1.pop());
            } else {
                result.push(half2.pop());
            }
        }
        while (!half1.isEmpty()) {
            result.push(half1.pop());
        }
        while (!half2.isEmpty()) {
            result.push(half2.pop());
        }
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
