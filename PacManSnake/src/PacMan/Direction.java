package PacMan;

public enum Direction {
    NORTH,SOUTH,EAST,WEST;


    public Direction turnLeft(){
        switch(this){
            case EAST:
               return NORTH;
            case WEST:
                return SOUTH;
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
        }
        return NORTH;
    }
}
