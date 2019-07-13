import java.util.ArrayList;

public class Snake {
    Point initpt =new Point();

    ArrayList<Point> body = new ArrayList<Point>();
    int direction;

    int length() {
        int x = body.size();
        return x;
    }

    void move() {
        body.add(0, Game.newHead(this));
        body.remove((length()) - 1);
    }

    void grow() {
        body.add(0, Game.newHead(this));
    }

}



