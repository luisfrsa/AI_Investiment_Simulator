import java.io.IOException;
import java.util.Arrays;

public class AIInvestimentSimulatorGenetico extends Config {


    public static void main(String[] args) throws IOException {
        AIInvestimentSimulatorGenetico self = new AIInvestimentSimulatorGenetico();
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
    public static String[] getSplitedString(String entrada){
        return entrada.split("-");
    }

    public static void setParams(String[] args) {
        START_MONEY = 100000.0;
        TO_PRINT_DATA = true;
        INSTANCES_TO_TRAIN = Arrays.asList("2010","2011");
        INSTANCE_TO_RUN = Arrays.asList("2012");
//        Arrays.stream(args).forEach(a->{
//            System.out.println(a);
//        });
////        System.out.println(args.toString());
        if (args.length > 0) {
            STATIC_PARAM_MIN_TO_BUY = Arrays.stream(getSplitedString(args[0])).mapToDouble(Double::parseDouble).toArray();
            STATIC_PARAM_MIN_TO_SELL = Arrays.stream(getSplitedString(args[1])).mapToDouble(Double::parseDouble).toArray();
            STATIC_PARAM_DISCARD_DIV = Arrays.stream(getSplitedString(args[2])).mapToDouble(Double::parseDouble).toArray();
            STATIC_PARAM_DAYS_TO_DISCARD = Arrays.stream(getSplitedString(args[3])).mapToInt(Integer::parseInt).toArray();
            STATIC_PARAM_MIN_DAYS_TO_BEGIN =Arrays.stream(getSplitedString(args[4])).mapToInt(Integer::parseInt).toArray();
            STATIC_PARAM_MAX_MONEY_TO_INVEST = Arrays.stream(getSplitedString(args[5])).mapToDouble(Double::parseDouble).toArray();
//
//            PARAM_MAX_MONEY_TO_INVEST = Double.parseDouble(args[5]); PARAM_MIN_TO_BUY = Double.parseDouble(args[0]);
////            PARAM_MIN_TO_SELL = Double.parseDouble(args[1]);
////            PARAM_DISCARD_DIV = Double.parseDouble(args[2]);
////            PARAM_DAYS_TO_DISCARD = Integer.parseInt(args[3]);
////            PARAM_MIN_DAYS_TO_BEGIN = Integer.parseInt(args[4]);
            START_MONEY = Double.parseDouble(args[6]);
            TO_PRINT_DATA = Integer.parseInt(args[7]) == 1;
            String[] instances = args[8].split(",");
            INSTANCES_TO_TRAIN = Arrays.asList(instances[0].split("-"));
            INSTANCE_TO_RUN = Arrays.asList(instances[1].split("-"));
            System.out.println(instances[1]);
        }
        PARAM_MIN_DAYS_TO_BEGIN = 0;
        START_MONEY = MONEY;
    }

    public AIInvestimentSimulatorGenetico() {
    }

    public void run() {
        setUpFiles();
        FileReader reader = new FileReader(service);
        for (File file : FILES_TO_TRAIN) {
            reader.readFileToTrain(file.getName());
        }
        for (File file : FILES_TO_RUN) {
            reader.readFileToRun(file.getName());
        }

        SMART_INVESTIMENT.run();
        service.printLoadedData();
        service.printResultCompanies();
        service.printFinalResult();

    }


}


