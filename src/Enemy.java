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
        if (dungeon.getBiome().equals(Biomes.HOT)) {
            do {
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
            } while (dungeon.getDungeonMatrix()[x][y] != ' ');
            randomNum = rand.nextInt(5);
            if (randomNum == 0) {
                type = "Ignarok the Blazing Tyrant";
                health = 12500;
                attack = 1500;
                defense = 750;
                gender = "Male";
                comment = "You dare step into my dominion mortal? Then suffer the wrath of the undying!";
            } else if (randomNum == 1) {
                type = "Pyralith the Worldburner";
                health = 10000;
                attack = 1750;
                defense = 500;
                gender = "Male";
                comment = "Foolish mortals  you dare challenge the skies themselves?";
            } else if (randomNum == 2) {
                type = "Ember Queen Seraphira";
                health = 20000;
                attack = 1000;
                defense = 750;
                gender = "Female";
                comment = "Resist all you want but in the end your soul will burn for me and you will be mine forever.";
            } else if (randomNum == 3) {
                type = "Velgrath the Bone King";
                health = 7500;
                attack = 2000;
                defense = 400;
                comment = "From the marrow of the dead  I rise to crush the living.";
            } else {
                type = "Scorvyn the Sand Reaper";
                health = 10000;
                attack = 1750;
                defense = 500;
                comment = "I reap the sands of time and with them, your fate.";
            }
            dungeon.getDungeonMatrix()[x][y] = 'E';
        } else if (dungeon.getBiome().equals(Biomes.WARM)) {
            do {
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
            } while (dungeon.getDungeonMatrix()[x][y] != ' ');
            randomNum = rand.nextInt(5);
            if (randomNum == 0) {
                type = "Sylvan Revenant";
                health = 10000;
                attack = 1500;
                defense = 750;
                comment = "Once protector  now vengeance bound  I haunt the forest forever.";
            } else if (randomNum == 1) {
                type = "Scorchscale Behemoth";
                health = 75000;
                attack = 1500;
                defense = 500;
                comment = "Born from the searing sands  I am the land’s living inferno.";
            } else if (randomNum == 2) {
                type = "Orc King";
                health = 15000;
                attack = 750;
                defense = 1000;
                gender = "Male";
                comment = "Me Orc King! Crush all who stand! Blood and bone belong to me!";
            } else if (randomNum == 3) {
                type = "Plaguevine Ravager";
                health = 10000;
                attack = 1700;
                defense = 400;
                comment = "Toxic vines and pestilence will claim you as their own.";
            } else {
                type = "Shadowbane Hero";
                health = 7500;
                attack = 1500;
                defense = 500;
                gender = "Male";
                comment = "Once hailed as a savior, now broken by betrayal—his shadow hides the pain no light can heal.";
            }
            dungeon.getDungeonMatrix()[x][y] = 'E';
        } else if (dungeon.getBiome().equals(Biomes.COLD)) {
            do {
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
            } while (dungeon.getDungeonMatrix()[x][y] != ' ');
            randomNum = rand.nextInt(5);
            if (randomNum == 0) {
                type = "Icevein Hydra";
                health = 10000;
                attack = 1500;
                defense = 750;
                gender = "Male";
                comment = "We are here to bring about your merciless death... and freeze you into oblivion.";
            } else if (randomNum == 1) {
                type = "Glacier Empress";
                health = 10000;
                attack = 1500;
                defense = 500;
                gender = "Female";
                comment = "I will freeze you  binding you to me for eternity.";
            } else if (randomNum == 2) {
                type = "Shardbreaker Yeti";
                health = 17500;
                attack = 1000;
                defense = 750;
                gender = "Male";
                comment = "I will crush your bones and shatter your spirit beneath the glacier’s weight.";
            } else if (randomNum == 3) {
                type = "Blizzard Queen";
                health = 7500;
                attack = 1250;
                defense = 500;
                gender = "Female";
                comment = "Within my storm  all hope freezes and dies.And you will be the next one...";
            } else {
                type = "Frostbane Sovereign";
                health = 12500;
                attack = 1500;
                defense = 400;
                gender = "Female";
                comment = "I am the end of all warmth your last breath will freeze in my presence.";
            }
            dungeon.getDungeonMatrix()[x][y] = 'E';
        }
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
                } else if (randomNum == 1) {
                    type = "Goblin";
                    health = 1500;
                    attack = 250;
                    defense = 100;
                    comment = "AHAHAHA !!!There is no way that mortal like you can beat me you are easy prey for me.";
                    gender = "Male";
                } else if(randomNum == 2) {
                    type = "Wicked Dryads";
                    health = 1700;
                    attack = 200;
                    defense = 200;
                    comment = "I have been protecting this forest for years.And I won't allow you to enter this forest.";
                    gender = "Female";
                } else {
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
                    health = 2750;
                    attack = 350;
                    defense = 75;
                    comment = "PPPLLEEASEE DODODON'T hurt me I am defenseless... Just kidding my army will destroy you.";
                    gender = "Male";
                }else if (randomNum == 1) {
                    type = "Skyrazor Flock";
                    health = 4000;
                    attack = 175;
                    defense = 200;
                    comment = "I am the defender of that plains.You can't go through that path.";
                    gender = "Female";
                }else if(randomNum == 2) {
                    type = "Earthhorn Bull";
                    health = 2000;
                    attack = 200;
                    defense = 75;
                    comment = "Are you wearing red.No you can't wear that so I will kill you.";
                    gender = "Male";
                } else {
                    type = "Doppelganger";
                    health = 1500;
                    attack = 225;
                    defense = 100;
                    comment = "Please help me!!! ... Huh just as I planned... you fell for my trap.";
                    gender = "Male";
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
                } while (dungeon.getDungeonMatrix()[x][y] != ' ');
                randomNum = rand.nextInt(4);
                if (randomNum == 0) {
                    type = "Swamp Ghoul";
                    health = 2500;
                    attack = 150;
                    defense = 150;
                    comment = "You you.Did you kill my people? I will make you pay for this.";
                    gender = "Male";
                } else if (randomNum == 1) {
                    type = "Reed Witch";
                    health = 1700;
                    attack = 300;
                    defense = 200;
                    comment = "I will make you fall in love with me and you will always remain my slave.";
                    gender = "Female";
                } else if (randomNum == 2) {
                    type = "Drowned Spirit";
                    health = 1400;
                    attack = 275;
                    defense = 125;
                    comment = "I am cursed. Please defeat the darkness within me to break this curse and save me.";
                    gender = "Female";
                } else {
                    type = "Undead";
                    health = 1750;
                    attack = 200;
                    defense = 75;
                    comment = "The grave could not hold me... and neither will you.";
                    gender = "Male";
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
                } while (dungeon.getDungeonMatrix()[x][y] != ' ');
                randomNum = rand.nextInt(4);
                if (randomNum == 0) {
                    type = "Sand Wraith";
                    health = 4500;
                    attack = 300;
                    defense = 250;
                    comment = "My touch withers all life in the desert.";
                    gender = "Female";
                } else if (randomNum == 1) {
                    type = "Cursed Mummy";
                    health = 3000;
                    attack = 450;
                    defense = 200;
                    comment = "Love turned lethal... that is my curse.";
                    gender = "Female";
                } else if(randomNum == 2) {
                    type = "Ancient Sentinel";
                    health = 3750;
                    attack = 200;
                    defense = 400;
                    comment = "Face me, and be measured by the weight of history";
                    gender = "Male";
                } else {
                    type = "Dust Elemental";
                    health = 3550;
                    attack = 475;
                    defense = 150;
                    comment = "I am the storm beneath your feet.";
                    gender = "Male";
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
                } while (dungeon.getDungeonMatrix()[x][y] != ' ');
                randomNum = rand.nextInt(4);
                if (randomNum == 0) {
                    type = "Fire Wolves";
                    health = 5000;
                    attack = 450;
                    defense = 250;
                    comment = "Burn with us... or be reduced to ashes";
                    gender = "Male";
                } else if (randomNum == 1) {
                    type = "Scorch Golem";
                    health = 6000;
                    attack = 400;
                    defense = 200;
                    comment = "Unbreakable unstoppable... I am the inferno made flesh.";
                    gender = "Male";
                } else if(randomNum == 2) {
                    type = "Lava Wisp";
                    health = 4550;
                    attack = 400;
                    defense = 200;
                    comment = "Catch me if you can  but beware the burn.";
                    gender = "Female";
                } else {
                    type = "Fire Elemental";
                    health = 4000;
                    attack = 600;
                    defense = 150;
                    comment = "No water can quench my eternal blaze.";
                    gender = "Female";
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
                } while (dungeon.getDungeonMatrix()[x][y] != ' ');
                randomNum = rand.nextInt(4);
                if (randomNum == 0) {
                    type = "Shadowmane";
                    health = 4000;
                    attack = 250;
                    defense = 150;
                    comment = "Fear the night  for I am its fury";
                    gender = "Female";
                } else if (randomNum == 1) {
                    type = "Earthcleaver";
                    health = 2750;
                    attack = 350;
                    defense = 200;
                    comment = "My cleave splits mountains and shatters bones.";
                    gender = "Male";
                } else if(randomNum == 2) {
                    type = "Totemic Wraiths";
                    health = 3725;
                    attack = 200;
                    defense = 150;
                    comment = "The totems call... and we obey";
                    gender = "Female";
                } else {
                    type = "Drywind Spirits";
                    health = 3050;
                    attack = 275;
                    defense = 150;
                    comment = "Our presence dries the land and chills the soul.";
                    gender = "Female";
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
                    health = 2500;
                    attack = 400;
                    defense = 75;
                    comment = "My breath freezes hope itself. You tread on sacred ice... now feel its bite.";
                    gender = "Male";
                } else if (randomNum == 1) {
                    type = "Frost Wraith";
                    health = 4000;
                    attack = 200;
                    defense = 200;
                    comment = "Your warmth… will not last and you can not outrun the frost.";
                    gender = "Female";
                } else if(randomNum == 2) {
                    type = "Icebound Shaman";
                    health = 2250;
                    attack = 250;
                    defense = 150;
                    comment = "The cold has no mercy. Nor do I.";
                    gender = "Male";
                } else {
                    type = "Shard Bears";
                    health = 3000;
                    attack = 250;
                    defense = 150;
                    comment = "You crack like thin ice beneath us";
                    gender = "Female";
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
                    attack = 300;
                    defense = 225;
                    comment = "Pierce the silence... leave nothing behind.";
                    gender = "Male";
                } else if (randomNum == 1) {
                    type = "Blizzard Stalker";
                    health = 3000;
                    attack = 150;
                    defense = 400;
                    comment = "You think you’re alone in the storm?";
                    gender = "Female";
                } else if(randomNum == 2) {
                    type = "Cryomancer’s Remnant";
                    health = 3000;
                    attack = 275;
                    defense = 225;
                    comment = "You walk where power died… and cursed it left behind.";
                    gender = "Female";
                } else {
                    type = "Icicle Bats";
                    health = 2750;
                    attack = 400;
                    defense = 175;
                    comment = "Our wings cut the frozen air. And you will be the next to be cut.";
                    gender = "Female";
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
                    health = 3500;
                    attack = 325;
                    defense = 175;
                    comment = "Your fate is sealed beneath the ice and waves.";
                    gender = "Male";
                } else if (randomNum == 1) {
                    type = "Frosthorn Herd";
                    health = 3200;
                    attack = 340;
                    defense = 250;
                    comment = "Challenge the herd… and be trampled beneath the frost.";
                    gender = "Male";
                } else if(randomNum == 2) {
                    type = "Lightfang Wolf";
                    health = 4250;
                    attack = 325;
                    defense = 175;
                    comment = "I hunt not just with claws but with light itself.";
                    gender = "Female";
                } else {
                    type = "Moonfur Bear";
                    health = 3250;
                    attack = 300;
                    defense = 250;
                    comment = "Beware the beast that howls at the full moon.";
                    gender = "Male";
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
