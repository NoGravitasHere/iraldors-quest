package iraldorsquest.main;

import iraldorsquest.main.Game;

public class Main {

    public static void main(String[] args) {
        System.out.println(123);
        // JFrame frame = new JFrame();
        // frame.pack();
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game(10, 10, "abc", 3);
        game.run();
    }

}