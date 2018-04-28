package aiinvestimentsimulator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class AIInvestimentSimulator extends Config {


    public static void main(String[] args) throws IOException {
        AIInvestimentSimulator self = new AIInvestimentSimulator();
        long startTime = System.currentTimeMillis();
        try {
            self.run();
            System.out.println(String.format("Programa executado em %d segundos.", (System.currentTimeMillis() - startTime) / 1000));
        } catch (Exception e) {
            System.out.println("Erro ao executar progeama: ");
            e.printStackTrace();
        }
    }

    public AIInvestimentSimulator() {
    }

    public void run() {
        List<File> filesList = setUpFiles();
        FileReader reader = new FileReader(service);
        filesList.forEach(file -> {
            reader.readFile(file.getName());
        });
    }

    private List<File> setUpFiles() {
        List<File> filesList = new ArrayList<>();
//        filesList.add(new File(1L, Year.of(2014), "COTAHIST_A2014-REDUZIDO.TXT"));
        filesList.add(new File(1L, Year.of(2014), "COTAHIST_A2014.TXT"));
//        filesList.add(new File(2L, Year.of(2015), "COTAHIST_A2015.TXT"));
        return filesList;
    }
}


