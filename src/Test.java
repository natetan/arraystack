/**
 * Created by Yulong on 6/24/2016.
 */

public class Test {

    public static void main(String[] stuff) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(2);
        stack.push(7);
        stack.push(5);
        System.out.println("Stack: " + stack.toString()); // b[2, 7, 5]t
        System.out.println("Size: " + stack.size()); // 3
        int removal = stack.pop();  // 5
        System.out.println("Just removed: " + removal);
        System.out.println("Stack after removal: " + stack.toString()); //b[2, 7]
        System.out.println("Size: " + stack.size()); // 2
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
        System.out.println("Stack: " + stack.toString());
        System.out.println("Size: " + stack.size());
    }
}
