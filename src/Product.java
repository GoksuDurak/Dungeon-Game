public class Product {
    private String name;
    private int price;
    private int quantity;
    private String costType;
    private int id;
    private int effect;
    private String type;
    public Product(String name,int price, int quantity,String costType, int id, int effect,String type)
    {
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.costType = costType;
        this.name = name;
        this.id = id;
        this.effect = effect;
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


    public String getCostType() {
        return costType;
    }
    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
