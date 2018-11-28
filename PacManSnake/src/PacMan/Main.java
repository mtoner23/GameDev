package PacMan;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
    private ObservableList objects;
    private Pacman player;
    GridPane board = new GridPane();
    Group pellets = new Group();
    Group borders = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        primaryStage.setScene(scene);
        scene.setFill(Color.BLACK);
        //Rectangle land = new Rectangle(GRID_SIZE,GRID_SIZE,(BOARD_WIDTH - 2) * GRID_SIZE,(BOARD_HEIGHT - 2 )*GRID_SIZE);
        //land.setFill(Color.GREEN);
        objects = root.getChildren();

        snake = new Snake();
        apple = new Apple();
        objects.add(snake);
        objects.add(apple);

        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                if(game) {
                    snake.head.direction = Direction.WEST;
                }else{
                    player.direction = Direction.WEST;
                }
            }else if (event.getCode() == KeyCode.RIGHT) {
                if(game) {
                    snake.head.direction = Direction.EAST;
                }else{
                    player.direction = Direction.EAST;
                }
            }else if (event.getCode() == KeyCode.UP) {
                if(game) {
                    snake.head.direction = Direction.NORTH;
                }else{
                    player.direction = Direction.NORTH;
                }
            }else if (event.getCode() == KeyCode.DOWN) {
                if(game) {
                    snake.head.direction = Direction.SOUTH;
                }else{
                    player.direction = Direction.SOUTH;
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
            playPacman();
        }
    }

    public void playSnake(){
        snake.act();
        if(grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] != 2){
            grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] = 1;
        }

        if(snake.head.x/GRID_SIZE == apple.x/GRID_SIZE && snake.head.y/GRID_SIZE == apple.y/GRID_SIZE){
            appleCnt++;
            if(appleCnt == 8) {
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
        pellets = new Group();
        borders = new Group();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    Rectangle r = new Rectangle(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);
                    r.setFill(Color.BLUE);
                    borders.getChildren().add(r);
                }else if(grid[i][j] == 2) {
                    Circle c = new Circle(i*GRID_SIZE + GRID_SIZE/2,j*GRID_SIZE + GRID_SIZE/2, GRID_SIZE/3);
                    c.setFill(Color.YELLOW);
                    pellets.getChildren().add(c);
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
        objects.add(borders);
        objects.add(pellets);
        objects.add(player);
        game = !game;
    }

    public void playPacman(){
        //player.act();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
