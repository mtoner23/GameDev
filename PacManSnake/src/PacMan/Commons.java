package PacMan;

public interface Commons {
    int GRID_SIZE = 10;
    int GRID_WIDTH = 20;
    int GRID_HEIGHT = 20;
    int BOARD_WIDTH = GRID_SIZE*GRID_WIDTH;
    int BOARD_HEIGHT = GRID_SIZE*GRID_HEIGHT;
    int INIT_LENGTH = 16;
    int SEGMENT_HEIGHT = GRID_SIZE;
    int SEGMENT_WIDTH = GRID_SIZE;
    int SHOT_SPEED = 9;
    int INIT_SPEED = 11;
    int PLAYER_AREA_HEIGHT = 100;
    int [][] grid = new int[GRID_WIDTH][GRID_HEIGHT];
}