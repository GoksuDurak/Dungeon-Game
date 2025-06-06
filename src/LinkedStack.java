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
    private Node top;
    public void push(Object data) {
        if (top == null) {
            top = new Node(data);
        } else {
            Node temp = new Node(data);
            temp.setLink(top);
            top = temp;
        }
    }
    public Object pop() {
        if (top == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            Node temp = top;
            top = temp.getLink();
            return temp.getData();
        }
    }
    public Object peek() {
        Node temp = top;
        if (temp == null) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return temp.getData();
        }
    }
    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }
    public int size() {
        if (top == null) {
            return -1;
        } else {
            Node temp = top;
            int count = 0;
            while (temp != null) {
                count++;
                temp = temp.getLink();
            }
            return count;
        }
    }
    public void display() {
        if (top == null) {
            System.out.println("Stack is empty");
            return;
        }
        Node temp = top;
        while (temp != null) {
            System.out.print(temp.getData());
            temp = temp.getLink();
        }
        System.out.println();
    }
}
