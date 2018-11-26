package PacMan;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Segment extends Circle implements Commons {


//    private Segment leftSegment;
//    private Segment rightSegment;
//    private String segHitImg = "src/images/centipede/segment_hit.png";
//    private String segRevHitImg = "src/images/centipede/segment_rotate_hit.png";
//    private final String segImg = "src/images/centipede/segment.png";
//    private final String headImg = "src/images/centipede/head.png";
//    private final String headRevImg = "src/images/centipede/head_rotate.png";
//    private final String segRevImg = "src/images/centipede/segment_rotate.png";
//    protected Image revImage;
    public int x,y;
    public Direction direction;
    public boolean head;
    //public int head = 0;

    public Segment(int x, int y, boolean head) {
        super(x + GRID_SIZE/2, y + GRID_SIZE/2, GRID_SIZE / 2, Color.GREEN);
        this.x = x;
        this.y = y;
        direction = Direction.WEST;
        this.head = head;
        if(this.head){
            this.setFill(Color.RED);
        }else{
            this.setFill(Color.GREEN);
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

//        if(head) {
//            setImage(headImg);
//            setRevImage(headRevImg);
//        }else{
//            setImage(segImg);
//            setRevImage(segRevImg);
//        }
//    }

//    @Override
//    public Image getImage(){
//        if(direction == -1){
//            return image;
//        }else if(direction == 1){
//            return revImage;
//        }else{
//            return null;
//        }
//    }
//    public void setHead(){
//        this.head = true;
//        setImage(headImg);
//        setRevImage(headRevImg);
//    }
//    public void setRevImage(String img){
//        ImageIcon ii = new ImageIcon(img);
//        this.revImage = ii.getImage();
//    }

    public void die(){

    }
}
