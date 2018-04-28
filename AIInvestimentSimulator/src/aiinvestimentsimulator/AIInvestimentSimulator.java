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
        FileReader reader = new FileReader(service);
        setUpFiles().forEach(file -> {
            reader.readFile(file.getName());
        });
        SMART_INVESTIMENT.run();
        service.printLoadedData();

    }


}


