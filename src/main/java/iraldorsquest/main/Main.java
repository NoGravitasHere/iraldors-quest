package iraldorsquest.main;

import iraldorsquest.main.Game;
import iraldorsquest.parser.*;

public class Main {

    public static void main(String[] args) {
        Game game = new Game(10, 10, "abc", 3);
        Parser parser = new Parser();
        View view = new View(game, parser);


        game.setView(view);
    }

    // public void run(){
    //     Scanner sc = new Scanner(System.in);
    //     while(!isFinished()){
    //         processInput(sc.nextLine());
    //     }
    //     sc.close();
    // }

}