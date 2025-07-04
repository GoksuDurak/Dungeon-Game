import enigma.console.TextAttributes;

import java.awt.*;
import java.util.LinkedList;

public class Paint {
    private int row;
    private int column;
    private char[][] grid;
    private boolean isPaintFinished = false;
    private Game game;
    private GamePlay gamePlay;
    private int paintIndexX = -1;
    private int paintIndexY = -1;
    private Vertex vertex = null;
    private Color color = Color.RED;
    private LinkedQueue paintedVertexQueue = new LinkedQueue();

    public Paint(int row, int column,Game game,GamePlay gamePlay) {
        this.row = row;
        this.row = column;
        grid = new char[row][column];
        this.gamePlay = gamePlay;
    }

    public void start() {
        createGrid();
        gamePlay.setMouseX(-1);
        gamePlay.setMouseY(-1);
        while (true) {
            game.Clear();
            printColorStatic();
            printGrid();
            paintGrid();
            chooseColor();
            if (isPaintFinished) {
                break;
            }
            try {
                Thread.sleep(121);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void printColorStatic() {
        game.cn.getTextWindow().setCursorPosition(grid[0].length + 1,0);
        System.out.print("Active color : ");
        game.cn.setTextAttributes(new TextAttributes(color));
        System.out.print("█");
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        game.cn.getTextWindow().setCursorPosition(grid[0].length + 1,1);
        System.out.print("Red   ");
        game.cn.setTextAttributes(new TextAttributes(Color.RED));
        System.out.print("█");
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        game.cn.getTextWindow().setCursorPosition(grid[0].length + 1,2);
        System.out.print("Blue  ");
        game.cn.setTextAttributes(new TextAttributes(Color.BLUE));
        System.out.print("█");
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        game.cn.getTextWindow().setCursorPosition(grid[0].length + 1,3);
        System.out.print("Green ");
        game.cn.setTextAttributes(new TextAttributes(Color.GREEN));
        System.out.print("█");
        game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
        game.cn.getTextWindow().setCursorPosition(0,0);
    }

    public void chooseColor() {
        if (gamePlay.getMouseX() != -1 && gamePlay.getMouseY() != -1) {
            if (gamePlay.getMouseY() == 1 && gamePlay.getMouseX() == grid[0].length + 7) {
                color = Color.RED;
            } else if (gamePlay.getMouseY() == 2 && gamePlay.getMouseX() == grid[0].length + 7) {
                color = Color.BLUE;
            } else if (gamePlay.getMouseY() == 3 && gamePlay.getMouseX() == grid[0].length + 7) {
                color = Color.GREEN;
            }
        }
    }

    public void createGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '█';
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int k = 0; k < paintedVertexQueue.size(); k++) {
                    Vertex paintedVertex = (Vertex) paintedVertexQueue.peek();
                    paintIndexX = paintedVertex.getVertexX();
                    paintIndexY = paintedVertex.getVertexY();
                    if ((paintIndexX != -1 && paintIndexY != -1) && paintIndexY == i && paintIndexX == j) {
                        game.cn.setTextAttributes(new TextAttributes(color));
                        paintIndexX = -1;
                        paintIndexY = -1;
                    }
                    paintedVertexQueue.enqueue(paintedVertexQueue.dequeue());
                }
                System.out.print(grid[i][j]);
                game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            }
            System.out.println();
        }
    }
    public void paintGrid() {
        if (gamePlay.getMouseY() != -1 && gamePlay.getMouseX() != -1) {
            paintIndexX = gamePlay.getMouseX();
            paintIndexY = gamePlay.getMouseY();
            vertex = new Vertex(paintIndexX, paintIndexY);
            paintedVertexQueue.enqueue(vertex);
            //grid[gamePlay.getMouseY()][gamePlay.getMouseX()] = ' ';
        }
    }
}
