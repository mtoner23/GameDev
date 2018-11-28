package PacMan;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Pacman extends Arc implements Commons {
    public int x,y;
    Direction direction;

    public Pacman(Snake s){
        this.x = s.head.x;
        this.y = s.head.y;
        this.direction = s.head.direction;
        this.setCenterX(this.x + GRID_SIZE/2);
        this.setCenterY(this.y + GRID_SIZE/2);
        this.setRadiusX(GRID_SIZE/2);
        this.setRadiusY(GRID_SIZE/2);
        this.setStartAngle(45);
        this.setLength(270);
        this.setFill(Color.YELLOW);
        this.setType(ArcType.ROUND);
    }

    public void act(){
        switch (this.direction) {
            case EAST:
                if(grid[this.x/GRID_SIZE + 1][this.y/GRID_SIZE] != 0){
                    setX(this.x - GRID_SIZE);
                }
                break;
            case WEST:
                if(grid[this.x/GRID_SIZE - 1][this.y/GRID_SIZE] != 0){
                    setX(this.x - GRID_SIZE);
                }
                break;
            case SOUTH:
                if(grid[this.x/GRID_SIZE][this.y/GRID_SIZE + 1] != 0){
                    setY(this.y - GRID_SIZE);
                }
                break;
            case NORTH:
                if(grid[this.x/GRID_SIZE][this.y/GRID_SIZE - 1] != 0){
                    setY(this.y - GRID_SIZE);
                }
                break;
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
