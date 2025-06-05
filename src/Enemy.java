import java.util.Random;

public class Enemy {
    private int health;
    private int attack;
    private int defence;
    private String type;
    private int x;
    private int y;
    private String comment;
    private String gender;
    private Random rand = new Random();
    private boolean dead; // düşam öldü mü
    Enemy(int health, int attack, int defence, String type, int x, int y,String comment,boolean dead,String gender) {
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.type = type;
        this.x = x;
        this.y = y;
        this.comment = comment;
        this.dead = dead;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
        String gender = "";
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
        enemies[10] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
        if (dungeon.getBiomeType().equals(BiomeTypes.FOREST)) {
            for (int i = 0; i < 10; i++) {
                do {
                    x = rand.nextInt(dungeon.getDungeonX());
                    y = rand.nextInt(dungeon.getDungeonY());
                }while (dungeon.getDungeonMatrix()[x][y] != ' ');
                randomNum = rand.nextInt(4);
                if (randomNum == 0) {
                    type = "Orc";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                    comment = "I will show no mercy mortal.Prepare to be crushed.";
                    gender = "Male";
                }else if (randomNum == 1) {
                    type = "Goblin";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                    comment = "AHAHAHA !!!There is no way that mortal like you can beat me you are easy prey for me.";
                    gender = "Male";
                }else if(randomNum == 2) {
                    type = "Wicked Dryads";
                    health = 1700;
                    attack = 200;
                    defense = 200;
                    comment = "I have been protecting this forest for years.And I won't allow you to enter this forest.";
                    gender = "Female";
                }
                else {
                    type = "Fog Wraiths";
                    health = 1600;
                    attack = 250;
                    defense = 200;
                    comment = "I have been protecting this forest for years.And I won't allow you to enter this forest.";
                    gender = "Female";
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.PLAINS)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Wandering Husk";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Skyrazor Flock";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                    gender = "Female";
                }else if(randomNum == 2) {
                    type = "Earthhorn Bull";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Grassfiend";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.SWAMP)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Swamp Ghoul";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Reed Witch";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Drowned Spirit";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Undead";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.DESERT)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Sand Wraith";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Cursed Mummy";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Ancient Sentinel";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Dust Elemental";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.VOLCANIC)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Fire Wolves";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Scorch Golem";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Lava Wisp";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Fire Elemental";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.SAVANNA)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Shadowmane";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Earthcleaver";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Totemic Wraiths";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Drywind Spirits";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.TUNDRA)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Glacier Wolf";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Frost Wraith";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Icebound Shaman";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Shard Bears";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.ICE_SPIKES)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Spikewind Wraiths";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Blizzard Stalker";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Cryomancer’s Remnant";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Icicle Bats";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
        } else if (dungeon.getBiomeType().equals(BiomeTypes.POLAR)) {
            for (int i = 0; i < 10; i++) {
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
                if (randomNum == 0) {
                    type = "Polar Leviathan";
                    health = 3000;
                    attack = 175;
                    defense = 100;
                }else if (randomNum == 1) {
                    type = "Frosthorn Herd";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                }else if(randomNum == 2) {
                    type = "Lightfang Wolf";
                    health = 1250;
                    attack = 225;
                    defense = 100;
                }
                else {
                    type = "Moonfur Bear";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                }
                dungeon.getDungeonMatrix()[x][y] = 'E';
                enemies[i] = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
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
