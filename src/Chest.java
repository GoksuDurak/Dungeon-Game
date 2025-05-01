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
    public void randomChest(Random random)
    {
        int randomNum = 0;
        if(type.equals("common")) {
            items = new String[5];
            for (int i = 0; i < items.length; i++)
            {
                //100 - 200 para 0
                //sıradan zırh 1
                //xp 2
                randomNum = random.nextInt(3);
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

                }else
                {
                    items[i] = "xp";
                }

            }
        } else if (type.equals("rare")) {
            items = new String[10];
            for (int i = 0; i < items.length; i++)
            {
                //200 - 400 para
                //ender zırh
                //xp
                randomNum = random.nextInt(3);
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
                        items[i] = "helmet"; // helmet defence 500
                    }
                    else if (randomNum == 1)
                    {
                        items[i] = "chestPlate"; // chestPlate defence 750
                    }
                    else if(randomNum == 2)
                    {
                        items[i] = "boots"; // boots defence 500
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

                }else
                {
                    items[i] = "xp";
                }


            }
        } else if (type.equals("epic")) {
            items = new String[15];
            for (int i = 0; i < items.length; i++)
            {
                //400 - 800 para
                //destansı zırh
                //xp
                randomNum = random.nextInt(3);
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

                }else
                {
                    items[i] = "xp";
                }

            }
            setItems(items);
        }
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}
