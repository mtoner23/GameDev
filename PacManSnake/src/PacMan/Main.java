package PacMan;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.input.*;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;

public class Main extends Application implements Commons{

    private boolean ingame;
    private boolean pause = false;
    private boolean restart = false;
    private boolean quit = false;
    private boolean game = true;
    private Snake snake;
    private Apple apple;
    private int appleCnt = 0;
    private ObservableList snakeObjects;
    private ObservableList pacmanObjects;
    private Pacman player;
    private int wait = 0;
    GridPane board = new GridPane();
    Group pellets = new Group();
    Group borders = new Group();
    Group snakeRoot;
    Group pacmanRoot;
    Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PacMan && Snake");
        snakeRoot = new Group();
        pacmanRoot = new Group();
        scene = new Scene(snakeRoot, BOARD_WIDTH, BOARD_HEIGHT);
        primaryStage.setScene(scene);
        scene.setFill(Color.BLACK);

        snakeObjects = snakeRoot.getChildren();
        pacmanObjects = pacmanRoot.getChildren();

        snake = new Snake();
        apple = new Apple();
        snakeObjects.add(snake);
        snakeObjects.add(apple);

        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                if(game) {
                    snake.head.direction = Direction.WEST;
                }else{
                    player.direction = Direction.WEST;
                    player.setStartAngle(180+45);
                }
            }else if (event.getCode() == KeyCode.RIGHT) {
                if(game) {
                    snake.head.direction = Direction.EAST;
                }else{
                    player.direction = Direction.EAST;
                    player.setStartAngle(45);
                }
            }else if (event.getCode() == KeyCode.UP) {
                if(game) {
                    snake.head.direction = Direction.NORTH;
                }else {
                    player.direction = Direction.NORTH;
                    player.setStartAngle(90+45);
                }
            }else if (event.getCode() == KeyCode.DOWN) {
                if(game) {
                    snake.head.direction = Direction.SOUTH;
                }else{
                    player.direction = Direction.SOUTH;
                    player.setStartAngle(270+45);
                }
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> animation()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();

    }

    public void animation(){
        if(game){
            playSnake();
        }else{
            if(wait == 1) {
                playPacman();
                wait = 0;
            }
            wait++;
        }
    }

    public void playSnake(){
        snake.act();
        if(grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] == 0){
            grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] = 1;
        }

        if(snake.head.x/GRID_SIZE == apple.x/GRID_SIZE && snake.head.y/GRID_SIZE == apple.y/GRID_SIZE){
            appleCnt++;
            if(appleCnt == 5) {
                switchGames();
            }else{
                apple.move();
                for (Segment s : snake.segments) {
                    if (s.x / GRID_SIZE == apple.x / GRID_SIZE && s.y / GRID_SIZE == apple.y / GRID_SIZE) {
                        apple.move();
                    }
                }
                grid[apple.x / GRID_SIZE][apple.y / GRID_SIZE] = 2;
                snake.extend();
            }
        }
    }

    public void switchGames(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    Rectangle r = new Rectangle(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);
                    r.setFill(Color.BLUE);
                    borders.getChildren().add(r);
                    grid[i][j] = 4;
                }else if(grid[i][j] == 2) {
                    Circle c = new Circle(i*GRID_SIZE + GRID_SIZE/2,j*GRID_SIZE + GRID_SIZE/2, GRID_SIZE/3);
                    c.setFill(Color.YELLOW);
                    pellets.getChildren().add(c);
                }else if(grid[i][j] == 3) {
                    Rectangle r = new Rectangle(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);
                    r.setFill(Color.RED);
                    borders.getChildren().add(r);
                }else{
                    Circle c = new Circle(i*GRID_SIZE + GRID_SIZE/2,j*GRID_SIZE + GRID_SIZE/2, GRID_SIZE/6);
                    c.setFill(Color.YELLOW);
                    pellets.getChildren().add(c);
                }
            }
        }

        player = new Pacman(snake);
        snake.setVisible(false);
        apple.setVisible(false);
        pacmanObjects.add(borders);
        pacmanObjects.add(pellets);
        pacmanObjects.add(player);
        scene.setRoot(pacmanRoot);
        game = !game;
    }

    public void playPacman(){
        player.act();
        if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 1){
            grid[player.x/GRID_SIZE][player.y/GRID_SIZE] = 0;
            removePellet(player.x, player.y);
        }else if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 2){
            grid[player.x/GRID_SIZE][player.y/GRID_SIZE] = 0;
            removePellet(player.x, player.y);
        }else if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 3){

        }
    }

    public void removePellet(int x, int y){
        for(int i = 0; i < pellets.getChildren().size(); i++){
            Circle c = (Circle) pellets.getChildren().get(i);
            if(c.getCenterX() == x + GRID_SIZE/2 && c.getCenterY() == y + GRID_SIZE/2){
                pellets.getChildren().remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
