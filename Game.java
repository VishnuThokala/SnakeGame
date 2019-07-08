import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static java.awt.event.KeyEvent.*;


public class Game extends JFrame {

    static Snake snake1 = new Snake();
    static Point food1 = new Point();
    static Point food2 = new Point();
    Random rand = new Random();


    public static Point newHead(Snake s1) {
        Point temp = new Point();
        if (s1.direction == 0) {
            temp.x = s1.body.get(0).x + 10;
            temp.y = s1.body.get(0).y;
        } else if (s1.direction == 1) {
            temp.x = s1.body.get(0).x;
            temp.y = s1.body.get(0).y + 10;

        } else if (s1.direction == 2) {
            temp.x = s1.body.get(0).x - 10;
            temp.y = s1.body.get(0).y;
        } else if (s1.direction == 3) {
            temp.x = s1.body.get(0).x;
            temp.y = s1.body.get(0).y - 10;
        }
        return (temp);
    }


    private Drw cnvs;

    void generateFood1() {
        food1.x = rand.nextInt(49) * 10;
        food1.y = rand.nextInt(49) * 10;
    }

    void generateFood2() {
        food2.x = rand.nextInt(49) * 10;
        food2.y = rand.nextInt(49) * 10;

    }

    public Game() {
        setBackground(Color.BLACK);
        setSize(500, 500);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setTitle("Snake Game");
         Container contain = getContentPane();
        cnvs = new Drw();
        cnvs.setPreferredSize(new Dimension(500, 500));
        contain.add(cnvs);
        setContentPane(contain);



        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch (evt.getKeyCode()) {
                    case VK_LEFT:
                        snake1.direction = 2;
                        break;
                    case VK_RIGHT:
                        snake1.direction = 0;
                        break;
                    case VK_UP:
                        snake1.direction = 3;
                        break;

                    case VK_DOWN:
                        snake1.direction = 1;
                        break;
                }
            }
        });
        ActionListener x = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        };
        new Timer(200, x).start();
    }


    boolean touch(Point x, Point y) {
        if (x.x == y.x && x.y == y.y) {
            return true;
        } else {
            return false;
        }
    }

    boolean collide() {
        if (snake1.body.get(0).y < 0 || snake1.body.get(0).x < 0 || snake1.body.get(0).y >=500|| snake1.body.get(0).x >=500)
            return true;
        else
            return false;
    }

    boolean eatItSelf() {
        boolean check=false;
        for (int i = 3; i < snake1.length(); i++) {
            if (touch(snake1.body.get(0), snake1.body.get(i))) {
                check = true;
                break;
            }
        }
        if (check == false) {
            return false;
        } else {
            return true;
        }
    }

    boolean checkfood1() {
        if (touch(food1, snake1.body.get(0))) {
            return true;
        } else {
            return false;
        }
    }

    boolean checkfood2() {
        if (touch(food2, snake1.body.get(0))) {
            return true;
        } else {
            return false;
        }
    }

    void update() {
        if (eatItSelf()) {
            setVisible(false);
        } else if (collide()) {
            setVisible(false);
        } else if (checkfood1()) {
            snake1.grow();
            generateFood1();
            generateFood2();
        } else if (checkfood2()) {
            snake1.grow();
            snake1.grow();
            generateFood1();
            generateFood2();
        } else {
            snake1.move();
        }
    }

    class Drw extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            for (int i = 0; i < Game.snake1.length(); i++) {
                g.fillRect(Game.snake1.body.get(i).x, Game.snake1.body.get(i).y, 10, 10);
            }
            g.setColor(Color.yellow);
            g.fillRect(food1.x, food1.y, 10, 10);
            g.setColor(Color.green);
            g.fillRect(food2.x, food2.y, 10, 10);

        }

    }

}