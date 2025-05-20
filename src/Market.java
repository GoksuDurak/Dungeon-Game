import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Market {
    private int capacity;
    private Random rand = new Random();
    private GamePlay gamePlay;
    private Product[] marketProducts;
    private boolean[] usedProduct;
    private boolean isMarketStable = true;
    private boolean isStableChange = false;
    private Product[] productsList = new Product[44];

    Market(int capacity, GamePlay gamePlay) {
        marketProducts = new Product[capacity];
        usedProduct = new boolean[capacity];
        this.gamePlay = gamePlay;
    }

    public Product[] getMarketProducts() {
        return marketProducts;
    }

    public void setMarketProducts(Product[] marketProducts) {
        this.marketProducts = marketProducts;
    }

    public Product[] fillMarketProducts() {

        Integer[] randomNumbers = new Integer[marketProducts.length / 8];
        int elementCount = 0;
        boolean isExist = false;
        //ucuz ürünler
        int effect = 0;
        int quantity = 0;
        effect = rand.nextInt(100)+100;
        quantity = rand.nextInt(100)+100;
        productsList[0] = new Product("healthPotion", 200, quantity, "cheap",24,effect,"Potion"); // 100-200 hp
        effect = rand.nextInt(100)+100;
        productsList[1] = new Product("defencePotion", 200, quantity, "cheap",27,effect,"Potion"); // 100-200 dp
        effect = rand.nextInt(100)+100;
        productsList[2] = new Product("attackPotion", 200, quantity, "cheap",30,effect,"Potion"); // 100-200 ap
        productsList[3] = new Product("helmet", 250, 1, "cheap",6,250,"Armor"); // defence 250
        productsList[4] = new Product("chestplate", 500, 1, "cheap",3,500,"Armor"); //defence 500
        productsList[5] = new Product("boots", 250, 1, "cheap",0,250,"Armor"); // defence 250
        productsList[6] = new Product("spear", 250, 1, "cheap",21,175,"Weapon"); // attack 175
        productsList[7] = new Product("sword", 275, 1, "cheap",9,200,"Weapon"); // attack 200
        productsList[8] = new Product("staff", 300, 1, "cheap",15,150,"Weapon"); // attack 225
        productsList[9] = new Product("bow", 325, 1, "cheap",12,250,"Weapon"); // attack 250
        productsList[10] = new Product("axe", 350, 1, "cheap",18,275,"Weapon"); // attack 275
        //orta pahalılıkta ürünler
        effect = rand.nextInt(200)+200;
        quantity = rand.nextInt(100)+100;
        productsList[11] = new Product("healthPotion", 350, quantity, "moderate",25,effect,"Potion"); // 200-400 hp
        effect = rand.nextInt(200)+200;
        productsList[12] = new Product("defencePotion", 350, quantity, "moderate",28,effect,"Potion"); // 200-400 dp
        effect = rand.nextInt(200)+200;
        productsList[13] = new Product("attackPotion", 350, quantity, "moderate",31,effect,"Potion"); // 200-400 ap
        productsList[14] = new Product("helmet", 400, 1, "moderate",7,500,"Armor"); // defence 500
        productsList[15] = new Product("chestplate", 800, 1, "moderate",4,1000,"Armor"); // defence 1000
        productsList[16] = new Product("boots", 400, 1, "moderate",1,500,"Armor"); // defence 500
        productsList[17] = new Product("spear", 400, 1, "moderate",22,350,"Weapon"); // attack 350
        productsList[18] = new Product("sword", 450, 1, "moderate",10,400,"Weapon"); // attack 400
        productsList[19] = new Product("staff", 500, 1, "moderate",16,450,"Weapon"); // attack 450
        productsList[20] = new Product("bow", 550, 1, "moderate",13,500,"Weapon"); // attack 500
        productsList[21] = new Product("axe", 600, 1, "moderate",19,550,"Weapon"); // attack 550
        //pahalı ürünler
        effect = rand.nextInt(400)+400;
        quantity = rand.nextInt(100)+100;
        productsList[22] = new Product("healthPotion", 450, quantity, "expensive",26,effect,"Potion"); // 400-800 hp
        effect = rand.nextInt(400)+400;
        productsList[23] = new Product("defencePotion", 450, quantity, "expensive",29,effect,"Potion"); // 400-800 dp
        effect = rand.nextInt(400)+400;
        productsList[24] = new Product("attackPotion", 450, quantity, "expensive",32,effect,"Potion"); // 400-800 ap
        productsList[25] = new Product("helmet", 700, 1, "expensive",8,1000,"Armor"); // defence 1000
        productsList[26] = new Product("chestplate", 1400, 1, "expensive",5,2000,"Armor"); // defence 2000
        productsList[27] = new Product("boots", 700, 1, "expensive",2,1000,"Armor"); // defence 1000
        productsList[28] = new Product("spear", 700, 1, "expensive",23,700,"Weapon"); //  attack 700
        productsList[29] = new Product("sword", 800, 1, "expensive",11,800,"Weapon"); //  attack 800
        productsList[30] = new Product("staff", 900, 1, "expensive",17,900,"Weapon"); //  attack 900
        productsList[31] = new Product("bow", 1000, 1, "expensive",14,1000,"Weapon"); //  attack 1000
        productsList[32] = new Product("axe", 1100, 1, "expensive",20,1100,"Weapon"); //  attack 1100
        //özel ürünler (envantere eklenecek)
        effect = rand.nextInt(1000)+500;
        quantity = rand.nextInt(100)+100;
        productsList[33] = new Product("healthPotion", 800, quantity, "special",33,effect,"Potion"); // 1000-1500 hp
        effect = rand.nextInt(1000)+500;
        productsList[34] = new Product("defencePotion", 800, quantity, "special",34,effect,"Potion"); // 1000-1500 dp
        effect = rand.nextInt(1000)+500;
        productsList[35] = new Product("attackPotion", 800, quantity, "special",35,effect,"Potion"); // 1000-1500 ap
        productsList[36] = new Product("helmet", 2000, 1, "special",36,2500,"Armor"); // defence 2500
        productsList[37] = new Product("chestplate", 4000, 1, "special",37,5000,"Armor"); // defence 5000
        productsList[38] = new Product("boots", 2000, 1, "special",38,2500,"Armor"); // defence 2500
        productsList[39] = new Product("spear", 2000, 1, "special",43,1750,"Weapon"); //  attack 1750
        productsList[40] = new Product("sword", 2200, 1, "special",39,2000,"Weapon"); //  attack 2000
        productsList[41] = new Product("staff", 2400, 1, "special",41,2500,"Weapon"); //  attack 2250
        productsList[42] = new Product("bow", 2600, 1, "special",40,2500,"Weapon"); //  attack 2500
        productsList[43] = new Product("axe", 2800, 1, "special",42,2750,"Weapon"); //  attack 2750


        int cheapElementCount = 0;
        int moderateElementCount = 0;
        int expensiveElementCount = 0;
        int specialElementCount = 0;
        boolean isCountCorrect;
        do {
            isCountCorrect = false;
            elementCount = 0;
            randomNumbers = new Integer[marketProducts.length / 5];
            int randomNum = rand.nextInt(44);
            randomNumbers[elementCount] = randomNum;
            elementCount++; //ilk eleman aldık
            cheapElementCount = 0;
            moderateElementCount = 0;
            expensiveElementCount = 0;
            specialElementCount = 0;
            for (int i = 0; i < randomNumbers.length - 1; i++)
            { // burda x tane random attık
                do {
                    randomNum = rand.nextInt(100);
                    if(randomNum < 65)
                    {
                        randomNum = rand.nextInt(11);
                        cheapElementCount++;
                    }else if(randomNum < 80)
                    {
                        randomNum = rand.nextInt(11) + 11;
                        moderateElementCount++;
                    }else if(randomNum < 95)
                    {
                        randomNum = rand.nextInt(11) + 22;
                        expensiveElementCount++;
                    }else
                    {
                        randomNum = rand.nextInt(11) + 33;
                        specialElementCount++;
                    }
                    isExist = false;

                    for (int j = 0; j < elementCount; j++) {  //içindeki eleman sayısı kadar dön
                        if (randomNum == randomNumbers[j])
                        {
                            isExist = true;
                        }
                    }
                } while (isExist);
                randomNumbers[elementCount] = randomNum;
                elementCount++;
            }
            if (cheapElementCount >= 5 && moderateElementCount >= 5 && expensiveElementCount >= 5 && specialElementCount >= 5) {
                isCountCorrect = true;
            }
        } while (!isCountCorrect);
        int specialCount = 0;
        int cheapCount = 0;
        int moderateCount = 0;
        int expensiveCount = 0;
        for(int i = 0; i < randomNumbers.length; i++)
        {
            Product product = productsList[randomNumbers[i]];
            if(product.getCostType().equals("cheap"))
            {
                marketProducts[i] = product;
                cheapCount++;
            } else if (product.getCostType().equals("moderate"))
            {
                marketProducts[i] = product;
                moderateCount++;
            } else if (product.getCostType().equals("expensive"))
            {
                marketProducts[i] = product;
                expensiveCount++;
            } else if (product.getCostType().equals("special"))
            {
                marketProducts[i] = product;
                specialCount++;
            }
        }
        System.out.println(cheapCount);
        System.out.println(moderateCount);
        System.out.println(expensiveCount);
        System.out.println(specialCount);
        System.out.println(marketProducts.length);
        return marketProducts;
    }
    public void marketOpened(Scanner scanner, Game game)
    {
        Product[] products = new Product[5];
        String costType = "";
        Product currentProduct = null;
        int productCount = 0;
        int cursorPositionX = 0;
        game.Clear();
        System.out.println("Market opened!!!");

            while (gamePlay.isMarketOpened()) {
                if(isMarketStable) {
                    cursorPositionX = 0;
                    productCount = 0;
                    for (int i = 0; i < 15; i += 5) {
                        if (i == 0) {
                            costType = "cheap";
                        } else if (i == 5) {
                            costType = "moderate";
                        } else {
                            costType = "expensive";
                        }
                        for (int j = 0; j < marketProducts.length; j++) {
                            if (marketProducts[j] != null) {
                                if (marketProducts[j].getCostType().equals(costType)) {
                                    products[productCount] = marketProducts[j];
                                    productCount++;
                                    if (productCount == 5) {
                                        productCount = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        //----------- en büyük uzunluk bulduk -----------------
                        int currentLength = -1;
                        for (int j = 0; j < products.length; j++) {
                            if (products[j] != null) {
                                if (currentLength < products[j].getCostType().length()) {
                                    currentLength = products[j].getCostType().length();
                                }
                                if (currentLength < products[j].getName().length()) {
                                    currentLength = products[j].getName().length();
                                }
                                if (currentLength < String.valueOf(products[j].getPrice()).length()) {
                                    currentLength = String.valueOf(products[j].getPrice()).length();
                                }
                                if (currentLength < String.valueOf(products[j].getQuantity()).length()) {
                                    currentLength = String.valueOf(products[j].getQuantity()).length();
                                }
                            }
                        }
                        currentLength++;
                        for (int a = 0; a < 30; a += 6) { // her seferi 6 ürün
                            for (int j = 0; j < marketProducts.length; j++) {

                                game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 1);
                                for (int k = 0; k < currentLength + 14; k++) {
                                    System.out.print("-");
                                }
                                if (!usedProduct[j] && marketProducts[j] != null && marketProducts[j].getCostType().equals(costType)) {
                                    currentProduct = marketProducts[j];
                                    usedProduct[j] = true;
                                    //------- Uzunlukları buldum en büyüğünü --------------
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 2);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Type");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getCostType());
                                    for (int k = 0; k < currentLength - currentProduct.getCostType().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 3);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Name");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getName());
                                    for (int k = 0; k < currentLength - currentProduct.getName().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 4);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Effect");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("   : ");

                                    System.out.print(currentProduct.getEffect());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getEffect()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 5);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Price");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("    : ");
                                    System.out.print(currentProduct.getPrice());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getPrice()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 6);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Quantity");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print(" : ");
                                    System.out.print(currentProduct.getQuantity());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getQuantity()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    break;
                                }
                            }
                            System.out.println();
                        }
                        if (i == 0) {
                            cursorPositionX += currentLength;
                            cursorPositionX += 13;
                        } else if (i == 5) {
                            cursorPositionX += 13;
                            cursorPositionX += currentLength;
                        } else {
                            cursorPositionX += currentLength;
                        }
                        game.cn.getTextWindow().setCursorPosition(0, 31);
                        for (int k = 0; k < cursorPositionX+14; k++) {
                            System.out.print("-");
                        }
                    }
                    for (int j = 0; j < usedProduct.length; j++) {
                        if (usedProduct[j]) {
                            usedProduct[j] = false;
                        }
                    }
                    System.out.println();
                    if(GamePlay.isBuyActive){
                        String choice;
                        while (true) {
                            System.out.println("Do you want to buy item ? Your money : "+gamePlay.getPlayer().getMoney());
                            choice = scanner.nextLine();
                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                int x = game.cn.getTextWindow().getCursorX();
                                int y = game.cn.getTextWindow().getCursorY();
                                gamePlay.clearPart(x,y-2,game,3,70);
                                while (true) {
                                    System.out.println("Which item do you want to buy ?");
                                    System.out.println("Write first name then cost type with one space.");
                                    choice = scanner.nextLine();
                                    String[] data = choice.split(" ");
                                    boolean found = false;
                                    if(data.length == 2){
                                        Product product = new Product("",0,0,"",0,0,"");
                                        for(int i = 0;i<marketProducts.length;i++){
                                            product = marketProducts[i];
                                            if(product != null){
                                                if(data[1].equalsIgnoreCase(marketProducts[i].getCostType())){
                                                    if(data[0].equalsIgnoreCase(product.getName())){
                                                        found = true;
                                                        // we buy item
                                                        while (true) {
                                                            System.out.println("Item cost: " + product.getPrice() + " Your money: " + gamePlay.getPlayer().getMoney());
                                                            System.out.println("Do you want to buy ?");
                                                            choice = scanner.nextLine();
                                                            gamePlay.clearPart(x,y-2,game,6,70);
                                                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                                                while (true) {
                                                                    System.out.println("How many items do you want to buy ?");
                                                                    choice = scanner.nextLine();
                                                                    if(choice.matches("\\d+") && choice.length() <= 6){
                                                                        break;
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                }
                                                                gamePlay.clearPart(x,y-2,game,5,70);
                                                                if (Integer.parseInt(choice) * product.getPrice() > gamePlay.getPlayer().getMoney()) {
                                                                    System.out.println("Total cost : "+(Integer.parseInt(choice) * product.getPrice())+" Your money isn't enough.");
                                                                    try {
                                                                        Thread.sleep(1000);
                                                                    } catch (InterruptedException e) {
                                                                        throw new RuntimeException(e);
                                                                    }
                                                                }else {
                                                                    gamePlay.getPlayer().setMoney(gamePlay.getPlayer().getMoney()-(product.getPrice() * Integer.parseInt(choice)));
                                                                    String type = "";
                                                                    if(data[0].equalsIgnoreCase("healthPotion") || data[0].equalsIgnoreCase("attackPotion") || data[0].equalsIgnoreCase("defencePotion")){
                                                                        type = "Potion";
                                                                    } else if (data[0].equalsIgnoreCase("helmet")|| data[0].equalsIgnoreCase("chestPlate") || data[0].equalsIgnoreCase("boots")) {
                                                                        type = "Armor";
                                                                    } else if (data[0].equalsIgnoreCase("sword") || data[0].equalsIgnoreCase("spear") || data[0].equalsIgnoreCase("bow") || data[0].equalsIgnoreCase("staff") || data[0].equalsIgnoreCase("axe")) {
                                                                        type = "Weapon";
                                                                    }

                                                                    if (type.equals("Armor")){
                                                                        Armor armor = new Armor(product.getEffect(), product.getName());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),armor);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Weapon")) {
                                                                        Weapon weapon = new Weapon(product.getName(),product.getEffect());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),weapon);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Potion")) {
                                                                        String color = "";
                                                                        String description = "";
                                                                        if(product.getName().equals("healthPotion")){
                                                                            color = "red";
                                                                            description = "This increase your health";
                                                                        } else if (product.getName().equals("defencePotion")) {
                                                                            color = "yellow";
                                                                            description = "This increase your defence";
                                                                        } else if (product.getName().equals("attackPotion")) {
                                                                            color = "blue";
                                                                            description = "This increase your attack";
                                                                        }
                                                                        Potion potion = new Potion(product.getName(),description, product.getEffect(),color);
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),potion);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                    if(marketProducts[i].getQuantity() - Integer.parseInt(choice) == 0){
                                                                        if(marketProducts[i].getName().equalsIgnoreCase(product.getName()) && marketProducts[i].getCostType().equalsIgnoreCase(product.getCostType())){
                                                                            //we find product
                                                                            Product product1 = new Product("",0,0,"",0,0,"");
                                                                            if(product.getCostType().equalsIgnoreCase("cheap")){
                                                                                int random = rand.nextInt(11);
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("moderate")) {
                                                                                int random = rand.nextInt(11) + 11;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("expensive")) {
                                                                                int random = rand.nextInt(11) + 22;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("special")) {
                                                                                product1 = productsList[rand.nextInt(11)];
                                                                            }
                                                                            marketProducts[i].setName(product1.getName());
                                                                            marketProducts[i].setType(product1.getType());
                                                                            marketProducts[i].setCostType(product1.getCostType());
                                                                            marketProducts[i].setQuantity(product1.getQuantity());
                                                                            marketProducts[i].setPrice(product1.getPrice());
                                                                            marketProducts[i].setId(product1.getId());
                                                                            marketProducts[i].setEffect(product1.getEffect());
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                                                break;
                                                            }
                                                            gamePlay.clearPart(x,y-2,game,5,70);
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if(!found){
                                            gamePlay.clearPart(x,y-2,game,5,70);
                                            System.out.println("Item not found.");
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        break;
                                    }
                                    gamePlay.clearPart(x,y-2,game,5,70);
                                }
                                break;
                            } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                if(GamePlay.isMarketMovesLeft){
                                    GamePlay.isMarketMovesLeft = false;
                                }
                                GamePlay.isBuyActive = false;
                                break;
                            }
                            int x = game.cn.getTextWindow().getCursorX();
                            int y = game.cn.getTextWindow().getCursorY();
                            gamePlay.clearPart(x,y-2,game,5,70);
                        }

                    }
                }
                if(GamePlay.isMarketMovesLeft || GamePlay.isMarketMovesRight) {
                    isStableChange = true;
                }
                if(isStableChange){
                    Game.Clear();
                    isStableChange = false;
                }
                if (GamePlay.isMarketMovesRight) {
                    isMarketStable = false;
                    if(GamePlay.isMarketMovesLeft){
                        GamePlay.isMarketMovesLeft = false;
                    }
                    cursorPositionX = 0;
                    productCount = 0;
                    for (int i = 0; i < 15; i += 5) {
                        if (i == 0) {
                            costType = "moderate";
                        } else if (i == 5) {
                            costType = "expensive";
                        } else {
                            costType = "special";
                        }
                        for (int j = 0; j < marketProducts.length; j++) {
                            if (marketProducts[j] != null) {
                                if (marketProducts[j].getCostType().equals(costType)) {
                                    products[productCount] = marketProducts[j];
                                    productCount++;
                                    if (productCount == 5) {
                                        productCount = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        //----------- en büyük uzunluk bulduk -----------------
                        int currentLength = -1;
                        for (int j = 0; j < products.length; j++) {
                            if (products[j] != null) {
                                if (currentLength < products[j].getCostType().length()) {
                                    currentLength = products[j].getCostType().length();
                                }
                                if (currentLength < products[j].getName().length()) {
                                    currentLength = products[j].getName().length();
                                }
                                if (currentLength < String.valueOf(products[j].getPrice()).length()) {
                                    currentLength = String.valueOf(products[j].getPrice()).length();
                                }
                                if (currentLength < String.valueOf(products[j].getQuantity()).length()) {
                                    currentLength = String.valueOf(products[j].getQuantity()).length();
                                }
                            }
                        }
                        currentLength++;
                        for (int a = 0; a < 30; a += 6) { // her seferi 5 ürün
                            for (int j = 0; j < marketProducts.length; j++) {

                                game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 1);
                                for (int k = 0; k < currentLength + 14; k++) {
                                    System.out.print("-");
                                }
                                if (!usedProduct[j] && marketProducts[j] != null && marketProducts[j].getCostType().equals(costType)) {
                                    currentProduct = marketProducts[j];
                                    usedProduct[j] = true;
                                    //------- Uzunlukları buldum en büyüğünü --------------
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 2);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Type");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getCostType());
                                    for (int k = 0; k < currentLength - currentProduct.getCostType().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 3);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Name");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getName());
                                    for (int k = 0; k < currentLength - currentProduct.getName().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 4);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Effect");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("   : ");

                                    System.out.print(currentProduct.getEffect());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getEffect()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 5);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Price");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("    : ");
                                    System.out.print(currentProduct.getPrice());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getPrice()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 6);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Quantity");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print(" : ");
                                    System.out.print(currentProduct.getQuantity());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getQuantity()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    break;
                                }
                            }
                            System.out.println();
                        }
                        if (i == 0) {
                            cursorPositionX += currentLength;
                            cursorPositionX += 13;
                        } else if (i == 5) {
                            cursorPositionX += 13;
                            cursorPositionX += currentLength;
                        } else {
                            cursorPositionX += currentLength;
                        }
                        game.cn.getTextWindow().setCursorPosition(0, 31);
                        for (int k = 0; k < cursorPositionX + 14; k++) {
                            System.out.print("-");
                        }
                    }
                    for (int j = 0; j < usedProduct.length; j++) {
                        if (usedProduct[j]) {
                            usedProduct[j] = false;
                        }
                    }
                    System.out.println();
                    if(GamePlay.isBuyActive){
                        String choice;
                        while (true) {
                            System.out.println("Do you want to buy item ? Your money : "+gamePlay.getPlayer().getMoney());
                            choice = scanner.nextLine();
                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                int x = game.cn.getTextWindow().getCursorX();
                                int y = game.cn.getTextWindow().getCursorY();
                                gamePlay.clearPart(x,y-2,game,3,70);
                                while (true) {
                                    System.out.println("Which item do you want to buy ?");
                                    System.out.println("Write first name then cost type with one space.");
                                    choice = scanner.nextLine();
                                    String[] data = choice.split(" ");
                                    boolean found = false;
                                    if(data.length == 2){
                                        Product product = new Product("",0,0,"",0,0,"");
                                        for(int i = 0;i<marketProducts.length;i++){
                                            product = marketProducts[i];
                                            if(product != null){
                                                if(data[1].equalsIgnoreCase(marketProducts[i].getCostType())){
                                                    if(data[0].equalsIgnoreCase(product.getName())){
                                                        found = true;
                                                        // we buy item
                                                        while (true) {
                                                            System.out.println("Item cost: " + product.getPrice() + " Your money: " + gamePlay.getPlayer().getMoney());
                                                            System.out.println("Do you want to buy ?");
                                                            choice = scanner.nextLine();
                                                            gamePlay.clearPart(x,y-2,game,6,70);
                                                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                                                while (true) {
                                                                    System.out.println("How many items do you want to buy ?");
                                                                    choice = scanner.nextLine();
                                                                    if(choice.matches("\\d+") && choice.length() <= 6){
                                                                        break;
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                }
                                                                gamePlay.clearPart(x,y-2,game,5,70);
                                                                if (Integer.parseInt(choice) * product.getPrice() > gamePlay.getPlayer().getMoney()) {
                                                                    System.out.println("Total cost : "+(Integer.parseInt(choice) * product.getPrice())+" Your money isn't enough.");
                                                                    try {
                                                                        Thread.sleep(1000);
                                                                    } catch (InterruptedException e) {
                                                                        throw new RuntimeException(e);
                                                                    }
                                                                }else {
                                                                    gamePlay.getPlayer().setMoney(gamePlay.getPlayer().getMoney()-(product.getPrice() * Integer.parseInt(choice)));
                                                                    String type = "";
                                                                    if(data[0].equalsIgnoreCase("healthPotion") || data[0].equalsIgnoreCase("attackPotion") || data[0].equalsIgnoreCase("defencePotion")){
                                                                        type = "Potion";
                                                                    } else if (data[0].equalsIgnoreCase("helmet")|| data[0].equalsIgnoreCase("chestPlate") || data[0].equalsIgnoreCase("boots")) {
                                                                        type = "Armor";
                                                                    } else if (data[0].equalsIgnoreCase("sword") || data[0].equalsIgnoreCase("spear") || data[0].equalsIgnoreCase("bow") || data[0].equalsIgnoreCase("staff") || data[0].equalsIgnoreCase("axe")) {
                                                                        type = "Weapon";
                                                                    }

                                                                    if (type.equals("Armor")){
                                                                        Armor armor = new Armor(product.getEffect(), product.getName());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),armor);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Weapon")) {
                                                                        Weapon weapon = new Weapon(product.getName(),product.getEffect());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),weapon);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Potion")) {
                                                                        String color = "";
                                                                        String description = "";
                                                                        if(product.getName().equals("healthPotion")){
                                                                            color = "red";
                                                                            description = "This increase your health";
                                                                        } else if (product.getName().equals("defencePotion")) {
                                                                            color = "yellow";
                                                                            description = "This increase your defence";
                                                                        } else if (product.getName().equals("attackPotion")) {
                                                                            color = "blue";
                                                                            description = "This increase your attack";
                                                                        }
                                                                        Potion potion = new Potion(product.getName(),description, product.getEffect(),color);
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),potion);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                    if(marketProducts[i].getQuantity() - Integer.parseInt(choice) == 0){
                                                                        if(marketProducts[i].getName().equalsIgnoreCase(product.getName()) && marketProducts[i].getCostType().equalsIgnoreCase(product.getCostType())){
                                                                            //we find product
                                                                            Product product1 = new Product("",0,0,"",0,0,"");
                                                                            if(product.getCostType().equalsIgnoreCase("cheap")){
                                                                                int random = rand.nextInt(11);
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("moderate")) {
                                                                                int random = rand.nextInt(11) + 11;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("expensive")) {
                                                                                int random = rand.nextInt(11) + 22;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("special")) {
                                                                                product1 = productsList[rand.nextInt(11)];
                                                                            }
                                                                            marketProducts[i].setName(product1.getName());
                                                                            marketProducts[i].setType(product1.getType());
                                                                            marketProducts[i].setCostType(product1.getCostType());
                                                                            marketProducts[i].setQuantity(product1.getQuantity());
                                                                            marketProducts[i].setPrice(product1.getPrice());
                                                                            marketProducts[i].setId(product1.getId());
                                                                            marketProducts[i].setEffect(product1.getEffect());
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                                                break;
                                                            }
                                                            gamePlay.clearPart(x,y-2,game,5,70);
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if(!found){
                                            gamePlay.clearPart(x,y-2,game,5,70);
                                            System.out.println("Item not found.");
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        break;
                                    }
                                    gamePlay.clearPart(x,y-2,game,5,70);
                                }
                                break;
                            } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                if(GamePlay.isMarketMovesLeft){
                                    GamePlay.isMarketMovesLeft = false;
                                }
                                GamePlay.isBuyActive = false;
                                break;
                            }
                            int x = game.cn.getTextWindow().getCursorX();
                            int y = game.cn.getTextWindow().getCursorY();
                            gamePlay.clearPart(x,y-2,game,5,70);
                        }

                    }else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
                if (GamePlay.isMarketMovesLeft) {
                    cursorPositionX = 0;
                    productCount = 0;
                    for (int i = 0; i < 15; i += 5) {
                        if (i == 0) {
                            costType = "cheap";
                        } else if (i == 5) {
                            costType = "moderate";
                        } else {
                            costType = "expensive";
                        }
                        for (int j = 0; j < marketProducts.length; j++) {
                            if (marketProducts[j] != null) {
                                if (marketProducts[j].getCostType().equals(costType)) {
                                    products[productCount] = marketProducts[j];
                                    productCount++;
                                    if (productCount == 5) {
                                        productCount = 0;
                                        break;
                                    }
                                }
                            }
                        }
                        //----------- en büyük uzunluk bulduk -----------------
                        int currentLength = -1;
                        for (int j = 0; j < products.length; j++) {
                            if (products[j] != null) {
                                if (currentLength < products[j].getCostType().length()) {
                                    currentLength = products[j].getCostType().length();
                                }
                                if (currentLength < products[j].getName().length()) {
                                    currentLength = products[j].getName().length();
                                }
                                if (currentLength < String.valueOf(products[j].getPrice()).length()) {
                                    currentLength = String.valueOf(products[j].getPrice()).length();
                                }
                                if (currentLength < String.valueOf(products[j].getQuantity()).length()) {
                                    currentLength = String.valueOf(products[j].getQuantity()).length();
                                }
                            }
                        }
                        currentLength++;
                        for (int a = 0; a < 30; a += 6) { // her seferi 5 ürün
                            for (int j = 0; j < marketProducts.length; j++) {

                                game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 1);
                                for (int k = 0; k < currentLength + 14; k++) {
                                    System.out.print("-");
                                }
                                if (!usedProduct[j] && marketProducts[j] != null && marketProducts[j].getCostType().equals(costType)) {
                                    currentProduct = marketProducts[j];
                                    usedProduct[j] = true;
                                    //------- Uzunlukları buldum en büyüğünü --------------
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 2);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Type");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getCostType());
                                    for (int k = 0; k < currentLength - currentProduct.getCostType().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 3);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Name");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("     : ");
                                    System.out.print(currentProduct.getName());
                                    for (int k = 0; k < currentLength - currentProduct.getName().length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 4);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Effect");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("   : ");

                                    System.out.print(currentProduct.getEffect());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getEffect()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 5);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Price");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print("    : ");
                                    System.out.print(currentProduct.getPrice());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getPrice()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    game.cn.getTextWindow().setCursorPosition(cursorPositionX, a + 6);
                                    System.out.print("| ");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.red));
                                    System.out.print("Quantity");
                                    game.cn.setTextAttributes(new TextAttributes(Color.white, Color.black));
                                    System.out.print(" : ");
                                    System.out.print(currentProduct.getQuantity());
                                    for (int k = 0; k < currentLength - String.valueOf(currentProduct.getQuantity()).length(); k++) {
                                        System.out.print(" ");
                                    }
                                    System.out.print("| ");
                                    break;
                                }
                            }
                            System.out.println();
                        }
                        if (i == 0) {
                            cursorPositionX += currentLength;
                            cursorPositionX += 13;
                        } else if (i == 5) {
                            cursorPositionX += 13;
                            cursorPositionX += currentLength;
                        } else {
                            cursorPositionX += currentLength;
                        }
                        game.cn.getTextWindow().setCursorPosition(0, 31);
                        for (int k = 0; k < cursorPositionX + 14; k++) {
                            System.out.print("-");
                        }
                    }
                    for (int j = 0; j < usedProduct.length; j++) {
                        if (usedProduct[j]) {
                            usedProduct[j] = false;
                        }
                    }
                    System.out.println();
                    if(GamePlay.isBuyActive){
                        String choice;
                        while (true) {
                            System.out.println("Do you want to buy item ? Your money : "+gamePlay.getPlayer().getMoney());
                            choice = scanner.nextLine();
                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                int x = game.cn.getTextWindow().getCursorX();
                                int y = game.cn.getTextWindow().getCursorY();
                                gamePlay.clearPart(x,y-2,game,3,70);
                                while (true) {
                                    System.out.println("Which item do you want to buy ?");
                                    System.out.println("Write first name then cost type with one space.");
                                    choice = scanner.nextLine();
                                    String[] data = choice.split(" ");
                                    boolean found = false;
                                    if(data.length == 2){
                                        Product product = new Product("",0,0,"",0,0,"");
                                        for(int i = 0;i<marketProducts.length;i++){
                                            product = marketProducts[i];
                                            if(product != null){
                                                if(data[1].equalsIgnoreCase(marketProducts[i].getCostType())){
                                                    if(data[0].equalsIgnoreCase(product.getName())){
                                                        found = true;
                                                        // we buy item
                                                        while (true) {
                                                            System.out.println("Item cost: " + product.getPrice() + " Your money: " + gamePlay.getPlayer().getMoney());
                                                            System.out.println("Do you want to buy ?");
                                                            choice = scanner.nextLine();
                                                            gamePlay.clearPart(x,y-2,game,6,70);
                                                            if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                                                while (true) {
                                                                    System.out.println("How many items do you want to buy ?");
                                                                    choice = scanner.nextLine();
                                                                    if(choice.matches("\\d+") && choice.length() <= 6){
                                                                        break;
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                }
                                                                gamePlay.clearPart(x,y-2,game,5,70);
                                                                if (Integer.parseInt(choice) * product.getPrice() > gamePlay.getPlayer().getMoney()) {
                                                                    System.out.println("Total cost : "+(Integer.parseInt(choice) * product.getPrice())+" Your money isn't enough.");
                                                                    try {
                                                                        Thread.sleep(1000);
                                                                    } catch (InterruptedException e) {
                                                                        throw new RuntimeException(e);
                                                                    }
                                                                }else {
                                                                    gamePlay.getPlayer().setMoney(gamePlay.getPlayer().getMoney()-(product.getPrice() * Integer.parseInt(choice)));
                                                                    String type = "";
                                                                    if(data[0].equalsIgnoreCase("healthPotion") || data[0].equalsIgnoreCase("attackPotion") || data[0].equalsIgnoreCase("defencePotion")){
                                                                        type = "Potion";
                                                                    } else if (data[0].equalsIgnoreCase("helmet")|| data[0].equalsIgnoreCase("chestPlate") || data[0].equalsIgnoreCase("boots")) {
                                                                        type = "Armor";
                                                                    } else if (data[0].equalsIgnoreCase("sword") || data[0].equalsIgnoreCase("spear") || data[0].equalsIgnoreCase("bow") || data[0].equalsIgnoreCase("staff") || data[0].equalsIgnoreCase("axe")) {
                                                                        type = "Weapon";
                                                                    }

                                                                    if (type.equals("Armor")){
                                                                        Armor armor = new Armor(product.getEffect(), product.getName());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),armor);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Weapon")) {
                                                                        Weapon weapon = new Weapon(product.getName(),product.getEffect());
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),weapon);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    } else if (type.equals("Potion")) {
                                                                        String color = "";
                                                                        String description = "";
                                                                        if(product.getName().equals("healthPotion")){
                                                                            color = "red";
                                                                            description = "This increase your health";
                                                                        } else if (product.getName().equals("defencePotion")) {
                                                                            color = "yellow";
                                                                            description = "This increase your defence";
                                                                        } else if (product.getName().equals("attackPotion")) {
                                                                            color = "blue";
                                                                            description = "This increase your attack";
                                                                        }
                                                                        Potion potion = new Potion(product.getName(),description, product.getEffect(),color);
                                                                        Item item = new Item(product.getName(), Integer.parseInt(choice),product.getId(),potion);
                                                                        gamePlay.getPlayer().getInventory().addItem(item);
                                                                    }
                                                                    gamePlay.clearPart(x,y-2,game,5,70);
                                                                    if(marketProducts[i].getQuantity() -Integer.parseInt(choice) == 0){
                                                                        if(marketProducts[i].getName().equalsIgnoreCase(product.getName()) && marketProducts[i].getCostType().equalsIgnoreCase(product.getCostType())){
                                                                            //we find product
                                                                            Product product1 = new Product("",0,0,"",0,0,"");
                                                                            if(product.getCostType().equalsIgnoreCase("cheap")){
                                                                                int random = rand.nextInt(11);
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("moderate")) {
                                                                                int random = rand.nextInt(11) + 11;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("expensive")) {
                                                                                int random = rand.nextInt(11) + 22;
                                                                                product1 = productsList[random];
                                                                            } else if (product.getCostType().equalsIgnoreCase("special")) {
                                                                                product1 = productsList[rand.nextInt(11)];
                                                                            }
                                                                            marketProducts[i].setName(product1.getName());
                                                                            marketProducts[i].setType(product1.getType());
                                                                            marketProducts[i].setCostType(product1.getCostType());
                                                                            marketProducts[i].setQuantity(product1.getQuantity());
                                                                            marketProducts[i].setPrice(product1.getPrice());
                                                                            marketProducts[i].setId(product1.getId());
                                                                            marketProducts[i].setEffect(product1.getEffect());
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                                                break;
                                                            }
                                                            gamePlay.clearPart(x,y-2,game,5,70);
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        if(!found){
                                            gamePlay.clearPart(x,y-2,game,5,70);
                                            System.out.println("Item not found.");
                                            try {
                                                Thread.sleep(1000);
                                            } catch (InterruptedException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                        break;
                                    }
                                    gamePlay.clearPart(x,y-2,game,5,70);
                                }
                                break;
                            } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                if(GamePlay.isMarketMovesRight){
                                    GamePlay.isMarketMovesRight = false;
                                }
                                GamePlay.isBuyActive = false;
                                break;
                            }
                            int x = game.cn.getTextWindow().getCursorX();
                            int y = game.cn.getTextWindow().getCursorY();
                            gamePlay.clearPart(x,y-2,game,5,70);
                        }

                    }else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if(GamePlay.inventoryOpened){
                    GamePlay.inventoryOpened = false;
                }
            }


    }
}
