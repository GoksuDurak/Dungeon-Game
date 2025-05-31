import java.io.*;

public class Setting {
    private GamePlay gamePlay;
    private Product[] products;
    private Item[] items;
    private Dungeon dungeon;
    private int enemyCount = 0;
    public Setting(GamePlay gamePlay,Product[] products) {
        this.gamePlay = gamePlay;
        this.products = products;
        items = gamePlay.getPlayer().getInventory().getItems();
        this.dungeon = gamePlay.getDungeon();
    }
    public void saveFile(Dungeon dungeon) throws IOException {
        //we create player  txt
        BufferedWriter bfWriterE = new BufferedWriter(new FileWriter("Enemy.txt"));
        Enemy[] enemies = gamePlay.getEnemies();
        for (int i = 0; i < enemies.length; i++) {
            bfWriterE.write(enemies[i].getHealth()+",");
            bfWriterE.write(enemies[i].getAttack()+",");
            bfWriterE.write(enemies[i].getDefence()+",");
            bfWriterE.write(enemies[i].getType()+",");
            bfWriterE.write(enemies[i].getX()+",");
            bfWriterE.write(enemies[i].getY()+",");
            bfWriterE.write(enemies[i].getComment()+",");
            if(enemies[i].isDead()){
                bfWriterE.write("true");
            }else{
                bfWriterE.write("false");
            }
            bfWriterE.write("\n");
            enemyCount++;
        }
        bfWriterE.close();

        BufferedWriter bfWriterN = new BufferedWriter(new FileWriter("Npc.txt"));
        MultiLinkedList mll = gamePlay.getNpcs().getNpcNames();
        MultiLinkedList.CategoryNode tempCategoryNode = mll.getHead();
        while (tempCategoryNode != null) {
            MultiLinkedList.ItemNode itemNode = tempCategoryNode.getRight();
            bfWriterN.write((String) tempCategoryNode.getData());
            bfWriterN.write("\n");
            while (itemNode != null) {
                NPC npc = (NPC) itemNode.getData();
                if (npc.isQuestFinished()) {
                    bfWriterN.write("true _");
                } else {
                    bfWriterN.write("false _");
                }
                bfWriterN.write(npc.getName() + "_" );
                bfWriterN.write(npc.getJob() + "_" );
                bfWriterN.write(npc.getMessage() + "_");
                if (npc.getJob().equals("Lumberjack")) {
                    bfWriterN.write("forest_");
                }
                bfWriterN.write(npc.getGender() + "_" );
                Vertex vertex = npc.getNPCVertex();
                bfWriterN.write(vertex.getVertexX() + "_" + vertex.getVertexY() + "_" );
                bfWriterN.write("gamePlay");
                bfWriterN.write("\n");
                itemNode = itemNode.getNext();
            }
            tempCategoryNode = tempCategoryNode.getDown();
        }
        bfWriterN.close();
        BufferedWriter bfWriter = new BufferedWriter(new FileWriter("Player.txt"));
        bfWriter.write(gamePlay.getPlayer().getName()+",");
        bfWriter.write(gamePlay.getPlayer().getScore()+",");
        bfWriter.write(gamePlay.getPlayer().getLevel()+",");
        bfWriter.write(gamePlay.getPlayer().getHp()+",");
        bfWriter.write(gamePlay.getPlayer().getStamina()+",");
        bfWriter.write(gamePlay.getPlayer().getMoney()+",");
        bfWriter.write(gamePlay.getPlayer().getXp()+",");
        bfWriter.write(gamePlay.getPlayer().getAttack()+",");
        bfWriter.write(gamePlay.getPlayer().getDefence()+",");
        bfWriter.write(gamePlay.getPlayer().getMana()+",");
        bfWriter.write(gamePlay.getX()+",");
        bfWriter.write(gamePlay.getY()+",");
        bfWriter.close();
        // we market inventory txt
        BufferedWriter bfWriter2 = new BufferedWriter(new FileWriter("market.txt"));
        for (int i = 0; i < products.length; i++) {
            if(products[i] != null) {
                bfWriter2.write(products[i].getName() + ",");
                bfWriter2.write(products[i].getPrice() + ",");
                bfWriter2.write(products[i].getQuantity() + ",");
                bfWriter2.write(products[i].getCostType() + ",");
                bfWriter2.write(products[i].getId() + ",");
                bfWriter2.write(products[i].getEffect() + ",");
                bfWriter2.write(products[i].getType());
                bfWriter2.write("\n");
            }
        }
        bfWriter2.close();
        // we create inventory txt
        BufferedWriter bfWriter3 = new BufferedWriter(new FileWriter("inventory.txt"));

        Item currentItem = null;
            currentItem = gamePlay.getCurrentHelmet();
        if(gamePlay.getCurrentHelmet() != null) {
            bfWriter3.write("h"+",");
            bfWriter3.write(currentItem.getItemName() + ",");
            bfWriter3.write(1 + ",");
            bfWriter3.write(currentItem.getItemID() + ",");
            if (currentItem.getData() instanceof Armor) {
                bfWriter3.write("Armor" + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getDefense() + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getArmorType() + ",");
            }
            bfWriter3.write("\n");
        }
        if(gamePlay.getCurrentChestPLate() != null) {
            bfWriter3.write("c"+",");
            currentItem = gamePlay.getCurrentChestPLate();
            bfWriter3.write(currentItem.getItemName() + ",");
            bfWriter3.write(1 + ",");
            bfWriter3.write(currentItem.getItemID() + ",");
            if (currentItem.getData() instanceof Armor) {
                bfWriter3.write("Armor" + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getDefense() + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getArmorType() + ",");
            }
            bfWriter3.write("\n");
        }
        if(gamePlay.getCurrentBoots() != null) {
            bfWriter3.write("b"+",");
            currentItem = gamePlay.getCurrentBoots();
            bfWriter3.write(currentItem.getItemName() + ",");
            bfWriter3.write(1 + ",");
            bfWriter3.write(currentItem.getItemID() + ",");
            if (currentItem.getData() instanceof Armor) {
                bfWriter3.write("Armor" + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getDefense() + ",");
                bfWriter3.write(((Armor) currentItem.getData()).getArmorType() + ",");
            }
            bfWriter3.write("\n");
        }
        if(gamePlay.getCurrentWeapon() != null) {
            currentItem = gamePlay.getCurrentWeapon();
            bfWriter3.write("w"+",");
            bfWriter3.write(currentItem.getItemName()+",");
            bfWriter3.write(1+",");
            bfWriter3.write(currentItem.getItemID()+",");
            if(currentItem.getData() instanceof  Weapon) {
                bfWriter3.write("Weapon" + ",");
                bfWriter3.write(((Weapon) currentItem.getData()).getType() + ",");
                bfWriter3.write(((Weapon) currentItem.getData()).getDamage() + ",");
            }
            bfWriter3.write("\n");
        }

        for (int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                bfWriter3.write(items[i].getItemName() + ",");
                bfWriter3.write(items[i].getQuantity() + ",");
                bfWriter3.write(items[i].getItemID() + ",");
                if(items[i].getData() instanceof Potion){
                    bfWriter3.write("Potion" + ",");
                    bfWriter3.write(((Potion) items[i].getData()).getName() + ",");
                    bfWriter3.write(((Potion) items[i].getData()).getDescription() + ",");
                    bfWriter3.write(((Potion) items[i].getData()).getEffect() + ",");
                    bfWriter3.write(((Potion) items[i].getData()).getColor() + ",");
                }else if(items[i].getData() instanceof Weapon){
                    bfWriter3.write("Weapon" + ",");
                    bfWriter3.write(((Weapon) items[i].getData()).getType() + ",");
                    bfWriter3.write(((Weapon) items[i].getData()).getDamage() + ",");
                }else if(items[i].getData() instanceof Armor){
                    bfWriter3.write("Armor" + ",");
                    bfWriter3.write(((Armor) items[i].getData()).getDefense() + ",");
                    bfWriter3.write(((Armor) items[i].getData()).getArmorType() + ",");
                }
                bfWriter3.write("\n");
            }
        }

