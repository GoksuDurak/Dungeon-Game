import java.util.Random;
import java.util.Scanner;

public class NPC {
    private String name;
    private String job;
    private String message;
    private String gender;
    private Object quest;
    private MultiLinkedList npcs;
    private MultiLinkedList npcNames;
    private Random rand = new Random();
    private GamePlay gamePlay;
    private int x ;
    private int y ;
    private Vertex NPCVertex;
    private boolean isQuestFinished;
    NPC(boolean isQuestFinished, String name, String job, String message, Object quest, String gender, Vertex NPCVertex, GamePlay gamePlay) {
        this.name = name;
        this.job = job;
        this.message = message;
        this.quest = quest;
        this.gender = gender;
        npcNames = new MultiLinkedList();
        this.NPCVertex = NPCVertex;
        this.gamePlay = gamePlay;
        this.isQuestFinished = isQuestFinished;
    }

    public boolean isQuestFinished() {
        return isQuestFinished;
    }

    public void setQuestFinished(boolean questFinished) {
        isQuestFinished = questFinished;
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public void setGamePlay(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    public void setJob(String job)
    {
        this.job = job;
    }
    public String getJob()
    {
        return job;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
    public MultiLinkedList getNpcNames() {
        return npcNames;
    }

    public void setNpcNames(MultiLinkedList npcNames) {
        this.npcNames = npcNames;
    }
    public Object getQuest() {
        return quest;
    }

    public void setQuest(Object quest) {
        this.quest = quest;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public void addLumberJacks(Dungeon dungeon) {
        //if (dungeon.getBiome().equals(Biomes.WARM)) {
            npcNames.addCategory("Lumberjacks");
            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            Vertex npcVertex = new Vertex(x, y);
            // --------------------woman--------------------
            String npcName = "Elra Treehewer";
            String npcGender = "Girl";
            String npcJob = "Lumberjack";
            String npcMessage = "Quiet but strong.Her axe does not stop even in winter!!!";

            Object npcQuest = new Forest(); // buraya görev gelecek
            NPC npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Mira Ironbark";
            npcMessage = "She became a legend with her axe.Villagers respect her.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Thalia Woodsmile";
            npcMessage = "A gentle girl with a singing voice and a shiny axe. The forest knows her name and calls out to her --> Thalia.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Brynn Oakram";
            npcMessage = "She draws her strength from her arms and carries heavy logs all by herself.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Sera Axewind";
            npcMessage = "She is known for her swift axe, trees fall as if they bow to her.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Velda Timberjaw";
            npcMessage = "Brave, a former soldier who was used by others and had wielded an axe in battle.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Lira Logcutter";
            npcMessage = "She lives alone in the forest, immersed in nature.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Ysolde Barkcloak";
            npcMessage = "She wears a cloak made of leaves, a mysterious person.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Maren Pinewhisper";
            npcMessage = "She is quiet and careful,prays to every tree first.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Freya Branchbreaker";
            npcMessage = "A large-built woman, a woodswoman known for her legendary strength.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            // --------------------man--------------------

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcGender = "Boy";
            npcName = "Garruk Woodsplitter";
            npcMessage = "Muscular, stern-faced, the strongest man in the village.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Thorne Axeblade";
            npcMessage = "A woodsman who expertly swings his enchanted axe.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Bram Ironlog";
            npcMessage = "Calm and patient, teaches young woodsmen.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Kael Timberfist";
            npcMessage = "A fierce man; it wouldn’t be surprising if he could knock down a tree with his fist.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Borin Pinecleaver";
            npcMessage = "Cheerful, an old woodsman who constantly tells stories.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Ulric Barkskin";
            npcMessage = "His face is as tough as tree bark, he lives in very ancient forests.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Darnel Axehand";
            npcMessage = "Lost one arm but replaced it with an axe-hand.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Varek Oakhew";
            npcMessage = "Serious, disciplined, and dedicated to duty.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Rurik Woodmaul";
            npcMessage = "Prefers to fell trees not with an axe but with a giant hammer.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);

            do {
                x = rand.nextInt(gamePlay.getDungeon().getDungeonX());
                y = rand.nextInt(gamePlay.getDungeon().getDungeonX());
            } while (gamePlay.getDungeon().getDungeonMatrix()[x][y] != ' ');
            npcVertex = new Vertex(x, y);

            npcQuest = new Forest();
            npcName = "Fenric Logsplitter";
            npcMessage = "Sells firewood to caravans, turning woodsman skills into trade.";
            npc = new NPC(false, npcName, npcJob, npcMessage, npcQuest, npcGender, npcVertex, gamePlay);
            npcNames.addItem("Lumberjacks", npc);
            for (int i = 0; i < 20; i++) {
                NPC npc1 = selectRandomNPC("Lumberjacks", npcNames, 0, 20);
                Vertex npcVertex1 = npc1.getNPCVertex();
                gamePlay.getDungeon().getDungeonMatrix()[npcVertex1.getVertexX()][npcVertex1.getVertexY()] = 'N';
            }
        //}
    }
    public void displayNpc(Object category,MultiLinkedList mll) {
        MultiLinkedList.CategoryNode categoryNode = mll.getHead();
        if (categoryNode == null) {
            System.out.println("No category found");
        } else {
            System.out.println("Category: " + categoryNode.getData());
            while (categoryNode != null && !categoryNode.getData().equals(category)) {
                categoryNode = categoryNode.getDown();
            }
            if (categoryNode == null) {
                System.out.println("No category found");
                return;
            }
            MultiLinkedList.ItemNode itemNode = categoryNode.getRight();
            if (itemNode == null) {
                System.out.println("No NPC found");
            } else {
                while (itemNode != null) {
                    System.out.print("NPC : ");
                    NPC npc = (NPC) itemNode.getData();
                    System.out.print(" Name "+npc.getName());
                    System.out.print(" Job "+npc.getJob());
                    System.out.print(" Message "+npc.getMessage());
                    System.out.print(" Quest "+npc.getQuest());
                    System.out.print(" Gender "+npc.getGender());
                    itemNode = itemNode.getNext();
                    System.out.println();
                }
            }
        }
    }
    public NPC selectRandomNPC(Object category,MultiLinkedList mll,int minInterval ,int maxInterval) {
        MultiLinkedList.CategoryNode categoryNode = mll.getHead();
        if (categoryNode == null) {
            System.out.println("No category found");
            return null;
        } else {
            while (categoryNode != null && !categoryNode.getData().equals(category)) {
                categoryNode = categoryNode.getDown();
            }
            if (categoryNode == null) {
                System.out.println("No category found");
                return null;
            }
            MultiLinkedList.ItemNode itemNode = categoryNode.getRight();
            if (itemNode == null) {
                System.out.println("No NPC found");
                return null;
            } else {
                int randomNum = rand.nextInt(maxInterval - minInterval + 1) + minInterval;  // min ile max arası sayı
                while (itemNode.getNext() != null) {
                    if (randomNum < 0) {
                        break;
                    }
                    itemNode = itemNode.getNext();
                    randomNum--;
                }
                return (NPC) itemNode.getData();
            }
        }
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

    public Vertex getNPCVertex() {
        return NPCVertex;
    }

    public void setNPCVertex(Vertex NPCVertex) {
        this.NPCVertex = NPCVertex;
    }
}
