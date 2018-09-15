package analizador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        primaryStage.setTitle("Analizador Léxico");
        primaryStage.setScene(new Scene(root, 640, 387));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        Path pathActual = Paths.get("");
        String p = pathActual.toAbsolutePath().toString();

        String path = p + "/src/analizador/Lexer.flex";
        generarLexer(path);

        launch(args);
    }

    public static void generarLexer(String pPath)
    {
        File file = new File(pPath);
        jflex.Main.generate(file);
    }
}
