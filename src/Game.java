import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import enigma.console.TextAttributes;

public class Game {

    public static enigma.console.Console cn = Enigma.getConsole("Mouse and Keyboard",81,41,20);
    public TextMouseListener tmlis;
    public KeyListener klis;

    // ------ Standard variables for mouse and keyboard ------
    public int mousepr;          // mouse pressed?
    public int mousex, mousey;   // mouse text coords.
    public int keypr;   // key pressed?
    public int rkey;    // key   (for press/release)
    // ----------------------------------------------------


    Game() throws Exception {   // --- Contructor

        // ------ Standard code for mouse and keyboard ------ Do not change
        tmlis=new TextMouseListener() {
            public void mouseClicked(TextMouseEvent arg0) {}
            public void mousePressed(TextMouseEvent arg0) {
                if(mousepr==0) {
                    mousepr=1;
                    mousex=arg0.getX();
                    mousey=arg0.getY();
                }
            }
            public void mouseReleased(TextMouseEvent arg0) {}
        };
        cn.getTextWindow().addTextMouseListener(tmlis);
/*
        klis=new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(keypr==0) {
                    keypr=1;
                    rkey=e.getKeyCode();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) { // buara kod yazabilirsin
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        cn.getTextWindow().addKeyListener(klis);

 */
        // ----------------------------------------------------


    }
    public static void Clear() {
        cn.getTextWindow().setCursorPosition(0,0);

        for(int i=0;i<cn.getTextWindow().getRows();i++)
        {
            for (int j=0;j<cn.getTextWindow().getColumns()-1;j++)
            {
                cn.getTextWindow().setCursorPosition(j, i); // Her hücreye tek tek git
                cn.getTextWindow().output(' '); // Boşluk yaz
            }
        }
        cn.getTextWindow().setCursorPosition(0, 0); // tekrar başa dön
    }


}

