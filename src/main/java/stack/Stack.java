package stack;

class Stack<T> implements Stackable<T> {
    private Stack<T> previous;
    private T value;

    Stack() {
    }

    Stack(T value) {
        this.value = value;
    }

    Stack(Stack<T> previous, T value) {
        this.previous = previous;
        this.value = value;
    }

    @Override
    public void push(T value) {
        if (this.value != null) {
            this.previous = new Stack<>(this.previous, this.value);
        }
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
        return this.previous == null && this.value == null;
    }

    @Override
    public int size() {
        if (this.isEmpty())
            return 0;
        if (this.value != null && this.previous == null)
            return 1;
        return 1 + this.previous.size();
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
        if (this.previous != null)
            return this.previous + " <- " + this.value;
        return this.value != null ? String.valueOf(this.value) : "";
    }
}