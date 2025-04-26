public class Stack {
    private int top = -1;
    private Object[] elements;
    public Stack(int capacity) {
        elements = new Object[capacity];
    }
    public void push(Object element)
    {
        if (isFull()) {
            System.out.println("Stack is full");
            return;
        }
        top++;
        elements[top] = element;
    }
    public Object pop()
    {
        if(isEmpty())
        {
            System.out.println("Stack is empty");
            return null;
        }
        Object data = elements[top];
        elements[top] = null;
        top--;
        return data;
    }
    public  Object peek()
    {
        if(isEmpty())
        {
            System.out.println("Stack is empty");
            return null;
        }
        return elements[top];
    }
    public int size()
    {
        return top + 1;
    }
    public boolean isFull()
    {
        return top + 1 == elements.length;
    }
    public boolean isEmpty()
    {
        return top == -1;
    }
}
