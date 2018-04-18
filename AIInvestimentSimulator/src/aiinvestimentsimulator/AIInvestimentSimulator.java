package aiinvestimentsimulator;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class AIInvestimentSimulator {

    public static void main(String[] args) throws IOException {
        AIInvestimentSimulator self = new AIInvestimentSimulator();
        try {
            self.run();
        } catch (Exception e) {
            System.out.println("Erro ao executar progeama: ");
            e.printStackTrace();
        }
    }

    public void run() {
        List<File> filesList = setUpFiles();
        FileReader reader = new FileReader();
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
