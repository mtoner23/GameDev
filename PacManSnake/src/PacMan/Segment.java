package PacMan;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Segment extends Circle implements Commons {

    public int x,y;
    public Direction direction;
    public boolean head;
    //public int head = 0;

    public Segment(int x, int y, boolean head) {
        super(x + GRID_SIZE/2, y + GRID_SIZE/2, GRID_SIZE / 2, Color.GREEN);
        this.x = x;
        this.y = y;
        direction = Direction.EAST;
        this.head = head;
        if(this.head){
            this.setFill(Color.RED);
        }else{
            this.setFill(Color.GREEN);
        }
    }

    public void setX(int x){
        this.x = x;
        super.setCenterX(x + GRID_SIZE/2);
    }

    public void setY(int y){
        this.y = y;
        super.setCenterY(y + GRID_SIZE/2);
    }
}
