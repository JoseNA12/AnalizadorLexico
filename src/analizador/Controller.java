package analizador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import jdk.nashorn.internal.parser.Lexer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    @FXML public TextArea ta_insertar_texto_id;
    @FXML public TextArea ta_resultado_id;

    @FXML public Button btn_abrir_archivo_id, btn_procesar_id;

    List<Identificador> tokenslist;

    public void probarLexerFile() throws IOException
    {/*
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

        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer(reader);
        String resultado="";
        while (true)
        {
            Token token =lexer.yylex();

            if (token == null)
            {
                for(int i=0;i<tokenslist.size();i++)
                {
                    System.out.println(tokenslist.get(i).nombre + "=" + tokenslist.get(i).ID);
                }
                ta_resultado_id.setText(resultado);
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
        }*/
    }

}
