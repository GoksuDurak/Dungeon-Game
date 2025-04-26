import enigma.console.TextAttributes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    //Oyun içinde oylaları burada başlar ve burada yaratırlır
    //Dunegon Npc Player burada yaratılır.
    private final Random rand = new Random();
    private Vertex startVertex;
    private int enenmyX = 0;
    private int enenmyY = 0;
    private Vertex finalVertex;
    private Vertex vertex;
    private Vertex vertexF;
    private boolean takesInput = true;
    private final Scanner scanner = new Scanner(System.in);
    private boolean quit = false;
    private Stack dungeonStack = new Stack(5); // zindanları burda tutacağız
    //private char[][] dungeon;
    private boolean enemyIsDefeated = false; // düşman yenildi mi
    private Dungeon dungeon;
    private Enemy[] enemies;
    private Enemy enemy;
    private Dungeon dungeon2;
    private Player player;
    private boolean infoIsCome = true;
    private int x = 1, y = 1;  // Başlangıç konumu
    private  boolean flag = false;
    GamePlay()
    {

    }

    private int clamp(int x, int min, int max) { // defans atacktan büyükse
        return Math.min(Math.max(x, min), max);
    }

    public void Start() throws Exception//Oyun buarada başlıyacak
    {
        Game game = new Game();
        //Oyun başladı önce oyuncu oluşur
        System.out.println("Welcome to the Dungeon");
        System.out.print("Please enter your name : ");
        String name = scanner.nextLine();
        game.Clear();
        player = new Player(name,0,0,225,200,200,0,225,150);
        /*
        Dungeon dungeon = new Dungeon(17,17);
        dungeon.createRandomDungeon(); // we create matrix for random dungeon
        createRandomDungeon(dungeon);
        Stack stack = new Stack(4);
        stack.push(dungeon);
        ((Dungeon)stack.peek()).printDungeon();
        Dungeon dungeon1 = new Dungeon(15,17);// create own dungen manuel
        dungeon1.getDungeonMatrix();
        //writeText();
        readText();
        */

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
        //dungeon.printDungeon(game);
        dungeon = dungeon.randomMoney(dungeon); // burda mevcut zindanı parayla döndür
        enemy = new Enemy(0,0,0,"",0,0,"");
        enemies = enemy.randomEnemy(dungeon);
        dungeon.printDungeon(game);

        game.Clear();
        movement(); //hareket fonksiyonu

        while (true) {
            if(infoIsCome) {
                infoIsCome = false;
                game.Clear();
                dungeon.printDungeon(game);
                game.cn.getTextWindow().setCursorPosition(dungeon.getDungeonY()+1, 0);
                player.printMoney(player.getMoney()); // parayı yazdır
                while (!takesInput)
                {
                    for (int i = 0; i < enemies.length; i++)
                    {
                        if(enenmyX == enemies[i].getX() && enenmyY == enemies[i].getY())
                        {
                            enemy = enemies[i];
                        }
                    }
                    game.Clear();
                    System.out.print("You encountered ");
                    game.cn.setTextAttributes(new TextAttributes(Color.cyan));
                    System.out.print(enemy.getType()); //türü
                    game.cn.setTextAttributes(new TextAttributes(Color.white));
                    System.out.println(".");
                    printEnemy(enemy,game);
                    System.out.print("Do you want to fight? (y/n) : ");
                    String choice = scanner.nextLine();
                    choice = choice.toLowerCase();
                    if(choice.equals("n"))
                    {
                        takesInput = true;
                        infoIsCome = true;
                    }else if(choice.equals("y"))
                    {
                        game.Clear();
                        System.out.println("War begin !!! ");
                        game.cn.setTextAttributes(new TextAttributes(Color.yellow));
                        System.out.println(enemy.getType());
                        game.cn.setTextAttributes(new TextAttributes(Color.white));
                        printEnemy(enemy,game);
                        printPlayer(game);
                        //savaş olacak
                        /*
                        if(player wins) enemy.getHealth() <= 0 enemyIsDefeated true ve  takesInput true
                        else player.getHP() <= 0 enemyIsDefeated false ve  takesInput true
                        */
                        String choice1 = scanner.nextLine();
                    }
                }

            }
            if(quit)
            {
                Dungeon dungeon1 = new Dungeon(35,37);
                dungeon1.createRandomDungeon();
                createRandomDungeon(dungeon1);
                startVertex = vertex;
                x = startVertex.getVertexX();
                y = startVertex.getVertexY();
                finalVertex = vertexF;
                dungeon = dungeon1;
                dungeon = dungeon.randomMoney(dungeon); // burda mevcut zindanı parayla döndür
                quit = false;
                infoIsCome = true;
                enemies = enemy.randomEnemy(dungeon);
                dungeon.printDungeon(game);
                while (!takesInput)
                {
                    for (int i = 0; i < enemies.length; i++)
                    {
                        if(enenmyX == enemies[i].getX() && enenmyY == enemies[i].getY())
                        {
                            enemy = enemies[i];
                        }
                    }
                    game.Clear();
                    System.out.print("You encountered ");
                    game.cn.setTextAttributes(new TextAttributes(Color.cyan));
                    System.out.print(enemy.getType()); //türü
                    game.cn.setTextAttributes(new TextAttributes(Color.white));
                    System.out.println(".");
                    printEnemy(enemy,game);
                    System.out.print("Do you want to fight? (y/n) : ");
                    String choice = scanner.nextLine();
                    choice = choice.toLowerCase();
                    if(choice.equals("n"))
                    {
                        takesInput = true;
                        infoIsCome = true;
                    }else if(choice.equals("y"))
                    {
                        System.out.println("War begin !!! ");
                    }
                }
            }
            Thread.sleep(10);
        }


        //player moves then we write '●' instead of 'P'
    }
    public void printEnemy(Enemy enemy,Game game)
    {
        System.out.print(enemy.getType().toUpperCase()+ "'s Health --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.red));
        System.out.print(enemy.getHealth()); //canı
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        System.out.print(enemy.getType().toUpperCase()+ "'s Attcak --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.green));
        System.out.print(enemy.getAttack()); //attack
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        System.out.print(enemy.getType().toUpperCase()+ "'s Defense --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.blue));
        System.out.print(enemy.getDefence()); //defans
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
    }
    public void printPlayer(Game game)
    {
        game.cn.getTextWindow().setCursorPosition(35, 1);
        game.cn.setTextAttributes(new TextAttributes(Color.yellow));
        System.out.print(player.getName());
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        game.cn.getTextWindow().setCursorPosition(35, 2);
        System.out.print(player.getName().toUpperCase()+ "'s Health --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.red));
        System.out.print(player.getHp()); //canı
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        game.cn.getTextWindow().setCursorPosition(35, 3);
        System.out.print(player.getName().toUpperCase()+ "'s Attcak --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.green));
        System.out.print(player.getAttack()); //attack
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
        game.cn.getTextWindow().setCursorPosition(35, 4);
        System.out.print(player.getName().toUpperCase()+ "'s Defense --> ");
        game.cn.setTextAttributes(new TextAttributes(Color.blue));
        System.out.print(player.getDefense()); //defans
        game.cn.setTextAttributes(new TextAttributes(Color.white));
        System.out.println(".");
    }
    //Player movement

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
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (dungeon.getDungeonMatrix()[x - 1][y] != '+' && dungeon.getDungeonMatrix()[x - 1][y] != '-') {

                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x-1][y] == 'E') // yukarısı E ise
                            {
                                enenmyX = x - 1;
                                enenmyY = y;
                            }
                            x = x - 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P'; //konumu x ve y
                            if (dungeon.getDungeonMatrix()[enenmyX][enenmyY] == ' ' && !enemyIsDefeated) {
                                dungeon.getDungeonMatrix()[enenmyX][enenmyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (dungeon.getDungeonMatrix()[x + 1][y] != '+' && dungeon.getDungeonMatrix()[x + 1][y] != '-') {

                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x+1][y] == 'E') // yukarısı E ise
                            {
                                enenmyX = x + 1;
                                enenmyY = y;
                            }
                            x = x + 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P';
                            if (dungeon.getDungeonMatrix()[enenmyX][enenmyY] == ' ' && !enemyIsDefeated) {
                                dungeon.getDungeonMatrix()[enenmyX][enenmyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (dungeon.getDungeonMatrix()[x][y + 1] != '+' && dungeon.getDungeonMatrix()[x][y + 1] != '|') {

                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x][y + 1] == 'E') // yukarısı E ise
                            {
                                enenmyX = x;
                                enenmyY = y + 1;
                            }
                            y = y + 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P';
                            if (dungeon.getDungeonMatrix()[enenmyX][enenmyY] == ' ' && !enemyIsDefeated) {
                                dungeon.getDungeonMatrix()[enenmyX][enenmyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }

                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (dungeon.getDungeonMatrix()[x][y - 1] != '+' && dungeon.getDungeonMatrix()[x][y - 1] != '|') {

                            dungeon.getDungeonMatrix()[x][y] = ' ';
                            if(dungeon.getDungeonMatrix()[x][y - 1] == 'E') // yukarısı E ise
                            {
                                enenmyX = x;
                                enenmyY = y - 1;
                            }
                            y = y - 1;
                            if (dungeon.getDungeonMatrix()[x][y] == '¤') {
                                player.setMoney(player.getMoney() + 1);
                            }
                            dungeon.getDungeonMatrix()[x][y] = 'P';
                            if (dungeon.getDungeonMatrix()[enenmyX][enenmyY] == ' ' && !enemyIsDefeated) {
                                dungeon.getDungeonMatrix()[enenmyX][enenmyY] = 'E';
                            }
                            if (dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[startVertex.getVertexX()][startVertex.getVertexY()] = '●';
                            }
                            if (dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] == ' ') {
                                dungeon.getDungeonMatrix()[finalVertex.getVertexX()][finalVertex.getVertexY()] = 'x';
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        if (x == finalVertex.getVertexX() && y == finalVertex.getVertexY()) //x çıkış  noktasındaysa
                        {
                            quit = true;
                        }
                        if(x == enenmyX && y == enenmyY)
                        {
                            takesInput = false;
                        }
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

}
