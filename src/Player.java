import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
    //Attributes
    private String name;
    private int score;
    private int attack;
    private int defence;
    private int level;
    private int hp;
    private int stamina;
    private int money;
    private int xp;
    private  int mana;
    //Constructor
    Player(String name, int score, int level, int hp, int stamina, int money, int xp,int attack,int defence,int mana)
    {
        this.name = name;
        this.attack = attack;
        this.score = score;
        this.level = level;
        this.hp = hp;
        this.stamina = stamina;
        this.money = money;
        this.xp = xp;
        this.defence = defence;
        this.mana = mana;
    }
    //Get-Set
    //Name

    public String getName()
    {
        return name;
    }
    //Score
    public void setScore(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }
    //Money
    public void setMoney(int money)
    {
        this.money = money;
    }
    public int getMoney()
    {
        return money;
    }
    //Level
    public void setLevel(int level)
    {
        this.level = level;
    }
    public int getLevel()
    {
        return level;
    }
    //Hp
    public void setHp(int hp)
    {
        this.hp = hp;
    }
    public int getHp()
    {
        return hp;
    }
    //Stamina
    public void setStamina(int stamina)
    {
        this.stamina = stamina;
    }
    public int getStamina()
    {
        return stamina;
    }
    //Xp
    public void setXp(int xp)
    {
        this.xp = xp;
    }
    public int getXp()
    {
        return xp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
}
