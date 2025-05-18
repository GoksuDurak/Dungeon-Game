import java.util.Random;

public class Chest {
    private int x;
    private int y;
    private String type;
    private String[] items;
    public Chest(int x, int y, String type,String[] items)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.items = items;
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
    public void randomChest1(Random random,int capacity)
    {
        int randomNum = 0;
            items = new String[capacity];
            for (int i = 0; i < items.length; i++)
            {
                //para 0
                //zırh 1
                //xp 2
                randomNum = random.nextInt(4);
                if(randomNum == 0)
                {
                    items[i] = "money";
                }else if(randomNum == 1)
                {
                    // 0 kask
                    // 1 pantolon
                    // 2 zırh
                    // 3 kılıç
                    randomNum = random.nextInt(4);
                    if(randomNum == 0)
                    {
                        items[i] = "helmet"; // helmet defence 250
                    }
                    else if (randomNum == 1)
                    {
                        items[i] = "chestPlate"; // chestPlate defence 500
                    }
                    else if(randomNum == 2)
                    {
                        items[i] = "boots"; // boots defence 250
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
                            items[i] = "sword";
                        }
                        else if (randomNum == 1)
                        {
                            items[i] = "bow";
                        }
                        else if (randomNum == 2)
                        {
                            items[i] = "spear";
                        }
                        else if (randomNum == 3)
                        {
                            items[i] = "axe";
                        }else
                        {
                            items[i] = "staff";
                        }

                    }

                }else if(randomNum == 2)
                {
                    items[i] = "xp";
                }else
                {
                    randomNum = random.nextInt(3);
                    if(randomNum == 0)
                    {
                        items[i] = "healthPotion";
                    }else if(randomNum == 1)
                    {
                        items[i] = "defencePotion";
                    }else
                    {
                        items[i] = "attackPotion"; // 1 iksir etkisi 0-x
                    }

                }

            }
        }


    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