        bfWriter3.close();
        BufferedWriter bfWriter4 = new BufferedWriter(new FileWriter("dungeonSave.txt"));
        Vertex vertex = gamePlay.getStartVertex();
        bfWriter4.write(vertex.getVertexX()+","+vertex.getVertexY());
        bfWriter4.write("\n");
        vertex = gamePlay.getFinalVertex();
        bfWriter4.write(vertex.getVertexX()+","+vertex.getVertexY());
        bfWriter4.write("\n");
        char[][] dungeonMatrix = dungeon.getDungeonMatrix();
        for (int i = 0; i < dungeonMatrix.length; i++) {
            for (int j = 0; j < dungeonMatrix[i].length; j++) {
                bfWriter4.write(dungeonMatrix[i][j] +",");
            }
            bfWriter4.write("\n");
        }
        bfWriter4.close();

    }
    public void loadFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new  FileReader("Player.txt"));
        String line = bufferedReader.readLine();
        String[] playerInfo = line.split(",");
        for (int i = 0; i < playerInfo.length; i++) {
            System.out.println(playerInfo[i]);
        }
        gamePlay.getPlayer().setName(playerInfo[0]);
        gamePlay.getPlayer().setScore(Integer.parseInt(playerInfo[1]));
        gamePlay.getPlayer().setLevel(Integer.parseInt(playerInfo[2]));
        gamePlay.getPlayer().setHp(Integer.parseInt(playerInfo[3]));
        gamePlay.getPlayer().setStamina(Integer.parseInt(playerInfo[4]));
        gamePlay.getPlayer().setMoney(Integer.parseInt(playerInfo[5]));
        gamePlay.getPlayer().setXp(Integer.parseInt(playerInfo[6]));
        gamePlay.getPlayer().setAttack(Integer.parseInt(playerInfo[7]));
        gamePlay.getPlayer().setDefence(Integer.parseInt(playerInfo[8]));
        gamePlay.getPlayer().setMana(Integer.parseInt(playerInfo[9]));
        gamePlay.setX(Integer.parseInt(playerInfo[10]));
        gamePlay.setY(Integer.parseInt(playerInfo[11]));
        bufferedReader.close();
        BufferedReader bufferedReader1Length = new BufferedReader(new  FileReader("dungeonSave.txt"));
        String lineRead1;
        String[] datasRead;
        int row = 0;
        int col = 0;
        while ((lineRead1 = bufferedReader1Length.readLine()) != null) {
            datasRead = lineRead1.split(",");
            col = datasRead.length;
            row++;
        }
        bufferedReader1Length.close();
        BufferedReader bufferedReader1 = new BufferedReader(new  FileReader("dungeonSave.txt"));
        String line1;
        String[] datas;
        char[][] dungeonMatrix = new char[row][col];
        int a = 0;
        int b = 0;
        row -= 2;// we added 2 row
        String lineVertex = bufferedReader1.readLine();
        String[] lineVertex1 = lineVertex.split(",");
        Vertex startVertex = new Vertex(0,0);
        startVertex.setVertexX(Integer.parseInt(lineVertex1[0]));
        startVertex.setVertexY(Integer.parseInt(lineVertex1[1]));
        gamePlay.setStartVertex(startVertex);
        lineVertex = bufferedReader1.readLine();
        Vertex finalVertex = new Vertex(0,0);
        String[] lineVertex2 = lineVertex.split(",");
        finalVertex.setVertexX(Integer.parseInt(lineVertex2[0]));
        finalVertex.setVertexY(Integer.parseInt(lineVertex2[1]));
        gamePlay.setFinalVertex(finalVertex);
        while ((line1 = bufferedReader1.readLine()) != null) {
            datas = line1.split(",");
            b = 0;
            for (int i = 0; i < datas.length ; i++) {
                dungeonMatrix[a][b] = datas[i].charAt(0);
                b++;
            }
            a++;
        }
        gamePlay.getDungeon().setDungeonX(row);
        gamePlay.getDungeon().setDungeonY(col);
        gamePlay.getDungeon().setDungeonMatrix(dungeonMatrix);
        bufferedReader1.close();
        BufferedReader bufferedReader2 = new BufferedReader(new  FileReader("market.txt"));
        String line2;
        String[] datas2;
        a = 0;
        while ((line2 = bufferedReader2.readLine()) != null) {
            datas2 = line2.split(",");
            products[a].setName(datas2[0]);
            products[a].setPrice(Integer.parseInt(datas2[1]));
            products[a].setQuantity(Integer.parseInt(datas2[2]));
            products[a].setCostType(datas2[3]);
            products[a].setId(Integer.parseInt(datas2[4]));
            products[a].setEffect(Integer.parseInt(datas2[5]));
            products[a].setType(datas2[6]);
            a++;
        }
        bufferedReader2.close();
        BufferedReader bufferedReader3 = new BufferedReader(new  FileReader("inventory.txt"));
        String line3;
        String[] datas3;
        Item[] items = new Item[200];
        a = 0;
        while ((line3 = bufferedReader3.readLine()) != null) {
            datas3 = line3.split(",");
            if(datas3[0].equalsIgnoreCase("h")){
                // if helmet is wear
                Armor armor = new Armor(Integer.parseInt(datas3[5]),datas3[6]);
                Item item = new Item(datas3[1],Integer.parseInt(datas3[2]),Integer.parseInt(datas3[3]),armor);
                gamePlay.setCurrentHelmet(item);
                gamePlay.getCurrentHelmet().setQuantity(0); // kullanılmış olması lazım
                GamePlay.helmetUsed = true;
            }else if(datas3[0].equalsIgnoreCase("c")){
                Armor armor = new Armor(Integer.parseInt(datas3[5]),datas3[6]);
                Item item = new Item(datas3[1],Integer.parseInt(datas3[2]),Integer.parseInt(datas3[3]),armor);
                gamePlay.setCurrentChestPLate(item);
                gamePlay.getCurrentChestPLate().setQuantity(0); // kullanılmış olması lazım
                GamePlay.chestPlateUsed = true;
            }else if(datas3[0].equalsIgnoreCase("b")){
                Armor armor = new Armor(Integer.parseInt(datas3[5]),datas3[6]);
                Item item = new Item(datas3[1],Integer.parseInt(datas3[2]),Integer.parseInt(datas3[3]),armor);
                gamePlay.setCurrentBoots(item);
                gamePlay.getCurrentBoots().setQuantity(0); // kullanılmış olması lazım
                GamePlay.bootsUsed = true;
            }else if(datas3[0].equalsIgnoreCase("w")){
                Weapon weapon = new Weapon(datas3[5],Integer.parseInt(datas3[6]));
                Item item = new Item(datas3[1],Integer.parseInt(datas3[2]),Integer.parseInt(datas3[3]),weapon);
                gamePlay.setCurrentWeapon(item);
                gamePlay.getCurrentWeapon().setQuantity(0); // kullanılmış olması lazım
                GamePlay.weaponUsed = true;
            }else{
                if(datas3[3].equalsIgnoreCase("Armor")){
                    Armor armor = new Armor(Integer.parseInt(datas3[4]),datas3[5]);
                    Item item = new Item(datas3[0],Integer.parseInt(datas3[1]),Integer.parseInt(datas3[2]),armor);
                    items[a] = item;
                } else if (datas3[3].equalsIgnoreCase("Potion")) {
                    Potion potion = new Potion(datas3[4],datas3[5],Integer.parseInt(datas3[6]),datas3[7]);
                    Item item = new Item(datas3[0],Integer.parseInt(datas3[1]),Integer.parseInt(datas3[2]),potion);
                    items[a] = item;
                } else if (datas3[3].equalsIgnoreCase("Weapon")) {
                    Weapon weapon = new Weapon(datas3[4],Integer.parseInt(datas3[5]));
                    Item item = new Item(datas3[0],Integer.parseInt(datas3[1]),Integer.parseInt(datas3[2]),weapon);
                    items[a] = item;
                }
                a++;
            }
        }
        gamePlay.getPlayer().getInventory().resetInventory();

        for (int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                gamePlay.getPlayer().getInventory().addItem(items[i]);
            }
        }


        bufferedReader3.close();
        BufferedReader bufferedReader4 = new BufferedReader(new  FileReader("Enemy.txt"));
        // this part changed 4 row
        GamePlay.enemyX = 0;
        GamePlay.enemyY = 0;
        gamePlay.setEnemyCoordinateVertex(null);
        GamePlay.enemyCoordinateQueue.dequeue();
        GamePlay.enemyCoordinateQueue.dequeue();
        String line4;
        String[] datas4;
        //Enemy[] enemies = new Enemy[enemyCount];
        a = 0;
        while ((line4 = bufferedReader4.readLine()) != null) {
            datas4 = line4.split(",");
            gamePlay.getEnemies()[a].setHealth(Integer.parseInt(datas4[0]));
            gamePlay.getEnemies()[a].setAttack(Integer.parseInt(datas4[1]));
            gamePlay.getEnemies()[a].setDefence(Integer.parseInt(datas4[2]));
            gamePlay.getEnemies()[a].setType(datas4[3]);
            gamePlay.getEnemies()[a].setX(Integer.parseInt(datas4[4]));
            gamePlay.getEnemies()[a].setY(Integer.parseInt(datas4[5]));
            gamePlay.getEnemies()[a].setComment(datas4[6]);
            if(datas4[6].equals("true")){
                gamePlay.getEnemies()[a].setDead(true);
            }else {
                gamePlay.getEnemies()[a].setDead(false);
            }
            a++;
        }
        bufferedReader4.close();

        BufferedReader bufferedReader5 = new BufferedReader(new FileReader("Npc.txt"));
        GamePlay.NPCX = 0;
        GamePlay.NPCY = 0;
        gamePlay.setNPCCoordinateVertex(null);
        while (!GamePlay.NPCCoordinateQueue.isEmpty()) {
            GamePlay.NPCCoordinateQueue.dequeue();
            GamePlay.NPCCoordinateQueue.dequeue();
        }
        String line5;
        String[] datas5;
        a = 0;
        MultiLinkedList.CategoryNode categoryNode = gamePlay.getNpcs().getNpcNames().getHead();
        MultiLinkedList.ItemNode itemNode = null;
        while ((line5 = bufferedReader5.readLine()) != null) {
            datas5 = line5.split("_");
            if (a % 21 == 0) {
                categoryNode.setData(datas5[0]);
                itemNode = categoryNode.getRight();
            } else {
                boolean flag;
                if (datas5[0].equals("true")) {
                    flag = true;
                } else {
                    flag = false;
                }
                Object data = null;
                NPC npc = new NPC(false,"","","",null,"",null,null);
                if (datas5[4].equals("forest")) {
                    Forest forest = new Forest();
                    Vertex vertex = new Vertex(Integer.parseInt(datas5[6]),Integer.parseInt(datas5[7]));
                    npc.setQuestFinished(flag);
                    npc.setName(datas5[1]);
                    npc.setJob(datas5[2]);
                    npc.setMessage(datas5[3]);
                    npc.setQuest(forest);
                    npc.setGender(datas5[5]);
                    npc.setNPCVertex(vertex);
                    npc.setGamePlay(gamePlay);
                }
                itemNode.setData(npc);
                itemNode = itemNode.getNext();
            }
            a++;
        }
        bufferedReader5.close();
    }

}
