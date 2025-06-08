import enigma.console.TextAttributes;

import java.awt.*;
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
    private char[][] hiddenDungeon;
    private int playerX = 0;
    private int playerY = 0;
    private int x;
    private int y;
    private LinkedQueue doors = new LinkedQueue();
    private LinkedQueue lock = new LinkedQueue();
    private LinkedQueue direction = new LinkedQueue();
    private LinkedQueue keys = new LinkedQueue();
    private LinkedQueue yourKeys = new LinkedQueue();
    private LinkedQueue initialCoordinate = new LinkedQueue();
    private LinkedQueue terminalCoordinate = new LinkedQueue();
    private Vertex vertex = null;
    private int time = 0;

    public HiddenDungeon(int x, int y) {
        hiddenDungeon = new char[x][y];
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
                }
            }
        }
        playerX = 1;
        playerY = 1;
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
            hiddenDungeon[i + 1][17] = '|';
            hiddenDungeon[i + 8][21] = '|';
            hiddenDungeon[i + 12][19] = '|';
            hiddenDungeon[i + 20][27] = '|';
            hiddenDungeon[i + 20][17] = '|';
        }
        //doors
        // direction up and left 1
        doors.enqueue(new Vertex(7, 20));
        lock.enqueue(false);
        direction.enqueue(1);
        keys.enqueue(null);
        hiddenDungeon[8][20] = 'b';
        hiddenDungeon[10][5] = 'b';
        initialCoordinate.enqueue(new Vertex(8,20));
        terminalCoordinate.enqueue(new Vertex(10,5));

        doors.enqueue(new Vertex(4, 18));
        lock.enqueue(false);
        direction.enqueue(-1);
        keys.enqueue(null);
        hiddenDungeon[1][33] = 'c';
        hiddenDungeon[3][18] = 'c';
        initialCoordinate.enqueue(new Vertex(1,33));
        terminalCoordinate.enqueue(new Vertex(3,18));

        doors.enqueue(new Vertex(4, 16));
        lock.enqueue(true);
        direction.enqueue(1);
        keys.enqueue(new Key(new Color(214, 0, 120), "long",new Vertex(21, 6),'†'));
        hiddenDungeon[21][6] = '†';
        hiddenDungeon[1][5] = 'a';
        hiddenDungeon[3][16] = 'a';
        initialCoordinate.enqueue(new Vertex(1,5));
        terminalCoordinate.enqueue(new Vertex(3,16));
        //closedArea(new Vertex(1,5),new Vertex(3,16)); //select area
        // i 1 den küçük 3 ten büyük j 5 ten küçük 16 dan büyük olan eyerler yazılacak

        doors.enqueue(new Vertex(22, 4));
        lock.enqueue(true);
        direction.enqueue(1);
        keys.enqueue(new Key(new Color(126, 47, 17), "long",new Vertex(22, 20),'†'));
        hiddenDungeon[22][20] = '†';
        hiddenDungeon[20][5] = 'd';
        hiddenDungeon[22][16] = 'd';
        initialCoordinate.enqueue(new Vertex(20,5));
        terminalCoordinate.enqueue(new Vertex(22,16));

        doors.enqueue(new Vertex(20, 27));
        lock.enqueue(false);
        direction.enqueue(-1);
        keys.enqueue(null);
        hiddenDungeon[20][26] = 'e';
        hiddenDungeon[22][18] = 'e';
        initialCoordinate.enqueue(new Vertex(20,26));
        terminalCoordinate.enqueue(new Vertex(22,18));

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
        Game.cn.getTextWindow().setCursorPosition(0 ,0);
    }

    public void closedArea(Vertex initial,Vertex terminal) {
        int x = 0;
        int x1 = 0;
        int y = 0;
        int y1 = 0;
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
        for (int i = x1; i < x + 1; i++) {
            for (int j = y1; j < y + 1; j++) {
                Game.cn.getTextWindow().setCursorPosition(j, i);
                System.out.print("*");
            }
            System.out.println();
        }
        Game.cn.getTextWindow().setCursorPosition(0,0);
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
                Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            }
            System.out.println();
        }
        //-----------gizli alanı yazdır
        for (int i = 0; i < doors.size(); i++) {
            Vertex doorPosition = (Vertex) doors.peek();
            if ((int) hiddenDungeon[doorPosition.getVertexX()][doorPosition.getVertexY()] != 32) {
                closedArea((Vertex) initialCoordinate.peek(),(Vertex) terminalCoordinate.peek());
            }
            doors.enqueue(doors.dequeue());
            lock.enqueue(lock.dequeue());
            initialCoordinate.enqueue(initialCoordinate.dequeue());
            terminalCoordinate.enqueue(terminalCoordinate.dequeue());
        }
    }

    public void start() {
        createHiddenDungeon();
        while (true) {
            Game.Clear();
            printHiddenDungeon();
            printStatics();
            if (time % 1 == 0) {
                move();
            }
            try {
                Thread.sleep(100);
                time++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void move() {
        if (GamePlay.hiddenDungeonIsUpPressed) {
            if (hiddenDungeon[playerX - 1][playerY] != '-' || hiddenDungeon[playerX - 1][playerY] == '|') {
                hiddenDungeon[playerX][playerY] = ' ';
                playerX--;
                if (hiddenDungeon[playerX][playerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == playerX && key.getPosition().getVertexY() == playerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                }
                hiddenDungeon[playerX][playerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (playerX - 1 == vertexDoor.getVertexX() && playerY == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 5; j++) {
                            hiddenDungeon[playerX - 1][playerY - (j * direction1)] = ' ';
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
                        initialCoordinate.dequeue();
                        terminalCoordinate.dequeue();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 5; j++) {
                                    hiddenDungeon[playerX - 1][playerY - (j * direction1)] = ' ';
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
                                initialCoordinate.dequeue();
                                terminalCoordinate.dequeue();
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
                if (!initialCoordinate.isEmpty()) initialCoordinate.enqueue(initialCoordinate.dequeue());
                if (!terminalCoordinate.isEmpty()) terminalCoordinate.enqueue(terminalCoordinate.dequeue());
            }
            GamePlay.hiddenDungeonIsUpPressed = false;
        }
        if (GamePlay.hiddenDungeonIsDownPressed) {
            if (hiddenDungeon[playerX + 1][playerY] != '-' && hiddenDungeon[playerX + 1][playerY] != '|') {
                hiddenDungeon[playerX][playerY] = ' ';
                playerX++;
                if (hiddenDungeon[playerX][playerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == playerX && key.getPosition().getVertexY() == playerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                }
                hiddenDungeon[playerX][playerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (playerX + 1 == vertexDoor.getVertexX() && playerY == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 5; j++) {
                            hiddenDungeon[playerX + 1][playerY - (j * direction1)] = ' ';
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
                        initialCoordinate.dequeue();
                        terminalCoordinate.dequeue();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 5; j++) {
                                    hiddenDungeon[playerX + 1][playerY - (j * direction1)] = ' ';
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
                                initialCoordinate.dequeue();
                                terminalCoordinate.dequeue();
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
                if (!initialCoordinate.isEmpty()) initialCoordinate.enqueue(initialCoordinate.dequeue());
                if (!terminalCoordinate.isEmpty()) terminalCoordinate.enqueue(terminalCoordinate.dequeue());
            }
            GamePlay.hiddenDungeonIsDownPressed = false;
        }
        if (GamePlay.hiddenDungeonIsRightPressed) {
            if (hiddenDungeon[playerX][playerY + 1] != '|' && hiddenDungeon[playerX][playerY + 1] != '-') {
                hiddenDungeon[playerX][playerY] = ' ';
                playerY++;
                if (hiddenDungeon[playerX][playerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == playerX && key.getPosition().getVertexY() == playerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                }
                hiddenDungeon[playerX][playerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (playerX == vertexDoor.getVertexX() && playerY + 1 == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            hiddenDungeon[playerX - (j * direction1)][playerY + 1] = ' ';
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
                        initialCoordinate.dequeue();
                        terminalCoordinate.dequeue();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    hiddenDungeon[playerX - (j * direction1) ][playerY + 1] = ' ';
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
                                initialCoordinate.dequeue();
                                terminalCoordinate.dequeue();
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
                if (!initialCoordinate.isEmpty()) initialCoordinate.enqueue(initialCoordinate.dequeue());
                if (!terminalCoordinate.isEmpty()) terminalCoordinate.enqueue(terminalCoordinate.dequeue());

            }
            GamePlay.hiddenDungeonIsRightPressed = false;
        }
        if (GamePlay.hiddenDungeonIsLeftPressed) {
            if (hiddenDungeon[playerX][playerY - 1] != '|' && hiddenDungeon[playerX][playerY - 1] != '-') {
                hiddenDungeon[playerX][playerY] = ' ';
                playerY--;
                if (hiddenDungeon[playerX][playerY] == '†') {
                    for (int k = 0; k < keys.size(); k++) {
                        if (keys.peek() != null) {
                            Key key = (Key) keys.peek();
                            if (key.getPosition().getVertexX() == playerX && key.getPosition().getVertexY() == playerY) {
                                yourKeys.enqueue(key);
                            }
                        }
                        keys.enqueue(keys.dequeue());
                    }
                }
                hiddenDungeon[playerX][playerY] = 'P';
            }
            for (int i = 0; i < doors.size(); i++) {
                Vertex vertexDoor = (Vertex) doors.peek();
                boolean isLocked = (boolean) lock.peek();
                int direction1 = (int) direction.peek();
                Key doorKey = (Key) keys.peek();
                if (playerX == vertexDoor.getVertexX() && playerY - 1 == vertexDoor.getVertexY()) {
                    if (!isLocked) {
                        for (int j = 0; j < 3; j++) {
                            hiddenDungeon[playerX - (j * direction1)][playerY - 1] = ' ';
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
                        initialCoordinate.dequeue();
                        terminalCoordinate.dequeue();
                    } else {
                        if (!yourKeys.isEmpty()) {
                            Key key = (Key) yourKeys.peek();
                            if (key.equals(doorKey)) {
                                for (int j = 0; j < 3; j++) {
                                    hiddenDungeon[playerX - (j * direction1)][playerY - 1] = ' ';
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
                                initialCoordinate.dequeue();
                                terminalCoordinate.dequeue();
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
                if (!initialCoordinate.isEmpty()) initialCoordinate.enqueue(initialCoordinate.dequeue());
                if (!terminalCoordinate.isEmpty()) terminalCoordinate.enqueue(terminalCoordinate.dequeue());
            }
            GamePlay.hiddenDungeonIsLeftPressed = false;
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



}
