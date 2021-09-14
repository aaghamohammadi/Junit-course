package stack;

class Stack<T> implements Stackable<T> {
    private Stack<T> previous;
    private T value;

    Stack() {
    }

    Stack(Stack<T> previous, T value) {
        this.previous = previous;
        this.value = value;
    }

    @Override
    public void push(T value) {
        if (value == null)
            throw new NullPointerException("It is not possible to push a null item");

        this.previous = new Stack<>(this.previous, this.value);
        this.value = value;

    }

    @Override
    public T pop() {
        if (this.isEmpty())
            throw new IllegalArgumentException("Stack is empty");

        T top = this.value;
        this.value = this.previous.value;
        this.previous = this.previous.previous;

        return top;
    }

    @Override
    public T peek() {
        return this.value;
    }

    @Override
    public boolean isEmpty() {
        return this.previous == null;
    }

    @Override
    public int size() {
        return this.isEmpty() ? 0 : 1 + this.previous.size();
    }

    @Override
    public int search(Object o) {
        int count = 1;

        for (Stack<T> stack = this; !stack.isEmpty(); stack = stack.previous) {
            if (stack.value.equals(o))
                return count;
            count++;
        }

        return -1;
    }

    @Override
    public String toString() {
        if (this.size() > 1) {
            return this.previous + " <- " + this.value;
        }
        return this.value != null ? String.valueOf(this.value) : "";

    }
}