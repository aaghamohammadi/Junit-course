package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StackTest {
    private Stackable<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @DisplayName("Stack is empty")
    @Test
    void stackIsEmpty() {
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> stack.pop());
        Assertions.assertNull(stack.peek());
        Assertions.assertEquals("", String.valueOf(stack));
    }

    @DisplayName("Push one item")
    @Test
    void pushOneItem() {
        stack.push(2);
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(1, stack.size());
        Assertions.assertEquals(2, stack.peek());
        Assertions.assertEquals("2", String.valueOf(stack));
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, stack.search(2)),
                () -> Assertions.assertEquals(-1, stack.search(3)));
    }

    @DisplayName("Push multiple items")
    @Test
    void pushMultipleItems() {
        stack.push(5);
        stack.push(3);
        stack.push(7);
        Assertions.assertEquals(3, stack.size());
        Assertions.assertEquals(7, stack.peek());
        Assertions.assertEquals("5 <- 3 <- 7", String.valueOf(stack));
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, stack.search(7)),
                () -> Assertions.assertEquals(2, stack.search(3)),
                () -> Assertions.assertEquals(3, stack.search(5)),
                () -> Assertions.assertEquals(-1, stack.search(4))
        );
    }

    @DisplayName("Pop one item")
    @Test
    void popOneItem() {
        stack.push(5);
        int top = stack.pop();
        Assertions.assertEquals(5, top);
        Assertions.assertTrue(stack.isEmpty());
        Assertions.assertEquals(0, stack.size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> stack.pop());
    }

    @DisplayName("Pop multiple items")
    @Test
    void popMultipleItems() {
        stack.push(3);
        stack.push(7);
        Assertions.assertEquals(7, stack.pop());
        Assertions.assertEquals(3, stack.pop());
        Assertions.assertThrows(IllegalArgumentException.class, () -> stack.pop());
    }

    @DisplayName("Push a null item")
    @Test
    void pushNullItem(){
        Assertions.assertThrows(NullPointerException.class, () ->stack.push(null));
    }

}
