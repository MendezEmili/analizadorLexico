package alex;

import java.io.File;

/**
 *
 * @author Emili Mendez
 */
public class ALex {

    public static void main(String[] args) {
        interfaz ventana = new interfaz();
        ventana.setVisible(true);
        String path = "C:/Users/Emili Mendez/Documents/NetBeansProjects/ALex/src/alex/Lexer.flex";
        generarLexer(path);
    }

    private static void generarLexer(String path) {
        File file=new File(path);
        JFlex.Main.generate(file);   
    }
    
}
