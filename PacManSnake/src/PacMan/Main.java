package PacMan;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
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
//    GridPane board = new GridPane();
    Group pellets = new Group();
    Group borders = new Group();
    Group ghosts = new Group();
    Group snakeRoot;
    Group pacmanRoot;
    Scene snakeScene;
    Scene pacmanScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("PacMan && Snake");
        snakeRoot = new Group();
        pacmanRoot = new Group();
        snakeScene = new Scene(snakeRoot, BOARD_WIDTH, BOARD_HEIGHT);
        pacmanScene = new Scene(pacmanRoot, BOARD_WIDTH + 2 * GRID_SIZE, BOARD_HEIGHT + 2 * GRID_SIZE);
        snakeScene.setFill(Color.BLACK);
        pacmanScene.setFill(Color.BLACK);

        primaryStage.setScene(snakeScene);

        snakeObjects = snakeRoot.getChildren();
        pacmanObjects = pacmanRoot.getChildren();

        snake = new Snake();
        apple = new Apple();
        grid[apple.x / GRID_SIZE][apple.y / GRID_SIZE] = 2;
        snakeObjects.add(snake);
        snakeObjects.add(apple);

        snakeScene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                snake.head.direction = Direction.WEST;
            }else if (event.getCode() == KeyCode.RIGHT) {
                snake.head.direction = Direction.EAST;
            }else if (event.getCode() == KeyCode.UP) {
                snake.head.direction = Direction.NORTH;
            }else if (event.getCode() == KeyCode.DOWN) {
                snake.head.direction = Direction.SOUTH;
            }
        });

        pacmanScene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.LEFT) {
                player.desireDirection = Direction.WEST;
            }else if (event.getCode() == KeyCode.RIGHT) {
                player.desireDirection = Direction.EAST;
            }else if (event.getCode() == KeyCode.UP) {
                player.desireDirection = Direction.NORTH;
            }else if (event.getCode() == KeyCode.DOWN) {
                player.desireDirection = Direction.SOUTH;
            }
        });

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> animation(primaryStage)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();

    }

    public void animation(Stage primaryStage){
        if(game){
            playSnake(primaryStage);
        }else{
            playPacman(primaryStage);
        }
    }

    public void playSnake(Stage primaryStage){
        snake.act();
        if(grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] == 0){
            grid[snake.head.x / GRID_SIZE][snake.head.y / GRID_SIZE] = 1;
        }

        if(snake.head.x/GRID_SIZE == apple.x/GRID_SIZE && snake.head.y/GRID_SIZE == apple.y/GRID_SIZE){
            appleCnt++;
            if(appleCnt == 5) {
                switchGames(primaryStage);
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

    public void switchGames(Stage primaryStage){
        boolean ghost = true;

        Rectangle edgeTop = new Rectangle(-GRID_SIZE,-GRID_SIZE,BOARD_WIDTH + 2*GRID_SIZE,GRID_SIZE);
        Rectangle edgeBottom = new Rectangle(-GRID_SIZE,BOARD_HEIGHT,BOARD_WIDTH + 2*GRID_SIZE,GRID_SIZE);
        Rectangle edgeLeft = new Rectangle(-GRID_SIZE,0,GRID_SIZE,BOARD_HEIGHT);
        Rectangle edgeRight = new Rectangle(BOARD_WIDTH,0,GRID_SIZE,BOARD_HEIGHT);

        edgeTop.setFill(Color.BLUE);
        edgeBottom.setFill(Color.BLUE);
        edgeLeft.setFill(Color.BLUE);
        edgeRight.setFill(Color.BLUE);

        borders.getChildren().addAll(edgeTop,edgeBottom,edgeLeft,edgeRight);

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    Rectangle r = new Rectangle(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);
                    r.setFill(Color.BLUE);
                    borders.getChildren().add(r);
                    grid[i][j] = 4;
                }else if(grid[i][j] == 2) {
                    if(!ghost) {
                        Circle c = new Circle(i * GRID_SIZE + GRID_SIZE / 2, j * GRID_SIZE + GRID_SIZE / 2, GRID_SIZE / 3);
                        c.setFill(Color.YELLOW);
                        pellets.getChildren().add(c);
                        grid[i][j] = 5;
                    }else{
                        Ghost g = new Ghost(i * GRID_SIZE, j * GRID_SIZE);
                        ghosts.getChildren().add(g.image);
                        grid[i][j] = 6;
                    }
                    ghost = !ghost;
                }else if(grid[i][j] == 3) {
                    Rectangle r = new Rectangle(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);
                    r.setFill(Color.RED);
                    borders.getChildren().add(r);

                    Circle c = new Circle(i * GRID_SIZE + GRID_SIZE / 2, j * GRID_SIZE + GRID_SIZE / 2, GRID_SIZE / 6);
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
        pacmanObjects.add(borders);
        pacmanObjects.add(pellets);
        pacmanObjects.add(ghosts);
        pacmanObjects.add(player);
        Translate move = new Translate(GRID_SIZE,GRID_SIZE);
        pacmanRoot.getTransforms().add(move);
        primaryStage.setScene(pacmanScene);
        game = !game;
    }

    public void playPacman(Stage primaryStage){
        player.act();
        if(player.x % GRID_SIZE == 0 && player.y % GRID_SIZE == 0){
            if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 1){
                grid[player.x/GRID_SIZE][player.y/GRID_SIZE] = 0;
                removePellet(player.x, player.y);
            }else if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 2){
                grid[player.x/GRID_SIZE][player.y/GRID_SIZE] = 0;
                removePellet(player.x, player.y);
            }else if(grid[player.x/GRID_SIZE][player.y/GRID_SIZE] == 3){
                removePellet(player.x, player.y);
            }
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
