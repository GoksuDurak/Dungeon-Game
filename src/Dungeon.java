import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Random;

public class Dungeon {
    //
    private char[][] dungeon; // zindan matrisi
    private int x; // maris boyu i
    private int y; // matris genişliği j
    private Random rand = new Random();
    private Biomes biome;
    private BiomeTypes biomeType;
    private boolean isHiddenDungeonExcist = false;
    private int hiddenDoorX = 0;
    private int hiddenDoorY = 0;
    //Constructor
    public Dungeon(int x, int y)
    {
        this.x = x;
        this.y = y;
        dungeon = new char[x][y];
    }
    //Get-Set
    //Matrix

    public int getHiddeDoorX() {
        return hiddenDoorX;
    }

    public void setHiddeDoorX(int hiddeDoorX) {
        this.hiddenDoorX = hiddeDoorX;
    }

    public int getHiddeDoorY() {
        return hiddenDoorY;
    }

    public void setHiddeDoorY(int hiddeDoorY) {
        this.hiddenDoorY = hiddeDoorY;
    }

    public boolean isHiddenDungeonExcist() {
        return isHiddenDungeonExcist;
    }

    public void setHiddenDungeonExcist(boolean hiddenDungeonExcist) {
        isHiddenDungeonExcist = hiddenDungeonExcist;
    }


    public char[][] getDungeonMatrix()
    {
        return dungeon;
    }
    public void setDungeonMatrix(char[][] dungeon)
    {
        this.dungeon = dungeon;
    }
    //Matrix X
    public int getDungeonX()
    {
        return x;
    }
    public void setDungeonX(int x)
    {
        this.x = x;
    }
    //Matrix Y
    public int getDungeonY()
    {
        return y;
    }
    public void setDungeonY(int y)
    {
        this.y = y;
    }
    public BiomeTypes getBiomeType() {
        return biomeType;
    }

    public void setBiomeType(BiomeTypes biomeType) {
        this.biomeType = biomeType;
    }

    public Biomes getBiome() {
        return biome;
    }

