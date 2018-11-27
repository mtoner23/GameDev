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
import javafx.stage.Stage;

import javafx.scene.input.*;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class Main extends Application implements Commons{

    private boolean ingame;
    private boolean pause = false;
    private boolean restart = false;
    private boolean quit = false;
    private Snake snake;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH * GRID_SIZE, BOARD_HEIGHT * GRID_SIZE);
        primaryStage.setScene(scene);
        scene.setFill(Color.BLACK);
        //Rectangle land = new Rectangle(GRID_SIZE,GRID_SIZE,(BOARD_WIDTH - 2) * GRID_SIZE,(BOARD_HEIGHT - 2 )*GRID_SIZE);
        //land.setFill(Color.GREEN);
        ObservableList objects = root.getChildren();
        GridPane board = new GridPane();

        snake = new Snake();
        objects.add(snake);

        scene.setOnKeyPressed((KeyEvent event) -> {
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

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ae -> animation()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        primaryStage.show();

    }

    public void animation(){
        snake.act();
    }

//    @Override
//    public void run() {
//
//        long beforeTime, timeDiff, sleep;
//
//        beforeTime = System.currentTimeMillis();
//
//        while (!quit) {
//            if (restart) {
//                gameInit();
//                resetMouse();
//                restart = false;
//            }
//            if (!pause) {
//                repaint();
//                animationCycle();
//            }
//
//            timeDiff = System.currentTimeMillis() - beforeTime;
//            sleep = DELAY - timeDiff;
//
//            if (sleep < 0) {
//                sleep = 2;
//            }
//
//            try {
//                Thread.sleep(sleep);
//            } catch (InterruptedException e) {
//                System.out.println("interrupted");
//            }
//
//            beforeTime = System.currentTimeMillis();
//        }
//        sounds.stopMusic();
//        System.exit(0);
//    }



//    private class Keyboard extends KeyAdapter {
//
//        @Override
//        public void keyReleased(KeyEvent e) {
//
//        }
//
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int key = e.getKeyCode();
//
//            if(key == VK_R){
//                restart = true;
//            }else if(key == VK_Q){
//                quit = true;
//            }else if(key == VK_P){
//                pause = !pause;
//            }else if(key == VK_M){
//                sounds.mute();
//            }
//        }
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
