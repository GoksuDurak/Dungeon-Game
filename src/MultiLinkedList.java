public class MultiLinkedList {
    public class ItemNode {
        private ItemNode next;
        private ItemNode prev;
        private Object data;
        public ItemNode(Object dataToAdd) {
            data = dataToAdd;
            next = null;
            prev = null;
        }

        public ItemNode getNext() {
            return next;
        }

        public void setNext(ItemNode next) {
            this.next = next;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public ItemNode getPrev() {
            return prev;
        }

        public void setPrev(ItemNode prev) {
            this.prev = prev;
        }
    }
    public class CategoryNode {
        private ItemNode right;
        private ItemNode left;
        private CategoryNode down;
        private CategoryNode up;
        private Object data;
        public CategoryNode(Object dataToAdd) {
            data = dataToAdd;
            right = null;
            down = null;
        }

        public ItemNode getRight() {
            return right;
        }

        public void setRight(ItemNode right) {
            this.right = right;
        }

        public CategoryNode getDown() {
            return down;
        }

        public void setDown(CategoryNode down) {
            this.down = down;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public ItemNode getLeft() {
            return left;
        }

        public void setLeft(ItemNode left) {
            this.left = left;
        }

        public CategoryNode getUp() {
            return up;
        }

        public void setUp(CategoryNode up) {
            this.up = up;
        }
    }
    private CategoryNode head;
    private CategoryNode tail;
    public MultiLinkedList() {
        head = null;
        tail = null;
    }
    public CategoryNode getHead() {
        return head;
    }

    public void setHead(CategoryNode head) {
        this.head = head;
    }

    public CategoryNode getTail() {
        return tail;
    }

    public void setTail(CategoryNode tail) {
        this.tail = tail;
    }
    public void addCategory(Object dataToAdd) {
        if (head == null) {
            head = new CategoryNode(dataToAdd);
            tail = head;
        } else {
            CategoryNode newNode = new CategoryNode(dataToAdd);
            CategoryNode temp = head;
            while (temp.getDown() != null) {
                temp = temp.getDown();
            }
            temp.setDown(newNode);
            newNode.setUp(temp);
            tail = newNode;
        }
    }
    public void deleteCategory(Object dataToDelete) {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode temp = head;
            while (temp.getDown() != null) {
                if(temp.getData().equals(dataToDelete)) {
                    break;
                }
                temp = temp.getDown();
            }
            if (temp.getUp() == null) {
                head = temp.getDown();
            } else if (temp.getDown() == null) {
                tail = temp.getUp();
            } else {
                temp.getDown().setUp(temp.getUp());
                temp.getUp().setDown(temp.getDown());
            }
        }
    }
    public void displayCategoryHead() {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode temp = head;
            while (temp != null) {
                System.out.println(temp.getData());
                temp = temp.getDown();
            }
        }
    }
    public void displayCategoryTail() {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode temp = tail;
            while (temp != null) {
                System.out.println(temp.getData());
                temp = temp.getUp();
            }
        }
    }
    public void displayItem() {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode tempCategory = head;
            while (tempCategory != null) {
                System.out.println("-" + tempCategory.getData());
                ItemNode tempItem = tempCategory.getRight();
                while (tempItem != null) {
                    System.out.print(tempItem.getData() + " ");
                    tempItem = tempItem.getNext();
                }
                System.out.println();
                tempCategory = tempCategory.getDown();
            }
        }
    }
    public void addItem(Object category,Object dataToAdd) {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode tempCategory = head;
            while (tempCategory != null && !tempCategory.getData().equals(category)) {
                tempCategory = tempCategory.getDown();
            }
            if (tempCategory == null) {
                System.out.println("Add category first");
                return;
            }

            if (tempCategory.getRight() == null) {
                ItemNode newNode = new ItemNode(dataToAdd);
                tempCategory.setRight(newNode);
            } else {
                ItemNode tempItem = tempCategory.getRight();
                while (tempItem.getNext() != null) {
                    tempItem = tempItem.getNext();
                }
                ItemNode newNode = new ItemNode(dataToAdd);
                tempItem.setNext(newNode);
                newNode.setPrev(tempItem);
            }
        }
    }
    public void deleteItem(Object category,Object dataToDelete) {
        if (head == null) {
            System.out.println("The list is empty");
        } else {
            CategoryNode tempCategory = head;
            while (tempCategory != null && !tempCategory.getData().equals(category)) {
                tempCategory = tempCategory.getDown();
            }
            if (tempCategory == null) {
                System.out.println("Add category first");
                return;
            }
            ItemNode tempItem = tempCategory.getRight();
            if (tempItem.getData().equals(dataToDelete)) {
                tempCategory.setRight(tempItem.getNext());
            } else {
                while (tempItem.getNext() != null) {
                    if(tempItem.getData().equals(dataToDelete)) {
                        break;
                    }
                    tempItem = tempItem.getNext();
                }
                if (tempItem.getNext() == null) {
                    tempItem.getPrev().setNext(null);
                } else {
                    tempItem.getPrev().setNext(tempItem.getNext());
                    tempItem.getNext().setPrev(tempItem.getPrev());
                }
            }
        }
    }
}
