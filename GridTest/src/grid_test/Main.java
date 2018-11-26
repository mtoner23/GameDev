package grid_test;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.input.*;
import javafx.stage.Stage;
import javafx.event.*;

public class Main extends Application {

    public static final int GRID_SIZE = 26;
    public static final int BOARD_WIDTH = 20;
    public static final int BOARD_HEIGHT = 15;


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH * GRID_SIZE, BOARD_HEIGHT * GRID_SIZE);
        primaryStage.setScene(scene);
        //scene.setFill(Color.BLUE);
        //Rectangle land = new Rectangle(GRID_SIZE,GRID_SIZE,(BOARD_WIDTH - 2) * GRID_SIZE,(BOARD_HEIGHT - 2 )*GRID_SIZE);
        //land.setFill(Color.GREEN);
        ObservableList objects = root.getChildren();
        GridPane board = new GridPane();

        for(int i = 0; i < BOARD_HEIGHT; i++){
            for(int j = 0; j < BOARD_WIDTH; j++){
                final Tile t = new Tile(i*GRID_SIZE,j*GRID_SIZE,GRID_SIZE,GRID_SIZE);

                if(i == 0 || i == BOARD_HEIGHT - 1 || j == 0 || j == BOARD_WIDTH - 1) {
                    t.setOcean();
                }else{
                    t.setLand();
                    t.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            t.addUnit();
                        }
                    }));
                }
                board.add(t,j,i);
            }
        }
        objects.add(board);



        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
