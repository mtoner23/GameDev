package PacMan;

import java.util.Vector;
import javafx.scene.Group;

public class Snake extends Group implements Commons {

    public Vector<Segment> segments;
    public Segment head;
    public int size = INIT_LENGTH;
    private final int INIT_X = 150;
    private final int INIT_Y = 5;

    public Snake(){
        this(INIT_LENGTH,BOARD_WIDTH*GRID_SIZE/2 + (INIT_LENGTH/2)*GRID_SIZE,BOARD_HEIGHT*GRID_SIZE/2);
    };

    public Snake(int s, int x, int y){

        this.size = s;
        segments = new Vector<>();
        Segment seg = null;

        for(int i = 0; i < s; i++) {
            seg = new Segment(x, y,i == s-1);
            this.getChildren().add(seg);
            segments.add(seg);
            x += SEGMENT_WIDTH;
        }
        head = seg;
    }

    public Snake(int s){
        this.size = s;
        segments = new Vector<>();
    }

    public void act(){

        //FOr loop thorugh all segments and then move the last segment. better way to do it
        int i;
        for(i = 0; i < segments.size() - 1; i++) {
            segments.get(i).setX(segments.get(i + 1).x);
            segments.get(i).setY(segments.get(i + 1).y);
            segments.get(i).direction = segments.get(i + 1).direction;
        }
        Segment head = segments.get(i);

        switch(head.direction) {
            case EAST:
                head.setX(head.x + GRID_SIZE);
                break;
            case WEST:
                head.setX(head.x - GRID_SIZE);
                break;
            case SOUTH:
                head.setY(head.y + GRID_SIZE);
                break;
            case NORTH:
                head.setY(head.y - GRID_SIZE);
                break;
        }

        if (head.x >= BOARD_WIDTH*GRID_SIZE - BORDER_RIGHT) {
            head.setX(0);
        }else if (head.x < BORDER_LEFT) {
            head.setX(Commons.BOARD_WIDTH*GRID_SIZE);
        }else if (head.y >= BOARD_HEIGHT*GRID_SIZE) {
            head.setY(0);
        }else if (head.y < 0) {
            head.setY(BOARD_HEIGHT*GRID_SIZE);
        }
    }
}
