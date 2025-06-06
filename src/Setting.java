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
        //we create player  txt,
        //------------------------ MAZES TEXT ------------------------
        BufferedWriter bfWriterMazes = new BufferedWriter(new FileWriter("mazes.txt"));
        //sırayla her biri için
        int mazesSize = gamePlay.getMazeStack().size();
        LinkedStack tempMazeStack = gamePlay.getMazeStack();
        LinkedStack tempEnemyStack = gamePlay.getEnemyStack();
        LinkedStack tempNpcStack = gamePlay.getNpcStack();
        LinkedStack tempVertexStack = gamePlay.getVertexStack();
        LinkedStack tempBiomesStack = gamePlay.getBiomesStack();
        for (int a = 0; a < mazesSize; a++) {
            if (!gamePlay.getMazeStack().isEmpty()) {
                //---------------Write biomes-----------
                bfWriterMazes.write("\n");
                BiomeTypes biomeTypes = (BiomeTypes) gamePlay.getBiomesStack().pop();
                bfWriterMazes.write(biomeTypes.toString());
                bfWriterMazes.write("\n");
                Biomes biomes = (Biomes) gamePlay.getBiomesStack().pop();
                bfWriterMazes.write(biomes.toString());
                bfWriterMazes.write("\n");
                Dungeon dungeonMaze = (Dungeon) tempMazeStack.pop();
                int dungeonX = dungeonMaze.getDungeonX();
                bfWriterMazes.write(String.valueOf(dungeonX));
                bfWriterMazes.write("\n");
                int dungeonY = dungeonMaze.getDungeonY();
                bfWriterMazes.write(String.valueOf(dungeonY));
                bfWriterMazes.write("\n");
                for (int i = 0; i < dungeonMaze.getDungeonMatrix().length; i++) {
                    for (int j = 0; j < dungeonMaze.getDungeonMatrix()[i].length; j++) {
                        bfWriterMazes.write(dungeonMaze.getDungeonMatrix()[i][j] + ",");
                    }
                    bfWriterMazes.write("\n");
                }
                bfWriterMazes.write("\n");
            }
            if (!gamePlay.getEnemyStack().isEmpty()) {
                Enemy[] tempEnemies = (Enemy[]) tempEnemyStack.pop();
                int size = tempEnemies.length;
                bfWriterMazes.write(String.valueOf(size));
                bfWriterMazes.write("\n");
                for (int i = 0; i < tempEnemies.length; i++) {
                    Enemy tempEnemy = tempEnemies[i];
                    bfWriterMazes.write(tempEnemy.getHealth() + ",");
                    bfWriterMazes.write(tempEnemy.getAttack() + ",");
                    bfWriterMazes.write(tempEnemy.getDefence() + ",");
                    bfWriterMazes.write(tempEnemy.getType() + ",");
                    bfWriterMazes.write(tempEnemy.getX() + ",");
                    bfWriterMazes.write(tempEnemy.getY() + ",");
                    bfWriterMazes.write(tempEnemy.getComment() + ",");
                    bfWriterMazes.write(tempEnemy.getGender() + ",");
                    if (tempEnemy.isDead()) {
                        bfWriterMazes.write("true,");
                    } else {
                        bfWriterMazes.write("false,");
                    }
                    bfWriterMazes.write("\n");
                }
                bfWriterMazes.write("\n");
            }
            //-----------------------------Linked NPC--------------------
            if (!gamePlay.getNpcStack().isEmpty()) {
                NPC tempNpc = (NPC) tempNpcStack.pop();
                int npcSize = tempNpc.getNpcNames().size();
                bfWriterMazes.write(String.valueOf(npcSize));
                bfWriterMazes.write("\n");
                MultiLinkedList mll = tempNpc.getNpcNames();
                MultiLinkedList.CategoryNode tempCategoryNode = mll.getHead();
                while (tempCategoryNode != null) {
                    bfWriterMazes.write((String) tempCategoryNode.getData());
                    bfWriterMazes.write("\n");
                    MultiLinkedList.ItemNode tempItemNode = tempCategoryNode.getRight();
                    while (tempItemNode != null) {
                        NPC npc = (NPC) tempItemNode.getData();
                        String isQuestFinished = "false";
                        if (npc.isQuestFinished()) {
                            isQuestFinished = "true";
                        }
                        bfWriterMazes.write(isQuestFinished +"_");
                        bfWriterMazes.write(npc.getName() + "_");
                        bfWriterMazes.write(npc.getJob() + "_");
                        bfWriterMazes.write(npc.getMessage() + "_");
                        if (npc.getJob().equals("Lumberjack")) {
                            bfWriterMazes.write("forest_");
                        }
                        bfWriterMazes.write(npc.getGender() + "_");
                        Vertex tempVertex = npc.getNPCVertex();
                        bfWriterMazes.write(tempVertex.getVertexX() + "_" + tempVertex.getVertexY() + "_");
                        bfWriterMazes.write("gamePlay");
                        bfWriterMazes.write("\n");
                        tempItemNode = tempItemNode.getNext();
                    }
                    tempCategoryNode = tempCategoryNode.getDown();
                }
            }
            if (!gamePlay.getVertexStack().isEmpty()) {
                // first one player vertex
                // second one in final vertex
                // last one in start vertex
                bfWriterMazes.write("\n");
                Vertex tempVertex = (Vertex) tempVertexStack.pop();
                bfWriterMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterMazes.write("\n");
                tempVertex = (Vertex) tempVertexStack.pop();
                bfWriterMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterMazes.write("\n");
                tempVertex = (Vertex) tempVertexStack.pop();
                bfWriterMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterMazes.write("\n");
            }
        }
        bfWriterMazes.close();

        //----------------------------- MAZES NEXT TEXT ---------------
        BufferedWriter bfWriterNextMazes = new BufferedWriter(new FileWriter("mazesNext.txt"));
        int NextMazeSize = gamePlay.getNextMazeStack().size();
        LinkedStack tempMazeStack1 = gamePlay.getNextMazeStack();
        LinkedStack tempEnemyStack1 = gamePlay.getNextEnemyStack();
        LinkedStack tempNpcStack1 = gamePlay.getNextNpcStack();
        LinkedStack tempVertexStack1 = gamePlay.getNextVertexStack();
        for (int a = 0; a < NextMazeSize; a++) {
            if (!gamePlay.getNextMazeStack().isEmpty()) {
                //----------WRİTE BİOMES----------------
                bfWriterNextMazes.write("\n");
                BiomeTypes biomeTypes = (BiomeTypes) gamePlay.getNextBiomesStack().pop();
                bfWriterNextMazes.write(biomeTypes.toString());
                bfWriterNextMazes.write("\n");
                Biomes biomes = (Biomes) gamePlay.getNextBiomesStack().pop();
                bfWriterNextMazes.write(biomes.toString());
                bfWriterNextMazes.write("\n");
                Dungeon dungeonMaze = (Dungeon) tempMazeStack1.pop();
                int dungeonX = dungeonMaze.getDungeonX();
                bfWriterNextMazes.write(String.valueOf(dungeonX));
                bfWriterNextMazes.write("\n");
                int dungeonY = dungeonMaze.getDungeonY();
                bfWriterNextMazes.write(String.valueOf(dungeonY));
                bfWriterNextMazes.write("\n");
                for (int i = 0; i < dungeonMaze.getDungeonMatrix().length; i++) {
                    for (int j = 0; j < dungeonMaze.getDungeonMatrix()[i].length; j++) {
                        bfWriterNextMazes.write(dungeonMaze.getDungeonMatrix()[i][j] + ",");
                    }
                    bfWriterNextMazes.write("\n");
                }
                bfWriterNextMazes.write("\n");
            }
            if (!gamePlay.getNextEnemyStack().isEmpty()) {
                Enemy[] tempEnemies = (Enemy[]) tempEnemyStack1.pop();
                int size = tempEnemies.length;
                bfWriterNextMazes.write(String.valueOf(size));
                bfWriterNextMazes.write("\n");
                for (int i = 0; i < tempEnemies.length; i++) {
                    Enemy tempEnemy = tempEnemies[i];
                    bfWriterNextMazes.write(tempEnemy.getHealth() + ",");
                    bfWriterNextMazes.write(tempEnemy.getAttack() + ",");
                    bfWriterNextMazes.write(tempEnemy.getDefence() + ",");
                    bfWriterNextMazes.write(tempEnemy.getType() + ",");
                    bfWriterNextMazes.write(tempEnemy.getX() + ",");
                    bfWriterNextMazes.write(tempEnemy.getY() + ",");
                    bfWriterNextMazes.write(tempEnemy.getComment() + ",");
                    bfWriterNextMazes.write(tempEnemy.getGender() + ",");
                    if (tempEnemy.isDead()) {
                        bfWriterNextMazes.write("true,");
                    } else {
                        bfWriterNextMazes.write("false,");
                    }
                    bfWriterNextMazes.write("\n");
                }
                bfWriterNextMazes.write("\n");
            }
            if (!gamePlay.getNextNpcStack().isEmpty()) {
                NPC tempNpc = (NPC) tempNpcStack1.pop();
                int npcSize = tempNpc.getNpcNames().size();
                bfWriterNextMazes.write(String.valueOf(npcSize));
                bfWriterNextMazes.write("\n");
                MultiLinkedList mll = tempNpc.getNpcNames();
                MultiLinkedList.CategoryNode tempCategoryNode = mll.getHead();
                while (tempCategoryNode != null) {
                    bfWriterNextMazes.write((String) tempCategoryNode.getData());
                    bfWriterNextMazes.write("\n");
                    MultiLinkedList.ItemNode tempItemNode = tempCategoryNode.getRight();
                    while (tempItemNode != null) {
                        NPC npc = (NPC) tempItemNode.getData();
                        String isQuestFinished = "false";
                        if (npc.isQuestFinished()) {
                            isQuestFinished = "true";
                        }
                        bfWriterNextMazes.write(isQuestFinished + "_");
                        bfWriterNextMazes.write(npc.getName() + "_");
                        bfWriterNextMazes.write(npc.getJob() + "_");
                        bfWriterNextMazes.write(npc.getMessage() + "_");
                        if (npc.getJob().equals("Lumberjack")) {
                            bfWriterNextMazes.write("forest_");
                        }
                        bfWriterNextMazes.write(npc.getGender() + "_");
                        Vertex tempVertex = npc.getNPCVertex();
                        bfWriterNextMazes.write(tempVertex.getVertexX() + "_" + tempVertex.getVertexY() + "_");
                        bfWriterNextMazes.write("gamePlay");
                        bfWriterNextMazes.write("\n");
                        tempItemNode = tempItemNode.getNext();
                    }
                    tempCategoryNode = tempCategoryNode.getDown();
                }
            }
            if (!gamePlay.getNextVertexStack().isEmpty()) {
                // first one player vertex
                // second one in final vertex
                // last one in start vertex
                bfWriterNextMazes.write("\n");
                Vertex tempVertex = (Vertex) tempVertexStack1.pop();
                bfWriterNextMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterNextMazes.write("\n");
                tempVertex = (Vertex) tempVertexStack1.pop();
                bfWriterNextMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterNextMazes.write("\n");
                tempVertex = (Vertex) tempVertexStack1.pop();
                bfWriterNextMazes.write(tempVertex.getVertexX() + "," + tempVertex.getVertexY() + ",");
                bfWriterNextMazes.write("\n");
            }
        }
        bfWriterNextMazes.close();
        //----------------------------- ENEMY TEXT ---------------
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
            bfWriterE.write(enemies[i].getGender()+",");
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
        Biomes biomes = gamePlay.getBiomes();
        BiomeTypes biomeTypes = gamePlay.getBiomeTypes();
        bfWriter4.write(gamePlay.getDungeon().getDungeonX()+","+gamePlay.getDungeon().getDungeonY());
        bfWriter4.write("\n");
        Vertex vertex = gamePlay.getStartVertex();
        bfWriter4.write(biomes.toString());
        bfWriter4.write("\n");
        bfWriter4.write(biomeTypes.toString());
        bfWriter4.write("\n");
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
        //---------------------Player file load--------------------------
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

        //---------------------Dungeon Save file load--------------------------
        BufferedReader bufferedReader1 = new BufferedReader(new  FileReader("dungeonSave.txt"));
        String line1;
        String[] datas;
        line1 = bufferedReader1.readLine();
        datas = line1.split(",");
        int row = Integer.parseInt(datas[0]);
        int col = Integer.parseInt(datas[1]);
        char[][] dungeonMatrix = new char[row][col];
        line1 = bufferedReader1.readLine();
        if (line1.equals("HOT")) {
            gamePlay.getDungeon().setBiome(Biomes.HOT);
            gamePlay.setBiomes(Biomes.HOT);
        } else if (line1.equals("COLD")) {
            gamePlay.getDungeon().setBiome(Biomes.COLD);
            gamePlay.setBiomes(Biomes.COLD);
        } else {
            gamePlay.getDungeon().setBiome(Biomes.WARM);
            gamePlay.setBiomes(Biomes.WARM);
        }
        line1 = bufferedReader1.readLine();
        if (line1.equals("ICE_SPIKES")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.ICE_SPIKES);
            gamePlay.setBiomeTypes(BiomeTypes.ICE_SPIKES);
        } else if (line1.equals("DESERT")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.DESERT);
            gamePlay.setBiomeTypes(BiomeTypes.DESERT);
        } else if (line1.equals("FOREST")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.FOREST);
            gamePlay.setBiomeTypes(BiomeTypes.FOREST);
        } else if (line1.equals("PLAINS")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.PLAINS);
            gamePlay.setBiomeTypes(BiomeTypes.PLAINS);
        } else if (line1.equals("SWAMP")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.SWAMP);
            gamePlay.setBiomeTypes(BiomeTypes.SWAMP);
        } else if (line1.equals("POLAR")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.POLAR);
            gamePlay.setBiomeTypes(BiomeTypes.POLAR);
        } else if (line1.equals("VOLCANIC")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.VOLCANIC);
            gamePlay.setBiomeTypes(BiomeTypes.VOLCANIC);
        } else if (line1.equals("SAVANNA")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.SAVANNA);
            gamePlay.setBiomeTypes(BiomeTypes.SAVANNA);
        } else if (line1.equals("TUNDRA")) {
            gamePlay.getDungeon().setBiomeType(BiomeTypes.TUNDRA);
            gamePlay.setBiomeTypes(BiomeTypes.TUNDRA);
        }
        int a = 0;
        int b = 0;
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
        //---------------------Market file load--------------------------
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
        //---------------------Inventory file load--------------------------
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

        //---------------------Enemy file load--------------------------
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
            gamePlay.getEnemies()[a].setGender(datas4[7]);
            if(datas4[8].equals("true")){
                gamePlay.getEnemies()[a].setDead(true);
            }else {
                gamePlay.getEnemies()[a].setDead(false);
            }
            a++;
        }
        bufferedReader4.close();

        //---------------------NPC file load--------------------------
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
        //---------------------MAZES file load--------------------------
        BufferedReader mazeReader = new BufferedReader(new FileReader("mazes.txt"));
        String lineMaze = "";
        int counterSpaces = 0;
        while ((lineMaze = mazeReader.readLine()) != null){
            if (lineMaze.trim().isEmpty()) {
                if (counterSpaces == 0) {
                    //-------------- maze oku ------------------
                    lineMaze = mazeReader.readLine();
                    Biomes biome = null;
                    BiomeTypes biomeType = null;
                    if (lineMaze.equals("ICE_SPIKES")) {
                        biomeType = BiomeTypes.ICE_SPIKES;
                    } else if (lineMaze.equals("DESERT")) {
                        biomeType = BiomeTypes.DESERT;
                    } else if (lineMaze.equals("FOREST")) {
                        biomeType = BiomeTypes.FOREST;
                    } else if (lineMaze.equals("PLAINS")) {
                        biomeType = BiomeTypes.PLAINS;
                    } else if (lineMaze.equals("SWAMP")) {
                        biomeType = BiomeTypes.SWAMP;
                    } else if (lineMaze.equals("POLAR")) {
                        biomeType = BiomeTypes.POLAR;
                    } else if (lineMaze.equals("VOLCANIC")) {
                        biomeType = BiomeTypes.VOLCANIC;
                    } else if (lineMaze.equals("SAVANNA")) {
                        biomeType = BiomeTypes.SAVANNA;
                    } else if (lineMaze.equals("TUNDRA")) {
                        biomeType = BiomeTypes.TUNDRA;
                    }
                    lineMaze = mazeReader.readLine();
                    if (lineMaze.equals("HOT")) {
                        biome = Biomes.HOT;
                    } else if (lineMaze.equals("COLD")) {
                        biome = Biomes.COLD;
                    } else {
                        biome = Biomes.WARM;
                    }
                    gamePlay.getBiomesStack().push(biome);
                    gamePlay.getBiomesStack().push(biomeType);
                    lineMaze = mazeReader.readLine();
                    int mazeX = Integer.parseInt(lineMaze);
                    lineMaze = mazeReader.readLine();
                    int mazeY = Integer.parseInt(lineMaze);
                    Dungeon dungeon = new Dungeon(mazeX, mazeY);
                    for (int i = 0; i < mazeX; i++) {
                        lineMaze = mazeReader.readLine();
                        String[] dataDungeon = lineMaze.split(",");
                        for (int j = 0; j < mazeY; j++) {
                            dungeon.getDungeonMatrix()[i][j] = dataDungeon[j].charAt(0);
                        }
                    }
                    gamePlay.getMazeStack().push(dungeon);
                } else if (counterSpaces == 1) {
                    //enemy oku
                    lineMaze = mazeReader.readLine();
                    int sizeEnemyStack = Integer.parseInt(lineMaze);
                    Enemy[] enemies = new Enemy[sizeEnemyStack];
                    for (int enemyCount = 0; enemyCount < sizeEnemyStack; enemyCount++){
                        lineMaze = mazeReader.readLine();
                        String[] dataEnemy = lineMaze.split(",");
                        boolean isDead = false;
                        if (dataEnemy[8].equalsIgnoreCase("true")) {
                            isDead = true;
                        }
                        Enemy enemy = new Enemy(Integer.parseInt(dataEnemy[0]),Integer.parseInt(dataEnemy[1]),Integer.parseInt(dataEnemy[2]),dataEnemy[3],Integer.parseInt(dataEnemy[4]),Integer.parseInt(dataEnemy[5]),dataEnemy[6],isDead,dataEnemy[7]);
                        enemies[enemyCount] = enemy;
                    }
                    gamePlay.getEnemyStack().push(enemies);
                } else if (counterSpaces == 2) {
                    //--------------npc oku-------------- // buradayım
                    lineMaze = mazeReader.readLine();
                    int size = Integer.parseInt(lineMaze);
                    lineMaze = mazeReader.readLine(); // bu Lumberjacks türü
                    NPC tempNPC = new NPC(false,"","","",null,"",null,null);

                    MultiLinkedList multiLinkedListNPC = new MultiLinkedList();
                    String categoryString = lineMaze;
                    multiLinkedListNPC.addCategory(categoryString);
                    for (int i = 0; i < size; i++) {
                        lineMaze = mazeReader.readLine();
                        String[] npcData = lineMaze.split("_");
                        boolean isQuestFinished = false;
                        if (npcData[0].equals("true")) {
                            isQuestFinished = true;
                        }
                        Vertex npcVertex = new Vertex(Integer.parseInt(npcData[6]),Integer.parseInt(npcData[7]));
                        Forest forest = null;
                        if (npcData[4].equals("forest")) {
                            forest = new Forest();
                        }
                        NPC npc = new NPC(isQuestFinished, npcData[1], npcData[2], npcData[3], forest, npcData[5],npcVertex,gamePlay);
                        multiLinkedListNPC.addItem(categoryString,npc);
                        //npc.getNpcNames()
                    }
                    tempNPC.setNpcNames(multiLinkedListNPC);
                    gamePlay.getNpcStack().push(tempNPC);
                } else if (counterSpaces == 3) {
                    //--------------vertex oku---------------
                    lineMaze = mazeReader.readLine();
                    String[] dataVertex = lineMaze.split(",");
                    Vertex PlayerVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));

                    lineMaze = mazeReader.readLine();
                    dataVertex = lineMaze.split(",");
                    Vertex FinalVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));

                    lineMaze = mazeReader.readLine();
                    dataVertex = lineMaze.split(",");
                    Vertex StartVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));
                    //lineMaze = lineMaze.replaceAll(" ", "");

                    gamePlay.getVertexStack().push(PlayerVertex);
                    gamePlay.getVertexStack().push(FinalVertex);
                    gamePlay.getVertexStack().push(StartVertex);


                }
                counterSpaces++;
                if (counterSpaces == 4) { counterSpaces = 0;}
            }
        }
        System.out.println(counterSpaces);
        gamePlay.setMazeStack(reverseStack(gamePlay.getMazeStack()));
        gamePlay.setEnemyStack(reverseStack(gamePlay.getEnemyStack()));
        gamePlay.setNpcStack(reverseStack(gamePlay.getNpcStack()));
        gamePlay.setVertexStack(reverseStack(gamePlay.getVertexStack()));
        mazeReader.close();
        //---------------------MAZES NEXT file load--------------------------
        BufferedReader mazeNextReader = new BufferedReader(new FileReader("mazesNext.txt"));
        String lineMaze1 = "";
        int counterSpaces1 = 0;
        while ((lineMaze1 = mazeNextReader.readLine()) != null){
            if (lineMaze1.trim().isEmpty()) {
                if (counterSpaces1 == 0) {
                    //-------------- maze oku ------------------
                    Biomes biome = null;
                    BiomeTypes biomeType = null;
                    lineMaze1 = mazeNextReader.readLine();
                    if (lineMaze1.equals("ICE_SPIKES")) {
                        biomeType = BiomeTypes.ICE_SPIKES;
                    } else if (lineMaze1.equals("DESERT")) {
                        biomeType = BiomeTypes.DESERT;
                    } else if (lineMaze1.equals("FOREST")) {
                        biomeType = BiomeTypes.FOREST;
                    } else if (lineMaze1.equals("PLAINS")) {
                        biomeType = BiomeTypes.PLAINS;
                    } else if (lineMaze1.equals("SWAMP")) {
                        biomeType = BiomeTypes.SWAMP;
                    } else if (lineMaze1.equals("POLAR")) {
                        biomeType = BiomeTypes.POLAR;
                    } else if (lineMaze1.equals("VOLCANIC")) {
                        biomeType = BiomeTypes.VOLCANIC;
                    } else if (lineMaze1.equals("SAVANNA")) {
                        biomeType = BiomeTypes.SAVANNA;
                    } else if (lineMaze1.equals("TUNDRA")) {
                        biomeType = BiomeTypes.TUNDRA;
                    }
                    lineMaze1 = mazeNextReader.readLine();
                    if (lineMaze1.equals("HOT")) {
                        biome = Biomes.HOT;
                    } else if (lineMaze1.equals("COLD")) {
                        biome = Biomes.COLD;
                    } else {
                        biome = Biomes.WARM;
                    }
                    gamePlay.getNextBiomesStack().push(biome);
                    gamePlay.getNextBiomesStack().push(biomeType);
                    lineMaze1 = mazeNextReader.readLine();
                    int mazeX = Integer.parseInt(lineMaze1);
                    lineMaze1 = mazeNextReader.readLine();
                    int mazeY = Integer.parseInt(lineMaze1);
                    Dungeon dungeon = new Dungeon(mazeX, mazeY);
                    for (int i = 0; i < mazeX; i++) {
                        lineMaze1 = mazeNextReader.readLine();
                        String[] dataDungeon = lineMaze1.split(",");
                        for (int j = 0; j < mazeY; j++) {
                            dungeon.getDungeonMatrix()[i][j] = dataDungeon[j].charAt(0);
                        }
                    }
                    gamePlay.getNextMazeStack().push(dungeon);
                } else if (counterSpaces1 == 1) {
                    //----------enemy oku------------
                    lineMaze1 = mazeNextReader.readLine();
                    int sizeEnemyStack = Integer.parseInt(lineMaze1);
                    Enemy[] enemies = new Enemy[sizeEnemyStack];
                    for (int enemyCount = 0; enemyCount < sizeEnemyStack; enemyCount++){
                        lineMaze1 = mazeNextReader.readLine();
                        String[] dataEnemy = lineMaze1.split(",");
                        boolean isDead = false;
                        if (dataEnemy[8].equalsIgnoreCase("true")) {
                            isDead = true;
                        }
                        Enemy enemy = new Enemy(Integer.parseInt(dataEnemy[0]),Integer.parseInt(dataEnemy[1]),Integer.parseInt(dataEnemy[2]),dataEnemy[3],Integer.parseInt(dataEnemy[4]),Integer.parseInt(dataEnemy[5]),dataEnemy[6],isDead,dataEnemy[7]);
                        enemies[enemyCount] = enemy;
                    }
                    gamePlay.getNextEnemyStack().push(enemies);
                } else if (counterSpaces1 == 2) {
                    //--------------npc oku-------------- // buradayım
                    lineMaze1 = mazeNextReader.readLine();
                    int size = Integer.parseInt(lineMaze1);
                    lineMaze1 = mazeNextReader.readLine(); // bu Lumberjacks türü
                    NPC tempNPC = new NPC(false,"","","",null,"",null,null);

                    MultiLinkedList multiLinkedListNPC = new MultiLinkedList();
                    String categoryString = lineMaze1;
                    multiLinkedListNPC.addCategory(categoryString);
                    for (int i = 0; i < size; i++) {
                        lineMaze1 = mazeNextReader.readLine();
                        String[] npcData = lineMaze1.split("_");
                        boolean isQuestFinished = false;
                        if (npcData[0].equals("true")) {
                            isQuestFinished = true;
                        }
                        Vertex npcVertex = new Vertex(Integer.parseInt(npcData[6]),Integer.parseInt(npcData[7]));
                        Forest forest = null;
                        if (npcData[4].equals("forest")) {
                            forest = new Forest();
                        }
                        NPC npc = new NPC(isQuestFinished, npcData[1], npcData[2], npcData[3], forest, npcData[5],npcVertex,gamePlay);
                        multiLinkedListNPC.addItem(categoryString,npc);
                        //npc.getNpcNames()
                    }
                   tempNPC.setNpcNames(multiLinkedListNPC);
                   gamePlay.getNextNpcStack().push(tempNPC);
                } else if (counterSpaces1 == 3) {
                    //--------------vertex oku---------------
                    lineMaze1 = mazeNextReader.readLine();
                    String[] dataVertex = lineMaze1.split(",");
                    Vertex PlayerVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));

                    lineMaze1 = mazeNextReader.readLine();
                    dataVertex = lineMaze1.split(",");
                    Vertex FinalVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));

                    lineMaze1 = mazeNextReader.readLine();
                    dataVertex = lineMaze1.split(",");
                    Vertex StartVertex = new Vertex(Integer.parseInt(dataVertex[0]),Integer.parseInt(dataVertex[1]));
                    //lineMaze = lineMaze.replaceAll(" ", "");
                    /*
                    gamePlay.getNextVertexStack().push(StartVertex);
                    gamePlay.getNextVertexStack().push(FinalVertex);
                    gamePlay.getNextVertexStack().push(PlayerVertex);

                     */
                    gamePlay.getNextVertexStack().push(PlayerVertex);
                    gamePlay.getNextVertexStack().push(FinalVertex);
                    gamePlay.getNextVertexStack().push(StartVertex);
                }
                counterSpaces1++;
                if (counterSpaces1 == 4) { counterSpaces1 = 0;}
            }
        }
        System.out.println(counterSpaces1);
        gamePlay.setNextMazeStack(reverseStack(gamePlay.getNextMazeStack()));
        gamePlay.setNextEnemyStack(reverseStack(gamePlay.getNextEnemyStack()));
        gamePlay.setNextNpcStack(reverseStack(gamePlay.getNextNpcStack()));
        gamePlay.setNextVertexStack(reverseStack(gamePlay.getNextVertexStack()));
        mazeNextReader.close();
    }
    public LinkedStack reverseStack(LinkedStack stackToBeReversed) {
        LinkedStack reversedStack = new LinkedStack();
        while (!stackToBeReversed.isEmpty()) {
            reversedStack.push(stackToBeReversed.pop());
        }
        return reversedStack;
    }

}
