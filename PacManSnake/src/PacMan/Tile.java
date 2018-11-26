package PacMan;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Tile extends Group {

    public Rectangle ground;
//    public Unit unit;
    public int test;

    public Tile(int x, int y, int w, int h){
        ground = new Rectangle(x,y,w,h);
        ground.setStroke(Color.BLACK);
        ground.setStrokeType(StrokeType.INSIDE);
        this.getChildren().add(ground);
    }

    public void setOcean(){
        ground.setFill(Color.BLUE);
    }
    public void setLand(){
        ground.setFill(Color.GREEN);
    }

    public void addUnit(){
//        unit = new Unit(ground);
//        this.getChildren().addAll(unit);
    }


}
