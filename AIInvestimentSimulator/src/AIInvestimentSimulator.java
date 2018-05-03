import java.io.IOException;
import java.util.Arrays;

public class AIInvestimentSimulator extends Config {


    public static void main(String[] args) throws IOException {
        AIInvestimentSimulator self = new AIInvestimentSimulator();
        setParams(args);

        long startTime = System.currentTimeMillis();
        try {
            self.run();
            System.out.println(String.format("Programa executado em %d milisegundos.", (System.currentTimeMillis() - startTime)));
        } catch (Exception e) {
            System.out.println("Erro ao executar programa: ");
            e.printStackTrace();
        }
    }

    public static void setParams(String[] args) {
        PARAM_MIN_TO_BUY= 0.03;
        PARAM_MIN_TO_SELL= 0.1;
        PARAM_DISCARD_DIV= 2.5;
        PARAM_DAYS_TO_DISCARD= 10;
        PARAM_MIN_DAYS_TO_BEGIN= 0;
        PARAM_MAX_MONEY_TO_INVEST= 1.0;
//        MONEY= 100000.0;
        MONEY= 1000;
        TO_PRINT_DATA = false;
      //  PARAM_DISCARD_DIV= 1.5;
       // PARAM_DAYS_TO_DISCARD= 15;
//        Arrays.stream(args).forEach(a->{
//            System.out.println(a);
//        });
////        System.out.println(args.toString());
        if (args.length > 0) {
            PARAM_MIN_TO_BUY = Double.parseDouble(args[0]);
            PARAM_MIN_TO_SELL = Double.parseDouble(args[1]);
            PARAM_DISCARD_DIV = Double.parseDouble(args[2]);
            PARAM_DAYS_TO_DISCARD = Integer.parseInt(args[3]);
            PARAM_MIN_DAYS_TO_BEGIN = Integer.parseInt(args[4]);
            PARAM_MAX_MONEY_TO_INVEST = Double.parseDouble(args[5]);
            MONEY = Double.parseDouble(args[6]);
            TO_PRINT_DATA = Integer.parseInt(args[7]) == 1;
        }
        PARAM_MIN_DAYS_TO_BEGIN = 0;
        START_MONEY = MONEY;
    }

    public AIInvestimentSimulator() {
    }

    public void run() {
        setUpFiles();
        FileReader reader = new FileReader(service);
        FILES_TO_TRAIN.forEach(file -> {
            reader.readFileToTrain(file.getName());
        });
        FILES_TO_RUN.forEach(file -> {
            reader.readFileToRun(file.getName());
        });

        SMART_INVESTIMENT.run();
        service.printLoadedData();
        service.printResultCompanies();
        service.printFinalResult();

    }


}


