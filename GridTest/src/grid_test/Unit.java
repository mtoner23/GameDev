package grid_test;


import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.shape.*;


public class Unit extends Group {
    public Circle c;


    public Unit(Rectangle ground){
        double circleX = ground.getX() + ground.getWidth()/2;
        double circleY = ground.getY() + ground.getHeight()/2;

        c = new Circle(circleX,circleY,3,Color.RED);
        this.getChildren().add(c);
    }
}
