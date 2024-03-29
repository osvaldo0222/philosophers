package visual;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logical.Controller;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private FileInputStream inputStream = new FileInputStream("src/main/img/Barrel.png");
    private Image image = new Image(inputStream);
    private FileInputStream inputStreamLeyend = new FileInputStream("src/main/img/leyend.png");
    private Image imageLeyend = new Image(inputStreamLeyend);

    public Main() throws FileNotFoundException { }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.getChildren().add(leyend());
        root.getChildren().add(createCircle());
        //root.getChildren().add(createMenu());
        for (int i = 0;i < Controller.getInstance().getInitConfig().getNPhil();i++){
            root.getChildren().add(Controller.getInstance().getPhilosophers().get(i).getCircle());
        }
        Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Filosofos Comensales");
        primaryStage.setScene(scene);
        Controller.getInstance().start();
        primaryStage.show();
        //This is for application to stop with the exit button and kill and the Threads
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
    }

    public Circle createCircle() {
        Circle table = new Circle();
        table.setRadius(360);
        table.setLayoutX(screenSize.getWidth()/2);
        table.setLayoutY(screenSize.getHeight()/2 - 28);
        table.setFill(new ImagePattern(image));
        return table;
    }
    public Rectangle leyend(){
         Rectangle leyend = new Rectangle();
         leyend.setWidth(300);
         leyend.setHeight(300);
         leyend.setLayoutX(20);
         leyend.setLayoutY(20);
         leyend.setFill(new ImagePattern(imageLeyend));
        return leyend;
    }

    public javafx.scene.control.MenuBar createMenu(){
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("File");
        menu.getItems().add(new javafx.scene.control.MenuItem("Close"));
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        return menuBar;
    }
}
