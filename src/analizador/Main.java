package analizador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jflex.anttask.JFlexTask;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        String path = "Lexer.flex";
        generarLexer(path);

        launch(args);
    }

    public static void generarLexer(String pPath)
    {
        File file = new File(pPath);
        JFlex.Main.generate(file);
    }
}
