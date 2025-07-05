import enigma.console.TextAttributes;

import java.awt.*;
import java.util.Scanner;

public class Paint {

    public class Pixel {
        private Color color;
        private char symbol;
        public Pixel(Color color,char symbol) {
            this.symbol = symbol;
            this.color = color;
        }
        public char getSymbol() {
            return symbol;
        }

        public void setSymbol(char symbol) {
            this.symbol = symbol;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

    }
    private int row;
    private int column;
    private Pixel[][] grid;
    private boolean isPaintFinished = false;
    private Game game;
    private GamePlay gamePlay;
    private int paintIndexX = -1;
    private int paintIndexY = -1;
    private Vertex vertex = null;
    private Color color = Color.RED;
    private Scanner scanner;

    public Paint(int row, int column,Game game,GamePlay gamePlay) {
        this.row = row;
        this.column = column;
        grid = new Pixel[row][column];
        this.gamePlay = gamePlay;
        scanner = new Scanner(System.in);
    }

    public void start() {
        createGrid();
        gamePlay.setMouseX(-1);
        gamePlay.setMouseY(-1);
        while (true) {
            game.Clear();
            if (gamePlay.isPalletteMode()) {
                colorPalette();
            }
            printColorStatic();
            printGrid();
            paintGrid();
            chooseColor();
            if (!gamePlay.isPaintMode()) {
                scanner.close();
                break;
            }
            try {
                Thread.sleep(100);
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

    public void colorPalette() {
        game.Clear();
        boolean isColorAccepted = false;
        game.cn.getTextWindow().setCursorPosition(20,0);
        System.out.println("Welcome Color Palette");
        int r = 0;
        int g = 0;
        int b = 0;
        while(!isColorAccepted) {
            gamePlay.clearPart(0, 1, game, 8, 70);
            while (true) {
                gamePlay.clearPart(0, 1, game, 5, 70);
                System.out.println("Write RGB Code of Color :");
                System.out.print("R: ");
                String rStr = scanner.nextLine();
                System.out.print("G: ");
                String gStr = scanner.nextLine();
                System.out.print("B: ");
                String bStr = scanner.nextLine();
                if ((rStr.matches("\\d+") && gStr.matches("\\d+") && bStr.matches("\\d+"))) {
                    r = Integer.parseInt(rStr);
                    g = Integer.parseInt(gStr);
                    b = Integer.parseInt(bStr);
                    if ((r < 256 && g < 256 && b < 256) && (r > -1 && g > -1 && b > -1)) {
                        break;
                    }
                }
            }
            
            while (true) {
                gamePlay.clearPart(0, 5, game, 5, 70);
                System.out.print("Your color ---> ");
                game.cn.setTextAttributes(new TextAttributes(new Color(r, g, b)));
                System.out.println('█');
                game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
                System.out.println("Do you accept the color?");
                String choice = scanner.nextLine();
                if (choice.equals("yes") || choice.equals("y")) {
                    isColorAccepted = true;
                    color = new Color(r, g, b);
                    break;
                } else if (choice.equals("no") || choice.equals("n")) {
                    break;
                }
            }
        }
        gamePlay.setPalletteMode(false);
    }

    public void createGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Pixel pixel = new Pixel(new Color(255,255,255),'█');
                grid[i][j] = pixel;
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                game.cn.setTextAttributes(new TextAttributes(grid[i][j].getColor()));
                System.out.print(grid[i][j].getSymbol());
                game.cn.setTextAttributes(new TextAttributes(Color.WHITE));
            }
            System.out.println();
        }
    }
    public void paintGrid() {
        if ((gamePlay.getMouseY() != -1 && gamePlay.getMouseX() != -1) && (gamePlay.getMouseY() < grid.length) && (gamePlay.getMouseX() < grid[0].length)) {
            grid[gamePlay.getMouseY()][gamePlay.getMouseX()].setColor(color);
            //grid[gamePlay.getMouseY()][gamePlay.getMouseX()] = ' ';
        }
    }
}
