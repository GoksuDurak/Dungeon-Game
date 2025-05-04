public class Potion {
    private String name;
    private String description; // etkisi
    private String color;
    private int effect;
    public Potion(String name, String description,int effect,String color)
    {
        this.name = name;
        this.description = description;
        this.effect = effect;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
