package analizador;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @FXML public TextArea ta_insertar_texto_id;
    @FXML public TableView<ItemTablaTokens> tv_tokens_encontrados_id;
    @FXML public TableColumn<ItemTablaTokens, String> tc_token_id, tc_tipo_token_id, tc_linea_token_id;
    @FXML public TableView<ItemTablaErrores> tv_errores_lexicos_id;
    @FXML public TableColumn<ItemTablaErrores, String> tc_error_id, tc_linea_error_id;
    private final ObservableList<ItemTablaTokens> info_tabla_tokens = FXCollections.observableArrayList();
    private final ObservableList<ItemTablaErrores> info_tabla_errores = FXCollections.observableArrayList();

    @FXML public Button btn_abrir_archivo_id, btn_procesar_id;

    private List<Identificador> tokenslist;

    private Stage miPrimaryStage;

    public void probarLexerFile()
    {
        int contIDs=0;
        tokenslist = new LinkedList<Identificador>();
        File fichero = new File ("fichero.txt");
        PrintWriter writer;

        try {
            writer = new PrintWriter(fichero);
            writer.print(ta_insertar_texto_id.getText());
            writer.close();
        }
        catch (FileNotFoundException ex) {
            // Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

        Reader reader = null;
        try {
            reader = new BufferedReader(new FileReader("fichero.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Lexer lexer = new Lexer(reader);
        String resultado="";
        while (true)
        {
            Token token = null;
            try {
                token = lexer.yylex();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (token == null)
            {
                for(int i=0;i<tokenslist.size();i++)
                {
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                agregarElementoTablaTokens(resultado, "", "");
                return;
            }
            switch (token)
            {
                case OPERADOR:
                    resultado=resultado+ "<->"; // corregir por los correctos (estos es solo para probar que funciona)
                    break;
                case PALABRA_RESERVADA:
                    resultado=resultado+ "<*>";
                    break;
                case LITERAL:
                    resultado=resultado+ "</>";
                    break;
                case ERROR:
                    resultado=resultado+ "Error, simbolo no reconocido ";
                    break;
                case IDENTIFICADOR: {
                    contIDs++;
                    Identificador tokenitem=new Identificador();
                    tokenitem.nombre=lexer.lexeme;
                    tokenitem.ID=contIDs;
                    tokenslist.add(tokenitem);
                    resultado=resultado+ "<ID" + contIDs + "> ";
                    break;
                }
                case INT:
                    resultado=resultado+ "< " + lexer.lexeme + "> ";
                    break;
                default:
                    resultado=resultado+ "<"+ lexer.lexeme + "> ";
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        miPrimaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        miPrimaryStage.setTitle("Analizador Léxico");
        miPrimaryStage.setScene(new Scene(root, 851, 545));
        miPrimaryStage.show();
    }

    public static void main(String[] args)
    {
        Path pathActual = Paths.get("");
        String p = pathActual.toAbsolutePath().toString();
        String path = p + "/src/analizador/Lexer.flex";
        generarLexer(path);

        launch(args);
    }

    @FXML
    void initialize(){
        initTablesViews();
    }

    /**
     * Inicializar los valores de las tablas asi como sus columnas
     */
    private void initTablesViews()
    {
        // Tokens
        tc_token_id.setCellValueFactory(new PropertyValueFactory<>("token"));
        tc_tipo_token_id.setCellValueFactory(new PropertyValueFactory<>("tipoToken"));
        tc_linea_token_id.setCellValueFactory(new PropertyValueFactory<>("linea"));
        tv_tokens_encontrados_id.setItems(info_tabla_tokens);

        // Errores
        tc_error_id.setCellValueFactory(new PropertyValueFactory<>("error"));
        tc_linea_error_id.setCellValueFactory(new PropertyValueFactory<>("linea_error"));
        tv_errores_lexicos_id.setItems(info_tabla_errores);
    }

    public static void generarLexer(String pPath)
    {
        File file = new File(pPath);
        jflex.Main.generate(file);
    }

    /**
     * Encargado de agregar valores a la tabla de tokens de la interfaz
     * @param pToken
     * @param pTipo
     * @param pLineas
     */
    private void agregarElementoTablaTokens(String pToken, String pTipo, String pLineas)
    {
        info_tabla_tokens.add(new ItemTablaTokens(new SimpleStringProperty(pToken),
                new SimpleStringProperty(pTipo), new SimpleStringProperty(pLineas)));
    }

    /**
     * Encargado de agregar valores a la tabla de errores de la interfaz
     * @param pToken
     * @param pLineas
     */
    private void agregarElementoTablaError(String pToken, String pLineas)
    {
        info_tabla_errores.add(new ItemTablaErrores(new SimpleStringProperty(pToken),
                new SimpleStringProperty(pLineas)));
    }

    /**
     * Encargado de abrir el FileChooser para seleccionar el archivo txt
     */
    public void btn_action_abrirArchivo() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showOpenDialog(miPrimaryStage);
        if(file != null){
            ta_insertar_texto_id.setText(readFile(file));
        }
    }

    /**
     * Encargado de leer el archivo txt en memoria
     * @param file
     * @return
     */
    private String readFile(File file){
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }
}
