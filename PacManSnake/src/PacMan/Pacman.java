package PacMan;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Pacman extends Arc implements Commons {
    public int x,y,speed;
    Direction direction, desireDirection;

    public Pacman(Snake s){
        this.x = s.head.x;
        this.y = s.head.y;
        this.speed = 5;
        this.direction = s.head.direction;
        this.desireDirection = this.direction;
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
        }else{
            switch (this.desireDirection) {
                case EAST:
                    if (this.x + GRID_SIZE >= BOARD_WIDTH) {
                        //Teleport
                    } else if (grid[this.x / GRID_SIZE + 1][this.y / GRID_SIZE] != 4) {
                        this.direction = Direction.EAST;
                        this.setStartAngle(45);
                    }
                    break;
                case WEST:
                    if (this.x - GRID_SIZE < 0) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE - 1][this.y / GRID_SIZE] != 4) {
                        this.direction = Direction.WEST;
                        this.setStartAngle(180+45);
                    }
                    break;
                case SOUTH:
                    if (this.y + GRID_SIZE >= BOARD_HEIGHT) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE][this.y / GRID_SIZE + 1] != 4) {
                        this.direction = Direction.SOUTH;
                        this.setStartAngle(270+45);
                    }
                    break;
                case NORTH:
                    if (this.y - GRID_SIZE < 0) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE][this.y / GRID_SIZE - 1] != 4) {
                        this.direction = Direction.NORTH;
                        this.setStartAngle(90+45);
                    }
                    break;
            }

            switch (this.direction) {
                case EAST:
                    if (this.x + GRID_SIZE >= BOARD_WIDTH) {
                        //Teleport
                    } else if (grid[this.x / GRID_SIZE + 1][this.y / GRID_SIZE] != 4) {
                        setX(this.x + speed);
                    }
                    break;
                case WEST:
                    if (this.x - GRID_SIZE < 0) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE - 1][this.y / GRID_SIZE] != 4) {
                        setX(this.x - speed);
                    }
                    break;
                case SOUTH:
                    if (this.y + GRID_SIZE >= BOARD_HEIGHT) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE][this.y / GRID_SIZE + 1] != 4) {
                        setY(this.y + speed);
                    }
                    break;
                case NORTH:
                    if (this.y - GRID_SIZE < 0) {
                        //TELEport
                    } else if (grid[this.x / GRID_SIZE][this.y / GRID_SIZE - 1] != 4) {
                        setY(this.y - speed);
                    }
                    break;
            }
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
