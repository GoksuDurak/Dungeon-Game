import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class HiddenDungeon {
    public class Key {
        private char key;
        private Color color;
        private String size;
        private Vertex position;

        public Key(Color color, String size, Vertex position, char key) {
            this.color = color;
            this.size = size;
            this.position = position;
            this.key = key;
        }

        public Vertex getPosition() {
            return position;
        }

        public void setPosition(Vertex position) {
            this.position = position;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public char getKey() {
            return key;
        }

        public void setKey(char key) {
            this.key = key;
        }

    }

    public class Area {
        private LinkedQueue squares = new LinkedQueue();
        private Vertex initial;
        private Vertex terminal;
        private boolean isAreaActive;
        public Area(boolean isAreaActive) {
            this.isAreaActive = isAreaActive;
        }
        public void addSquare(Vertex initial,Vertex terminal) {
            squares.enqueue(initial);
            squares.enqueue(terminal);
        }

        public LinkedQueue getSquares() {
            return squares;
        }

        public void setSquares(LinkedQueue squares) {
            this.squares = squares;
        }

        public Vertex getInitial() {
            return initial;
        }

        public void setInitial(Vertex initial) {
            this.initial = initial;
        }

        public Vertex getTerminal() {
            return terminal;
        }

        public void setTerminal(Vertex terminal) {
            this.terminal = terminal;
        }

        public boolean isAreaActive() {
            return isAreaActive;
        }

        public void setAreaActive(boolean areaActive) {
            isAreaActive = areaActive;
        }


    }
    private char[][] hiddenDungeon;
    private int hiddenPlayerX = 0;
    private int hiddenPlayerY = 0;
    private LinkedQueue doors = new LinkedQueue();
    private LinkedQueue lock = new LinkedQueue();
    private LinkedQueue direction = new LinkedQueue();
    private LinkedQueue keys = new LinkedQueue();
    private LinkedQueue yourKeys = new LinkedQueue();
    private LinkedQueue areasOFDungeon = new LinkedQueue();
    private LinkedQueue openedAreasOFDungeon = new LinkedQueue();
    private LinkedQueue enemyQueue = new LinkedQueue();
    private GamePlay gamePlay;
    private Game game;
    private Vertex vertex = null;
    private boolean isGameFinished = false;
    private int time = 0;
    private boolean downActive = true;
    private boolean upActive = false;
    private int directionBall = 1;
    private int ballX = 0;
    private int ballY = 0;
    private int ball1X = 0;
    private int ball1Y = 0;
    private int ball2X = 0;
    private int ball2Y = 0;
    private int playerLife = 5;

    public HiddenDungeon(int x, int y,GamePlay gamePlay) {
        hiddenDungeon = new char[x][y];
        this.gamePlay = gamePlay;
    }
    public void createHiddenDungeon() {
        for (int i = 0; i < hiddenDungeon.length; i++) {
            for (int j = 0; j < hiddenDungeon[i].length; j++) {
                if (i == 0 || i == hiddenDungeon.length - 1) {
                    hiddenDungeon[i][j] = '-';
                } else if (j == 0 || j == hiddenDungeon[i].length - 1) {
                    hiddenDungeon[i][j] = '|';
                } else if (j == 4) {
                    if (i < 31) {
                        hiddenDungeon[i][j] = '|';
                    } else {
                        hiddenDungeon[i][j + 15] = '|';
                    }
                } else {
                    hiddenDungeon[i][j] = ' ';
                }
            }
        }
        hiddenPlayerX = 1;
        hiddenPlayerY = 1;
        hiddenDungeon[1][1] = 'P';
        hiddenDungeon[5][4] = ' ';
        hiddenDungeon[6][4] = ' ';
        hiddenDungeon[28][4] = ' ';
        hiddenDungeon[29][4] = ' ';

        for (int i = 0; i < hiddenDungeon[i].length - 5; i++) {
            hiddenDungeon[4][i + 4] = '-';
            hiddenDungeon[7][i + 4] = '-';
            if (i < hiddenDungeon[i].length - 8) {
                hiddenDungeon[27][i + 4] = '-';
            }
            hiddenDungeon[30][i + 4] = '-';
            if (i != 0) {
                hiddenDungeon[11][i + 4] = '-';
                if (3 < i && i <hiddenDungeon[i].length - 5) {
                    hiddenDungeon[15][i + 4] = '-';

                }
                if (i < hiddenDungeon[i].length - 8) {
                    hiddenDungeon[19][i + 4] = '-';
                }
                hiddenDungeon[23][i + 4] = '-';
            }
        }
        for (int i = 0; i < 3; i++) {
            hiddenDungeon[2][i + 1] = '-';
            hiddenDungeon[i + 1][17] = '|';
            hiddenDungeon[i + 8][21] = '|';
            hiddenDungeon[i + 12][19] = '|';
            hiddenDungeon[i + 20][27] = '|';
            hiddenDungeon[i + 20][17] = '|';
            hiddenDungeon[i + 31][4] = '|';
        }
        hiddenDungeon[28][4] = '|';
        hiddenDungeon[29][4] = '|';
        hiddenDungeon[5][4] = '|';
        hiddenDungeon[6][4] = '|';
        //doors
        // direction up and left 1
        doors.enqueue(new Vertex(7, 20));
        lock.enqueue(false);
        direction.enqueue(1);
        keys.enqueue(null);
        putMaze(new Vertex(8,20),new Vertex(10,5),'c',2);

        Area area = new Area(true);
        area.addSquare(new Vertex(8, 20),new Vertex(10,5));
        areasOFDungeon.enqueue(area);

        doors.enqueue(new Vertex(4, 18));
        lock.enqueue(false);
        direction.enqueue(-1);
        keys.enqueue(null);
        putMaze(new Vertex(1,33),new Vertex(3,18),'c',2);

        area = new Area(true);
        area.addSquare(new Vertex(1, 33),new Vertex(3,18));
        areasOFDungeon.enqueue(area);


        int randomX = 0;
        int randomY = 0;

        doors.enqueue(new Vertex(4, 16));
        lock.enqueue(true);
        direction.enqueue(1);
        do {
            randomX = (int)(Math.random() * 3) + 20;
            randomY = (int)(Math.random() * 12) + 6;
        } while (hiddenDungeon[randomX][randomY] != ' ');

        keys.enqueue(new Key(new Color(214, 0, 120), "long",new Vertex(randomX, randomY),'†'));
        hiddenDungeon[randomX][randomY] = '†';
        putMaze(new Vertex(1, 5),new Vertex(3,16),'c',2);

        area = new Area(true);
        area.addSquare(new Vertex(1, 5),new Vertex(3,16));
        areasOFDungeon.enqueue(area);
        //closedArea(new Vertex(1,5),new Vertex(3,16)); //select area
        // i 1 den küçük 3 ten büyük j 5 ten küçük 16 dan büyük olan eyerler yazılacak

        doors.enqueue(new Vertex(22, 4));
        lock.enqueue(true);
        direction.enqueue(1); // kapı yönü

        do {
             randomX = (int)(Math.random() * 3) + 20;
             randomY = (int)(Math.random() * 9) + 18;
        } while (hiddenDungeon[randomX][randomY] != ' ');

        keys.enqueue(new Key(new Color(126, 47, 17), "long",new Vertex(randomX, randomY),'†'));
        putMaze(new Vertex(20,5),new Vertex(22,16),'c',2);
        hiddenDungeon[randomX][randomY] = '†';

        area = new Area(true);
        area.addSquare(new Vertex(20, 5),new Vertex(22,16));
        areasOFDungeon.enqueue(area);

        doors.enqueue(new Vertex(20, 27));
        lock.enqueue(false);
        direction.enqueue(-1);
        keys.enqueue(null);
        putMaze(new Vertex(20,26),new Vertex(22,18),'c',1);
        area = new Area(true);
        area.addSquare(new Vertex(20, 26),new Vertex(22,18));
        areasOFDungeon.enqueue(area);

        doors.enqueue(new Vertex(23, 33));
        lock.enqueue(false);
        direction.enqueue(1);
        keys.enqueue(null);
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(22, 28),new Vertex(20,33));
        area.addSquare(new Vertex(19, 30),new Vertex(19,33));
        area.addSquare(new Vertex(16, 5),new Vertex(18,33));
        area.addSquare(new Vertex(15, 5),new Vertex(15,8));
        area.addSquare(new Vertex(12, 5),new Vertex(14,18));

        doors.enqueue(new Vertex(11, 33));
        lock.enqueue(false);
        direction.enqueue(1);
        keys.enqueue(null);
        putMaze(new Vertex(10,33),new Vertex(8,22),'c',1);

        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(12, 20),new Vertex(14,33));

        doors.enqueue(new Vertex(7, 22));
        lock.enqueue(true);

        do {
            randomX = (int)(Math.random() * 3) + 1;
            randomY = (int)(Math.random() * 16) + 18;
        } while (hiddenDungeon[randomX][randomY] != ' ');

        keys.enqueue(new Key(new Color(100, 40, 130), "long",new Vertex(randomX, randomY),'†'));
        hiddenDungeon[randomX][randomY] = '†';
        direction.enqueue(-1);

        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(8, 22),new Vertex(10,33));

        doors.enqueue(new Vertex(31, 4));
        lock.enqueue(true);
        direction.enqueue(-1);

        do {
            randomX = (int)(Math.random() * 3) + 12;
            randomY = (int)(Math.random() * 14) + 20;
        } while (hiddenDungeon[randomX][randomY] != ' ');

        keys.enqueue(new Key(new Color(120, 190, 130), "long",new Vertex(randomX, randomY),'†'));
        hiddenDungeon[randomX][randomY] = '†';
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(31, 5),new Vertex(33,33));


        doors.enqueue(new Vertex(29, 4));
        lock.enqueue(false);
        direction.enqueue(1);
        keys.enqueue(null);
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(28, 5),new Vertex(29,33));
        area.addSquare(new Vertex(27, 30),new Vertex(27,33));
        area.addSquare(new Vertex(26, 5),new Vertex(24,33));

        doors.enqueue(new Vertex(5, 4));
        lock.enqueue(false);
        direction.enqueue(-1);
        keys.enqueue(null);
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(6, 5),new Vertex(5,33));
        //putMaze(new Vertex(6,5),new Vertex(5,33),'H',5);

        doors.enqueue(new Vertex(2, 1));
        lock.enqueue(true);
        keys.enqueue(new Key(new Color(190, 120, 99), "long",new Vertex(1, 3),'†'));
        hiddenDungeon[1][3] = '†';
        direction.enqueue(-1);
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(3, 1),new Vertex(33,3));
/*
        doors.enqueue(new Vertex(2, 1));
        lock.enqueue(true);
        keys.enqueue(new Key(new Color(130, 80, 90), "long",new Vertex(1, 2),'†'));
        hiddenDungeon[1][2] = '†';
        direction.enqueue(-1);
        area = new Area(true);
        areasOFDungeon.enqueue(area);
        area.addSquare(new Vertex(3, 1),new Vertex(33,3));
 */
    }
    public void putMaze(Vertex initial,Vertex terminal,char object,int count) {
        int x,y;
        for (int i = 0; i < count; i++) {
            int minX = 0;
            int minY = 0;
            int maxX = 0;
            int maxY = 0;
            if (terminal.getVertexX() > initial.getVertexX()) {
                // büyük olan max küçük olan x min
                maxX = terminal.getVertexX();
                minX = initial.getVertexX();
            } else if (terminal.getVertexX() <= initial.getVertexX()) {
                minX = terminal.getVertexX();
                maxX = initial.getVertexX();
            }
            if (terminal.getVertexY() > initial.getVertexY()) {
                // terminal büyükse terminal max
                maxY = terminal.getVertexY();
                minY = initial.getVertexY();
            } else if (terminal.getVertexY() <= initial.getVertexY()) {
                minY = terminal.getVertexY();
                maxY = initial.getVertexY();
            }
            do {
                x = (int) (Math.random() * (maxX - minX + 1)) + minX;
                y = (int) (Math.random() * (maxY - minY + 1)) + minY;
            } while (hiddenDungeon[x][y] != ' ');
            hiddenDungeon[x][y] = object;
        }
    }

    public void printStatics() {
        Game.cn.getTextWindow().setCursorPosition(hiddenDungeon[0].length + 1 ,1);
        System.out.print("Your keys : ");
        for (int i = 0; i < yourKeys.size(); i++) {
            Key key = (Key) yourKeys.peek();
            Game.cn.setTextAttributes(new TextAttributes(key.getColor()));
            System.out.print(key.getKey());
            Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            if (i != yourKeys.size() - 1) {
                System.out.print(" ");
            }
            yourKeys.enqueue(yourKeys.dequeue());
        }
        if (yourKeys.isEmpty()) {
            System.out.print("none");
        }
        Game.cn.getTextWindow().setCursorPosition(hiddenDungeon[0].length + 1 ,2);
        System.out.print("Your Life : ");
        for (int i = 0; i < playerLife; i++) {
            Game.cn.setTextAttributes(new TextAttributes(Color.RED));
            System.out.print("♥");
        }
        Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        Game.cn.getTextWindow().setCursorPosition(0 ,0);
    }

    public void closedArea(Area area,boolean isAresClosed) {
        int x = 0;
        int x1 = 0;
        int y = 0;
        int y1 = 0;
        int size = area.getSquares().size() / 2; // 1 olmalı
        for (int a = 0; a < size; a++) {
            Vertex initial = (Vertex) area.getSquares().peek();
            area.getSquares().enqueue(area.getSquares().dequeue());
            Vertex terminal = (Vertex) area.getSquares().peek();
            area.getSquares().enqueue(area.getSquares().dequeue());
            if (initial.getVertexX() > terminal.getVertexX()) {
                x = initial.getVertexX();
                x1 = terminal.getVertexX();
            } else {
                x1 = initial.getVertexX();
                x = terminal.getVertexX();
            }
            if (initial.getVertexY() > terminal.getVertexY()) {
                y = initial.getVertexY();
                y1 = terminal.getVertexY();
            } else {
                y1 = initial.getVertexY();
                y = terminal.getVertexY();
            }
            int selectedSize  = (x - x1 + 1) * (y - y1 + 1);
            int[] selectedX = new int[selectedSize];
            int[] selectedY = new int[selectedSize];
            for (int i = 0; i < selectedSize; i++) {
                selectedX[i] = -1;
                selectedY[i] = -1;
            }
            int index = 0;
            int i1 = 0;
            int j1 = 0;
            boolean flag = false;
            for (int i = x1; i < x + 1; i++) {
                for (int j = y1; j < y + 1; j++) {
                    if (isAresClosed) {
                        Game.cn.getTextWindow().setCursorPosition(j, i);
                        System.out.print(' ');
                    } else {
                        Game.cn.getTextWindow().setCursorPosition(j, i);
                        System.out.print(hiddenDungeon[i][j]);
                        area.setAreaActive(true);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        /*
                        do {
                            flag = false;
                            i1 = (int)(Math.random() * (x + 1- x1)) + x1;
                            j1 = (int)(Math.random() * (y + 1- y1)) + y1;
                            for (int k = 0; k < selectedX.length; k++) {
                                if (i1 == selectedX[k] && j1 == selectedY[k]) {
                                    flag = true;
                                }
                            }
                        } while (flag);
                        selectedX[index] = i1;
                        selectedY[index] = j1;
                        index++;
                        Game.cn.getTextWindow().setCursorPosition(j1, i1);
                        System.out.print(hiddenDungeon[i1][j1]);
                        area.setAreaActive(true);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                         */
                    }
                }
                System.out.println();
            }
            Game.cn.getTextWindow().setCursorPosition(0, 0);
        }
    }

    public void printHiddenDungeon() {
        for (int i = 0; i < hiddenDungeon.length; i++) {
            for (int j = 0; j < hiddenDungeon[i].length; j++) {
                if (hiddenDungeon[i][j] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (i == key.getPosition().getVertexX() && j == key.getPosition().getVertexY()) {
                                Game.cn.setTextAttributes(new TextAttributes(key.getColor()));
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                } else {
                    if (!doors.isEmpty()) {
                        for (int k = 0; k < doors.size(); k++) {
                            Vertex doorPosition = (Vertex) doors.peek();
                            if (keys.peek() != null) {
                                Key key = (Key) keys.peek();
                                if (i == doorPosition.getVertexX() && j == doorPosition.getVertexY()) {
                                    Game.cn.setTextAttributes(new TextAttributes(key.getColor()));
                                }
                            } else {
                                if (i == doorPosition.getVertexX() && j == doorPosition.getVertexY()) {
                                    Game.cn.setTextAttributes(new TextAttributes(Color.GREEN));
                                }
                            }
                            doors.enqueue(doors.dequeue());
                            keys.enqueue(keys.dequeue());
                        }
                    }
                }
                System.out.print(hiddenDungeon[i][j]);
                /*
                if (hiddenDungeon[i][j] != 'E') {
                    System.out.print(hiddenDungeon[i][j]);
                } else {
                    System.out.print(' ');
                }

                 */
                Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            }
            System.out.println();
        }
        /*
        //-----------gizli alanı yazdır----------
        for (int i = 0; i < areasOFDungeon.size(); i++) {
            Vertex doorPosition = (Vertex) doors.peek();
            Area area = (Area)areasOFDungeon.peek();
            closedArea(area, area.isAreaActive);
            doors.enqueue(doors.dequeue());
            lock.enqueue(lock.dequeue());
            areasOFDungeon.enqueue(areasOFDungeon.dequeue());
        }

         */

    }
    public void openArea() {
        for (int i = 0; i < openedAreasOFDungeon.size(); i++) {
            Area area = (Area)openedAreasOFDungeon.peek();
            closedArea(area, area.isAreaActive);
            openedAreasOFDungeon.dequeue();
        }
    }
    public void createBall(int x, int y) {
        hiddenDungeon[x][y] = '●';
    }
    public Vertex moveBall(boolean flag,int ball1X,int ball1Y) {
        if (!flag) {
            downActive = false;
            upActive = true;
            directionBall = -1;
        }
        if (downActive) {
            if (hiddenDungeon[ball1X + 1][ball1Y] != '-') {
                hiddenDungeon[ball1X][ball1Y] = ' ';
                if (ball1X == hiddenPlayerX && ball1Y == hiddenPlayerY) {
                    if (playerLife <= 0) {
                        isGameFinished = true;
                        return null;
                    }
                    playerLife--;
                    hiddenPlayerX = 1;
                    hiddenPlayerY = 1;
                    hiddenDungeon[1][1] = 'P';
                }
                ball1X += directionBall;
                hiddenDungeon[ball1X][ball1Y] = '●';
            } else {
                downActive = false;
                upActive = true;
                directionBall = -1;
            }
        }
        if (upActive) {
            if (hiddenDungeon[ball1X - 1][ball1Y] != '-') {
                hiddenDungeon[ball1X][ball1Y] = ' ';
                if (ball1X == hiddenPlayerX && ball1Y == hiddenPlayerY) {
                    if (playerLife <= 0) {
                        isGameFinished = true;
                        return null;
                    }
                    playerLife--;
                    hiddenPlayerX = 1;
                    hiddenPlayerY = 1;
                    hiddenDungeon[1][1] = 'P';
                }
                ball1X += directionBall;
                hiddenDungeon[ball1X][ball1Y] = '●';
            } else {
                upActive = false;
                downActive = true;
                directionBall = 1;
            }
        }
        return new Vertex(ball1X,ball1Y);
    }
    public void start() {
        createHiddenDungeon();
        createBall(3,2);
        ballX = 3;
        ballY = 2;
        createBall(22,1);
        ball1X = 22;
        ball1Y = 1;
        createBall(22,3);
        ball2X = 22;
        ball2Y = 3;
        enemy(15);
        while (true) {
            Game.Clear();
            printHiddenDungeon();
            printStatics();
            if (time % 1 == 0) {
                move();
            }
            if (isGameFinished) {
                break;
            }
            if (hiddenDungeon[2][2] != '-') {
                Vertex newBall = moveBall(true,ball1X,ball1Y);
                if (newBall != null) {
                    ball1X = newBall.getVertexX();
                    ball1Y = newBall.getVertexY();
                }
                newBall = moveBall(true,ballX,ballY);
                if (newBall != null) {
                    ballX = newBall.getVertexX();
                    ballY = newBall.getVertexY();
                }
                newBall = moveBall(true,ball2X,ball2Y);
                if (newBall != null) {
                    ball2X = newBall.getVertexX();
                    ball2Y = newBall.getVertexY();
                }
            }
            try {
                Thread.sleep(100);
                time++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void enemy (int enemyNumber) {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        String type = "";
        int health = 0;
        int attack = 0;
        int defense = 0;
        String gender = "";
        String comment = "";
        Enemy enemy = null;
        for (int i = 0; i <enemyNumber; i++) {
            do {
                x = rand.nextInt(hiddenDungeon.length);
                y = rand.nextInt(hiddenDungeon[0].length);
            } while (hiddenDungeon[x][y] != ' ' || y < 5);
            hiddenDungeon[x][y] = 'E';
            int randomNum = rand.nextInt(6);

            if (randomNum == 0) {
                type = "Mega Slime";
                health = 7500;
                attack = 1500;
                defense = 500;
                gender = "Male";
                comment = "blubblubblubblub...";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            } else if (randomNum == 1) {
                type = "Skeleton";
                health = 6500;
                attack = 1750;
                defense = 500;
                gender = "Male";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            } else if (randomNum == 2) {
                type = "Zombie";
                health = 8000;
                attack = 1500;
                defense = 500;
                gender = "Male";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            } else if (randomNum == 3) {
                type = "Witch";
                health = 7500;
                attack = 1500;
                defense = 750;
                gender = "Female";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            } else if (randomNum == 4) {
                type = "Golem";
                health = 7500;
                attack = 1500;
                defense = 500;
                gender = "Male";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            } else {
                type = "Big Spider";
                health = 7500;
                attack = 1500;
                defense = 500;
                gender = "Female";
                enemy = new Enemy(health,attack,defense,type,x,y,comment,false,gender);
            }
            enemyQueue.enqueue(enemy);
        }
    }
    public void move() {
        if (GamePlay.hiddenDungeonIsUpPressed) {
            if (hiddenDungeon[hiddenPlayerX - 1][hiddenPlayerY] != '-' || hiddenDungeon[hiddenPlayerX - 1][hiddenPlayerY] == '|') {
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = ' ';
                hiddenPlayerX--;
                if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == hiddenPlayerX && key.getPosition().getVertexY() == hiddenPlayerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'c') {
                    Game.Clear();
                    gamePlay.chest(game,false);
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'E') {
                    for (int k = 0; k < enemyQueue.size(); k++) {
                        Enemy enemy = (Enemy) enemyQueue.peek();
                        if (enemy.getX() == hiddenPlayerX && enemy.getY() == hiddenPlayerY) {
                            try {
                                gamePlay.war(enemy,game);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        enemyQueue.enqueue(enemyQueue.dequeue());
                    }
                }
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (hiddenPlayerX - 1 == vertexDoor.getVertexX() && hiddenPlayerY == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            hiddenDungeon[hiddenPlayerX - 1][hiddenPlayerY - (j * direction1)] = ' ';
                            Game.Clear();
                            printHiddenDungeon();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        keys.dequeue();
                        doors.dequeue();
                        direction.dequeue();
                        lock.dequeue();
                        Area area = (Area) areasOFDungeon.peek();
                        area.setAreaActive(false);
                        openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                        openArea();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    hiddenDungeon[hiddenPlayerX - 1][hiddenPlayerY - (j * direction1)] = ' ';
                                    Game.Clear();
                                    printHiddenDungeon();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                yourKeys.dequeue();
                                keys.dequeue();
                                doors.dequeue();
                                direction.dequeue();
                                lock.dequeue();
                                Area area = (Area) areasOFDungeon.peek();
                                area.setAreaActive(false);
                                openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                                openArea();
                            }
                            if (!yourKeys.isEmpty()) {
                                yourKeys.enqueue(yourKeys.dequeue());
                            }
                        }
                    }
                }
                if (!doors.isEmpty()) doors.enqueue(doors.dequeue());
                if (!lock.isEmpty()) lock.enqueue(lock.dequeue());
                if (!direction.isEmpty()) direction.enqueue(direction.dequeue());
                if (!keys.isEmpty()) keys.enqueue(keys.dequeue());
                if (!areasOFDungeon.isEmpty()) areasOFDungeon.enqueue(areasOFDungeon.dequeue());
            }
            GamePlay.hiddenDungeonIsUpPressed = false;
        }
        if (GamePlay.hiddenDungeonIsDownPressed) {
            if (hiddenDungeon[hiddenPlayerX + 1][hiddenPlayerY] != '-' && hiddenDungeon[hiddenPlayerX + 1][hiddenPlayerY] != '|') {
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = ' ';
                hiddenPlayerX++;
                if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == hiddenPlayerX && key.getPosition().getVertexY() == hiddenPlayerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'c') {
                    Game.Clear();
                    gamePlay.chest(game,false);
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'E') {
                    for (int k = 0; k < enemyQueue.size(); k++) {
                        Enemy enemy = (Enemy) enemyQueue.peek();
                        if (enemy.getX() == hiddenPlayerX && enemy.getY() == hiddenPlayerY) {
                            try {
                                gamePlay.war(enemy,game);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        enemyQueue.enqueue(enemyQueue.dequeue());
                    }
                }
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (hiddenPlayerX + 1 == vertexDoor.getVertexX() && hiddenPlayerY == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            hiddenDungeon[hiddenPlayerX + 1][hiddenPlayerY - (j * direction1)] = ' ';
                            Game.Clear();
                            printHiddenDungeon();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        keys.dequeue();
                        doors.dequeue();
                        direction.dequeue();
                        lock.dequeue();
                        Area area = (Area) areasOFDungeon.peek();
                        area.setAreaActive(false);
                        openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                        openArea();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    hiddenDungeon[hiddenPlayerX + 1][hiddenPlayerY - (j * direction1)] = ' ';
                                    Game.Clear();
                                    printHiddenDungeon();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                yourKeys.dequeue();
                                keys.dequeue();
                                doors.dequeue();
                                direction.dequeue();
                                lock.dequeue();
                                Area area = (Area) areasOFDungeon.peek();
                                area.setAreaActive(false);
                                openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                                openArea();
                            }
                            if (!yourKeys.isEmpty()) {
                                yourKeys.enqueue(yourKeys.dequeue());
                            }
                        }
                    }
                }
                if (!doors.isEmpty()) doors.enqueue(doors.dequeue());
                if (!lock.isEmpty()) lock.enqueue(lock.dequeue());
                if (!direction.isEmpty()) direction.enqueue(direction.dequeue());
                if (!keys.isEmpty()) keys.enqueue(keys.dequeue());
                if (!areasOFDungeon.isEmpty()) areasOFDungeon.enqueue(areasOFDungeon.dequeue());
            }
            GamePlay.hiddenDungeonIsDownPressed = false;
        }
        if (GamePlay.hiddenDungeonIsRightPressed) {
            if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY + 1] != '|' && hiddenDungeon[hiddenPlayerX][hiddenPlayerY + 1] != '-') {
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = ' ';
                hiddenPlayerY++;
                if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == hiddenPlayerX && key.getPosition().getVertexY() == hiddenPlayerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'c') {
                    Game.Clear();
                    gamePlay.chest(game,false);
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'E') {
                    for (int k = 0; k < enemyQueue.size(); k++) {
                        Enemy enemy = (Enemy) enemyQueue.peek();
                        if (enemy.getX() == hiddenPlayerX && enemy.getY() == hiddenPlayerY) {
                            try {
                                gamePlay.war(enemy,game);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        enemyQueue.enqueue(enemyQueue.dequeue());
                    }
                }
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (hiddenPlayerX == vertexDoor.getVertexX() && hiddenPlayerY + 1 == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            if (hiddenDungeon[hiddenPlayerX - (j * direction1) ][hiddenPlayerY + 1] == '-') {
                                break;
                            }
                            hiddenDungeon[hiddenPlayerX - (j * direction1)][hiddenPlayerY + 1] = ' ';
                            Game.Clear();
                            printHiddenDungeon();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        keys.dequeue();
                        doors.dequeue();
                        direction.dequeue();
                        lock.dequeue();
                        Area area = (Area) areasOFDungeon.peek();
                        area.setAreaActive(false);
                        openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                        openArea();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    if (hiddenDungeon[hiddenPlayerX - (j * direction1) ][hiddenPlayerY + 1] == '-') {
                                        break;
                                    }
                                    hiddenDungeon[hiddenPlayerX - (j * direction1) ][hiddenPlayerY + 1] = ' ';
                                    Game.Clear();
                                    printHiddenDungeon();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                yourKeys.dequeue();
                                keys.dequeue();
                                doors.dequeue();
                                direction.dequeue();
                                lock.dequeue();
                                Area area = (Area) areasOFDungeon.peek();
                                area.setAreaActive(false);
                                openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                                openArea();
                            }
                            if (!yourKeys.isEmpty()) {
                                yourKeys.enqueue(yourKeys.dequeue());
                            }
                        }
                    }
                }
                if (!doors.isEmpty()) doors.enqueue(doors.dequeue());
                if (!lock.isEmpty()) lock.enqueue(lock.dequeue());
                if (!direction.isEmpty()) direction.enqueue(direction.dequeue());
                if (!keys.isEmpty()) keys.enqueue(keys.dequeue());
                if (!areasOFDungeon.isEmpty()) areasOFDungeon.enqueue(areasOFDungeon.dequeue());
            }
            GamePlay.hiddenDungeonIsRightPressed = false;
        }
        if (GamePlay.hiddenDungeonIsLeftPressed) {
            if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY - 1] != '|' && hiddenDungeon[hiddenPlayerX][hiddenPlayerY - 1] != '-') {
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = ' ';
                hiddenPlayerY--;
                if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == hiddenPlayerX && key.getPosition().getVertexY() == hiddenPlayerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'c') {
                    Game.Clear();
                    gamePlay.chest(game,false);
                } else if (hiddenDungeon[hiddenPlayerX][hiddenPlayerY] == 'E') {
                    for (int k = 0; k < enemyQueue.size(); k++) {
                        Enemy enemy = (Enemy) enemyQueue.peek();
                        if (enemy.getX() == hiddenPlayerX && enemy.getY() == hiddenPlayerY) {
                            try {
                                gamePlay.war(enemy,game);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        enemyQueue.enqueue(enemyQueue.dequeue());
                    }
                }
                hiddenDungeon[hiddenPlayerX][hiddenPlayerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (hiddenPlayerX == vertexDoor.getVertexX() && hiddenPlayerY - 1 == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            if (hiddenDungeon[hiddenPlayerX - (j * direction1) ][hiddenPlayerY + 1] == '-') {
                                break;
                            }
                            hiddenDungeon[hiddenPlayerX - (j * direction1)][hiddenPlayerY - 1] = ' ';
                            Game.Clear();
                            printHiddenDungeon();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        keys.dequeue();
                        doors.dequeue();
                        direction.dequeue();
                        lock.dequeue();
                        Area area = (Area) areasOFDungeon.peek();
                        area.setAreaActive(false);
                        openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                        openArea();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    if (hiddenDungeon[hiddenPlayerX - (j * direction1) ][hiddenPlayerY + 1] == '-') {
                                        break;
                                    }
                                    hiddenDungeon[hiddenPlayerX - (j * direction1)][hiddenPlayerY - 1] = ' ';
                                    Game.Clear();
                                    printHiddenDungeon();
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                                yourKeys.dequeue();
                                keys.dequeue();
                                doors.dequeue();
                                direction.dequeue();
                                lock.dequeue();
                                Area area = (Area) areasOFDungeon.peek();
                                area.setAreaActive(false);
                                openedAreasOFDungeon.enqueue(areasOFDungeon.dequeue());
                                openArea();
                            }
                            if (!yourKeys.isEmpty()) {
                                yourKeys.enqueue(yourKeys.dequeue());
                            }
                        }
                    }
                }
                if (!doors.isEmpty()) doors.enqueue(doors.dequeue());
                if (!lock.isEmpty()) lock.enqueue(lock.dequeue());
                if (!direction.isEmpty()) direction.enqueue(direction.dequeue());
                if (!keys.isEmpty()) keys.enqueue(keys.dequeue());
                if (!areasOFDungeon.isEmpty()) areasOFDungeon.enqueue(areasOFDungeon.dequeue());
            }
            GamePlay.hiddenDungeonIsLeftPressed = false;
        }
    }

}
