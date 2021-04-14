/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Emili Mendez
 */
public class archivo {
    String resultado="";
    public void probarLexerFile(JTextArea area) throws IOException{
        
        File fichero = new File ("fichero.txt");
        PrintWriter writer;
        try {
            writer = new PrintWriter(fichero);
            writer.print(area.getText());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
        Reader reader = new BufferedReader(new FileReader("fichero.txt"));
        Lexer lexer = new Lexer (reader);
       
        while(true){
            Token token = lexer.yylex();
            if(token==null){
                resultado = resultado + "EOF";
                //end of file 
                return;
            }
            switch(token){
                //identificadores y numeros
                case NUMERICO: case ID: case PALABRA: case VAR:
                    resultado = resultado + token + ": " + lexer.lexeme+"\n";
                    break;
                //simbologia 
                case SIGNO: case MATEMATICO: case LOGICO: case RELACIONAL:
                    resultado = resultado + token + ": " + lexer.lexeme+"\n";
                    break;
                case ERROR:
                    resultado = resultado + token + ": " + lexer.lexeme+"\n";
                    break;
                default:
                    resultado = resultado + token + ": " + lexer.lexeme + "\n";
                    
            }
        }
    }
}
