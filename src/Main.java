import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game.snake1.direction = 1;
        Game.snake1.initpt.x = 200;
        Game.snake1.initpt.y = 200;
        Game.snake1.body.add(Game.snake1.initpt);
        for (int i = 1; i < 5; i++) {
            Point x1 = new Point();
            x1.x = Game.snake1.initpt.x + i * 10;
            x1.y = Game.snake1.initpt.y;
            Game.snake1.body.add(x1);

        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });

    }
}

