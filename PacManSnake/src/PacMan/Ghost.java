package PacMan;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ghost implements Commons{

    public Image redGhost = new Image("pacmanGhost.png");
    public ImageView image;

    public Ghost(int x, int y){
        image = new ImageView(redGhost);
        image.setFitWidth(GRID_SIZE);
        image.setPreserveRatio(true);
        image.setX(x);
        image.setY(y);
    }
}
