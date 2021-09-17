package stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class StackTest {
    private Stackable<Integer> stack;

    public static Stream<Arguments> ProvideArgumentsForPopMultipleItems() {
        // The first argument is the list of integers, which are going to be pushed
        // The second argument is the last item
        // The third argument is the penultimate
        return Stream.of(
                Arguments.of(new int[]{3, 7}, 7, 3),
                Arguments.of(new int[]{1, -1, 5}, 5, -1),
                Arguments.of(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, 4, 13}, 13, 4)
        );
    }

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
    @ParameterizedTest(name = "Test: {index} - Push {0}")
    @ValueSource(ints = {0, 2, -9, Integer.MAX_VALUE})
    void pushOneItem(Integer number) {
        stack.push(number);
        Assertions.assertFalse(stack.isEmpty());
        Assertions.assertEquals(1, stack.size());
        Assertions.assertEquals(number, stack.peek());
        Assertions.assertEquals(String.valueOf(number), String.valueOf(stack));
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, stack.search(number)),
                () -> Assertions.assertEquals(-1, stack.search(number - 1)));
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
    @ParameterizedTest(name = "Test: {index} - Push each element of {0} and pop two times")
    @MethodSource("ProvideArgumentsForPopMultipleItems")
    void popMultipleItems(int[] numbers, int last, int penultimate) {
        for (int number : numbers) {
            stack.push(number);
        }

        Assertions.assertEquals(last, stack.pop());
        Assertions.assertEquals(penultimate, stack.pop());
    }

    @DisplayName("Push a null item")
    @Test
    void pushNullItem() {
        Assertions.assertThrows(NullPointerException.class, () -> stack.push(null));
    }

}
