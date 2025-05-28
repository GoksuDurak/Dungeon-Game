import enigma.console.TextAttributes;

import java.awt.*;

public class Animation {
    private int frames;

    public Animation(int frames) {
        this.frames = frames;
    }
    public void loadAnimation() {
        int counter = 0;
        int a = 0;
        for(int i = 0; i < frames; i++){
            if(counter == 5) {
                if(a == 0) {
                    Game.cn.setTextAttributes(new TextAttributes(Color.yellow));
                } else if (a == 1) {
                    Game.cn.setTextAttributes(new TextAttributes(Color.green));
                } else if (a == 2) {
                    Game.cn.setTextAttributes(new TextAttributes(Color.magenta));
                } else if (a == 3) {
                    Game.cn.setTextAttributes(new TextAttributes(Color.red));
                }
                System.out.print("_");
                counter = 0;
                a++;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        Game.cn.setTextAttributes(new TextAttributes(Color.white));
        int x = Game.cn.getTextWindow().getCursorX();
        int y = Game.cn.getTextWindow().getCursorY();
        Game.cn.getTextWindow().setCursorPosition(0,y);
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 20; j++){
                System.out.print(" ");
            }
        }
        Game.cn.getTextWindow().setCursorPosition(0,y);
    }
}
