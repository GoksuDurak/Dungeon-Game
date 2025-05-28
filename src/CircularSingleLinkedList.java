public class CircularSingleLinkedList {
    public class Node {
        private Object data;
        private Node link;
        public Node(Object data) {
            this.data = data;
            this.link = null;
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

        public void setData(Object data) {
            this.data = data;
        }
    }
    private Node head;
    public CircularSingleLinkedList() {
        head = null;
    }

    public void add(Object data) {
        if  (head == null) {
            head = new Node(data);
            head.setLink(head);
        } else {
            Node temp = head;

            do {
                temp = temp.getLink();
            } while (temp.getLink() != head);

            Node newNode = new Node(data);
            temp.setLink(newNode);
            newNode.setLink(head);
        }
    }
    public void delete(Object dataToDelete) {
        if (head == null) {
            System.out.println("List is empty");
        } else {
            if (head.getData().equals(dataToDelete)) {
                if (head.getLink() == head) {
                    head = null;
                } else {
                    Node oldHead = head;
                    head = head.getLink();
                    Node temp = head;
                    Node newHead = temp;
                    while (temp.getLink() != oldHead) {
                        temp = temp.getLink();
                    }
                    temp.setLink(newHead);
                }
            } else {
                Node temp = head;
                while (temp.getLink() != head) {
                    if (temp.getLink().getData().equals(dataToDelete)) {
                        break;
                    }
                    temp = temp.getLink();
                }
                temp.setLink(temp.getLink().getLink());
            }

        }
    }
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
        } else {
            Node temp = head;
            do {
                System.out.println(temp.getData() + " ");
                temp = temp.getLink();
            } while (temp != head);
        }
    }

}
