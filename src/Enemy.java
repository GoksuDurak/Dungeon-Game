import java.util.Random;

public class Enemy {
    private int health;
    private int attack;
    private int defence;
    private String type;
    private int x;
    private int y;
    private String comment;
    private Enemy[] enemies; //düşmanlar
    private Random rand = new Random();
    Enemy(int health, int attack, int defence, String type, int x, int y,String comment) {
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.type = type;
        this.x = x;
        this.y = y;
        this.comment = comment;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Enemy[] randomEnemy(Dungeon dungeon)
    {
        Enemy[] enemies = new Enemy[5];
        for (int i = 0; i < 5; i++)
        {
            String type = " ";
            int health = 0;
            int attack = 0;
            int defense = 0;
            String comment = "";
            int randomNum = 0;
            int x = 0;
            int y = 0;
            do {
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
            }while (dungeon.getDungeonMatrix()[x][y] != ' ');
            randomNum = rand.nextInt(4);
            if (randomNum == 0)
            {
                type = "orc";
                health = 300;
                attack = 175;
                defense = 100;
            }else if (randomNum == 1)
            {
                type = "goblin";
                health = 150;
                attack = 250;
                defense = 100;
            }else if(randomNum == 2)
            {
                type = "skeleton";
                health = 125;
                attack = 225;
                defense = 100;
            }
            else
            {
                type = "undead";
                health = 175;
                attack = 200;
                defense = 75;
            }
            dungeon.getDungeonMatrix()[x][y] = 'E';
            enemies[i] = new Enemy(health,attack,defense,type,x,y,comment);
        }
        return enemies;
    }
}
