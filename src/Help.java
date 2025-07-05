import java.util.Scanner;

public class Help {
    private Game game;
    private Scanner scanner;
    public Help(Game game) {
        this.game = game;
        scanner = new Scanner(System.in);
    }
    public void informations() {
        while(true) {
            game.Clear();
            System.out.println("0-Exit");
            System.out.println("1-Movement");
            System.out.println("2-Inventory");
            System.out.println("3-War Mechanic");
            System.out.println("4-Market");
            System.out.println("5-Hidden Dungeon");
            System.out.println("6-Settings");
            System.out.println("7-Biomes");
            System.out.println("8-Chest System");
            System.out.println("9-Enemies");
            String choice = game.cn.readLine();
            if (choice.equals("1")) {
                movementInformation();
            } else if (choice.equals("2")) {
                inventoryInformation();
            } else if (choice.equals("3")) {
                warInformation();
            } else if (choice.equals("4")) {
                marketInformation();
            } else if (choice.equals("5")) {
                hiddenDungeonInformation();
            } else if (choice.equals("6")) {
                settingsInformations();
            } else if (choice.equals("7")) {
                biomesInformations();
            } else if (choice.equals("8")) {
                chestInformations();
            } else if (choice.equals("9")) {
                enemyInformations();
            } else if (choice.equals("0")) {
                break;
            }
        }
        scanner.close();
    }
    public void hiddenDungeonInformation() {
        game.Clear();
        //hidden dungeon Infos
        System.out.println("Hidden Dungeon");
        System.out.println("--> Every dungeon has hidden door %50 chance.");
        System.out.println("--> If dungeon has hidden door you can go through walls (border of the dungeon).");
        System.out.println("--> In dungeons 'E' enemy 'C' chest.");
        System.out.println("--> If you hit balls your health decrease.");
        System.out.println("--> For open keys you must collect key.");
        System.out.println("--> Keys and doors color must match.");
        System.out.println("--> Green colors door is open and you don't need key for green doors.");
        scanner.nextLine();
    }
    public void enemyInformations() {
        game.Clear();
        //Enemy Infos
        System.out.println("Enemy");
        System.out.println("--> There different types of enemies.");
        System.out.println("--> Every biomeType has own enemy.");
        System.out.println("--> There are bosses of every dungeon.");
        System.out.println("--> Every biomes has own boss.");
        System.out.println("--> In hidden dungeon if you encountered enemy you must battle.");
        System.out.println("--> In normal dungeon if you encountered enemy \n" +
                "if you want fight you must press space.");
        System.out.println("--> If you want to learn fight you can look War Mechanic.");
        System.out.println("--> You can get chest when you beat enemy.");
        scanner.nextLine();
    }
    public void chestInformations() {
        game.Clear();
        //Chest infos
        System.out.println("Chest System");
        System.out.println("--> There are four different types of chests.Common,rare,epic,mega.");
        System.out.println("--> You can get potion,armor,weapon,xp and money from chests.");
        System.out.println("--> You have Common 5,Rare 10,Epic 15,Mega 30 click.");
        System.out.println("--> You can get chests from hiddenDungeons or when you beat enemy.");
        scanner.nextLine();
    }

