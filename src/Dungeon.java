import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Random;

public class Dungeon {
    //
    private char[][] dungeon; // zindan matrisi
    private int x; // maris boyu i
    private int y; // matris genişliği j
    private Random rand = new Random();
    //Constructor
    public Dungeon(int x, int y)
    {
        this.x = x;
        this.y = y;
        dungeon = new char[x][y];
    }
    //Get-Set
    //Matrix
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
    public void printDungeon(Game game)
    {
        for (int i = 0; i < getDungeonX(); i++)
        {
            for (int j = 0; j < getDungeonY(); j++)
            {
                if(dungeon[i][j] == '¤')
                {
                    game.cn.setTextAttributes(new TextAttributes(Color.orange));
                }
                System.out.print(dungeon[i][j]);
                game.cn.setTextAttributes(new TextAttributes(Color.white));
            }
            System.out.println();
        }
    }
    public void createRandomDungeon()
    {
        for (int i = 0; i < getDungeonX(); i++)
        {
            for (int j = 0; j < getDungeonY(); j++)
            {
                if(i % 2 == 0 && j % 2 == 0)
                {
                    dungeon[i][j] = '+';
                } else if (i % 2 == 1 && j % 2 == 0) {
                    dungeon[i][j] = '|';
                } else if (i % 2 == 0 && j % 2 == 1) {
                    dungeon[i][j] = '-';
                }else
                {
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
