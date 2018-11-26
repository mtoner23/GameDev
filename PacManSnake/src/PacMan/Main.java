package PacMan;

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
        primaryStage.show();

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        ingame = true;

        while(!quit){
            if (!pause) {
                animation();
                primaryStage.show();
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();

        }

    }

    public void animation(){

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
