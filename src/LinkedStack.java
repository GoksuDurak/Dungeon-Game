public class LinkedStack {
    public class Node {
        private Object data;
        private Node link;

        public void setData(Object data) {
            this.data = data;
        }

        public Node getLink() {
            return link;
        }

        public void setLink(Node link) {
            this.link = link;
        }


        public Object getData() {
            return data;
        }


        public Node(Object data) {
            this.data = data;
            this.link = null;
        }
    }
    private Node head;
    public void push(Object data) {
        if (head == null) {
            head = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.setLink(head);
            head = temp;
        }
    }
    public Object pop() {
        if (head == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            Node temp = head;
            head = temp.getLink();
            return temp.getData();
        }
    }
    public Object peek() {
        Node temp = head;
        if (temp == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return temp.getData();
        }
    }
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }
    public int size() {
        if (head == null) {
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
        if (head == null) {
            System.out.println("Stack is empty");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getData());
            temp = temp.getLink();
        }
        System.out.println();
    }
}
