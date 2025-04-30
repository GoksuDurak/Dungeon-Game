import java.util.Random;

public class Chest {
    private int x;
    private int y;
    private String type;
    private String[] chest;
    public Chest(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void randomChest(Random random)
    {
        String[] randomType = new String[3];
        randomType[0] = "common";
        randomType[1] = "rare";
        randomType[2] = "super rare";
        int randomNum = random.nextInt(100);
        if(randomNum < 60)
        {
            randomNum = 0;
        } else if (randomNum < 90) {
            randomNum = 1;
        }else {
            randomNum = 2;
        }
        String type = randomType[randomNum];
        if(type.equals("common")) {
            chest = new String[5];
            for (int i = 0; i < chest.length; i++)
            {
                //100 - 200 para 0
                //sıradan zırh 1
                //xp 2
                randomNum = random.nextInt(3);
                if(randomNum == 0)
                {
                    chest[i] = "m "+(random.nextInt(100) + 100);
                }else if(randomNum == 1)
                {
                    // 0 kask
                    // 1 pantolon
                    // 2 zırh
                    // 3 kılıç
                    randomNum = random.nextInt(4);
                    if(randomNum == 0)
                    {
                        chest[i] = "helmet"; // helmet
                    }
                    else if (randomNum == 1)
                    {
                        chest[i] = "chestPlate"; // chestPlate 1000 defence gibi
                    }
                    else if(randomNum == 2)
                    {
                        chest[i] = "boots"; // boots
                    }else
                    {
                        //silahlar
                        // 1 kılıç sword
                        // 2 yay bow
                        // 3 mızrak spear
                        // 4 balta axe
                        // 5 asa staff
                        randomNum = random.nextInt(5);
                        if(randomNum == 0)
                        {
                            chest[i] = "sword";
                        }
                        else if (randomNum == 1)
                        {
                            chest[i] = "bow";
                        }
                        else if (randomNum == 2)
                        {
                            chest[i] = "spear";
                        }
                        else if (randomNum == 3)
                        {
                            chest[i] = "axe";
                        }else
                        {
                            chest[i] = "staff";
                        }

                    }

                }else
                {

                }

            }
        } else if (type.equals("rare")) {
            chest = new String[10];
            for (int i = 0; i < chest.length; i++)
            {
                //200 - 400 para
                //ender zırh
                //xp


            }
        } else if (type.equals("super rare")) {
            chest = new String[15];
            for (int i = 0; i < chest.length; i++)
            {
                //400 - 800 para
                //destansı zırh
                //xp


            }
        }
    }
}
