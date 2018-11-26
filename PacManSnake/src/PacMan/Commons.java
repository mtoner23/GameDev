package PacMan;

public interface Commons {
    public static final int GRID_SIZE = 10;
    public static final int BOARD_WIDTH = 50;
    public static final int BOARD_HEIGHT = 50;
    int BORDER_RIGHT = 0;
    int BORDER_LEFT = 0;
    int CHANCE = 5;
    int DELAY = 17;
    int PLAYER_WIDTH = 14;
    int PLAYER_HEIGHT = 16;
    int SPIDER_WIDTH = 30;
    int SPIDER_HEIGHT = 12;
    int INIT_LENGTH = 4;
    int SEGMENT_HEIGHT = GRID_SIZE;
    int SEGMENT_WIDTH = GRID_SIZE;
    int SHOT_SPEED = 9;
    int INIT_SPEED = 11;
    int PLAYER_AREA_HEIGHT = 100;
    int START_Y = BOARD_HEIGHT - PLAYER_AREA_HEIGHT - GRID_SIZE;
    int START_X = BOARD_WIDTH / 2;
    int [][] grid = new int[BOARD_HEIGHT/GRID_SIZE][BOARD_WIDTH/GRID_SIZE];
}