/**
 * Yulong Tan
 * 6.24.16
 * <p>
 * Array implementation of a stack. First in, last out structure
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Iterable<E> {
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
        this.stack = (E[]) new Object[capacity];
    }

    private class ArrayStackIterator implements Iterator<E> {
        private int position;
        private boolean isRemovable;
        private ArrayStack stack;

        public ArrayStackIterator(ArrayStack stack) {
            this.stack = stack;
            this.position = 0;
            this.isRemovable = false;
        }

        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            E data = (E) this.stack.get(position);
            this.position++;
            this.isRemovable = true;
            return data;
        }

        public boolean hasNext() {
            return this.position < this.stack.size();
        }

        public void remove() {
            if (!this.isRemovable) {
                throw new IllegalStateException();
            }
            this.stack.remove(this.position - 1);
            this.position--;
            this.isRemovable = false;
        }
    }

    // Clears the stack
    public void clear() {
        this.size = 0;
    }

    // Ensures that the capcacity of array
    private void ensureCapacity() {
        if (this.size >= this.stack.length) {
            E[] newArray = (E[]) new Object[this.stack.length * 2];
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

    public Iterator iterator() {
        return new ArrayStackIterator(this);
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

    public E remove() {
        return this.remove(0);
    }

    public E remove(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        E data = this.stack[index];
        for (int i = index; i < this.size - 1; i++) {
            this.stack[i] = this.stack[i + 1];
        }
        this.size--;
        return data;
    }

    // Returns the size of the stack.
    public int size() {
        return this.size;
    }

    public void sort() {
        E[] toSort = (E[]) new Object[size];
        for (int i = 0; i < this.size; i++) {
            toSort[i] = this.stack[i];
        }
        this.sort(toSort, this.stack);
    }

    private void sort(E[] a1, E[] a2) {
        if (a1.length > 1) {
            int size1 = a1.length / 2;
            int size2 = a1.length - size1;
            E[] half1 = (E[]) new Object[DEFAULT_CAPACITY];
            E[] half2 = (E[]) new Object[DEFAULT_CAPACITY];
            for (int i = 0; i < size1; i++) {
                half1[i] = a1[i];
            }
            for (int i = 0; i < size2; i++) {
                half2[i] = a2[i];
            }
            this.sort(a1, this.stack);
            this.sort(a2, this.stack);
            this.mergeSort(a1, a2, this.stack);
        }
    }

    private void mergeSort(E[] half1, E[] half2, E[] result) {
        while (half1.length != 0 && half2.length != 0) {
            if (((Comparable) half1[half1.length - 1]).compareTo(half2[half2.length - 1]) <= 0) {
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
