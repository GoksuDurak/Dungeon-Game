import java.util.Random;

public class Enemy {
    private int health;
    private int attack;
    private int defence;
    private String type;
    private int x;
    private int y;
    private String comment;
    private Random rand = new Random();
    private boolean dead; // düşam öldü mü
    Enemy(int health, int attack, int defence, String type, int x, int y,String comment,boolean dead) {
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.type = type;
        this.x = x;
        this.y = y;
        this.comment = comment;
        this.dead = dead;
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
        Enemy[] enemies = new Enemy[11];
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
        randomNum = rand.nextInt(5);
        if(randomNum == 0){
            type = "demon king";
            health = 12500;
            attack = 1500;
            defense = 750;
            comment = "You encountered the demon king";
        }else if(randomNum == 1){
            type = "dragon";
            health = 10000;
            attack = 1750;
            defense = 500;
            comment = "You encountered the dragon";
        }else if(randomNum == 2){
            type = "orc king";
            health = 20000;
            attack = 1000;
            defense = 750;
            comment = "You encountered orc king";
        }else if(randomNum == 3){
            type = "skeleton king";
            health = 7500;
            attack = 2000;
            defense = 400;
            comment = "You encountered the skeleton king";
        }else{
            type = "hero";
            health = 10000;
            attack = 1750;
            defense = 500;
            comment = "You encountered hero";
        }
        dungeon.getDungeonMatrix()[x][y] = 'E';
        enemies[10] = new Enemy(health,attack,defense,type,x,y,comment,false);
        for (int i = 0; i < 10; i++)
        {
             type = " ";
             health = 0;
             attack = 0;
             defense = 0;
             comment = "";
             randomNum = 0;
             x = 0;
             y = 0;
            do {
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
            }while (dungeon.getDungeonMatrix()[x][y] != ' ');
            randomNum = rand.nextInt(4);
            if (randomNum == 0)
            {
                type = "orc";
                health = 3000;
                attack = 175;
                defense = 100;
            }else if (randomNum == 1)
            {
                type = "goblin";
                health = 1500;
                attack = 250;
                defense = 100;
            }else if(randomNum == 2)
            {
                type = "skeleton";
                health = 1250;
                attack = 225;
                defense = 100;
            }
            else
            {
                type = "undead";
                health = 1750;
                attack = 200;
                defense = 75;
            }
            dungeon.getDungeonMatrix()[x][y] = 'E';
            enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false);
        }
        return enemies;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
