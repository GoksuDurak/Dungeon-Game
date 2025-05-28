import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

class GameTest {
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        CircularSingleLinkedList list = new CircularSingleLinkedList();
        list.display();
        System.out.println();
        list.add(12);
        list.display();
        list.delete(12);

        System.out.println();

        list.add(312);

        list.display();
        System.out.println();

        list.add(45);

        list.display();
        System.out.println();

        sc.nextLine();
        Game game = new Game();
        GamePlay gamePlay = new GamePlay();
        gamePlay.Start();
    }
}
//TODO List
/*
*envanter zırh giyme
* */
