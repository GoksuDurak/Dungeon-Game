public class CircularQueue {
    private int front = 0;
    private int rear = -1;
    private int count = 0;
    private Object[] elements;
    public CircularQueue(int capacity) {
        elements = new Object[capacity];
    }
    public void enqueue(Object element) {
        if(isFull())
        {
            System.out.println("Queue is full");
            return;
        }
        rear = rear + 1 % elements.length;
        count++;
        elements[rear] = element;

    }
    public Object dequeue()
    {
        if(isEmpty())
        {
            System.out.println("Queue is empty");
            return null;
        }
        Object data = elements[front];
        elements[front] = null;
        front = front + 1 % elements.length;
        count--;
        return data;
    }
    public Object peek()
    {
        if(isEmpty())
        {
            System.out.println("Queue is empty");
            return null;
        }
        return elements[front];
    }
    public int size()
    {
        return count;
    }
    public boolean isEmpty()
    {
        return count == 0;
    }
    public boolean isFull()
    {
        return count == elements.length;
    }
}
