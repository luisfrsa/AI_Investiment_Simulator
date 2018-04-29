//package aiinvestimentsimulator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WriteInFile {

    public final String SAIDA_TXT = "saida.txt";
    private StringBuilder data = new StringBuilder();

    void addToWrite(String string) {
        data.append(string + "\n");
    }

    void writeInFile(String saida) {
        if (saida.equals("")) {
            saida = SAIDA_TXT;
        }
        try (PrintWriter out = new PrintWriter(saida)) {
            out.println(data.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
