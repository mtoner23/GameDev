package PacMan;

import java.util.Vector;
import javafx.scene.Group;

public class Snake extends Group implements Commons {

    public Vector<Segment> segments;
    public int size = INIT_LENGTH;
    private final int INIT_X = 150;
    private final int INIT_Y = 5;

    public Snake(){
        this(INIT_LENGTH,BOARD_WIDTH*GRID_SIZE/2 + (INIT_LENGTH/2)*GRID_SIZE,BOARD_HEIGHT*GRID_SIZE/2);
    };

    public Snake(int s, int x, int y){

        this.size = s;
        segments = new Vector<>();

        for(int i = 0; i < s; i++) {
            Segment seg = new Segment(x, y,i == s-1);
            this.getChildren().add(seg);
            segments.add(seg);
            x += SEGMENT_WIDTH;
        }
    }

    public Snake(int s){
        this.size = s;
        segments = new Vector<>();
    }

    public void act(){

        //FOr loop thorugh all segments and then move the last segment. better way to do it
        int i;
        for(i = 0; i < segments.size(); i++) {
            segments.get(i).setX(segments.get(i + 1).x);
            segments.get(i).setY(segments.get(i + 1).y);
            segments.get(i).direction = segments.get(i + 1).direction;
        }
        Segment head = segments.get(i);

        if (head.x >= BOARD_WIDTH - BORDER_RIGHT) {
            head.direction = head.direction.turnLeft();
        }else if (head.x < BORDER_LEFT) {
            head.direction = head.direction.turnLeft();
        }else if (head.y >= BOARD_HEIGHT - PLAYER_AREA_HEIGHT) {
            head.direction = head.direction.turnLeft();
        }else if (head.y <= 0) {
            head.direction = head.direction.turnLeft();
        }

        switch(head.direction) {
            case EAST:
                head.setX(head.x + GRID_SIZE);
            case WEST:
                head.setX(head.x - GRID_SIZE);
            case SOUTH:
                head.setY(head.y + GRID_SIZE);
            case NORTH:
                head.setY(head.y - GRID_SIZE);
        }
    }
}
