package PacMan;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class Ghost extends ImageView implements Commons{

    public Image redGhost = new Image("pacmanGhost.png");
    public int x,y;
    public Point target;
    public Direction direction;
    public int speed = GRID_SIZE/2;

    public Ghost(int x, int y){
        this.setImage(redGhost);
        this.setFitWidth(GRID_SIZE);
        this.setPreserveRatio(true);
        this.setX(x);
        this.setY(y);
        target = new Point();
    }

    public void setTarget(int x, int y){
        target.x = x;
        target.y = y;
    }

    public void act(){
        if(this.x % GRID_SIZE != 0 || this.y % GRID_SIZE != 0) {
            switch (this.direction) {
                case EAST:
                    setX(this.x + speed);
                    break;
                case WEST:
                    setX(this.x - speed);
                    break;
                case NORTH:
                    setY(this.y - speed);
                    break;
                case SOUTH:
                    setY(this.y + speed);
                    break;
            }
        }else {
            int edges = 0;
            edges += grid[x/GRID_SIZE + 1][y/GRID_SIZE];
            edges += grid[x/GRID_SIZE - 1][y/GRID_SIZE];
            edges += grid[x/GRID_SIZE][y/GRID_SIZE + 1];
            edges += grid[x/GRID_SIZE][y/GRID_SIZE - 1];
            if(edges > 2){

            }else{
                switch (this.direction) {
                    case EAST:
                        setX(this.x + speed);
                        break;
                    case WEST:
                        setX(this.x - speed);
                        break;
                    case NORTH:
                        setY(this.y - speed);
                        break;
                    case SOUTH:
                        setY(this.y + speed);
                        break;
                }
            }

        }
    }

    public void setDirection(){
        //Calculates what direction to move based on the target tile

    }

    public void setX(int x){
        this.x = x;
        super.setX(x);
    }

    public void setY(int y){
        this.y = y;
        super.setY(y);
    }
}
