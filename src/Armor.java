public class Armor {
    private int defense;
    private String armorType;
    public Armor(int defense, String armorType)
    {
        this.defense = defense;
        this.armorType = armorType;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
