public class Item {
    private String itemName;
    private int quantity;
    private int itemID;
    private Object data;

    public Item(String itemName, int quantity,int itemID, Object data)
    {
        this.itemName = itemName;
        this.quantity = quantity;
        this.itemID = itemID;
        this.data = data;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

