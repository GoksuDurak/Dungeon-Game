import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Random;

public class Forest {
    private final Random rand = new Random();

    public int getPlayerForestY() {
        return playerForestY;
    }

    public void setPlayerForestY(int playerForesY) {
        this.playerForestY = playerForesY;
    }

    public int getPlayerForestX() {
        return playerForestX;
    }

    public void setPlayerForestX(int playerForestX) {
        this.playerForestX = playerForestX;
    }

    //-----------------Computer---------------------
    public class Computer {
        private int computerX = 0;
        private int computerY = 0;
        private int firstComputerX = 0;
        private int firstComputerY = 0;
        private int treasureX = 0;
        private int treasureY = 0;
        private boolean isRandomMove = false;
        private boolean isTargetFound = false;
        private Stack targetPath;

        public Computer() {}

        public int getComputerX() {
            return computerX;
        }

        public void setComputerX(int computerX) {
            this.computerX = computerX;
        }

        public int getComputerY() {
            return computerY;
        }

        public void setComputerY(int computerY) {
            this.computerY = computerY;
        }

        public void pathFindingRobot() {

            if ((treasureX == 0 && treasureY == 0 && computerX == 0 && computerY == 0) || isRandomMove) {
                //start
                do {
                    treasureY = rand.nextInt(forestMatrix.length);
                    treasureX = rand.nextInt(forestMatrix[0].length);
                } while (forestMatrix[treasureY][treasureX] != ' ');
                //forestMatrix[treasureY][treasureX] = 'T';

                do {
                    computerX = rand.nextInt(forestMatrix[0].length - 1);
                    computerY = rand.nextInt(forestMatrix.length - 1);
                } while (forestMatrix[computerY][computerX] != ' ');
                forestMatrix[computerY][computerX] = 'C';
                firstComputerX = computerX;
                firstComputerY = computerY;
            } else {
                computerX = treasureX;
                computerY = treasureY;
                treasureX = firstComputerX;
                treasureY = firstComputerY;
                //forestMatrix[treasureY][treasureX] = 'T';
                forestMatrix[computerY][computerX] = 'C';
                firstComputerX = computerX;
                firstComputerY = computerY;
            }
            Vertex targetVertex = new Vertex(treasureX, treasureY);
            boolean[][] visitedPoints = new boolean[forestMatrix.length][forestMatrix[0].length];
            for (int i = 0; i < forestMatrix.length; i++) {
                for (int j = 0; j < forestMatrix[0].length; j++) {
                    if (forestMatrix[i][j] == '|' || forestMatrix[i][j] == '-' ) {
                        visitedPoints[i][j] = true;
                    }
                }
            }

            Vertex computerVertex = new Vertex(computerX, computerY);
            Stack way = new Stack(1);
            CircularQueue queue = new CircularQueue(forestMatrix.length * forestMatrix[0].length);
            //take start point
            way.push(computerVertex);
            queue.enqueue(way);
            visitedPoints[computerVertex.getVertexY()][computerVertex.getVertexX()] = true;

            while (!queue.isEmpty()) {
                Stack currentPath = (Stack) queue.dequeue();
                Vertex currentVertex = (Vertex) currentPath.peek();

                if (currentVertex.equals(targetVertex)) {
                    way = currentPath;
                    break;
                }

                while (true) {
                    Vertex nextPosition = selectRandomPosition(visitedPoints,currentVertex);
                    if (nextPosition == null) break;
                    Stack temp = copyStack(currentPath);
                    temp.push(nextPosition);
                    queue.enqueue(temp);
                    visitedPoints[nextPosition.getVertexY()][nextPosition.getVertexX()] = true;
                }
            }
            targetPath = new Stack(way.size());
            while (!way.isEmpty()) {
                Vertex lastVertex = (Vertex) way.pop();
                if (!lastVertex.equals(computerVertex) && forestMatrix[lastVertex.getVertexY()][lastVertex.getVertexX()] == ' ') {
                    //forestMatrix[lastVertex.getVertexY()][lastVertex.getVertexX()] = '.';
                }
                targetPath.push(lastVertex);
            }
            targetPath.pop();

        }
        public void moveRobotRandom(boolean flag) {
            isRandomMove = flag;
            while (!isTargetFound) {
                pathFindingRobot();
                isTargetFound = true;
            }
            if (!targetPath.isEmpty()) {
                Vertex targetVertex = (Vertex) targetPath.pop();
                forestMatrix[computerY][computerX] = ' ';
                if (forestMatrix[targetVertex.getVertexY()][targetVertex.getVertexX()] == 'P') {
                    if (playerLife - 1 > 0) {
                        playerLife--;
                        forestMatrix[1][1] = 'P';
                        playerForestX = 1;
                        playerForestY = 1;
                    } else {
                        System.out.println("Game Over You Loose");
                        isGameFinished = true;
                    }
                }
                forestMatrix[targetVertex.getVertexY()][targetVertex.getVertexX()] = 'C';
                if (targetPath.isEmpty()) {
                    //son eleman
                    forestMatrix[targetVertex.getVertexY()][targetVertex.getVertexX()] = ' ';
                }
                computerX = targetVertex.getVertexX();
                computerY = targetVertex.getVertexY();
            } else {
                isTargetFound = false;
            }
        }
        public Vertex selectRandomPosition(boolean[][] visitedPoints , Vertex currentVertex) {
            Stack randomLocation = new Stack(4);
            if (currentVertex.getVertexY() - 1 > 0) {
                if (!visitedPoints[currentVertex.getVertexY() - 1][currentVertex.getVertexX()]) {
                    // up direction
                    randomLocation.push(new Vertex(currentVertex.getVertexX(), currentVertex.getVertexY() - 1));
                }
            }
            if (currentVertex.getVertexY() + 1 < visitedPoints.length) {
                if (!visitedPoints[currentVertex.getVertexY() + 1][currentVertex.getVertexX()]) {
                    // down direction
                    randomLocation.push(new Vertex(currentVertex.getVertexX(), currentVertex.getVertexY() + 1));
                }
            }
            if (currentVertex.getVertexX() + 1 < visitedPoints[0].length) {
                if (!visitedPoints[currentVertex.getVertexY()][currentVertex.getVertexX() + 1]) {
                    // right direction
                    randomLocation.push(new Vertex(currentVertex.getVertexX() + 1, currentVertex.getVertexY()));
                }
            }
            if (currentVertex.getVertexX() - 1 > 0) {
                if (!visitedPoints[currentVertex.getVertexY()][currentVertex.getVertexX() - 1]) {
                    // left direction
                    randomLocation.push(new Vertex(currentVertex.getVertexX() - 1, currentVertex.getVertexY()));
                }
            }
            if (randomLocation.isEmpty()) {
                return null;
            }
            int randomIndex = rand.nextInt(randomLocation.size());
            for (int i = 0; i < randomIndex; i++) {
                randomLocation.pop();
            }
            return (Vertex) randomLocation.pop();
        }
        public Stack copyStack(Stack stack) {
            Stack copy = new Stack(stack.size() + 1);
            Stack temp = new Stack(stack.size());

            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }

