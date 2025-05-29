import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Random;

public class Forest {
    private final Random rand = new Random();
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
    public class inputList {
        private CircularSingleLinkedList woodList = new CircularSingleLinkedList();
        public inputList() {}
        public void fillInputList(int count) {
            for (int i = 0; i < count; i++) {
                woodList.add("\\");
            }
        }
        public void addWoodToForest() {
            int x = 0;
            int y = 0;
            do {
                x = rand.nextInt(forestMatrix.length - 1);
                y = rand.nextInt(forestMatrix[0].length - 1);
            } while (forestMatrix[x][y] != ' ');
            forestMatrix[x][y] = '\\';
        }
    }
    private int x;
    private int y;
    private char[][] forestMatrix;
    //private Random rand = new Random();
    private int playerX = 0;
    private int playerY = 0;
    private int time = 0;
    private int currentMoveTime = 0;
    private int wantedMoveTime = 0;
    private int woodenCount = 0;
    public Forest(int x, int y) {
        forestMatrix = new char[x][y];
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

    public char[][] getForestMatrix() {
        return forestMatrix;
    }

    public void setForestMatrix(char[][] forestMatrix) {
        this.forestMatrix = forestMatrix;
    }
    public void startGame() {
        creatForestMatrix();
        inputList inputList = new inputList();
        inputList.fillInputList(20);

        Computer computer = new Computer();
        Computer computer1 = new Computer();
        Computer computer2 = new Computer();
        int randomNum = rand.nextInt((forestMatrix.length -1) * (forestMatrix[0].length -1) - forestMatrix[0].length / 4) + (forestMatrix.length -1) * (forestMatrix[0].length -1) / 4;
        randomNum = randomNum / 8;
        fillForestMatrix(rand,randomNum);
        while (true) {
            Game.Clear();
            computer.moveRobotRandom(true);
            computer1.moveRobotRandom(false);
            computer2.moveRobotRandom(true);
            printForestMatrix();
            move();
            time++;
            inputList.addWoodToForest();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //--------burada oyun oynanacak bazı değerlere erişim izni ver----------(boolean değer ver ve onunla  input al buradan)
    }

    public void fillForestMatrix(Random rand, int count) {
        int row = 0;
        int col = 0;
        for (int i = 0; i < count; i++) {
            do {
                row = rand.nextInt(forestMatrix.length - 1);
                col = rand.nextInt(forestMatrix[0].length - 1);
            } while (forestMatrix[row][col] != ' ');
            forestMatrix[row][col] = '\\';
        }
    }

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
        playerX = 1;
        playerY = 1;
    }
    public void printForestMatrix() {
        for (int i = 0; i < forestMatrix.length; i++) {
            for (int j = 0; j < forestMatrix[i].length; j++) {
                if (forestMatrix[i][j] == '\\') {
                    Game.cn.setTextAttributes(new TextAttributes(Color.orange));
                }
                System.out.print(forestMatrix[i][j]);
                Game.cn.setTextAttributes(new TextAttributes(Color.white));
            }
            System.out.println();
        }
    }

    public void move() {
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 0);
        System.out.print("Time : ");
        System.out.println(time);
        Game.cn.getTextWindow().setCursorPosition(forestMatrix[0].length + 1 , 1);
        System.out.print("Wood count : ");
        System.out.println(woodenCount);
        if (GamePlay.forestIsRigthPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerX][playerY + 1] != '|' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerX][playerY] = ' ';
                playerY++;
                if (forestMatrix[playerX][playerY] == '\\') {
                    woodenCount++;
                }
                forestMatrix[playerX][playerY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsRigthPressed = false;
        } else if (GamePlay.forestIsLeftPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerX][playerY - 1] != '|' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerX][playerY] = ' ';
                playerY--;
                if (forestMatrix[playerX][playerY] == '\\') {
                    woodenCount++;
                }
                forestMatrix[playerX][playerY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsLeftPressed = false;

        } else if (GamePlay.forestIsDownPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerX + 1][playerY] != '-' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerX][playerY] = ' ';
                playerX++;
                if (forestMatrix[playerX][playerY] == '\\') {
                    woodenCount++;
                }
                forestMatrix[playerX][playerY] = 'P';
            }
            wantedMoveTime = currentMoveTime + 2;
            GamePlay.forestIsDownPressed = false;

        } else if (GamePlay.forestIsUpPressed) {
            currentMoveTime = time;
            if (forestMatrix[playerX - 1][playerY] != '-' && currentMoveTime > wantedMoveTime) {
                currentMoveTime = 0;
                forestMatrix[playerX][playerY] = ' ';
                playerX--;
                if (forestMatrix[playerX][playerY] == '\\') {
                    woodenCount++;
                }
                forestMatrix[playerX][playerY] = 'P';
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
