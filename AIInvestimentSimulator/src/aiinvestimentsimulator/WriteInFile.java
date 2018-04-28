package aiinvestimentsimulator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteInFile {

    public static final String SAIDA_TXT = "saida.txt";
    private static StringBuilder data = new StringBuilder();

    static void addToWrite(String string){
        data.append(string+"\n");
    }

    static void writeInFile(String saida) {
        if(saida.equals("")){
            saida = SAIDA_TXT;
        }
        try (PrintWriter out = new PrintWriter(saida)) {
            out.println(data.toString());
            data = new StringBuilder();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
