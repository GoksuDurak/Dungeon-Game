import enigma.console.TextAttributes;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    //Oyun içinde oylaları burada başlar ve burada yaratırlır
    //Dunegon Npc Player burada yaratılır.
    private final Random rand = new Random();
    private boolean forestMazeActive = false;
    private boolean bossDefeated = false;
    private Vertex startVertex;
    private int defenceElixir = 0;
    private int attackElixir = 0;
    private String[] loots;
    private Chest chest;
    public static int enemyX = 0;
    private int playerFirstLife =0;
    private int enemyFirstLife =0;
    public static int enemyY = 0;
    public static int NPCX = 0;
    public static int NPCY = 0;
    private Item currentHelmet = null;
    private Item currentChestPLate = null;
    private Item currentBoots = null;
    private Item currentWeapon= null;
    private int weaponAttack = 0;
    private int helmetDefence = 0; // kaskın eski defansı tutmak ve çıkarınca ekleneni çıkarmak için
    private int chestplateDefence = 0;
    private int bootsDefence = 0;
    private Vertex finalVertex;
    private Vertex vertex;
    private Vertex vertexF;
    private Setting setting;
    private boolean gameFinished = false;
    private boolean isMarketOpened = false; // market açıldı mı
    private boolean isWarBegin = false;
    public static boolean inventoryOpened = false;
    private boolean takesInput = true;
    public static boolean helmetUsed = false;
    public static boolean chestPlateUsed = false;
    public static boolean bootsUsed = false;
    public static boolean weaponUsed = false;
    public static boolean isMarketMovesRight = false;
    public static boolean isMarketMovesLeft = false;
    public static boolean isBuyActive = false;
    private final Scanner scanner = new Scanner(System.in);
    private boolean quit = false;
    private Stack dungeonStack = new Stack(5); // zindanları burda tutacağız
    public static CircularQueue enemyCoordinateQueue = new CircularQueue(2);
    private Vertex enemyCoordinateVertex;
    public static CircularQueue NPCCoordinateQueue = new CircularQueue(2);
    private Vertex NPCCoordinateVertex;
    //private char[][] dungeon;
    private Dungeon dungeon;
    private int PlayerHP =0; // playerı can
    private int PlayerDefence =0; // playerı can
    private int PlayerAttack =0; // playerı can
    private Enemy[] enemies;
    private Enemy enemy;
    private NPC npcs;
    private NPC npc;
    private NPC secondNPC;
    private Inventory inventory;
    private Enemy secondEnemy;
    private Dungeon dungeon2;
    private Player player;
    private Item item;
    private Armor armor;
    private Weapon weapon;
    private Potion potion;
    private Market market = new Market(200,this); // boyutu
    private boolean infoIsCome = true;
    private boolean isSettingOpened = false;
    private boolean isMousePressedActive = false;
    private boolean isNPCEncountered = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private int x = 1, y = 1;  // Başlangıç konumu // player coordinate
    //--------------Forest---------------
    private Forest forest;
    private LinkedStack mazeStack = new LinkedStack();
    private LinkedStack enemyStack = new LinkedStack();
    private LinkedStack npcStack = new LinkedStack();
    private LinkedStack vertexStack = new LinkedStack();
    private LinkedStack biomesStack = new LinkedStack();
    private LinkedStack hiddenDungeonStack = new LinkedStack();
    private LinkedStack nextMazeStack = new LinkedStack();
    private LinkedStack nextEnemyStack = new LinkedStack();
    private LinkedStack nextNpcStack = new LinkedStack();
    private LinkedStack nextVertexStack = new LinkedStack();
    private LinkedStack nextBiomesStack = new LinkedStack();
    private LinkedStack nextHiddenDungeonStack = new LinkedStack();
    private int stacksSize = 0;
    private boolean randomStackActive = false;
    public static boolean forestIsRigthPressed = false;
    public static boolean forestIsLeftPressed = false;
    public static boolean forestIsUpPressed = false;
    public static boolean forestIsDownPressed = false;
    public static boolean hiddenDungeonIsRightPressed = false;
    public static boolean hiddenDungeonIsLeftPressed = false;
    public static boolean hiddenDungeonIsUpPressed = false;
    public static boolean hiddenDungeonIsDownPressed = false;
    private Biomes biomes;
    private BiomeTypes biomeTypes;
    private boolean isTranformHidden = false;
    GamePlay() {}


    public LinkedStack getHiddenDungeonStack() {
        return hiddenDungeonStack;
    }

    public void setHiddenDungeonStack(LinkedStack hiddenDungeonStack) {
        this.hiddenDungeonStack = hiddenDungeonStack;
    }

    public LinkedStack getNextHiddenDungeonStack() {
        return nextHiddenDungeonStack;
    }

    public void setNextHiddenDungeonStack(LinkedStack nextHiddenDungeonStack) {
        this.nextHiddenDungeonStack = nextHiddenDungeonStack;
    }

    public LinkedStack getNextBiomesStack() {
        return nextBiomesStack;
    }

    public void setNextBiomesStack(LinkedStack nextBiomesStack) {
        this.nextBiomesStack = nextBiomesStack;
    }

    public LinkedStack getBiomesStack() {
        return biomesStack;
    }

    public void setBiomesStack(LinkedStack biomesStack) {
        this.biomesStack = biomesStack;
    }


    public Biomes getBiomes() {
        return biomes;
    }

    public void setBiomes(Biomes biomes) {
        this.biomes = biomes;
    }

    public BiomeTypes getBiomeTypes() {
        return biomeTypes;
    }

    public void setBiomeTypes(BiomeTypes biomeTypes) {
        this.biomeTypes = biomeTypes;
    }
    public LinkedStack getMazeStack() {
        return mazeStack;
    }

    public void setMazeStack(LinkedStack mazeStack) {
        this.mazeStack = mazeStack;
    }

    public LinkedStack getEnemyStack() {
        return enemyStack;
    }

    public void setEnemyStack(LinkedStack enemyStack) {
        this.enemyStack = enemyStack;
    }

    public LinkedStack getNpcStack() {
        return npcStack;
    }

    public void setNpcStack(LinkedStack npcStack) {
        this.npcStack = npcStack;
    }

    public LinkedStack getVertexStack() {
        return vertexStack;
    }

    public void setVertexStack(LinkedStack vertexStack) {
        this.vertexStack = vertexStack;
    }

    public LinkedStack getNextMazeStack() {
        return nextMazeStack;
    }

    public void setNextMazeStack(LinkedStack nextMazeStack) {
        this.nextMazeStack = nextMazeStack;
    }

    public LinkedStack getNextEnemyStack() {
        return nextEnemyStack;
    }

    public void setNextEnemyStack(LinkedStack nextEnemyStack) {
        this.nextEnemyStack = nextEnemyStack;
    }

    public LinkedStack getNextNpcStack() {
        return nextNpcStack;
    }

    public void setNextNpcStack(LinkedStack nextNpcStack) {
        this.nextNpcStack = nextNpcStack;
    }

    public LinkedStack getNextVertexStack() {
        return nextVertexStack;
    }

    public void setNextVertexStack(LinkedStack nextVertexStack) {
        this.nextVertexStack = nextVertexStack;
    }

    public NPC getNpcs() {
        return npcs;
    }

    public void setNpcs(NPC npcs) {
        this.npcs = npcs;
    }


    public Vertex getNPCCoordinateVertex() {
        return NPCCoordinateVertex;
    }

    public void setNPCCoordinateVertex(Vertex NPCCoordinateVertex) {
        this.NPCCoordinateVertex = NPCCoordinateVertex;
    }


    public void setEnemyCoordinateVertex(Vertex enemyCoordinateVertex) {
        this.enemyCoordinateVertex = enemyCoordinateVertex;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }
    public Item getCurrentBoots() {
        return currentBoots;
    }

    public void setCurrentBoots(Item currentBoots) {
        this.currentBoots = currentBoots;
    }

    public Item getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Item currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Item getCurrentChestPLate() {
        return currentChestPLate;
    }

    public void setCurrentChestPLate(Item currentChestPLate) {
        this.currentChestPLate = currentChestPLate;
    }

    public Item getCurrentHelmet() {
        return currentHelmet;
    }

    public void setCurrentHelmet(Item currentHelmet) {
        this.currentHelmet = currentHelmet;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getFinalVertex() {
        return finalVertex;
    }

    public void setFinalVertex(Vertex finalVertex) {
        this.finalVertex = finalVertex;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
    private int clamp(int x, int min, int max) { // defans atacktan büyükse
        return Math.min(Math.max(x, min), max);
    }


    public void startScreen() throws IOException {
        isMousePressedActive = true;
        while (true) {
            Game.Clear();
            System.out.println("Welcome to the Dungeon");
            System.out.println("----------------");
            System.out.println("--- New Game ---");
            System.out.println("----------------");
            System.out.println("--- Load     ---");
            System.out.println("----------------");
            System.out.println("--- Exit     ---");
            System.out.println("----------------");
            if(mouseY == 4 && mouseX > 3 && mouseX < 8) {
                //Load
                setting.loadFile();
                break;
            } else if (mouseY == 6 && mouseX > 3 && mouseX < 8) {
                //Exit
                gameFinished = true;
                isSettingOpened = false;
                isMousePressedActive = false;
                mouseX = 0;
                mouseY = 0;
                break;
            } else if (mouseY == 2 && mouseX > 3 && mouseX < 12) {
                isMousePressedActive = false;
                Game.Clear();
                System.out.println("Welcome to the Dungeon Warrior ");
                System.out.print("Please enter your name : ");
                String name = scanner.nextLine();
                player.setName(name);
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isMousePressedActive = false;

    }
    public void Start() throws Exception//Oyun buarada başlıyacak
    {
        Game game = new Game();

        //Oyun başladı önce oyuncu oluşur

        String name = "";

        player = new Player(name,0,0,2250,200,200,0,22500,150,3,200);
        PlayerHP = player.getHp();
        PlayerDefence = player.getDefence();
        PlayerAttack = player.getAttack();
        Product[] marketProducts = market.fillMarketProducts();

        dungeon = readText(); // we take dungeon here main dungeon
        dungeonStack.push(dungeon);
        dungeon = ((Dungeon) dungeonStack.peek());
        for (int i = 0; i < dungeon.getDungeonX(); i++) {
            for (int j = 0; j < dungeon.getDungeonY(); j++) {
                if (dungeon.getDungeonMatrix()[i][j] == '●') {
                    startVertex = new Vertex(i, j); // başlangıç konum aldık
                } else if (dungeon.getDungeonMatrix()[i][j] == 'x') {
                    finalVertex = new Vertex(i, j);
                }
            }
        }
        dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = 'P';
        biomes = Biomes.COLD;
        biomeTypes = biomes.next();
        biomes = biomeTypes.getBiome();
        dungeon.setBiome(biomes);
        dungeon.setBiomeType(biomeTypes);
        //dungeon.printDungeon(game);
        dungeon = dungeon.randomMoney(dungeon); // burda mevcut zindanı parayla döndür
        enemy = new Enemy(0,0,0,"",0,0,"",false,"");
        enemies = enemy.randomEnemy(dungeon);

        //---------NPC------------
        npcs = new NPC(false,"","","","","",null,this);
        npcs.addLumberJacks(dungeon);
        dungeon.printDungeon(game);
        dungeon.addHiddenDoor();
        setting = new Setting(this,marketProducts);
        game.Clear();
        movement(); //hareket fonksiyonu
        mouse();
        startScreen();
        isSettingOpened = false;
        inventoryOpened = false;

        while (true) { // new game loop
            if(gameFinished){
                break;
            }
            if (infoIsCome) {
                //--------------------enemy-----------------
                infoIsCome = false;
                game.Clear();
                dungeon.printDungeon(game); // print dungeon
                game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY() + 1, 0);
                printStatics(game, player); // parayı yazdır
                while (!takesInput) {
                    game.Clear();
                    System.out.print("You encountered ");
                    for (int i = 0; i < enemies.length; i++) {
                        if(x == enemies[i].getX() && y == enemies[i].getY()){
                            enemy = enemies[i];
                        }
                    }
                    game.cn.setTextAttributes(new TextAttributes(Color.cyan));
                    System.out.print(enemy.getType()); //türü
                    game.cn.setTextAttributes(new TextAttributes(Color.white));
                    System.out.println(".");
                    game.cn.setTextAttributes(new TextAttributes(Color.pink));
                    System.out.println(enemy.getComment());
                    game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
                    scanner.nextLine();
                    game.Clear();
                    printEnemy(enemy, game);
                    printPlayer(game);
                    System.out.println();
                    System.out.print("Do you want to fight? (y/n) : ");
                    String choice = scanner.nextLine();
                    choice = choice.toLowerCase();
                    if (choice.equals("n")) {
                        takesInput = true;
                        infoIsCome = true;
                    } else if (choice.equals("y")) {
                        isWarBegin = true;
                        System.out.print("Do you want to use inventory? (y/n) : "); // envanteri kullanmak istiyor mu?
                        choice = scanner.nextLine();
                        choice = choice.toLowerCase();
                        if (choice.equals("y")) {
                            inventoryOpened = true; // envanter açıldı
                            inventory(game);
                        } else if (choice.equals("n")) {

                        }
                        enemyFirstLife = enemy.getHealth();
                        playerFirstLife = player.getHp();
                        game.Clear();
                        System.out.println("War begin !!! ");

                        war(enemy, game);
                        player.setHp(PlayerHP);
                        player.setDefence(player.getDefence() - defenceElixir);
                        defenceElixir = 0;
                        player.setAttack(player.getAttack() - attackElixir);
                        attackElixir = 0;

                        takesInput = true;
                        infoIsCome = true;
                    }
                }

            }
            //---------------HİDDEN DUNGEON--------
            if (isTranformHidden) {
                hiddenDugeon(game);
                infoIsCome = true;
            }
            //-------------------NPC-------------
            if (isNPCEncountered) {
                game.Clear();
                NPCEncountered(game);
                infoIsCome = true;
                isNPCEncountered = false;
            }
            //-------------------Exit first dungeon-------------
            if (quit) {
                //---------------create random dungeon----------
                if (randomStackActive) {
                    Dungeon dungeon1 = new Dungeon(35, 37);
                    dungeon1.createRandomDungeon();
                    createRandomDungeon(dungeon1);
                    startVertex = vertex;
                    x = startVertex.getVertexX();
                    y = startVertex.getVertexY();
                    finalVertex = vertexF;
                    dungeon = dungeon1;
                    biomes = Biomes.COLD;
                    biomeTypes = biomes.next();
                    biomes = biomeTypes.getBiome();
                    dungeon.setBiome(biomes);
                    dungeon.setBiomeType(biomeTypes);
                    dungeon = dungeon.randomMoney(dungeon); // burda mevcut zindanı parayla döndür
                    dungeon.addHiddenDoor();
                    enemy = new Enemy(0,0,0,"",0,0,"",false,"");
                    enemies = enemy.randomEnemy(dungeon);
                    npcs = new NPC(false, "", "", "", "", "", null, this);
                    npcs.addLumberJacks(dungeon);
                    randomStackActive = false;
                }
                quit = false;
                infoIsCome = true;
                game.Clear();
                dungeon.printDungeon(game);
                while (!takesInput) {
                    game.Clear();
                    System.out.print("You encountered ");
                    for (int i = 0; i < enemies.length; i++) {
                        if(x == enemies[i].getX() && y == enemies[i].getY()){
                            enemy = enemies[i];
                        }
                    }
                    game.cn.setTextAttributes(new TextAttributes(Color.cyan));
                    System.out.print(enemy.getType()); //türü
                    game.cn.setTextAttributes(new TextAttributes(Color.white));
                    System.out.println(".");
                    printEnemy(enemy, game);
                    printPlayer(game);
                    System.out.print("Do you want to fight? (y/n) : ");
                    String choice = scanner.nextLine();
                    choice = choice.toLowerCase();
                    if (choice.equals("n")) {
                        takesInput = true;
                        infoIsCome = true;
                    } else if (choice.equals("y")) {
                        isWarBegin = true;
                        System.out.print("Do you want to use inventory? (y/n) : "); // envanteri kullanmak istiyor mu?
                        choice = scanner.nextLine();
                        choice = choice.toLowerCase();
                        if (choice.equals("y")) {
                            inventoryOpened = true; // envanter açıldı
                            inventory(game);
                        } else if (choice.equals("n")) {

                        }
                        enemyFirstLife = enemy.getHealth();
                        playerFirstLife = player.getHp();
                        game.Clear();
                        System.out.println("War begin !!! ");
                        war(enemy, game);
                        player.setHp(PlayerHP);
                        player.setDefence(player.getDefence() - defenceElixir);
                        defenceElixir = 0;
                        player.setAttack(player.getAttack() - attackElixir);
                        attackElixir = 0;
                        takesInput = true;
                        infoIsCome = true; // savaş bitti
                    }
                }
            }
            inventory(game);
            if (isMarketOpened){
                market.marketOpened(scanner, game);
            }
            if (isSettingOpened){
                while (true) {
                    game.Clear();
                    System.out.println("----------");
                    System.out.println("- Resume -");
                    System.out.println("----------");
                    System.out.println("- Save   -");
                    System.out.println("----------");
                    System.out.println("- Load   -");
                    System.out.println("----------");
                    System.out.println("- Exit   -");
                    System.out.println("----------");

                    if(mouseY == 1 && mouseX < 8 && mouseX > 2){
                        //resume
                        isSettingOpened = false;
                        infoIsCome = true;
                        isMousePressedActive = false;
                        mouseX = 0;
                        mouseY = 0;
                        break;
                    } else if (mouseY == 7 && mouseX > 2 && mouseX < 6) {
                        //exit
                        gameFinished = true;
                        isSettingOpened = false;
                        isMousePressedActive = false;
                        mouseX = 0;
                        mouseY = 0;
                        break;
                    } else if (mouseY == 3 && mouseX > 2 && mouseX < 6) {
                        //Save
                        //save player ınfo
                        //save dungeon
                        //save ınventory
                        //save market products
                        setting.saveFile(dungeon);
                        isSettingOpened = false;
                        infoIsCome = true;
                        isMousePressedActive = false;
                        mouseX = 0;
                        mouseY = 0;
                        break;
                    } else if (mouseY == 5 && mouseX > 2 && mouseX < 6) {
                        //Load
                        //load all save datas
                        setting.loadFile();
                        isSettingOpened = false;
                        infoIsCome = true;
                        isMousePressedActive = false;
                        mouseX = 0;
                        mouseY = 0;
                        break;
                    }
                    Thread.sleep(100);
                    //Save
                    //Load
                    //Exit
                    //Resume
                    //String choice = scanner.nextLine();
                }

            }
            Thread.sleep(10); //Bura eklendi
        }
        System.out.println("game finished");
        System.exit(0);

        //player moves then we write '●' instead of 'P'
    }
    public void NPCEncountered(Game game) {
        takesInput = false;
        game.cn.setTextAttributes(new TextAttributes(Color.orange));
        System.out.print("Name : ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(npc.getName());
        game.cn.setTextAttributes(new TextAttributes(Color.orange));
        System.out.print("Job : ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(npc.getJob());
        game.cn.setTextAttributes(new TextAttributes(Color.orange));
        System.out.print("Comment : ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(npc.getMessage());
        String gender = npc.getGender();
        int x = game.cn.getTextWindow().getCursorX();
        int y = game.cn.getTextWindow().getCursorY();
        while (true) {
            if (gender.equals("girl")) {
                gender = "her";
            } else {
                gender = "him";
            }
            System.out.println("Do you want to talk "+gender+"? (y/n) : ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                game.Clear();
                talkNpc(game);
                break;
            } else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
                break;
            }
            clearPart(x,y,game,3,70);
        }
        takesInput = true;
    }
    public void talkNpc(Game game) {
        String commentFirst = "";
        String commentSecond = "";
        if (npc.getJob().equals("Lumberjack")) {
            commentFirst = "Hello "+ player.getName()+" , I need some help. Can you help me collect some wood ? (y/n)";
            commentSecond = "Thank you "+ player.getName()+".And be careful, there might be some bugs ahead.";
            forest = (Forest) npc.getQuest();
        }
        int x = game.cn.getTextWindow().getCursorX();
        int y = game.cn.getTextWindow().getCursorY();
        while (true) {
            System.out.println(commentFirst);
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                game.Clear();
                System.out.println(commentSecond);
                // burada oyunu çağıracağız ve başlayacak
                takesInput = false;
                forestMazeActive = true;
                forest.startGame();
                if (!forest.isGameWin()) {
                    npc.setQuestFinished(false);
                } else {
                    System.out.println("You have finished the game");
                    scanner.nextLine();
                    npc.setQuestFinished(true);
                }
                forestMazeActive = false;
                takesInput = true;
                break;
            } else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
                break;
            }
            clearPart(x,y,game,3,70);
        }
    }
    public void clearPart(int x, int y,Game game,int rowSizeToDelete,int columnSizeToDelete)
    {
        game.cn.getTextWindow().setCursorPosition(x, y);
        for (int i = 0; i < rowSizeToDelete; i++)
        {
            for (int j = 0; j < columnSizeToDelete; j++)
            {
                System.out.print(" ");
            }
            System.out.println();
        }
        game.cn.getTextWindow().setCursorPosition(x, y);
    }
    public void inventory(Game game)
    {
        while (inventoryOpened) // buardayım
        {
            game.Clear();
            System.out.println("inventory opened");
            game.cn.getTextWindow().setCursorPosition(0, 1);
            if (player.getInventory().size() == 0) {
                game.cn.setTextAttributes(new TextAttributes(Color.yellow));
                System.out.println("You have no item in your inventory.");
                game.cn.setTextAttributes(new TextAttributes(Color.white));
            } else {
                for (int i = 0; i < player.getInventory().size(); i++) {
                    Object data  = null;
                    if(player.getInventory().getItems()[i] != null) {
                      data = player.getInventory().getItems()[i].getData();
                        int value = 0;
                        String type = "";
                        if (data instanceof Armor) {
                            value = ((Armor) data).getDefense();
                            type = "defence";
                        } else if (data instanceof Weapon) {
                            value = ((Weapon) data).getDamage();
                            type = "damage";
                        } else if (data instanceof Potion) {
                            value = ((Potion) data).getEffect();
                            type = ((Potion) data).getName();
                        }
                      System.out.print(player.getInventory().getItems()[i].getItemName() + " " + type + " " + value + " Quantity " + player.getInventory().getItems()[i].getQuantity());
                    scanner.nextLine();
                    }
                }
            }
            int x =  game.cn.getTextWindow().getCursorX();
            int y =  game.cn.getTextWindow().getCursorY();
            printArmor(game,x,y);
            //---------------Armor wear--------------------
            while (true) {
                System.out.println("Do you want to wear armor ? \nyes(y) || no(n)  ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                    String[] datas = null;
                    while (true) {
                        printArmor(game,x,y);
                        System.out.println("");
                        System.out.println("");
                        System.out.println("You can use when you search the initial letters of the words of armor.");
                        System.out.print("Which armor piece do you want to wear? (Write with effect): \n"); // hangi item giyecek
                        choice = scanner.nextLine();
                        datas = choice.split(" ");
                        if (datas.length == 2 && datas[1].matches("\\d+"))
                        {
                            // Girdi geçerli
                            break;
                        }
                        clearPart(x,y,game,6,70);
                    }
                    clearPart(x, y, game, 10, 70);
                    //burda giyecek

                    String armorType = "";
                    int id =0;
                    int defence = 250;
                    if(datas[0].equalsIgnoreCase("chestPlate") || datas[0].equalsIgnoreCase("ch") )
                    { // can iksiriyse
                        armorType = "chestPlate"; //3,4,5
                        defence = defence * 2; // ikiye böldük
                        id += 3;
                    } else if (datas[0].equalsIgnoreCase("helmet") || datas[0].equalsIgnoreCase("he") ) {
                        armorType = "helmet"; //6,7,8
                        id += 6;
                    } else if (datas[0].equalsIgnoreCase("boots") || datas[0].equalsIgnoreCase("bo") ) {
                        armorType = "boots"; //0,1,2
                    }
                    if(Integer.parseInt(datas[1]) == defence)
                    {
                        id += 0;
                    }else if(Integer.parseInt(datas[1]) == (defence * 2))
                    {
                        id += 1;
                    }else if(Integer.parseInt(datas[1]) == (defence * 4))
                    {
                        id += 2;
                    } else if (Integer.parseInt(datas[1]) == (defence * 10)) {
                        if(armorType.equalsIgnoreCase("chestPlate"))
                        {
                            id = 37;
                        } else if (armorType.equalsIgnoreCase("helmet")) {
                            id = 36;
                        } else if (armorType.equalsIgnoreCase("boots")) {
                            id = 38;
                        }
                    }
                    if(armorType.equalsIgnoreCase("chestPlate"))
                    {
                        defence = defence / 2;
                    }
                    boolean isItemExist = false;
                    for(int j = 0; j < player.getInventory().size(); j++)
                    {
                        if (player.getInventory().getItems()[j] != null) {
                            Object data = player.getInventory().getItems()[j].getData();
                            if (data instanceof Armor) {
                                if(armorType.equalsIgnoreCase(player.getInventory().getItems()[j].getItemName()) && Integer.parseInt(datas[1]) == ((Armor) data).getDefense())
                                { // isim ve etki eşitse
                                    isItemExist = true; //item var
                                    break;
                                }
                            }
                        }
                    }
                    if (isItemExist) { // item var ve null değil
                        Item item = player.getInventory().searchItem(armorType,id);
                        Object data;
                        data = item.getData();
                        if(data instanceof Armor)
                        {
                            if(armorType.equalsIgnoreCase("helmet"))
                            {
                                if(currentHelmet == null)
                                {
                                    helmetUsed = true; // kask giyildi
                                    helmetDefence = (((Armor) data).getDefense()); // kaskın defansı
                                    PlayerDefence = player.getDefence(); // defansı arttırdık
                                    player.setDefence(PlayerDefence + helmetDefence);
                                    currentHelmet = item;
                                    player.getInventory().useItems(currentHelmet,1);
                                }else
                                {
                                    while (true) {
                                        System.out.print("Do you want to exchange your helmet : "); // hangi item giyecek
                                        choice = scanner.nextLine();
                                        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                            // eskisinin sayısı azalt
                                            helmetUsed = true; // kask giyildi
                                            if(currentHelmet.getQuantity() > 0) {
                                                currentHelmet.setQuantity(currentHelmet.getQuantity() + 1);
                                            }else {
                                                currentHelmet.setQuantity(1);
                                                player.getInventory().addItem(currentHelmet);
                                            }
                                            player.setDefence(player.getDefence() - helmetDefence);
                                            helmetDefence = 0;
                                            currentHelmet = item; // yeni kask
                                            data = item.getData();
                                            helmetDefence = ((Armor) data).getDefense();
                                            PlayerDefence = player.getDefence(); // defansı arttırdık
                                            player.setDefence(PlayerDefence + helmetDefence);
                                            player.getInventory().useItems(currentHelmet,1);
                                            printArmor(game,x,y);
                                            break;
                                        } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                            break;
                                        }
                                        clearPart(x,y,game,10,70);
                                    }
                                }
                            }else if (armorType.equalsIgnoreCase("chestPlate"))
                            {
                                if(currentChestPLate == null)
                                {
                                    chestPlateUsed = true;
                                    chestplateDefence= (((Armor) data).getDefense()); // kaskın defansı
                                    PlayerDefence = player.getDefence();
                                    player.setDefence(PlayerDefence + chestplateDefence);
                                    currentChestPLate = item;
                                    player.getInventory().useItems(currentChestPLate,1);
                                }else
                                {
                                    while (true) {
                                        System.out.print("Do you want to exchange your chestPlate : "); // hangi item giyecek
                                        choice = scanner.nextLine();
                                        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                            // eskisinin sayısı azlat
                                            chestPlateUsed = true;
                                            if (currentChestPLate.getQuantity() > 0) {
                                                currentChestPLate.setQuantity(currentChestPLate.getQuantity() + 1);// kullanırken 1 azalttık çıkaarıken 1 artırcaz eklemeden
                                            } else {
                                                currentChestPLate.setQuantity(1);
                                                player.getInventory().addItem(currentChestPLate);
                                            }
                                            player.setDefence(player.getDefence() - chestplateDefence);
                                            chestplateDefence = 0;
                                            currentChestPLate = item; // yeni chestPLate
                                            data = item.getData();
                                            chestplateDefence = ((Armor) data).getDefense();
                                            PlayerDefence = player.getDefence();
                                            player.setDefence(PlayerDefence + chestplateDefence);
                                            player.getInventory().useItems(currentChestPLate,1);
                                            printArmor(game,x,y);
                                            break;
                                        } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                            break;
                                        }
                                        clearPart(x,y,game,10,70);
                                    }
                                }

                            }else if (armorType.equalsIgnoreCase("boots"))
                            {
                                if(currentBoots == null) // ilk item
                                {
                                    bootsUsed = true;
                                    bootsDefence= (((Armor) data).getDefense()); // kaskın defansı
                                    PlayerDefence = player.getDefence();
                                    player.setDefence(PlayerDefence + bootsDefence);
                                    currentBoots = item;
                                    player.getInventory().useItems(currentBoots,1);
                                    System.out.println("currentBoots");
                                }else
                                {
                                    while (true) { // item varsa
                                        System.out.print("Do you want to exchange your boots : "); // hangi item giyecek
                                        choice = scanner.nextLine();
                                        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
                                            // eskisinin sayısı geri ekle(eskisi al ve ekle)
                                            bootsUsed = true;
                                            if(currentBoots.getQuantity() > 0) {
                                                currentBoots.setQuantity(currentBoots.getQuantity() + 1);
                                            } else {
                                                currentBoots.setQuantity(1);
                                                player.getInventory().addItem(currentBoots); // mevcut olanı envantere al
                                            }
                                            player.setDefence(player.getDefence() - bootsDefence);
                                            bootsDefence = 0;
                                            currentBoots = item; // itemi al
                                            data = item.getData(); // data güncelle
                                            bootsDefence = ((Armor) data).getDefense();  // itemin defansı al
                                            PlayerDefence = player.getDefence(); // oyuncu mevcut defans
                                            player.setDefence(PlayerDefence + bootsDefence);
                                            player.getInventory().useItems(currentBoots,1);
                                            printArmor(game,x,y);
                                            break;
                                        } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                            break;
                                        }
                                        clearPart(x,y,game,10,70);
                                    }
                                }
                            }
                            printArmor(game,x,y);
                        }

                    } else {
                        printArmor(game,x,y);
                        System.out.print("Item not found.");
                        scanner.nextLine();
                    }
                }else if(choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no"))
                {
                    break;
                }
                clearPart(x, y, game, 10, 70);
                printArmor(game,x,y);
            }
            printArmor(game,x,y);
            //---------------Armor take off--------------------
            while (true)
            {
                clearPart(x, y, game, 2, 43);
                System.out.println("Do you want to take of your armor pieces.\nyes(y) || no(n)");
                String choice = scanner.nextLine();
                clearPart(x,y,game,5,70);
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
                {
                    while (true) {
                        while (true) {
                            printArmor(game, x, y);
                            System.out.println("Do you want to take of your armor pieces.\nyes(y) || no(n)");
                            System.out.println();
                            System.out.println("You can use when you search the initial letters of the words of armor.");
                            System.out.print("Which armor piece do you want to take off? (Write only name): \n"); // hangi item giyecek
                            choice = "";
                            choice = scanner.nextLine();
                            if(choice.equalsIgnoreCase("he")||choice.equalsIgnoreCase("helmet"))
                            {
                                choice = "helmet";
                                break;
                            } else if (choice.equalsIgnoreCase("ch")||choice.equalsIgnoreCase("chestPlate")) {
                                choice = "chestplate";
                                break;
                            } else if (choice.equalsIgnoreCase("bo")||choice.equalsIgnoreCase("boots")) {
                                choice = "boots";
                                break;
                            }
                            clearPart(x,y,game,7,70);
                        }

                        if(currentHelmet != null) {
                            if (choice.equalsIgnoreCase(currentHelmet.getItemName())) {
                                if(currentHelmet.getQuantity() > 0) {
                                    currentHelmet.setQuantity(currentHelmet.getQuantity() + 1);
                                    // kask ekle envantere
                                }else{
                                    currentHelmet.setQuantity(1);
                                    player.getInventory().addItem(currentHelmet);
                                }
                                player.setDefence(player.getDefence() - helmetDefence);
                                helmetDefence = 0;
                                currentHelmet = null;
                                break;
                            }
                        }
                        if (currentChestPLate != null) {
                            if (choice.equalsIgnoreCase(currentChestPLate.getItemName())) {
                                if(currentChestPLate.getQuantity() > 0){
                                    // birden çok eleman varken
                                    currentChestPLate.setQuantity(currentChestPLate.getQuantity() + 1);
                                }else{
                                    // 1 eleman varken
                                    currentChestPLate.setQuantity(1);
                                    player.getInventory().addItem(currentChestPLate);
                                }
                                player.setDefence(player.getDefence() - chestplateDefence);
                                chestplateDefence = 0;
                                currentChestPLate = null;
                                break;

                            }
                        }
                        if (currentBoots != null)
                        {
                            if (choice.equalsIgnoreCase(currentBoots.getItemName())) {
                                if(currentBoots.getQuantity() > 0){
                                    // birden çok eleman varken
                                    currentBoots.setQuantity(currentBoots.getQuantity() + 1);
                                }else{
                                    // 1 eleman varken
                                    currentBoots.setQuantity(1);
                                    player.getInventory().addItem(currentBoots);
                                }
                                player.setDefence(player.getDefence() - bootsDefence);
                                bootsDefence = 0;
                                currentBoots = null;
                                break;
                            }
                        }
                        if((choice.equalsIgnoreCase("boots") && currentBoots == null) || (choice.equalsIgnoreCase("helmet") && currentHelmet == null) || (choice.equalsIgnoreCase("chestPlate") && currentChestPLate == null)) {
                            System.out.println("item not found.");
                            scanner.nextLine();
                            break;
                        }
                        clearPart(x, y, game, 10, 70);
                        printArmor(game,x,y);
                    }
                } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                    break;
                }
                clearPart(x, y, game, 10, 70);
                printArmor(game,x,y);
            }
            printArmor(game,x,y);
            //---------------Sword equip--------------------
            while (true)
            {
                clearPart(x, y, game, 2, 43);
                printArmor(game, x, y);
                System.out.println("Do you want to equip the weapon? :\nyes(y) || no(n)");
                String choice = scanner.nextLine();
                clearPart(x,y,game,5,70);
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
                {
                    String[] datas = null;
                    while (true) {
                        printArmor(game,x,y);
                        System.out.println("Do you want to equip the weapon? :\nyes(y) || no(n)");
                        System.out.println();
                        System.out.println("You can use when you search the initial letters of the words of weapon.");
                        System.out.print("Which weapon do you want to equip? (Write with effect): \n"); // hangi item giyecek
                        choice = scanner.nextLine();
                        datas = choice.split(" ");
                        if (datas.length == 2 && datas[1].matches("\\d+"))
                        {
                            // Girdi geçerli
                            break;
                        }
                        clearPart(x,y,game,7,70);
                    }
                    clearPart(x,y,game,7,70);
                    //burda giyecek
                    String weaponType = "";
                    int id =0;
                    int attack = 200;
                    if(datas[0].equalsIgnoreCase("sword") || datas[0].equalsIgnoreCase("sw") )
                    { // can iksiriyse
                        weaponType = "sword"; //9,10,11
                        id += 9;
                    } else if (datas[0].equalsIgnoreCase("bow")) {
                        weaponType = "bow"; //12,13,14
                        id += 12;
                        attack += 50;
                    } else if (datas[0].equalsIgnoreCase("staff") || datas[0].equalsIgnoreCase("st") ) {
                        weaponType = "staff"; //15,16,17
                        id += 15;
                        attack += 25;
                    }else if (datas[0].equalsIgnoreCase("axe")) {
                        weaponType = "axe"; //18,19,20
                        id += 18;
                        attack += 75;
                    }else if (datas[0].equalsIgnoreCase("spear") || datas[0].equalsIgnoreCase("sp") ) {
                        weaponType = "spear"; //21,22,23
                        id += 21;
                        attack -= 25;
                    }
                    if(Integer.parseInt(datas[1]) == attack)
                    {
                        id += 0;
                    }else if(Integer.parseInt(datas[1]) == (attack * 2))
                    {
                        id += 1;
                    }else if(Integer.parseInt(datas[1]) == (attack * 4))
                    {
                        id += 2;
                    } else if (Integer.parseInt(datas[1]) == (attack * 10)) {
                        if(weaponType.equalsIgnoreCase("sword"))
                        {
                            id = 39;
                        } else if (weaponType.equalsIgnoreCase("bow")) {
                            id = 40;
                        } else if (weaponType.equalsIgnoreCase("staff")) {
                            id = 41;
                        } else if (weaponType.equalsIgnoreCase("axe")) {
                            id = 42;
                        } else if (weaponType.equalsIgnoreCase("spear")) {
                            id = 43;
                        }
                    }

                    boolean isItemExist = false;
                    for(int j = 0; j < player.getInventory().size(); j++)
                    {
                        if (player.getInventory().getItems()[j] != null) {
                            Object data = player.getInventory().getItems()[j].getData();
                            if (data instanceof Weapon) {
                                if(weaponType.equalsIgnoreCase(player.getInventory().getItems()[j].getItemName()) && Integer.parseInt(datas[1]) == ((Weapon) data).getDamage())
                                { // isim ve etki eşitse
                                    isItemExist = true; //item var
                                    break;
                                }
                            }
                        }
                    }
                    if(isItemExist)
                    {
                        Item item = player.getInventory().searchItem(weaponType,id); // bu weapon
                        Object data;
                        data = item.getData();
                        if(data instanceof Weapon)
                        {
                            if(currentWeapon == null)
                            {
                                // silah yoksa
                                weaponUsed = true;
                                weaponAttack = ((Weapon) data).getDamage(); // silahın hasarı
                                PlayerAttack = player.getAttack();
                                player.setAttack(weaponAttack + PlayerAttack);
                                currentWeapon = item; // mevcut silah
                                player.getInventory().useItems(currentWeapon,1);
                            }else
                            {
                                //silah varsa
                                while (true)
                                {
                                    System.out.print("Do you want to exchange your weapon : "); // hangi item giyecek
                                    choice = scanner.nextLine();
                                    if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
                                    {
                                        weaponUsed = true;
                                        if(currentWeapon.getQuantity() > 0) {
                                            currentWeapon.setQuantity(currentWeapon.getQuantity() + 1); // silahı envanter ekle

                                        }else {
                                            currentWeapon.setQuantity(1);
                                            player.getInventory().addItem(currentWeapon);
                                        }
                                        player.setAttack(player.getAttack() - weaponAttack);
                                        weaponAttack = 0;
                                        currentWeapon = item;
                                        data = item.getData();
                                        if(data instanceof  Weapon)
                                        {
                                            weaponAttack = ((Weapon) data).getDamage();
                                            PlayerAttack = player.getAttack(); // mevcut attack
                                            player.setAttack(weaponAttack + PlayerAttack);
                                            player.getInventory().useItems(currentWeapon,1);
                                        }
                                        printArmor(game, x, y);
                                    } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                                        break;
                                    }
                                    clearPart(x, y, game, 10, 70);
                                }
                            }
                            printArmor(game,x,y);
                        }
                    } else {
                        System.out.println("item not found.");
                        break;
                    }
                    clearPart(x, y, game, 10, 70);
                    printArmor(game,x,y);
                } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                    // kılıç giymeyecekse
                    break;
                }
            }
            //---------------Sword unEquip--------------------
            while (true)
            {
                printArmor(game,x,y);
                clearPart(x, y, game, 2, 43);
                System.out.println("Do you want to take off your weapon.\nyes(y) || no(n)");
                String choice = scanner.nextLine();
                //printArmor(game,x,y);
                clearPart(x,y,game,5,70);
                if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))
                {
                    while (true) {
                        while (true) {
                            printArmor(game,x,y); // bu fonksiyon konumla oynuyo
                            System.out.println("Do you want to take off your weapon.\nyes(y) || no(n)");
                            System.out.println();
                            System.out.println("You can use when you search the initial letters of the words of weapon");
                            System.out.print("Which weapon do you want to take off? (Write only name): \n"); // hangi item giyecek
                            choice = scanner.nextLine();
                            if (choice.equalsIgnoreCase("sw") || choice.equalsIgnoreCase("sword")) {
                                choice = "sword";
                                break;
                            } else if (choice.equalsIgnoreCase("sp")|| choice.equalsIgnoreCase("spear")) {
                                choice = "spear";
                                break;
                            } else if (choice.equalsIgnoreCase("st")|| choice.equalsIgnoreCase("staff")) {
                                choice = "staff";
                                break;
                            } else if (choice.equalsIgnoreCase("axe")) {
                                choice = "axe";
                                break;
                            } else if (choice.equalsIgnoreCase("bow")) {
                                choice = "bow";
                                break;
                            }
                            clearPart(x,y,game,7,70);
                        }
                        if(currentWeapon != null) {
                            if (choice.equalsIgnoreCase(currentWeapon.getItemName())) {
                                if(currentWeapon.getQuantity() > 0) {
                                    //silah ekle envantere
                                    currentWeapon.setQuantity(currentWeapon.getQuantity() + 1); // envanter al
                                }else{
                                    currentWeapon.setQuantity(1);
                                    player.getInventory().addItem(currentWeapon);
                                }
                                player.setAttack(player.getAttack() - weaponAttack);
                                weaponAttack = 0;
                                currentWeapon = null;
                                break;
                            }
                        }
                        else {
                            System.out.println("item not found.");
                            scanner.nextLine();
                            break;
                        }
                        clearPart(x, y, game, 10, 70);
                        printArmor(game,x,y);
                    }
                    clearPart(x, y, game, 10, 70);
                } else if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
                    break;
                }
                clearPart(x, y, game, 10, 70);
                printArmor(game,x,y);
            }
            printArmor(game,x,y);
            //-----------------WAR BEGİN----------------
            if(isWarBegin) {
                 x =  game.cn.getTextWindow().getCursorX();
                 y =  game.cn.getTextWindow().getCursorY();
                boolean potionIsUsed = false;
                if (player.getInventory().size() != 0) {
                    while (true) {
                        System.out.println();
                        System.out.print("Do you want to use item? (y/n) : ");
                        String choice = scanner.nextLine();
                        choice = choice.toLowerCase();
                        clearPart(x,y,game,5,70);
                        if (choice.equals("y")) {
                            //booleanElixir true
                            //search item and decreaded 1 quantity
                            String[] datas = null;
                            while (true) {
                                printArmor(game,x,y);
                                System.out.println("You can use when you search the initial letters of the words of elixir");
                                System.out.print("Which item do you want to use? (Write with effect): ");
                                choice = scanner.nextLine();
                                datas = choice.split(" ");
                                if (datas.length == 2 && datas[1].matches("\\d+"))
                                {
                                    // Girdi geçerli
                                    break;
                                }
                                clearPart(x,y,game,5,70);
                            }
                            clearPart(x,y,game,5,70);
                            String elixirType = "";
                            int id = 0;
                            if(datas[0].equalsIgnoreCase("healthPotion") || datas[0].equalsIgnoreCase("hp") )
                            { // can iksiriyse
                                elixirType = "healthPotion";
                            } else if (datas[0].equalsIgnoreCase("defencePotion") || datas[0].equalsIgnoreCase("dp") ) {
                                elixirType = "defencePotion";
                                id += 3;
                            } else if (datas[0].equalsIgnoreCase("attackPotion") || datas[0].equalsIgnoreCase("ap") ) {
                                elixirType = "attackPotion";
                                id += 6;
                            }
                            int amount = 0;
                            while (true) {
                                printArmor(game,x,y);
                                System.out.print("How many potion do you want to use ? ");
                                String input = scanner.nextLine(); //bizim istediğimiz miktar
                                if(input.matches("\\d+")) // eğer sayıysa
                                {
                                    amount = Integer.parseInt(input);
                                    break;
                                }else {
                                    System.out.println("Geçersiz giriş! Lütfen geçerli bir sayı girin.");
                                }
                                clearPart(x,y,game,5,70);
                            }
                            clearPart(x,y,game,5,70);
                            if(Integer.parseInt(datas[1]) < 200)
                            {
                                id += 24;
                            }else if(Integer.parseInt(datas[1]) < 400 && Integer.parseInt(datas[1]) >= 200)
                            {
                                id += 25;
                            }else if(Integer.parseInt(datas[1]) < 800 && Integer.parseInt(datas[1]) >= 400)
                            {
                                id += 26;
                            }else if(Integer.parseInt(datas[1]) <= 1500 && Integer.parseInt(datas[1]) >= 1000)
                            {
                                if(elixirType.equalsIgnoreCase("healthPotion"))
                                {
                                    id = 33;
                                } else if (elixirType.equalsIgnoreCase("defencePotion")) {
                                    id = 34;
                                }else if (elixirType.equalsIgnoreCase("attackPotion"))
                                {
                                    id = 35;
                                }
                            }
                            boolean isItemExist = false;
                            for(int j = 0; j < player.getInventory().size(); j++)
                            {
                                if (player.getInventory().getItems()[j] != null) {
                                    Object data = player.getInventory().getItems()[j].getData();
                                    if (data instanceof Potion) {
                                        if(elixirType.equalsIgnoreCase(player.getInventory().getItems()[j].getItemName()) && Integer.parseInt(datas[1]) == ((Potion) data).getEffect())
                                        { // isim ve etki eşitse
                                            isItemExist = true; //item var
                                        }
                                    }
                                }
                            }
                            if (isItemExist) { // ite var ve null değil
                                Item item = player.getInventory().searchItem(elixirType,id);
                                // burda aranılan item bulunacak
                                Object data;
                                data = item.getData();
                                if(data instanceof Potion) {
                                    int quantity = item.getQuantity();
                                    if (quantity < amount) {
                                        clearPart(x,y,game,30,70);
                                        x =  game.cn.getTextWindow().getCursorX();
                                        y =  game.cn.getTextWindow().getCursorY();
                                        while (true) {
                                            System.out.print("You don't have enough potion.\nYou have " + quantity + " potions.");
                                            System.out.print("Do you want to use this item ?\nyes(y) or no(n) : ");
                                            choice = scanner.nextLine();
                                            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                                                clearPart(x,y,game,30,70);
                                                x =  game.cn.getTextWindow().getCursorX();
                                                y =  game.cn.getTextWindow().getCursorY();
                                                while (true) {
                                                    System.out.print("How many potion do you want to use ? ");
                                                    amount = 0;
                                                    while (true) {
                                                        printArmor(game,x,y);
                                                        System.out.print("How many potion do you want to use ? ");
                                                        String input = scanner.nextLine(); //bizim istediğimiz miktar
                                                        if(input.matches("\\d+")) // eğer sayıysa
                                                        {
                                                            amount = Integer.parseInt(input);
                                                            break;
                                                        }else {
                                                            System.out.println("Geçersiz giriş! Lütfen geçerli bir sayı girin.");
                                                        }
                                                        clearPart(x,y,game,5,70);
                                                    }
                                                    clearPart(x,y,game,5,70);
                                                    if (quantity < amount) { // yetersiz iksir
                                                        System.out.println("You don't have enough potion!!! ");
                                                        System.out.print("Do you want to use this item ? yes(y) or no(n) : ");
                                                        choice = game.cn.readLine();
                                                        if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no"))
                                                        {
                                                            break;
                                                        }
                                                    }else
                                                    {
                                                        player.getInventory().useItems(item,amount);
                                                        //yeterli iksir var ve kullanıcak
                                                        data = item.getData();
                                                        if(data instanceof Potion)
                                                        {
                                                            if(elixirType.equalsIgnoreCase("healthPotion") || elixirType.equalsIgnoreCase("hp"))
                                                            {
                                                                PlayerHP = player.getHp();
                                                                player.setHp(player.getHp()+ (((Potion) data).getEffect() * amount));
                                                            }else if (elixirType.equalsIgnoreCase("defencePotion")) {
                                                                defenceElixir = (((Potion) data).getEffect() * amount); // ekledik
                                                                PlayerDefence = player.getDefence();
                                                                player.setDefence(player.getDefence() + defenceElixir);
                                                            } else if (elixirType.equalsIgnoreCase("attackPotion")) {
                                                                attackElixir = (((Potion) data).getEffect() * amount);
                                                                PlayerAttack = player.getAttack();
                                                                player.setAttack(player.getAttack() +attackElixir);
                                                            }
                                                        }
                                                        potionIsUsed = true;
                                                        break;
                                                    }
                                                    clearPart(x,y,game,30,70);
                                                }
                                                if(potionIsUsed)
                                                {
                                                    break;
                                                }
                                            } else if (choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("no")) {
                                                break;
                                            }
                                            clearPart(x,y,game,30,70);
                                        }
                                    }else
                                    {
                                        // buarada yeterince var ve kullanıcak
                                        player.getInventory().useItems(item,amount);
                                        //yeterli iksir var ve kullanıcak
                                        data = item.getData();
                                        if(data instanceof Potion)
                                        {
                                            if(elixirType.equalsIgnoreCase("healthPotion"))
                                            {
                                                PlayerHP = player.getHp();
                                                player.setHp(player.getHp()+ (((Potion) data).getEffect() * amount));
                                            } else if (elixirType.equalsIgnoreCase("defencePotion")) {
                                                defenceElixir = (((Potion) data).getEffect() * amount); // ekledik
                                                PlayerDefence = player.getDefence();
                                                player.setDefence(player.getDefence() + defenceElixir);
                                            } else if (elixirType.equalsIgnoreCase("attackPotion")) {
                                                attackElixir = (((Potion) data).getEffect() * amount);
                                                PlayerAttack = player.getAttack();
                                                player.setAttack(player.getAttack() +attackElixir);
                                            }
                                        }
                                        break;
                                    }
                                }
                            }else
                            {
                                System.out.print("Item not found.");
                            }

                        } else if (choice.equals("n")) {
                            break;
                        }
                        if(potionIsUsed)
                        {
                            break;
                        }
                        clearPart(x,y,game,20,70);
                    }
                }
            }
            game.cn.readLine();
            infoIsCome = true;
            inventoryOpened = false;
        }
    }
    //------------------------------------ZIRH SEKMESI PRINT---------------------------
    public void printArmor(Game game,int x,int y)
    {
        game.cn.setTextAttributes(new TextAttributes(Color.cyan));
        game.cn.getTextWindow().setCursorPosition(45, 0);
        System.out.println("Helmet : ");
        game.cn.getTextWindow().setCursorPosition(45, 1);
        System.out.println("Chestplate : ");
        game.cn.getTextWindow().setCursorPosition(45, 2);
        System.out.println("Boots : ");
        game.cn.getTextWindow().setCursorPosition(45, 3);
        System.out.println("Weapon : ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        if(helmetUsed) {
            game.cn.getTextWindow().setCursorPosition(54, 0);
            if(currentHelmet != null) {
                Object data = currentHelmet.getData();
                if (data instanceof Armor) {
                    System.out.print(currentHelmet.getItemName() + " " + ((Armor) data).getDefense() + "     ");
                }
            } else {
                System.out.print("none       ");
            }
        }
        if (chestPlateUsed)
        {
            game.cn.getTextWindow().setCursorPosition(58, 1);
            if(currentChestPLate != null) {
                Object data = currentChestPLate.getData();
                if (data instanceof Armor) {
                    System.out.print(currentChestPLate.getItemName() + " " + ((Armor) data).getDefense() + "     ");
                }
            } else {
                System.out.print("none       ");
            }
        }
        if (bootsUsed)
        {
            game.cn.getTextWindow().setCursorPosition(53, 2);
            if(currentBoots != null) {
                Object data = currentBoots.getData();
                if (data instanceof Armor) {
                    System.out.print(currentBoots.getItemName() + " " + ((Armor) data).getDefense() + "     ");
                }
            }else
            {
                System.out.print("none       ");
            }
        }
        if (weaponUsed)
        {
            game.cn.getTextWindow().setCursorPosition(54, 3);
            if(currentWeapon != null) {
                Object data = currentWeapon.getData();
                if (data instanceof Weapon) {
                    System.out.print(currentWeapon.getItemName() + " " + ((Weapon) data).getDamage() + "     ");
                }
            }else
            {
                System.out.print("none       ");
            }
        }
        game.cn.getTextWindow().setCursorPosition(x, y);
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void printStatics(Game game,Player player) {
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 0);
        System.out.print("Current Biome : ");
        dungeon.setBiomeColor(game);
        System.out.println(dungeon.getBiomeType());
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 1);
        System.out.println(player.getName());
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 2);
        System.out.println("Level : " + player.getLevel());
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 3);
        System.out.println("Xp    : " + player.getXp());
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 4);
        System.out.println("Money : " + player.getMoney());
        game.cn.setTextAttributes(new TextAttributes(Color.red));
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+20, 2);
        System.out.print("HP      ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println( " : " +player.getHp());
        game.cn.setTextAttributes(new TextAttributes(Color.cyan));
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+20, 3);
        System.out.print("Attack  ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println( " : " +player.getAttack());
        game.cn.setTextAttributes(new TextAttributes(Color.green));
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+20, 4);
        System.out.print("Defence ");
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println( " : " +player.getDefence());
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 5);
        System.out.println("Hidden Dungeon Exist : "+ dungeon.isHiddenDungeonExcist());
        game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 6);
        System.out.println("Hidden Dungeon : "+ dungeon.getHiddeDoorX() + "," + dungeon.getHiddeDoorY());
        game.cn.getTextWindow().setCursorPosition(0, 0);
    }
    public int lengthNumbers(int x) // sayı uzunluk alıyo
    {
        if((x / 10) <= 0)
        {
            // tek basamaklı
            return 1;
        } else if ((x / 100) <= 0) {
            // iki basamak
            return 2;
        } else if ((x / 1000) <= 0) {
            // 3 basamak
            return 3;
        } else if ((x / 10000) <= 0) {
            // 4 basamak
            return 4;
        }else if ((x / 100000) <= 0)
        {
            // 5 basamak
            return 5;
        }else
        {
            // 6 basamak ve üsütü
            return 6;
        }
    }
    public void war(Enemy enemy,Game game) throws InterruptedException
    {
        player.setMana(3);
        int ultiCount = 0;
        int num = rand.nextInt(1000);
        CircularQueue loop = new CircularQueue(2);
        if(num % 2 == 0)
        {
            loop.enqueue(enemy.getType());
            loop.enqueue(player.getName());
        }else
        {
            loop.enqueue(player.getName());
            loop.enqueue(enemy.getType());
        }
        while (player.getHp() > 0 && enemy.getHealth() > 0) { // biri ölene kadar
            game.Clear();
            printEnemy(enemy,game);
            printPlayer(game);
            game.cn.getTextWindow().setCursorPosition(45, 4);
            System.out.print(player.getName().toUpperCase()+ "'s Mana --> ");
            game.cn.setTextAttributes(new TextAttributes(Color.orange));
            System.out.print(player.getMana()); //defans
            game.cn.setTextAttributes(new TextAttributes(Color.white));
            System.out.println(".");
            game.cn.getTextWindow().setCursorPosition(45, 5);
            System.out.print(player.getName().toUpperCase()+ "'s UltiCount --> ");
            game.cn.setTextAttributes(new TextAttributes(Color.green));
            System.out.print(ultiCount); //defans
            game.cn.setTextAttributes(new TextAttributes(Color.white));
            System.out.println(".");
            if(loop.peek().equals(player.getName())) // sıra playerdaysa
            {
                //sıra playerda
                String choice = "";
                int damage = 0;
                damage = player.getAttack()/((100+enemy.getDefence())/100);
                int x =  game.cn.getTextWindow().getCursorX();
                int y =  game.cn.getTextWindow().getCursorY();
                if(player.getMana() < 8)
                {
                    player.setMana(player.getMana()+1);
                    game.cn.getTextWindow().setCursorPosition(45, 4);
                    System.out.print(player.getName().toUpperCase()+ "'s Mana --> ");
                    game.cn.setTextAttributes(new TextAttributes(Color.orange));
                    System.out.print(player.getMana()); //defans
                    game.cn.setTextAttributes(new TextAttributes(Color.white));
                    System.out.println(".");
                }
                while (true) {
                    System.out.println();
                    System.out.println(player.getName() + " turn. \nChoose LowAttack(LA (+2 mana)) or Attack(A (-2 mana)) or \nHighAttack(HA (-4 mana)) or VeryHighAttack(VHA (-6 mana -2 ultiCount))");
                    choice = scanner.nextLine();
                    if(choice.equalsIgnoreCase("LA")) // low attack add 2 mana
                    {
                        if(player.getMana() + 2 <= 8) {
                            player.setMana(player.getMana()+2);
                        }
                        damage = damage / 2;
                        enemy.setHealth(enemy.getHealth()-damage);
                        break;
                    }else if(choice.equalsIgnoreCase("A")) // normal attack -2 mana
                    {
                        if((player.getMana() - 2 >= 0)) { // mana yeterse
                            player.setMana(player.getMana() - 2);
                            enemy.setHealth(enemy.getHealth() - damage);
                            break;
                        }else
                        {
                            System.out.println("Mana is not enough.\nYou need 2 mana, but you have only "+player.getMana()+".");
                            Thread.sleep(1000);
                        }
                    }else if(choice.equalsIgnoreCase("HA")) // high attack -4 mana
                    {
                        if((player.getMana() - 4 >= 0)) {
                            player.setMana(player.getMana() - 4);
                            damage = damage * 2;
                            enemy.setHealth(enemy.getHealth() - damage);
                            break;
                        }else
                        {
                            System.out.println("Mana is not enough.\nYou need 4 mana, but you have only "+player.getMana()+".");
                            Thread.sleep(1000);
                        }
                    }else if(choice.equalsIgnoreCase("VHA")) // very high attack -6 mana
                    {
                        if((ultiCount == 2)) { // ulti miktarı
                            if((player.getMana() - 6 >= 0)) {
                                ultiCount = -1;
                                player.setMana(player.getMana() - 6);
                                damage = damage * 5;
                                enemy.setHealth(enemy.getHealth() - damage);
                                break;
                            }else
                            {
                                System.out.println("Mana is not enough.\nYou need 6 mana, but you have only "+player.getMana()+".");
                                Thread.sleep(1000);
                            }
                        }else
                        {
                            //yetesiz ulti
                            System.out.println("Ulti is not enough.\nYou need 2 ultiCount, but you have only "+ultiCount+".");
                            Thread.sleep(1000);
                        }
                    }
                    game.cn.getTextWindow().setCursorPosition(x, y);
                    for(int i = 0; i < 20; i++) {
                        for(int j = 0; j < 70; j++) {
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    game.cn.getTextWindow().setCursorPosition(x, y-1);
                }
                System.out.println(player.getName()+ " hit " +enemy.getType()+" "+ damage);
                Thread.sleep(1000);
                loop.enqueue(loop.dequeue());
                if(ultiCount < 2) {
                    ultiCount++;
                }
            }else if(loop.peek().equals(enemy.getType()))
            {
                //sıra düşmanda
                int damage = 0;
                damage = enemy.getAttack()/((100+player.getDefence())/100);
                int x =  game.cn.getTextWindow().getCursorX();
                int y =  game.cn.getTextWindow().getCursorY();
                String choice = "";
                while (true) {
                    System.out.println("Enemy attacked you.\nDo you want to defence Yes(Y) or No(N)?");
                    choice = scanner.nextLine();
                    if((choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("Yes"))) // mana yetmeli
                    {
                        if(((player.getMana() - 2) >= 0)) {
                            player.setMana(player.getMana() - 2);
                            damage = damage / 2;
                            player.setHp(player.getHp() - damage);
                            break;
                        }else
                        {
                            System.out.println("Mana is not enough.\nYou need 2 mana, but you have only "+player.getMana()+".");
                            Thread.sleep(1000);
                        }
                    }else if((choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("No")))
                    {
                        player.setHp(player.getHp()-damage);
                        break;
                    }
                    game.cn.getTextWindow().setCursorPosition(x, y);
                    for(int i = 0; i < 20; i++)
                    {
                        for(int j = 0; j < 50; j++)
                        {
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                    game.cn.getTextWindow().setCursorPosition(x, y);
                }
                System.out.println(enemy.getType()+ " hit "+player.getName() +" "+ damage);
                Thread.sleep(1000);
                loop.enqueue(loop.dequeue());
            }
        }
        game.Clear();
        game.cn.setTextAttributes(new TextAttributes(Color.yellow));
        System.out.println(enemy.getType());
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        printEnemy(enemy,game);
        printPlayer(game);
        if(player.getHp() <= 0) // player loose
        {
            System.out.println("You are dead");
            enemy.setHealth(enemyFirstLife);
            player.setHp(playerFirstLife);
            System.out.println("To continue, press enter. ");
            String line = scanner.nextLine();
        }else // player win (gain money and xp)
        {
            System.out.println("You won");
            warLevelUp(); // level sistemi
            enemy.setDead(true); // düşam öldü
            if(enemy.getType().equalsIgnoreCase("dragon") || enemy.getType().equalsIgnoreCase("hero") || enemy.getType().equalsIgnoreCase("skeleton king") || enemy.getType().equalsIgnoreCase("demon king") || enemy.getType().equalsIgnoreCase("orc king")){
                bossDefeated = true;
                chest(game,bossDefeated);
                bossDefeated = false;
            }else {
                chest(game,bossDefeated);
            }
            player.setHp(playerFirstLife);
            System.out.println("To continue, press enter. ");
            scanner.nextLine();
        }
        isWarBegin = false;
    }
    public void chest(Game game,boolean flag)
    {
        int randomNum = rand.nextInt(100);
        String type = "";
        int damage = 0;
        String armorType = "";
        String potionType = "";
        String description = "";
        int capacity = 0;
        int defence = 0;
        int effection = 0;
        int minInterval = 0;
        int amount = 0;
        int maxInterval = 0;
        int itemID = 0;
        int lineCount = 0; // for animation
        if(randomNum < 60)
        {
            type = "common";
            minInterval = 100;
            maxInterval = 200;
            defence = 250;
            damage = 200;
            effection = rand.nextInt(200);
            capacity = 5;
            lineCount = 4;
        }else if(randomNum <90)
        {
            type = "rare";
            minInterval = 200;
            maxInterval = 400;
            defence = 500;
            damage = 400;
            itemID +=1;
            effection = rand.nextInt(200)+200;
            capacity = 10;
            lineCount = 6;
        }else
        {
            type = "epic";
            minInterval = 400;
            maxInterval = 800;
            defence = 1000;
            damage = 800;
            itemID +=2;
            effection = rand.nextInt(400)+400;
            capacity = 15;
            lineCount = 8;
        }
        if(flag){
            type = "mega";
            minInterval = 400;
            maxInterval = 800;
            defence = 1000;
            damage = 800;
            itemID +=2;
            effection = rand.nextInt(400)+400;
            capacity = 30;
            lineCount = 10;
        }
        armor = new Armor(0,""); // default armor
        weapon = new Weapon("",0);
        chest = new Chest(0,0,type,loots);
        //chest.randomChest(rand);
        chest.randomChest1(rand,capacity);
        loots = chest.getItems();
        Animation animation = new Animation(lineCount*5);
        animation.loadAnimation();
        System.out.println("You got " + chest.getType() + " chest.");
        scanner.nextLine();
        int itemsCount = loots.length - 1;
        for(int i = 0;i<loots.length ; i++) {
            if (loots[i].equals("money")) //item paraysa
            {
                amount = rand.nextInt(maxInterval - minInterval) + minInterval;
                player.setMoney(player.getMoney()+amount);
            } else if (loots[i].equals("xp")) {
                amount = rand.nextInt(maxInterval - minInterval) + minInterval;
                levelUP(amount);
            } else if (loots[i].equals("chestPlate") || loots[i].equals("boots") || loots[i].equals("helmet")) {
                if(loots[i].equals("chestPlate")) //ITEMID 3,4,5 defence 500
                {
                    itemID +=3;
                    defence = defence*2;
                    armorType = loots[i];
                    armor = new Armor(defence,armorType);
                    item = new Item(loots[i],1,itemID,armor);
                    player.getInventory().addItem(item);
                    defence = defence/2;
                    itemID -= 3;
                }else if(loots[i].equals("boots")) //ITEMID 0,1,2 defence 250
                {
                    armorType = loots[i];
                    armor = new Armor(defence,armorType);
                    item = new Item(loots[i],1,itemID,armor);
                    player.getInventory().addItem(item);
                } else if (loots[i].equals("helmet")) {//ITEMID 6,7,8 defence 250
                    itemID +=6;
                    armorType = loots[i];
                    armor = new Armor(defence,armorType);
                    item = new Item(loots[i],1,itemID,armor);
                    player.getInventory().addItem(item);
                    itemID -= 6;
                }
            } else if (loots[i].equals("sword") || loots[i].equals("bow") || loots[i].equals("staff")|| loots[i].equals("axe") || loots[i].equals("spear")) {
                if(loots[i].equals("sword")) //ITEMID 9,10,11 //damage 200
                {
                    itemID +=9;
                    armorType = loots[i];
                    weapon = new Weapon(armorType,damage);
                    item = new Item(loots[i],1,itemID,weapon);
                    player.getInventory().addItem(item);
                    itemID -= 9;
                }else if(loots[i].equals("bow")) //ITEMID 12,13,14 //damage 250
                {
                    itemID +=12;
                    armorType = loots[i];
                    damage = (damage * 5)/4;
                    weapon = new Weapon(armorType,damage);
                    item = new Item(loots[i],1,itemID,weapon);
                    player.getInventory().addItem(item);
                    damage = (damage * 4)/5;
                    itemID -=12;
                } else if (loots[i].equals("staff")) {//ITEMID 15,16,17 //damage 225
                    itemID +=15;
                    armorType = loots[i];
                    damage = (damage * 9) / 8;
                    weapon = new Weapon(armorType,damage);
                    item = new Item(loots[i],1,itemID,weapon);
                    player.getInventory().addItem(item);
                    damage = (damage * 8) / 9;
                    itemID -= 15;
                }
                else if(loots[i].equals("axe")) //ITEMID 18,19,20 // 275
                {
                    itemID +=18;
                    armorType = loots[i];
                    damage = (damage * 11) / 8;
                    weapon = new Weapon(armorType,damage);
                    item = new Item(loots[i],1,itemID,weapon);
                    player.getInventory().addItem(item);
                    damage = (damage * 8) / 11;
                    itemID -=18;
                } else if (loots[i].equals("spear")) {//ITEMID 21,22,23 // 175
                    itemID +=21;
                    armorType = loots[i];
                    damage = (damage * 7) / 8;
                    weapon = new Weapon(armorType,damage);
                    item = new Item(loots[i],1,itemID,weapon);
                    player.getInventory().addItem(item);
                    damage = (damage * 8) / 7;
                    itemID -= 21;
                }
            } else if (loots[i].equals("healthPotion") || loots[i].equals("defencePotion") || loots[i].equals("attackPotion")){
                if(loots[i].equals("healthPotion")) {//ITEMID 24,25,26
                    itemID += 24;
                    potionType = loots[i];
                    description = "This increase your health";
                    potion = new Potion(potionType,description,effection,"Red");
                    item = new Item(loots[i],1,itemID,potion);
                    player.getInventory().addItem(item);
                    itemID -= 24;
                } else if (loots[i].equals("defencePotion")) { //ITEMID 27,28,29
                    itemID += 27;
                    potionType = loots[i];
                    description = "This increase your defence";
                    potion = new Potion(potionType,description,effection,"Yellow");
                    item = new Item(loots[i],1,itemID,potion);
                    player.getInventory().addItem(item);
                    itemID -= 27;
                } else if  (loots[i].equals("attackPotion")) { //ITEMID 30,31,32
                    itemID += 30;
                    potionType = loots[i];
                    description = "This increase your attack";
                    potion = new Potion(potionType,description,effection,"Blue");
                    item = new Item(loots[i],1,itemID,potion);
                    player.getInventory().addItem(item);
                    itemID -= 30;
                }
            }
            System.out.print(loots[i] + " (");
            int randomColorNum = rand.nextInt(5);
            if(randomColorNum == 0)
            {
                game.cn.setTextAttributes(new TextAttributes(Color.red));
            } else if (randomColorNum == 1) {
                game.cn.setTextAttributes(new TextAttributes(Color.blue));
            } else if (randomColorNum == 2) {
                game.cn.setTextAttributes(new TextAttributes(Color.green));
            } else if (randomColorNum == 3) {
                game.cn.setTextAttributes(new TextAttributes(Color.orange));
            }else {
                game.cn.setTextAttributes(new TextAttributes(Color.cyan));
            }
            System.out.print(itemsCount);
            itemsCount--;
            game.cn.setTextAttributes(new TextAttributes(Color.white));
            System.out.print(") items left.");
            scanner.nextLine();
        }
    }
    public void levelUP(int XP) //level system
    {
        int currentLevel;
        player.setXp(player.getXp()+XP);
        boolean isCanLevelUp = true;
        while (isCanLevelUp) {
            currentLevel = player.getLevel();
            isCanLevelUp = false;
            for (int i = 0; i < 20; i++) {
                if (currentLevel == i) {
                    if (player.getXp() >= 5000 * (i + 1)) {
                        player.setLevel(player.getLevel() + 1);
                        player.setXp(player.getXp() - (5000 * (i + 1)));
                        isCanLevelUp = true;
                    }
                    break;
                }
            }
        }
    }
    public void warLevelUp() // war xp and money
    {
        int currentLevel = player.getLevel();
        for (int i = 0 ; i < 20;i++) // 20 level
        {
            if (currentLevel == i)
            {
                player.setMoney(player.getMoney()+(200 * (i +1))); // leveli ile orantılı para kazanıyo
                break;
            }
        }
        levelUP(200);
    }
    public void printEnemy(Enemy enemy,Game game)
    {
        game.cn.setTextAttributes(new TextAttributes(Color.YELLOW));
        System.out.println(enemy.getType());
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        System.out.print(enemy.getType()+ "'s Health --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.red));
        System.out.print(enemy.getHealth()); //canı
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        System.out.print(enemy.getType()+ "'s Attack --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.green));
        System.out.print(enemy.getAttack()); //attack
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        System.out.print(enemy.getType()+ "'s Defense --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.cyan));
        System.out.print(enemy.getDefence()); //defans
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
    }
    public void printPlayer(Game game)
    {
        game.cn.getTextWindow().setCursorPosition(45, 0);
        game.cn.setTextAttributes(new TextAttributes(Color.yellow));
        System.out.print(player.getName());
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        game.cn.getTextWindow().setCursorPosition(45, 1);
        System.out.print(player.getName().toUpperCase()+ "'s Health --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.red));
        System.out.print(player.getHp()); //canı
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        game.cn.getTextWindow().setCursorPosition(45, 2);
        System.out.print(player.getName().toUpperCase()+ "'s Attack --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.green));
        System.out.print(player.getAttack()); //attack
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        game.cn.getTextWindow().setCursorPosition(45, 3);
        System.out.print(player.getName().toUpperCase()+ "'s Defense --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.cyan));
        System.out.print(player.getDefence()); //defans
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
    }
    //Player movement
    public void mouse() throws Exception {
        Game game = new Game();
        game.tmlis = new TextMouseListener() {
            public void mouseClicked(TextMouseEvent arg0) {}

            public void mousePressed(TextMouseEvent arg0) {
                if(isMousePressedActive) {
                    mouseX = arg0.getX();
                    mouseY = arg0.getY();
                }
            }
            public void mouseReleased(TextMouseEvent arg0) {}
        };
        game.cn.getTextWindow().addTextMouseListener(game.tmlis);

    }
    public void movement() throws Exception
    {
        Game game = new Game();
        game.klis=new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if (takesInput) {
                    infoIsCome = true;
                    if (game.keypr == 0) {
                        game.keypr = 1;
                        game.rkey = e.getKeyCode();
                    }
                    if(e.getKeyCode() == KeyEvent.VK_S){
                        isSettingOpened=true;
                        infoIsCome = false;
                        isMousePressedActive=true;
                    }
                    if(e.getKeyCode() == KeyEvent.VK_E)
                    {
                        inventoryOpened = true;
                        infoIsCome = false;
                    }
                    if(e.getKeyCode() == KeyEvent.VK_M)
                    {
                        isMarketOpened = true;
                        infoIsCome = false;
                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (dungeon.isHiddenDungeonExcist()) {
                            if (dungeon.getHiddeDoorX() == (x - 1) && dungeon.getHiddeDoorY() == y) {
                                //hidden are unlocked
                                isTranformHidden = true;
                                infoIsCome = false;
                            }
                        }
                        if (dungeon.getDungeonMatrix()[x - 1][y] != '+' && dungeon.getDungeonMatrix()[x - 1][y] != '-') {
                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if (dungeon.getDungeonMatrix()[x-1][y] == 'E') {// yukarısı E ise
                                enemyX = x - 1;
                                enemyY = y;
                                enemyCoordinateVertex = new Vertex(enemyX,enemyY); //konumu aldık
                                if (enemyCoordinateQueue.size() == 2) { // queue doluysa
                                    enemyCoordinateQueue.dequeue();// son eleman sil
                                }
                                enemyCoordinateQueue.enqueue(enemyCoordinateVertex); // queue at
                            } else if (dungeon.getDungeonMatrix()[x-1][y] == 'N') {
                                infoIsCome = false;
                                isNPCEncountered = true;
                                NPCX = x - 1;
                                NPCY = y;
                                NPCCoordinateVertex = new Vertex(NPCX,NPCY);
                                if (NPCCoordinateQueue.size() == 2) {
                                    NPCCoordinateQueue.dequeue();
                                }
                                NPCCoordinateQueue.enqueue(NPCCoordinateVertex);
                            }
                            //-----------we select enemy----------
                            for (int i = 0; i < enemies.length; i++) {
                                if(enemyX == enemies[i].getX() && enemyY == enemies[i].getY()) {
                                    enemy = enemies[i];
                                }
                            }
                            //-----------we select npc----------
                            npc = selectNPC(NPCX, NPCY);
                            x = x - 1;
                            // we take money
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }

                            dungeon.getDungeonMatrix()[x][y] = 'P'; //konumu x ve y //ilerledik

                            // bu noktalar boşssa doldur
                            if (dungeon.getDungeonMatrix()[enemyX][enemyY] == ' ' && !enemy.isDead()) {
                                dungeon.getDungeonMatrix()[enemyX][enemyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[NPCX][NPCY] == ' ' && !npc.isQuestFinished()) {
                                dungeon.getDungeonMatrix()[NPCX][NPCY] = 'N';
                            }

                            // we select second enemy
                            for (int i = 0; i < enemies.length; i++) {
                                if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null) { // boş değilse
                                    if (((Vertex) enemyCoordinateQueue.peek()).getVertexX() == enemies[i].getX() && ((Vertex) enemyCoordinateQueue.peek()).getVertexY() == enemies[i].getY()) {
                                        secondEnemy = enemies[i];
                                    }
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                secondNPC = selectNPC(((Vertex) NPCCoordinateQueue.peek()).getVertexX(), ((Vertex) NPCCoordinateQueue.peek()).getVertexY());
                            }

                            //placed second npc and enemy
                            if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null){ // boş değilse
                                if (dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] == ' ' && !secondEnemy.isDead()) {
                                    dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] = 'E';
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                if(dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] == ' ' && !secondNPC.isQuestFinished()) {
                                   dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] = 'N';
                                }
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (dungeon.isHiddenDungeonExcist()) {
                            if (dungeon.getHiddeDoorX() == (x + 1) && dungeon.getHiddeDoorY() == y) {
                                //hidden are unlocked
                                isTranformHidden = true;
                                infoIsCome = false;
                            }
                        }
                        if (dungeon.getDungeonMatrix()[x + 1][y] != '+' && dungeon.getDungeonMatrix()[x + 1][y] != '-') {
                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x+1][y] == 'E') // aşağısı E ise
                            {
                                enemyX = x + 1; // bir sağında e var bir sağına daha bakalım
                                enemyY = y;
                                enemyCoordinateVertex = new Vertex(enemyX,enemyY); //konumu aldık
                                if(enemyCoordinateQueue.size() == 2) // queue doluysa
                                {
                                    enemyCoordinateQueue.dequeue();// son eleman sil
                                }
                                enemyCoordinateQueue.enqueue(enemyCoordinateVertex); // queue at
                            } else if (dungeon.getDungeonMatrix()[x+1][y] == 'N') {
                                infoIsCome = false;
                                isNPCEncountered = true;
                                NPCX = x + 1;
                                NPCY = y;
                                NPCCoordinateVertex = new Vertex(NPCX,NPCY);
                                if (NPCCoordinateQueue.size() == 2) {
                                    NPCCoordinateQueue.dequeue();
                                }
                                NPCCoordinateQueue.enqueue(NPCCoordinateVertex);
                            }

                            for (int i = 0; i < enemies.length; i++) {
                                if(enemyX == enemies[i].getX() && enemyY == enemies[i].getY())
                                {
                                    enemy = enemies[i];
                                }
                            }
                            npc = selectNPC(NPCX, NPCY);

                            x = x + 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P';

                            if (dungeon.getDungeonMatrix()[enemyX][enemyY] == ' ' && !enemy.isDead()) {
                                dungeon.getDungeonMatrix()[enemyX][enemyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[NPCX][NPCY] == ' ' && !npc.isQuestFinished()) {
                                dungeon.getDungeonMatrix()[NPCX][NPCY] = 'N';
                            }

                            for (int i = 0; i < enemies.length; i++)
                            {
                                if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null) { // boş değilse
                                    if (((Vertex) enemyCoordinateQueue.peek()).getVertexX() == enemies[i].getX() && ((Vertex) enemyCoordinateQueue.peek()).getVertexY() == enemies[i].getY()) {
                                        secondEnemy = enemies[i];
                                    }
                                }
                            }

                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                secondNPC = selectNPC(((Vertex) NPCCoordinateQueue.peek()).getVertexX(), ((Vertex) NPCCoordinateQueue.peek()).getVertexY());
                            }

                            if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null){ // boş değilse
                                if (dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] == ' ' && !secondEnemy.isDead()) {
                                    dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] = 'E';
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                if(dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] == ' ' && !secondNPC.isQuestFinished()) {
                                    dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] = 'N';
                                }
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (dungeon.isHiddenDungeonExcist()) {
                            if (dungeon.getHiddeDoorX() == x && dungeon.getHiddeDoorY() == (y + 1)) {
                                //hidden are unlocked
                                isTranformHidden = true;
                                infoIsCome = false;
                            }
                        }
                        if (dungeon.getDungeonMatrix()[x][y + 1] != '+' && dungeon.getDungeonMatrix()[x][y + 1] != '|') {
                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x][y + 1] == 'E') // sağında E ise
                            {
                                enemyX = x;
                                enemyY = y + 1;
                                enemyCoordinateVertex = new Vertex(enemyX,enemyY); //konumu aldık
                                if (enemyCoordinateQueue.size() == 2) // queue doluysa
                                {
                                    enemyCoordinateQueue.dequeue();// son eleman sil
                                }
                                enemyCoordinateQueue.enqueue(enemyCoordinateVertex); // queue at
                            } else if (dungeon.getDungeonMatrix()[x][y + 1] == 'N') {
                                infoIsCome = false;
                                isNPCEncountered = true;
                                NPCX = x ;
                                NPCY = y + 1;
                                NPCCoordinateVertex = new Vertex(NPCX,NPCY);
                                if (NPCCoordinateQueue.size() == 2) {
                                    NPCCoordinateQueue.dequeue();
                                }
                                NPCCoordinateQueue.enqueue(NPCCoordinateVertex);
                            }
                            for (int i = 0; i < enemies.length; i++) {
                                if(enemyX == enemies[i].getX() && enemyY == enemies[i].getY()) {
                                    enemy = enemies[i];
                                }
                            }

                            npc = selectNPC(NPCX, NPCY);

                            y = y + 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }

                            dungeon.getDungeonMatrix()[x][y] = 'P';
                            if (dungeon.getDungeonMatrix()[enemyX][enemyY] == ' ' && !enemy.isDead()) {
                                dungeon.getDungeonMatrix()[enemyX][enemyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[NPCX][NPCY] == ' ' && !npc.isQuestFinished()) {
                                dungeon.getDungeonMatrix()[NPCX][NPCY] = 'N';
                            }

                            for (int i = 0; i < enemies.length; i++) {
                                if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null) { // boş değilse
                                    if (((Vertex) enemyCoordinateQueue.peek()).getVertexX() == enemies[i].getX() && ((Vertex) enemyCoordinateQueue.peek()).getVertexY() == enemies[i].getY()) {
                                        secondEnemy = enemies[i];
                                    }
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                secondNPC = selectNPC(NPCX, NPCY);
                            }

                            if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null){ // boş değilse
                                if (dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] == ' ' && !secondEnemy.isDead()) {
                                    dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] = 'E';
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                if(dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] == ' ' && !secondNPC.isQuestFinished()) {
                                    dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] = 'N';
                                }
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (dungeon.isHiddenDungeonExcist()) {
                            if (dungeon.getHiddeDoorX() == x && dungeon.getHiddeDoorY() == (y - 1)) {
                                //hidden are unlocked
                                isTranformHidden = true;
                                infoIsCome = false;
                            }
                        }
                        if (dungeon.getDungeonMatrix()[x][y - 1] != '+' && dungeon.getDungeonMatrix()[x][y - 1] != '|') {
                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x][y - 1] == 'E') // solda E ise
                            {
                                enemyX = x;
                                enemyY = y - 1;
                                enemyCoordinateVertex = new Vertex(enemyX,enemyY); //konumu aldık
                                if (enemyCoordinateQueue.size() == 2) {  // queue doluysa
                                    enemyCoordinateQueue.dequeue();// son eleman sil
                                }
                                enemyCoordinateQueue.enqueue(enemyCoordinateVertex); // queue at
                            } else if (dungeon.getDungeonMatrix()[x][y - 1] == 'N') {
                                infoIsCome = false;
                                isNPCEncountered = true;
                                NPCX = x;
                                NPCY = y - 1;
                                NPCCoordinateVertex = new Vertex(NPCX,NPCY);
                                if (NPCCoordinateQueue.size() == 2) {
                                    NPCCoordinateQueue.dequeue();
                                }
                                NPCCoordinateQueue.enqueue(NPCCoordinateVertex);
                            }

                            for (int i = 0; i < enemies.length; i++)
                            {
                                if(enemyX == enemies[i].getX() && enemyY == enemies[i].getY())
                                {
                                    enemy = enemies[i];
                                }
                            }

                            npc = selectNPC(NPCX,NPCY);

                            y = y - 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P';
                            if (dungeon.getDungeonMatrix()[enemyX][enemyY] == ' ' && !enemy.isDead()) { // buarda düşman yaşıyo
                                dungeon.getDungeonMatrix()[enemyX][enemyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[NPCX][NPCY] == ' ' && !npc.isQuestFinished()) {
                                dungeon.getDungeonMatrix()[NPCX][NPCY] = 'N';
                            }

                            for (int i = 0; i < enemies.length; i++) {
                                if(!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null) { // boş değilse
                                    if (((Vertex) enemyCoordinateQueue.peek()).getVertexX() == enemies[i].getX() && ((Vertex) enemyCoordinateQueue.peek()).getVertexY() == enemies[i].getY()) {
                                        secondEnemy = enemies[i];
                                    }
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                secondNPC = selectNPC(((Vertex) NPCCoordinateQueue.peek()).getVertexX(),((Vertex) NPCCoordinateQueue.peek()).getVertexY());
                            }

                            if (!enemyCoordinateQueue.isEmpty() && enemyCoordinateQueue.peek() != null) { // boş değilse
                                if (dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] == ' ' && !secondEnemy.isDead()) {
                                    dungeon.getDungeonMatrix()[((Vertex) enemyCoordinateQueue.peek()).getVertexX()][((Vertex) enemyCoordinateQueue.peek()).getVertexY()] = 'E';
                                }
                            }
                            if (!NPCCoordinateQueue.isEmpty() && NPCCoordinateQueue.peek() != null) {
                                if (dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] == ' ' && !secondNPC.isQuestFinished()) {
                                    dungeon.getDungeonMatrix()[((Vertex) NPCCoordinateQueue.peek()).getVertexX()][((Vertex) NPCCoordinateQueue.peek()).getVertexY()] = 'N';
                                }
                            }

                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if (x == 1 && y == 3) {
                            mazeStack.push(dungeon);
                            enemyStack.push(enemies);
                            npcStack.push(npcs);
                            vertexStack.push(startVertex);
                            vertexStack.push(finalVertex);
                            Vertex playerVertex = new Vertex(x,y);
                            vertexStack.push(playerVertex);
                            quit = true;
                        }
                        if (x == finalVertex.getVertexX() && y == finalVertex.getVertexY()) { //x çıkış  noktasındaysa
                            enemy = null;
                            npc = null;
                            secondNPC = null;
                            secondEnemy = null;
                            enemyX = 0;
                            enemyY = 0;
                            NPCX = 0;
                            NPCY = 0;
                            while (!enemyCoordinateQueue.isEmpty()) {
                                enemyCoordinateQueue.dequeue();
                            }
                            while (!NPCCoordinateQueue.isEmpty()) {
                                NPCCoordinateQueue.dequeue();
                            }
                            //-------we push current data----------
                            if (nextMazeStack.isEmpty()) {
                                randomStackActive = true;
                                //-------------push to stack---------------------

                                Dungeon dungeon1 = new Dungeon(dungeon.getDungeonX(),dungeon.getDungeonY());
                                for (int i = 0; i < dungeon1.getDungeonMatrix().length; i++) {
                                    for (int j = 0; j < dungeon1.getDungeonMatrix()[i].length; j++) {
                                        dungeon1.getDungeonMatrix()[i][j] = dungeon.getDungeonMatrix()[i][j];
                                    }
                                }
                                Enemy[] enemies1 = enemies;
                                NPC npcs1 = npcs;
                                Vertex finalVertex1 = finalVertex;
                                Vertex startVertex1 = startVertex;
                                Biomes tempBiomes = biomes;
                                BiomeTypes tempBiomeTypes = biomeTypes;
                                boolean tempIsHiddenDungeonExcist = dungeon.isHiddenDungeonExcist();
                                int tempDoorX = dungeon.getHiddeDoorX();
                                int tempDoorY = dungeon.getHiddeDoorY();

                                biomesStack.push(tempBiomes);
                                biomesStack.push(tempBiomeTypes);
                                mazeStack.push(dungeon1);
                                enemyStack.push(enemies1);
                                npcStack.push(npcs1);
                                vertexStack.push(startVertex1);
                                vertexStack.push(finalVertex1);
                                Vertex playerVertex1 = new Vertex(x,y);
                                vertexStack.push(playerVertex1);
                                quit = true;
                                hiddenDungeonStack.push(tempDoorY);
                                hiddenDungeonStack.push(tempDoorX);
                                hiddenDungeonStack.push(tempIsHiddenDungeonExcist);
                                stacksSize++;
                            } else {
                                if (!nextMazeStack.isEmpty()) {
                                    //---- we take stack datas
                                    Dungeon dungeon1 = (Dungeon) nextMazeStack.pop();
                                    Vertex playerVertex = (Vertex) nextVertexStack.pop();
                                    Enemy[] enemies1 = (Enemy[]) nextEnemyStack.pop();
                                    NPC npcs1 = (NPC) nextNpcStack.pop();
                                    Vertex finalVertex1 = (Vertex) nextVertexStack.pop();
                                    Vertex startVertex1 = (Vertex) nextVertexStack.pop();
                                    BiomeTypes biomeTypes1 = (BiomeTypes) nextBiomesStack.pop();
                                    Biomes biomes1 = (Biomes) nextBiomesStack.pop();
                                    boolean isHiddenDungeonExcist1 = (boolean) nextHiddenDungeonStack.pop();
                                    int doorX1 = (int)nextHiddenDungeonStack.pop();
                                    int doorY1 = (int)nextHiddenDungeonStack.pop();
                                    //------ we push current stack data------------
                                    Dungeon tempDungeon = new Dungeon(dungeon.getDungeonX(),dungeon.getDungeonY());
                                    for (int i = 0; i < tempDungeon.getDungeonMatrix().length; i++) {
                                        for (int j = 0; j < tempDungeon.getDungeonMatrix()[i].length; j++) {
                                            tempDungeon.getDungeonMatrix()[i][j] = dungeon.getDungeonMatrix()[i][j];
                                        }
                                    }

                                    Enemy[] tempEnemies = new Enemy[enemies.length];
                                    for (int i = 0; i < tempEnemies.length; i++) {
                                        tempEnemies[i] = enemies[i];
                                    }

                                    NPC tempNpc = npcs;
                                    Vertex tempStartVertex = startVertex;
                                    Vertex tempFinalVertex = finalVertex;
                                    Biomes tempBiomes = biomes;
                                    BiomeTypes tempBiomeTypes = biomeTypes;
                                    boolean tempIsHiddenDungeonExcist = dungeon.isHiddenDungeonExcist();
                                    int tempDoorX = dungeon.getHiddeDoorX();
                                    int tempDoorY = dungeon.getHiddeDoorY();

                                    biomesStack.push(tempBiomes);
                                    biomesStack.push(tempBiomeTypes);
                                    mazeStack.push(tempDungeon);
                                    enemyStack.push(tempEnemies);
                                    npcStack.push(tempNpc);
                                    vertexStack.push(tempStartVertex);
                                    vertexStack.push(tempFinalVertex);
                                    hiddenDungeonStack.push(tempDoorY);
                                    hiddenDungeonStack.push(tempDoorX);
                                    hiddenDungeonStack.push(tempIsHiddenDungeonExcist);

                                    Vertex playerVertex1 = new Vertex(x,y);
                                    vertexStack.push(playerVertex1);

                                    if (mazeStack.size() == stacksSize) {
                                        stacksSize++;
                                    }
                                    //----- we set variables
                                    x = playerVertex.getVertexX();
                                    y = playerVertex.getVertexY();
                                    for (int i = 0; i < enemies1.length; i++) {
                                        enemies[i] = enemies1[i];
                                    }
                                    npcs.setNpcNames(npcs1.getNpcNames());
                                    finalVertex = finalVertex1;
                                    startVertex = startVertex1;
                                    dungeon.setDungeonX(dungeon1.getDungeonX());
                                    dungeon.setDungeonY(dungeon1.getDungeonY());
                                    dungeon.setDungeonMatrix(dungeon1.getDungeonMatrix());
                                    dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                                    dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                                    biomes = biomes1;
                                    biomeTypes = biomeTypes1;
                                    dungeon.setBiome(biomes);
                                    dungeon.setBiomeType(biomeTypes);
                                    dungeon.setHiddeDoorX(doorX1);
                                    dungeon.setHiddeDoorY(doorY1);
                                    dungeon.setHiddenDungeonExcist(isHiddenDungeonExcist1);
                                    quit = true;
                                }
                            }
                            //----------başka zindana geçme---------
                        } else if (x ==  startVertex.getVertexX() && y == startVertex.getVertexY()) {
                            if (!mazeStack.isEmpty()) {
                                //---- we take stack datas
                                enemy = null;
                                npc = null;
                                secondNPC = null;
                                secondEnemy = null;
                                enemyX = 0;
                                enemyY = 0;
                                NPCX = 0;
                                NPCY = 0;
                                while (!enemyCoordinateQueue.isEmpty()) {
                                    enemyCoordinateQueue.dequeue();
                                }
                                while (!NPCCoordinateQueue.isEmpty()) {
                                    NPCCoordinateQueue.dequeue();
                                }
                                Dungeon dungeon1 = (Dungeon) mazeStack.pop();
                                Vertex playerVertex = (Vertex) vertexStack.pop();
                                Enemy[] enemies1 = (Enemy[]) enemyStack.pop();
                                NPC npcs1 = (NPC) npcStack.pop();
                                Vertex finalVertex1 = (Vertex) vertexStack.pop();
                                Vertex startVertex1 = (Vertex) vertexStack.pop();
                                BiomeTypes biomeTypes1 = (BiomeTypes) biomesStack.pop();
                                Biomes biomes1 = (Biomes) biomesStack.pop();
                                boolean isHiddenDungeonExcist1 = (boolean) hiddenDungeonStack.pop();
                                int doorX1 = (int)hiddenDungeonStack.pop();
                                int doorY1 = (int)hiddenDungeonStack.pop();

                                //------ we push current stack data------------
                                Dungeon tempDungeon = new Dungeon(dungeon.getDungeonX(),dungeon.getDungeonY());
                                for (int i = 0; i < tempDungeon.getDungeonMatrix().length; i++) {
                                    for (int j = 0; j < tempDungeon.getDungeonMatrix()[i].length; j++) {
                                        tempDungeon.getDungeonMatrix()[i][j] = dungeon.getDungeonMatrix()[i][j];
                                    }
                                }

                                Enemy[] tempEnemies = new Enemy[enemies.length];
                                for (int i = 0; i < tempEnemies.length; i++) {
                                    tempEnemies[i] = enemies[i];
                                }
                                //---------------current situation-----------
                                NPC tempNpc = npcs;
                                Vertex tempStartVertex = startVertex;
                                Vertex tempFinalVertex = finalVertex;
                                Biomes tempBiomes = biomes;
                                BiomeTypes tempBiomeTypes = biomeTypes;
                                boolean tempIsHiddenDungeonExcist = dungeon.isHiddenDungeonExcist();
                                int tempDoorX = dungeon.getHiddeDoorX();
                                int tempDoorY = dungeon.getHiddeDoorY();

                                nextBiomesStack.push(tempBiomes);
                                nextBiomesStack.push(tempBiomeTypes);
                                nextMazeStack.push(tempDungeon);
                                nextEnemyStack.push(tempEnemies);
                                nextNpcStack.push(tempNpc);
                                nextVertexStack.push(tempStartVertex);
                                nextVertexStack.push(tempFinalVertex);
                                nextHiddenDungeonStack.push(tempDoorY);
                                nextHiddenDungeonStack.push(tempDoorX);
                                nextHiddenDungeonStack.push(tempIsHiddenDungeonExcist);
                                Vertex playerVertex1 = new Vertex(x,y);
                                nextVertexStack.push(playerVertex1);

                                if ((nextMazeStack.size() + mazeStack.size()) == stacksSize) {
                                    stacksSize++;
                                }

                                //----- we set variables
                                x = playerVertex.getVertexX();
                                y = playerVertex.getVertexY();
                                for (int i = 0; i < enemies1.length; i++) {
                                    enemies[i] = enemies1[i];
                                }
                                npcs.setNpcNames(npcs1.getNpcNames());
                                finalVertex = finalVertex1;
                                startVertex = startVertex1;
                                dungeon.setDungeonX(dungeon1.getDungeonX());
                                dungeon.setDungeonY(dungeon1.getDungeonY());
                                dungeon.setDungeonMatrix(dungeon1.getDungeonMatrix());
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                                biomes = biomes1;
                                biomeTypes = biomeTypes1;
                                dungeon.setBiome(biomes);
                                dungeon.setBiomeType(biomeTypes);
                                dungeon.setHiddeDoorX(doorX1);
                                dungeon.setHiddeDoorY(doorY1);
                                dungeon.setHiddenDungeonExcist(isHiddenDungeonExcist1);
                                quit = true;
                            }
                        }
                        if(x == enemyX && y == enemyY && !enemy.isDead()) {
                            takesInput = false;
                        }
                    }
                }
                if(inventoryOpened && !isMarketOpened) {
                      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                          System.out.println("helloww");
                      } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                          System.out.println("woruld");
                      }
                }
                if(isMarketOpened) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        isMarketMovesLeft = true;
                        isMarketMovesRight = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        isMarketMovesRight = true;
                        isMarketMovesLeft = false;
                    } else if(e.getKeyCode() == KeyEvent.VK_Q) {
                        setMarketOpened(false);
                        isBuyActive = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_B) {
                        isBuyActive = true;
                    }
                }
                //----------------FOREST---------------
                if (forestMazeActive) {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        forestIsRigthPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        forestIsLeftPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        forestIsDownPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        forestIsUpPressed = true;
                    }
                }
                //----------------HIDDEN DUNGEON-----------------
                if (isTranformHidden) {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        hiddenDungeonIsRightPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        hiddenDungeonIsLeftPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        hiddenDungeonIsDownPressed = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        hiddenDungeonIsUpPressed = true;
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
        };


        /*
        if(keyFlag.getValue() == false)
        {

        }
        */
         
        game.cn.getTextWindow().addKeyListener(game.klis);
    }

    public NPC selectNPC(int x, int y){
       MultiLinkedList.CategoryNode categoryNode = npcs.getNpcNames().getHead();
       if (categoryNode == null) {
           System.out.println("List is empty");
           return null;
       } else {
           NPC selectedNPC = null;
           while(categoryNode != null) {
               MultiLinkedList.ItemNode itemNode = categoryNode.getRight();
               while (itemNode != null) {
                   selectedNPC = (NPC) itemNode.getData();
                   if (x == selectedNPC.getNPCVertex().getVertexX() && y == selectedNPC.getNPCVertex().getVertexY()) {
                       break;
                   }
                   itemNode = itemNode.getNext();
               }
               categoryNode = categoryNode.getDown();
           }
           return selectedNPC;
       }
    }
    public void hiddenDugeon(Game game) {
        while (true) {
            HiddenDungeon hiddenDungeon = new HiddenDungeon(35,35);
            hiddenDungeon.start();
            break;
        }
        isTranformHidden = false;
    }

    public Dungeon readText() throws IOException
    {
        BufferedReader bfReader = new BufferedReader(new FileReader("dungeon.txt"));
        String line;
        int lineCount = 0;
        while ((line = bfReader.readLine()) != null)
        {
            lineCount++; // burda kaç satır olduğuna baktık
        }
        bfReader.close();
        bfReader = new BufferedReader(new FileReader("dungeon.txt"));
        String line1;
        String word = "";
        String[] words = new String[lineCount];

        int a = 0;
        while ((line1 = bfReader.readLine()) != null)
        {

            word = line1;
            words[a] = word;
            a++;
        }
        Dungeon dungeon = new Dungeon(lineCount,words[0].length());
        bfReader.close();
        for (int i = 0; i < dungeon.getDungeonX(); i++)
        {
            for (int j = 0; j < dungeon.getDungeonY(); j++)
            {
                dungeon.getDungeonMatrix()[i][j] += words[i].charAt(j);
            }

        }
        //System.out.println(word);
        return dungeon;
    }
    public void writeText() throws IOException
    {
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter("dungeon.txt"));
        bfWriter.write(String.valueOf(5));
        bfWriter.close();
    }
    public void createStartDungeon(Dungeon dungeon)
    {
        dungeon.getDungeonMatrix();
        for (int i = 0; i < dungeon.getDungeonMatrix().length; i++)
        {
            for(int j = 0; j < dungeon.getDungeonMatrix()[i].length; j++)
            {

            }
        }
    }
    public void createRandomDungeon(Dungeon dungeon) // random zindanlar için
    {
        Stack visitedPoints = new Stack(dungeon.getDungeonX() * dungeon.getDungeonY());
        Vertex[] randomArray = new Vertex[4];
        int x = 0;
        int y = 0;
        do {
            x = rand.nextInt(dungeon.getDungeonX());
            y = rand.nextInt(dungeon.getDungeonY());
        }while (x % 2 == 0 || y % 2 == 0);
        vertex = new Vertex(x,y); // start point
        int a = 0;// random array elemanları
        int count = 0; // bir kez bir yere uğraması lazım
        visitedPoints.push(new Vertex(x,y)); // 1,1 stack attık
        dungeon.getDungeonMatrix()[x][y] = ' '; // bu duvarı kırdık
        while (!visitedPoints.isEmpty())
        {
            a = 0;
            x += 2 ; // sağ komşu
            if(x < dungeon.getDungeonX() &&  dungeon.getDungeonMatrix()[x][y] == '.') // i uzunluğundan küçük ve duvar
            {
                randomArray[a] = new Vertex(x,y);
                a++;
            }
            x -= 4 ; // sol komşu
            if(x > 0 &&  dungeon.getDungeonMatrix()[x][y] == '.')
            {
                randomArray[a] = new Vertex(x,y);
                a++;
            }
            x += 2;
            y += 2 ; // yukarı komşu
            if(y < dungeon.getDungeonY() &&  dungeon.getDungeonMatrix()[x][y] == '.') // j uzunluğundan küçük olmalı
            {
                randomArray[a] = new Vertex(x,y);
                a++;
            }
            y -= 4 ; // aşağı komşu
            if(y > 0 &&  dungeon.getDungeonMatrix()[x][y] == '.')
            {
                randomArray[a] = new Vertex(x,y);
                a++;
            }
            y += 2;

            //dizi elimizde

            if(a == 0) // her pop yağtığımız ulaşabileceği en uç noktadır
            {
                if(count == 0)
                {
                    vertexF = new Vertex(((Vertex)visitedPoints.peek()).getVertexX(),((Vertex)visitedPoints.peek()).getVertexY());
                    dungeon.getDungeonMatrix()[vertexF.getVertexX()][vertexF.getVertexY()] = 'x';// x işareti;
                }
                visitedPoints.pop(); // eğer gidilecek eleman yoksa
                count++;
            }else
            {
                int randomNumber = rand.nextInt(a); // random elemanı seç
                dungeon.getDungeonMatrix()[randomArray[randomNumber].getVertexX()][randomArray[randomNumber].getVertexY()] = ' ';
                dungeon.getDungeonMatrix()[(randomArray[randomNumber].getVertexX() + ((Vertex)visitedPoints.peek()).getVertexX()) /2][(randomArray[randomNumber].getVertexY() + ((Vertex)visitedPoints.peek()).getVertexY()) /2] = ' ';
                visitedPoints.push(randomArray[randomNumber]);
            }
            if(!visitedPoints.isEmpty()) {
                x = ((Vertex) visitedPoints.peek()).getVertexX();
                y = ((Vertex) visitedPoints.peek()).getVertexY();
            }
        }
        dungeon.getDungeonMatrix()[vertex.getVertexX()][vertex.getVertexY()] = '●';// ● işareti;
    }

    public boolean isMarketOpened() {
        return isMarketOpened;
    }

    public void setMarketOpened(boolean marketOpened) {
        isMarketOpened = marketOpened;
    }


}
