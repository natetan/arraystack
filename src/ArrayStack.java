/**
 * Created by Yulong on 6/24/2016.
 *
 * First in, last out structure. Data at the end is the 'top'.
 * b[2, 5, 2]t
 */

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayStack<E> {
    private List<E> stack;

    public ArrayStack() {
        this.stack = new ArrayList<>();
    }

    public void push(E e) {
        this.stack.add(e);
    }

    public E pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.stack.remove(this.size() - 1);
    }

    public E peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.stack.get(this.size() - 1);
    }

    public int size() {
        return this.stack.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public String toString() {
        return "b" + this.stack.toString() + "t";
    }
}