    public void settingsInformations() {
        game.Clear();
        //Help infos
        System.out.println("Settings");
        System.out.println("--> Save : You can save game.");
        System.out.println("--> Load : You can load old saved game.");
        System.out.println("--> Exit : You can exit game.");
        System.out.println("--> Help : You can get information of the game.");
        System.out.println("--> New Game : You can create a new game.");
        System.out.println("--> Resume : You can continue game.");
        scanner.nextLine();
    }
    public void biomesInformations() {
        game.Clear();
        //biome infos
        System.out.println("Biomes");
        System.out.println("--> There are 3 types biome.Hot,Warm,Cold.");
        System.out.println("--> There are 9 types biomeTypes.\n" +
                "    SAVANNA(Biomes.HOT),\n" +
                "    DESERT(Biomes.HOT),\n" +
                "    VOLCANIC(Biomes.HOT),\n" +
                "    ICE_SPIKES(Biomes.COLD),\n" +
                "    TUNDRA(Biomes.COLD),\n" +
                "    POLAR(Biomes.COLD),\n" +
                "    PLAINS(Biomes.WARM),\n" +
                "    FOREST(Biomes.WARM),\n" +
                "    SWAMP(Biomes.WARM);.");
        System.out.println("--> Every biomeType has own special enemy and\nevery biomeTypes has own special boss.");
        System.out.println("--> Every dungeon has own biome and biomeType and\nyou can see dungeon colors different because of biomeType.");

        scanner.nextLine();
    }
    public void  marketInformation() {
        game.Clear();
        //Market informations
        System.out.println("Market");
        System.out.println("--> You can open market with  'M' key.");
        System.out.println("--> When you opened market you can dynamically move with → ←.");
        System.out.println("--> You can buy item with 'B' key after opening market.");
        System.out.println("--> There are four type items cheap ,moderate ,expensive ,special .");
        System.out.println("--> You can buy potions, weapons and armours.");
        System.out.println("--> When you are buying first write name then costType with one space.\nlike 'spear moderate'.");
        System.out.println("--> Then write count of items you want to buy.");
        System.out.println("--> You can close market with  'Q' key.");
        scanner.nextLine();
    }
    public void warInformation() {
        game.Clear();
        //war informations
        System.out.println("War Mechanic: ");
        System.out.println("--> When you encountered enemy if you press space you can make battle.");
        System.out.println("--> You can use inventory before battle.");
        System.out.println("--> In war system you have 3 mana and 0 ultiCount at the beginning ");
        System.out.println("--> Mana increases every round and ultiCount increases every acts (defence or attack).");
        System.out.println("--> When you enemy attacks you,you can defence with using 2 mana.");
        System.out.println("--> There are four type attack.Low,Normal,High,VeryHigh");
        System.out.println("--> You can use Low attack with +2 mana, Normal attack with -2 mana,\n" +
                "High attack with -4 mana and VeryHigh attack with -6 mana and -2 ultiCount");
        System.out.println("--> Low attack hits normal attacks / 2 ");
        System.out.println("--> High attack hits normal attacks * 2 ");
        System.out.println("--> Low attack hits normal attacks * 4 ");
        System.out.println("--> You can calculate normal attack with this equation.\n" +
                "attack / (( 100 + enemy defence ) / 100);");
        System.out.println("--> Enemy hits you normal attack with this system and enemy can not defence.");
        scanner.nextLine();
    }

    public void movementInformation() {
        game.Clear();
        //movement infos
        System.out.println("Movement");
        System.out.println("--> You can move with ↑ ↓ → ←.");
        System.out.println("--> When you encountered enemy you must press space.");
        System.out.println("--> When you encountered NPC you directly talk with NPC.");
        System.out.println("--> You can not walk through walls but some time,\n" +
                "dungeons include hidden dungeon,so you can try to walk into the walls.");
        //changing movement keys
        scanner.nextLine();
    }
    public void inventoryInformation() {
        game.Clear();
        //movement infos
        System.out.println("Inventory");
        System.out.println("--> You can open inventory with E key.");
        System.out.println("--> Inventory includes 2 type items.1-Armor and Weapon.2-Potion.");
        System.out.println("--> You can wear change and take off the armor and weapon.\nAnd they will be remain your inventory.");
        System.out.println("--> When you wear Armor or Weapon increase your health or defence or attack.");
        System.out.println("--> Potions increase your health,defence or attack one time only.");
        System.out.println("--> You can use potions when you enter the war.");
        System.out.println("--> You can use armor or weapon one time at the same time ,\n" +
                "but you cant use potions any count you have same time. ");
        System.out.println("--> Weapons or AttackPotions increases your attack.");
        System.out.println("--> Armours or DefencePotions increases your defence.");
        System.out.println("--> HealthPotions increases your health.");
        //changing movement keys
        scanner.nextLine();
    }
}
