import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player {
    //Attributes
    private String name;
    private int score;
    private int attack;
    private int defense;
    private int level;
    private int hp;
    private int stamina;
    private int money;
    private int xp;
    //Constructor
    Player(String name, int score, int level, int hp, int stamina, int money, int xp,int attack,int defense)
    {
        this.name = name;
        this.attack = attack;
        this.score = score;
        this.level = level;
        this.hp = hp;
        this.stamina = stamina;
        this.money = money;
        this.xp = xp;
        this.defense = defense;
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
    public void printMoney(int money)
    {
        System.out.println("Money : " + money);
    }


    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
