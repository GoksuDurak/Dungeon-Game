public class Market {
    private String type;
    private int price;
    private int quantity;
    private int sell;
    Market(String type, int price, int quantity, int sell) {
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.sell = sell;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    public int getSell() {
        return sell;
    }
    public void setSell(int sell) {
        this.sell = sell;
    }
}
