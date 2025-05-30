public class LinkedQueue {
    public class Node {
        private Object data;
        private Node link;

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getLink() {
            return link;
        }

        public void setLink(Node link) {
            this.link = link;
        }

        public Node(Object data) {
            this.data = data;
            link = null;
        }
    }
    private Node head;
    public LinkedQueue() {
        head = null;
    }
    public void enqueue(Object data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node newNode = new Node(data);
            newNode.setLink(head);
            head = newNode;
        }
    }
    public Object dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            Node temp = head;
            if (temp.getLink() == null) {
                Object data = temp.getData();
                head = null;
                return data;
            }
            while (temp.getLink().getLink() != null) {
                temp = temp.getLink();
            }
            Object data = temp.getLink().getData();
            temp.setLink(null);
            return data;
        }
    }
    public Object peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        Node temp = head;
        while (temp.getLink() != null) {
            temp = temp.getLink();
        }
        return temp.getData();
    }
    public boolean isEmpty() {
        return head == null;
    }
    public int size() {
        if (isEmpty()) {
            return -1;
        } else {
            Node temp = head;
            int count = 0;
            while (temp != null) {
                count++;
                temp = temp.getLink();
            }
            return count;
        }
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.getData() + " ");
                temp = temp.getLink();
            }
            System.out.println();
        }
    }
}
