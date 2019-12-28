package iraldorsquest.main;

import iraldorsquest.main.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(10, 10, "abc", 3);
        View view = new View(game);
        game.setView(view);
        game.run();
    }

}