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
    }
}
