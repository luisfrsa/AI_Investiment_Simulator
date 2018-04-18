package aiinvestimentsimulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author luisr
 */
public class FileReader {

    private static final String FILE_PATH = "../instances/";

    public void readFile(String fileDir) {
        try {
            Stream<String> stream = Files.lines(Paths.get(FILE_PATH + fileDir));
            handleLines(stream);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + fileDir);
            e.printStackTrace();
        }
    }

    private void handleLines(Stream<String> stream) {
        Set<DadosDoDia> listDadosDia = new HashSet<>();
        stream.parallel().forEach(line -> {
            DadosDoDia dadosDoDia = new DadosDoDia();
            String nome = line.substring(27, 39);
            String data = line.substring(2, 10);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            line.substring(56, 69);
            System.out.println(nome);
//            System.out.println(line.substring(70, 82));
//            System.out.println(line.substring(73, 95));
//            System.out.println(line.substring(96, 108));
//            System.out.println(line.substring(109, 121));
//            System.out.println(line.substring(122, 134));
//            System.out.println(line.substring(135, 152));
//            System.out.println(line.substring(153, 170));
//            System.out.println(line.substring(171, 187));
        });
    }

}
