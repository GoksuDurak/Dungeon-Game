public class Weapon {
    private String weaponType;
    private int damage;
    public Weapon(String weaponType, int damage)
    {
        this.weaponType = weaponType;
        this.damage = damage;
    }

    public String getType() {
        return weaponType;
    }

    public void setType(String type) {
        this.weaponType = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