            while (!temp.isEmpty()) {
                copy.push(temp.peek());
                stack.push(temp.pop());
            }
            return copy;
        }

    }
    //-----------------input list---------------------
    public class InputList {
        private int time = 0;
        public InputList() {}
        public void addWoodToForest() {
            if (forestMatrisWoods.size() < 40) {
                if (!woodList.isEmpty()) { // sorun buarada
                    woodList.dequeue();
                }
                int randomNum = rand.nextInt(4);
                Color color = null;
                if (randomNum == 0) {
                    color = Color.RED;
                } else if (randomNum == 1) {
                    color = Color.GREEN;
                } else if (randomNum == 2) {
                    color = Color.BLUE;
                } else {
                    color = Color.ORANGE;
                }
                int x = 0;
                int y = 0;
                do {
                    x = rand.nextInt(forestMatrix.length - 1);
                    y = rand.nextInt(forestMatrix[0].length - 1);
                } while (forestMatrix[x][y] != ' ');
                Wood wood = new Wood('\\', color);
                wood.setX(x);
                wood.setY(y);
                woodList.enqueue(wood);
                forestMatrisWoods.enqueue(wood);
            }
        }
        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public LinkedQueue getWoodList() {
            return woodList;
        }

    }
    public class Wood {
        private char woodChar;
        private int x;
        private int y;

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

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public char getWoodChar() {
            return woodChar;
        }

        public void setWoodChar(char woodChar) {
            this.woodChar = woodChar;
        }

        private Color color;
        public Wood(char woodChar, Color color) {
            this.woodChar = woodChar;
            this.color = color;
        }
    }
    //-----------forest------------
    private int x;
    private int y;
    private char[][] forestMatrix;
    private boolean isGameFinished = false;
    private boolean isGameWin = false;
    private int playerForestX = 1;
    private int playerForestY = 1;
    private int playerLife = 6;
    private int time = 0;
    private int currentDisplayTime = 0;
    private int currentMoveTime = 0;
    private int wantedMoveTime = 0;
    private int woodenCount = 0;
    private InputList inputList;
    private LinkedQueue woodList;
    private LinkedQueue forestMatrisWoods;
    public Forest() {}


    public boolean isGameWin() {
        return isGameWin;
    }

    public void setGameWin(boolean gameWin) {
        isGameWin = gameWin;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }


    public char[][] getForestMatrix() {
        return forestMatrix;
    }

    public void setForestMatrix(char[][] forestMatrix) {
        this.forestMatrix = forestMatrix;
    }
    public void startGame() {
        woodList = new LinkedQueue();
        forestMatrisWoods = new LinkedQueue();
        int width = rand.nextInt(15) + 25;
        int height = rand.nextInt(15) + 25; // add all npc deiiferent object
        forestMatrix = new char[height][width];
        creatForestMatrix();
        inputList = new InputList();
        Computer computer = new Computer();
        Computer computer1 = new Computer();
        Computer computer2 = new Computer();
        int randomNum = rand.nextInt((forestMatrix.length - 1) * (forestMatrix[0].length - 1));
        randomNum = randomNum / 8;
        if (randomNum > 21) {
            randomNum = rand.nextInt(25);
        }
        fillForestMatrix(rand,randomNum);
        while (true) {
            Game.Clear();
            computer.moveRobotRandom(true);
            computer1.moveRobotRandom(false);
            computer2.moveRobotRandom(true);
            printForestMatrix();
            move();
            time++;
            if (time % 10 == 0) {
                currentDisplayTime++;
            }
            inputList.setTime(inputList.getTime() + 1);
            if (10 == inputList.getTime()) {
                inputList.addWoodToForest();
                inputList.setTime(0);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (isGameFinished) {
                isGameFinished = false;
                if (!isGameWin) {
                     time = 0;
                     x = 0;
                     y = 0;
                     forestMatrix  = new char[x][y];;
                     playerForestX = 1;
                     playerForestY = 1;
                     playerLife = 6;
                     currentMoveTime = 0;
                     wantedMoveTime = 0;
                     woodenCount = 0;
                     InputList inputList = null;
                     currentDisplayTime = 0;
                }
                Game.Clear();
                break;
            }
        }
        //--------burada oyun oynanacak bazı değerlere erişim izni ver----------(boolean değer ver ve onunla  input al buradan)
    }
    //-------------------fill forest matrix with woods------------------
    public void fillForestMatrix(Random rand, int count) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < count; i++) {
            if (forestMatrisWoods.size() < 40) {
                do {
                    row = rand.nextInt(forestMatrix.length - 1);
                    col = rand.nextInt(forestMatrix[0].length - 1);
                } while (forestMatrix[row][col] != ' ');
                int randomNum = rand.nextInt(4);
                Color color = null;
                if (randomNum == 0) {
                    color =Color.RED;
                } else if (randomNum == 1) {
                    color =Color.GREEN;
                } else if (randomNum == 2) {
                    color =Color.BLUE;
                } else {
                    color =Color.ORANGE;
                }
                Wood wood = new Wood('\\', color);
                wood.setX(row);
                wood.setY(col);
                woodList.enqueue(wood);
                forestMatrisWoods.enqueue(wood);
                forestMatrix[row][col] = wood.getWoodChar();
            }
        }
    }
    //-------------------create forest matrix------------------
    public void creatForestMatrix() {
        for (int i = 0; i < forestMatrix.length; i++) {
            for (int j = 0; j < forestMatrix[i].length; j++) {
                if (i == 0 || i == forestMatrix.length - 1) {
                    forestMatrix[i][j] = '-';
                } else if (j == 0 || j == forestMatrix[i].length - 1) {
                    forestMatrix[i][j] = '|';
                } else {
                    forestMatrix[i][j] = ' ';
                }
            }
        }
        forestMatrix[1][1] = 'P';
        playerForestX = 1;
        playerForestY = 1;
    }
    //-------------------print forest matrix------------------
    /*
    public void printForestMatrix() {
        for (int i = 0; i < forestMatrix.length; i++) {
            for (int j = 0; j < forestMatrix[i].length; j++) {
                if (forestMatrix[i][j] == '\\') {
                    Game.cn.setTextAttributes(new TextAttributes(Color.ORANGE));
                }
                System.out.print(forestMatrix[i][j]);
                Game.cn.setTextAttributes(new TextAttributes(Color.white));
            }
            System.out.println();
        }
    }

     */

     public void printForestMatrix() {
        for (int i = 0; i < forestMatrix.length; i++) {
            for (int j = 0; j < forestMatrix[i].length; j++) {
                if (i != 0 && i != forestMatrix.length - 1  && j != 0 && j != forestMatrix[0].length - 1) {
                    int woodListSize = forestMatrisWoods.size();
                    for (int k = 0; k < woodListSize; k++) {
                        Wood wood = (Wood) forestMatrisWoods.peek();
                        forestMatrisWoods.enqueue(forestMatrisWoods.dequeue());
                        if (i == wood.getX() && j == wood.getY()) {
                            forestMatrix[i][j] = wood.getWoodChar();
                            Game.cn.setTextAttributes(new TextAttributes(wood.getColor()));
                        }
                    }
                }
                System.out.print(forestMatrix[i][j]);
                Game.cn.setTextAttributes(new TextAttributes(Color.white));
            }
            System.out.println();
        }
    }

    //-----------------movement of player------------------
    public void move() {
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 0);
        System.out.print("Time : ");
        System.out.println(currentDisplayTime);
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 1);
        System.out.print("Your wood count : ");
        System.out.println(woodenCount + " Target wood count : 256");
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 2);
        System.out.print("Wood list : ");
        int woodenSize = woodList.size();
        for (int i = 0; i < woodenSize; i++) {
            Wood wood = (Wood) woodList.peek();
            woodList.enqueue(woodList.dequeue());
            Game.cn.setTextAttributes(new TextAttributes(wood.getColor()));
            System.out.print(wood.getWoodChar() + " ");
            Game.cn.setTextAttributes(new TextAttributes(Color.white));
        }
        System.out.println();
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 3);
        System.out.print("Player life : ");
        for (int i = 0; i < playerLife; i++) {
            Game.cn.setTextAttributes(new TextAttributes(Color.RED));
            System.out.print("♥"); // ♥
            Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        }
        System.out.println();
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 4);
        System.out.print("Wooden types : ");
        for (int i = 0; i< 4 ; i++) {
            int quantity = 0;
            if (i == 0) {
                Game.cn.setTextAttributes(new TextAttributes(Color.RED));
                quantity = 2;
            } else if (i == 1) {
                Game.cn.setTextAttributes(new TextAttributes(Color.BLUE));
                quantity = 6;
            } else if (i == 2) {
                Game.cn.setTextAttributes(new TextAttributes(Color.GREEN));
                quantity = 4;
            } else {
                Game.cn.setTextAttributes(new TextAttributes(Color.ORANGE));
                quantity = 8;
            }
            System.out.print("\\");
            Game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            System.out.print(" --> "+ quantity);
            if (i < 3) {
                System.out.print(" , ");
            }
        }
        System.out.println();
        if (GamePlay.forestIsRigthPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerForestX][playerForestY + 1] != '|' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerForestX][playerForestY] = ' ';
                playerForestY++;
                if (forestMatrix[playerForestX][playerForestY] == '\\') {
                    int forestWoodListSize = forestMatrisWoods.size();
                    for (int k = 0; k < forestWoodListSize; k++) {
                        Wood wood = (Wood) forestMatrisWoods.peek();
                        if (playerForestX == wood.getX() && playerForestY == wood.getY()) {
                            if (wood.getColor().equals(Color.RED)) {
                                woodenCount += 2;
                            } else if (wood.getColor().equals(Color.GREEN)) {
                                woodenCount += 4;
                            } else if (wood.getColor().equals(Color.BLUE)) {
                                woodenCount += 6;
                            } else if (wood.getColor().equals(Color.ORANGE)) {
                                woodenCount += 8;
                            }
                            //---------------win condition------------
                            if (woodenCount > 256) {
                                isGameFinished = true;
                                isGameWin = true;
                            }
                            forestMatrisWoods.dequeue();
                        }
                        forestMatrisWoods.enqueue(forestMatrisWoods.dequeue());
                    }
                }
                forestMatrix[playerForestX][playerForestY] = 'P';
            } else if (forestMatrix[playerForestX][playerForestY] == 'C') {
                if (playerLife - 1 > 0) {
                    playerLife--;
                    forestMatrix[1][1] = 'P';
                    playerForestX = 1;
                    playerForestY = 1;
                } else {
                    System.out.println("Game Over You Loose");
                    isGameFinished = true;
                }
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsRigthPressed = false;
        } else if (GamePlay.forestIsLeftPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerForestX][playerForestY - 1] != '|' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerForestX][playerForestY] = ' ';
                playerForestY--;
                if (forestMatrix[playerForestX][playerForestY] == '\\') {
                    int forestWoodListSize = forestMatrisWoods.size();
                    for (int k = 0; k < forestWoodListSize; k++) {
                        Wood wood = (Wood) forestMatrisWoods.peek();
                        if (playerForestX == wood.getX() && playerForestY == wood.getY()) {
                            if (wood.getColor().equals(Color.RED)) {
                                woodenCount += 2;
                            } else if (wood.getColor().equals(Color.GREEN)) {
                                woodenCount += 4;
                            } else if (wood.getColor().equals(Color.BLUE)) {
                                woodenCount += 6;
                            } else if (wood.getColor().equals(Color.ORANGE)) {
                                woodenCount += 8;
                            }
                            //---------------win condition------------
                            if (woodenCount > 256) {
                                isGameFinished = true;
                                isGameWin = true;
                            }
                            forestMatrisWoods.dequeue();
                        }
                        forestMatrisWoods.enqueue(forestMatrisWoods.dequeue());
                    }
                } else if (forestMatrix[playerForestX][playerForestY] == 'C') {
                    if (playerLife - 1 > 0) {
                        playerLife--;
                        forestMatrix[1][1] = 'P';
                        playerForestX = 1;
                        playerForestY = 1;
                    } else {
                        System.out.println("Game Over You Loose");
                        isGameFinished = true;
                    }
                }
                forestMatrix[playerForestX][playerForestY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsLeftPressed = false;

        } else if (GamePlay.forestIsDownPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerForestX + 1][playerForestY] != '-' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerForestX][playerForestY] = ' ';
                playerForestX++;
                if (forestMatrix[playerForestX][playerForestY] == '\\') {
                    int forestWoodListSize = forestMatrisWoods.size();
                    for (int k = 0; k < forestWoodListSize; k++) {
                        Wood wood = (Wood) forestMatrisWoods.peek();
                        if (playerForestX == wood.getX() && playerForestY == wood.getY()) {
                            if (wood.getColor().equals(Color.RED)) {
                                woodenCount += 2;
                            } else if (wood.getColor().equals(Color.GREEN)) {
                                woodenCount += 4;
                            } else if (wood.getColor().equals(Color.BLUE)) {
                                woodenCount += 6;
                            } else if (wood.getColor().equals(Color.ORANGE)) {
                                woodenCount += 8;
                            }
                            //---------------win condition------------
                            if (woodenCount > 256) {
                                isGameFinished = true;
                                isGameWin = true;
                            }
                            forestMatrisWoods.dequeue();
                        }
                        forestMatrisWoods.enqueue(forestMatrisWoods.dequeue());
                    }
                } else if (forestMatrix[playerForestX][playerForestY] == 'C') {
                    if (playerLife - 1 > 0) {
                        playerLife--;
                        forestMatrix[1][1] = 'P';
                        playerForestX = 1;
                        playerForestY = 1;
                    } else {
                        System.out.println("Game Over You Loose");
                        isGameFinished = true;
                    }
                }
                forestMatrix[playerForestX][playerForestY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsDownPressed = false;

        } else if (GamePlay.forestIsUpPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerForestX - 1][playerForestY] != '-' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerForestX][playerForestY] = ' ';
                playerForestX--;
                if (forestMatrix[playerForestX][playerForestY] == '\\') {
                    int forestWoodListSize = forestMatrisWoods.size();
                    for (int k = 0; k < forestWoodListSize; k++) {
                        Wood wood = (Wood) forestMatrisWoods.peek();
                        if (playerForestX == wood.getX() && playerForestY == wood.getY()) {
                            if (wood.getColor().equals(Color.RED)) {
                                woodenCount += 2;
                            } else if (wood.getColor().equals(Color.GREEN)) {
                                woodenCount += 4;
                            } else if (wood.getColor().equals(Color.BLUE)) {
                                woodenCount += 6;
                            } else if (wood.getColor().equals(Color.ORANGE)) {
                                woodenCount += 8;
                            }
                            //---------------win condition------------
                            if (woodenCount > 256) {
                                isGameFinished = true;
                                isGameWin = true;
                            }
                            forestMatrisWoods.dequeue();
                        }
                        forestMatrisWoods.enqueue(forestMatrisWoods.dequeue());
                    }
                } else if (forestMatrix[playerForestX][playerForestY] == 'C') {
                    if (playerLife - 1 > 0) {
                        playerLife--;
                        forestMatrix[1][1] = 'P';
                        playerForestX = 1;
                        playerForestY = 1;
                    } else {
                        System.out.println("Game Over You Loose");
                        isGameFinished = true;
                    }
                }
                forestMatrix[playerForestX][playerForestY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsUpPressed = false;
        }
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