    public void setBiome(Biomes biome) {
        this.biome = biome;
    }
    public void setBiomeColor(Game game) {
        if (biomeType.equals(BiomeTypes.VOLCANIC)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(255, 0, 0)));
        } else if (biomeType.equals(BiomeTypes.DESERT)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(250, 213, 165)));
        } else if (biomeType.equals(BiomeTypes.SAVANNA)) {
            game.cn.setTextAttributes(new TextAttributes(new Color( 189, 183, 107)));
        } else if (biomeType.equals(BiomeTypes.POLAR)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(240, 248, 255)));
        } else if (biomeType.equals(BiomeTypes.ICE_SPIKES)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(173, 216, 230)));
        } else if (biomeType.equals(BiomeTypes.TUNDRA)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(176, 196, 222)));
        } else if (biomeType.equals(BiomeTypes.FOREST)) {
            game.cn.setTextAttributes(new TextAttributes(new Color(34, 139, 34)));
        } else if (biomeType.equals(BiomeTypes.PLAINS)) {
            game.cn.setTextAttributes(new TextAttributes(new Color( 124, 252, 0)));
        } else if (biomeType.equals(BiomeTypes.SWAMP)) {
            game.cn.setTextAttributes(new TextAttributes(new Color( 85, 107, 47)));
        }
    }
    public void printDungeon(Game game) {
        for (int i = 0; i < getDungeonX(); i++) {
            for (int j = 0; j < getDungeonY(); j++) {
                if (dungeon[i][j] == '¤') {
                    game.cn.setTextAttributes(new TextAttributes(Color.orange));
                } else if (dungeon[i][j] == '-' || dungeon[i][j] == '+' || dungeon[i][j] == '|') {
                   setBiomeColor(game);
                }
                System.out.print(dungeon[i][j]);
                game.cn.setTextAttributes(new TextAttributes(Color.white));
            }
            System.out.println();
        }
    }
    public void addHiddenDoor() {
        int randomNum = rand.nextInt(100);
        if (randomNum % 2 == 0) {
            // yüzde elli ihtimalle aktif olacak
            isHiddenDungeonExcist = true;
            int doorX = 0;
            int doorY = 0;
            do {
                doorX = rand.nextInt(getDungeonX());
                doorY = rand.nextInt(getDungeonY());
            } while (dungeon[doorX][doorY] != '|' && dungeon[doorX][doorY] != '-');
            hiddenDoorX = doorX;
            hiddenDoorY = doorY;
        }
        /*
        hiddenDoorX = 0;
        hiddenDoorY = 1;
        isHiddenDungeonExcist = true;
         */
    }
    public void createRandomDungeon() {
        for (int i = 0; i < getDungeonX(); i++) {
            for (int j = 0; j < getDungeonY(); j++) {
                if(i % 2 == 0 && j % 2 == 0) {
                    dungeon[i][j] = '+';
                } else if (i % 2 == 1 && j % 2 == 0) {
                    dungeon[i][j] = '|';
                } else if (i % 2 == 0 && j % 2 == 1) {
                    dungeon[i][j] = '-';
                } else {
                    dungeon[i][j] = '.';
                }
            }
        }
    }
    public Dungeon randomMoney(Dungeon dungeon)
    {
        int spcaeCount = 0;
        int moneyCount = 0;
        int x = 0;
        int y = 0;

        boolean flag = false;
        boolean dungeonFlag = false;
        for (int i = 0; i < dungeon.getDungeonX(); i++)
        {
            for(int j = 0; j < dungeon.getDungeonY(); j++)
            {
                if(dungeon.getDungeonMatrix()[i][j] == ' ')
                {
                    spcaeCount++;
                }
            }
        }
        if(spcaeCount == 0) return dungeon;
        int minBound = spcaeCount / 20; // kaç para ideal???
        int maxBound = spcaeCount / 10;
        moneyCount =rand.nextInt(maxBound-minBound) + minBound; //burada zindana kaç para koycaz
        int[] tempX = new int[moneyCount];
        int[] tempY = new int[moneyCount];
        do {
            x = rand.nextInt(dungeon.getDungeonX());
            y = rand.nextInt(dungeon.getDungeonY());
            tempX[0] = x;
            tempY[0] = y;
        }while ((dungeon.getDungeonMatrix()[x][y] == '●'
                || dungeon.getDungeonMatrix()[x][y] == 'P' || dungeon.getDungeonMatrix()[tempX[0]][tempY[0]] == 'x'  ||  dungeon.getDungeonMatrix()[tempX[0]][tempY[0]] == '-'  ||  dungeon.getDungeonMatrix()[tempX[0]][tempY[0]] == '|'  ||  dungeon.getDungeonMatrix()[tempX[0]][tempY[0]] == '+' ));
        for (int i = 1; i < moneyCount; i++) {
            do {
                flag = false;
                x = rand.nextInt(dungeon.getDungeonX());
                y = rand.nextInt(dungeon.getDungeonY());
                tempX[i] = x;
                tempY[i] = y;
                for (int j = 0; j < tempX.length; j++)
                {
                    if((tempX[i] == tempX[j] && tempY[i] == tempY[j]  && i != j) || (dungeon.getDungeonMatrix()[x][y] == '●'
                            || dungeon.getDungeonMatrix()[x][y] == 'P' ||dungeon.getDungeonMatrix()[tempX[i]][tempY[i]] == 'x'  ||  dungeon.getDungeonMatrix()[tempX[i]][tempY[i]] == '-'  ||  dungeon.getDungeonMatrix()[tempX[i]][tempY[i]] == '|'  ||  dungeon.getDungeonMatrix()[tempX[i]][tempY[i]] == '+' ))
                    {
                        flag = true;
                    }
                }
            }while (flag);
        }
        for (int i = 0; i < tempX.length; i++)
        {
            dungeon.getDungeonMatrix()[tempX[i]][tempY[i]] = '¤';
        }
        return dungeon;
    }

}
