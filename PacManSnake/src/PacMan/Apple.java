package PacMan;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Apple extends Circle implements Commons{
    public int x,y;
    public Random r;

    public Apple(){
        r = new Random();
        this.x = (r.nextInt(GRID_WIDTH - 2) + 1) * GRID_SIZE;
        this.y = (r.nextInt(GRID_HEIGHT - 2) + 1) * GRID_SIZE;
        super.setCenterX(this.x + GRID_SIZE/2);
        super.setCenterY(this.y + GRID_SIZE/2);
        super.setRadius(GRID_SIZE/2);
        super.setFill(Color.BLUE);
    }

    public void move(){
        this.x = (r.nextInt(GRID_WIDTH - 2) + 1) * GRID_SIZE;
        this.y = (r.nextInt(GRID_HEIGHT - 2) + 1) * GRID_SIZE;
        super.setCenterX(this.x + GRID_SIZE/2);
        super.setCenterY(this.y + GRID_SIZE/2);
    }

}
