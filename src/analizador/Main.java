package analizador;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @FXML public TextArea ta_insertar_texto_id;
    @FXML public TableView<ItemTablaTokens> tv_tokens_encontrados_id;
    @FXML public TableColumn<ItemTablaTokens, String> tc_token_id, tc_tipo_token_id, tc_linea_token_id;
    @FXML public TableView<ItemTablaErrores> tv_errores_lexicos_id;
    @FXML public TableColumn<ItemTablaErrores, String> tc_error_id, tc_linea_error_id, tc_tipo_error_id;
    private final ObservableList<ItemTablaTokens> info_tabla_tokens = FXCollections.observableArrayList();
    private final ObservableList<ItemTablaErrores> info_tabla_errores = FXCollections.observableArrayList();

    @FXML public Button btn_abrir_archivo_id, btn_procesar_id;

    private List<LineaToken> tokenslist, tokenslistErrores;

    private Stage miPrimaryStage;

    public void probarLexerFile()
    {
        tokenslist = new LinkedList<LineaToken>();
        tokenslistErrores = new LinkedList<LineaToken>();
        info_tabla_tokens.clear();
        info_tabla_errores.clear();
        File fichero = new File ("fichero.txt");
        PrintWriter writer;

        try {
            writer = new PrintWriter(fichero);
            writer.print(ta_insertar_texto_id.getText()); // .toUpperCase();
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
                agregarElementosTablaTokens();
                agregarElementosTablaTokensErrores();
                return;
            }

            if ((token != Token.ERROR) && (token != Token.ERROR_LITERAL) && (token != Token.ERROR_IDENTIFICADOR
                    && (token != Token.ERROR_OPERADOR) && (token != Token.ERROR_PALABRA_RESERVADA)))
            {
                agregarLineaToken(lexer.lexeme, token.getNombre(), lexer.line);
            }
            else
            {
                agregarLineaTokenErrores(lexer.lexeme, token.getNombre(), lexer.line);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        miPrimaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        miPrimaryStage.setTitle("Analizador Léxico");
        miPrimaryStage.setScene(new Scene(root, 1070, 623));
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
        tc_tipo_error_id.setCellValueFactory(new PropertyValueFactory<>("tipoError"));
        tc_linea_error_id.setCellValueFactory(new PropertyValueFactory<>("linea_error"));
        tv_errores_lexicos_id.setItems(info_tabla_errores);
    }

    public static void generarLexer(String pPath)
    {
        File file = new File(pPath);
        jflex.Main.generate(file);
    }

    /**
     * Agrega una nueva linea al tokenlist
     * Si ya existe el token llama a agregarLinea(int), sino crea una nueva Linea de Token con un nuevo HashMap
     * @param token token analizado
     * @param tipoToken tipo del token analizado
     * @param numeroLinea número de línea de aparición del token analizado
     */
    private void agregarLineaToken(String token, String tipoToken, int numeroLinea){
        LineaToken linea = null;
        boolean existe = false;
        for(int i=0; i<tokenslist.size(); i++){
            linea = tokenslist.get(i);
            if(linea.token.equalsIgnoreCase(token)){
                existe = true;
                break;
            }
        }
        if(existe){
            linea.agregarLinea(numeroLinea);
        }else{
            Map<Integer, Integer> lineasAparicion = new HashMap<Integer, Integer>();
            lineasAparicion.put(numeroLinea, 1);
            tokenslist.add(new LineaToken(token, tipoToken, lineasAparicion));
        }
    }

    /**
     * Encargado de agregar valores a la tabla de tokens de la interfaz
     * Utiliza el LinkedList tokenlist que posee todas las lineas de código resumidas por apariciones del token
     */
    private void agregarElementosTablaTokens()
    {
        String lineas;
        for(LineaToken l : tokenslist){
            lineas = "";
            Set<Integer> clavesLineas = l.lineasAparicion.keySet();     //retorna el set de claves del map
            for (Iterator<Integer> it = clavesLineas.iterator(); it.hasNext(); ) {
                Integer key = it.next();
                int cantidadApariciones = l.lineasAparicion.get(key);

                if(cantidadApariciones > 1){
                    lineas += (key + 1) + "(" + l.lineasAparicion.get(key) + "), ";
                }else{
                    lineas += (key + 1) + ", ";
                }
            }
            info_tabla_tokens.add(new ItemTablaTokens(new SimpleStringProperty(l.token),
                    new SimpleStringProperty(l.tipoToken), new SimpleStringProperty(lineas)));
        }
    }

    /**
     * Agrega una nueva linea al tokenlistErrrores
     * Si ya existe el token llama a agregarLinea(int), sino crea una nueva Linea de Token con un nuevo HashMap
     * @param token token analizado
     * @param numeroLinea número de línea de aparición del token analizado
     */
    private void agregarLineaTokenErrores(String token, String tipoError, int numeroLinea){
        LineaToken linea = null;
        boolean existe = false;
        for(int i=0; i< tokenslistErrores.size(); i++){
            linea = tokenslistErrores.get(i);
            if(linea.token.equalsIgnoreCase(token)){
                existe = true;
                break;
            }
        }
        if(existe){
            linea.agregarLinea(numeroLinea);
        }else{
            Map<Integer, Integer> lineasAparicion = new HashMap<Integer, Integer>();
            lineasAparicion.put(numeroLinea, 1);
            tokenslistErrores.add(new LineaToken(token, tipoError, lineasAparicion));
        }
    }

    /**
     * Encargado de agregar valores a la tabla de tokens de errores de la interfaz
     * Utiliza el LinkedList tokenlist que posee todas las lineas de código resumidas por apariciones del token
     */
    private void agregarElementosTablaTokensErrores()
    {
        String lineas;
        for(LineaToken l : tokenslistErrores){
            lineas = "";
            Set<Integer> clavesLineas = l.lineasAparicion.keySet();     //retorna el set de claves del map
            for (Iterator<Integer> it = clavesLineas.iterator(); it.hasNext(); ) {
                Integer key = it.next();
                int cantidadApariciones = l.lineasAparicion.get(key);

                if(cantidadApariciones > 1){
                    lineas += (key + 1) + "(" + l.lineasAparicion.get(key) + "), ";
                }else{
                    lineas += (key + 1) + ", ";
                }
            }
            info_tabla_errores.add(new ItemTablaErrores(new SimpleStringProperty(l.token), new SimpleStringProperty(l.tipoToken), new SimpleStringProperty(lineas)));
        }
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
