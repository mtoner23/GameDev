package PacMan;

import javafx.scene.Node;

public class Pacman extends Node {
    public int x,y;
    Direction direction;

    public Pacman(Snake s){
        this.x = s.head.x;
        this.y = s.head.y;
        this.direction = s.head.direction;
    }
}
