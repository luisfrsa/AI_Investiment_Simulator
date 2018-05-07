
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Config {

    protected static double PARAM_MIN_TO_BUY;
    protected static double PARAM_MIN_TO_SELL;
    protected static double PARAM_DISCARD_DIV;
    protected static double PARAM_MAX_MONEY_TO_INVEST;
    protected static int PARAM_DAYS_TO_DISCARD;
    protected static int PARAM_MIN_DAYS_TO_BEGIN;

    protected static boolean TO_PRINT_DATA;
    protected static double MONEY;
    protected static double START_MONEY;

    protected static SmartInvestiment SMART_INVESTIMENT = new SmartInvestiment();
    protected static final Service service = new Service();

    protected static final HashMap<String, Company> COMPANY_HASH_MAP = new HashMap<>();
    protected static final TreeMap<LocalDate, Set<DadosDoDia>> HASH_DATE_DADOS = new TreeMap<>();

    protected static final Set<String> avaliableCompanies = new HashSet<>();

    protected static final List<File> FILES_TO_TRAIN = new ArrayList<>();
    protected static final List<File> FILES_TO_RUN = new ArrayList<>();
    protected static final String RESULT_PATH = "../result/";
    protected static List<String> INSTANCES_TO_TRAIN = new ArrayList<>();
    protected static List<String> INSTANCE_TO_RUN = new ArrayList<>();
    protected static String RUNNED_INSTANCE_STRING = "";
    protected static final int NUM_ACTIONS = 220;
    public static final int AHUNDREAD = 100;


    protected void setUpFiles() {
//        TO_PRINT_DATA = true;
//        FILES_TO_TRAIN.add(new File("COTAHIST_A2013.TXT"));
//        FILES_TO_TRAIN.add(new File("COTAHIST_A2014.TXT"));
//        FILES_TO_TRAIN.add(new File("COTAHIST_A2015.TXT"));
//        FILES_TO_TRAIN.add(new File("COTAHIST_A2016.TXT"));

//        FILES_TO_RUN.add(new File( "COTAHIST_A2013.TXT"));
//        FILES_TO_RUN.add(new File( "COTAHIST_A2014.TXT"));
//        FILES_TO_RUN.add(new File( "COTAHIST_A2015.TXT"));
//        FILES_TO_RUN.add(new File( "COTAHIST_A2016.TXT"));
//        FILES_TO_RUN.add(new File("COTAHIST_A2017.TXT"));
//        INSTANCES_TO_TRAIN.forEach(instance -> {
//            System.out.println("File to train "+instance);
//            FILES_TO_TRAIN.add(new File("COTAHIST_A" + instance + ".TXT"));
//        });
        INSTANCE_TO_RUN.forEach(instance -> {
            System.out.println("File to RUN "+instance);
            RUNNED_INSTANCE_STRING += instance + "_";
            FILES_TO_RUN.add(new File("COTAHIST_A" + instance + ".TXT"));
        });

    }


    protected void generateCompanys2() {
        avaliableCompanies.add("LEVE3F-NM");
        avaliableCompanies.add("GRND3-NM");
        avaliableCompanies.add("GRND3F-NM");
        avaliableCompanies.add("JSLG3-NM");
        avaliableCompanies.add("VIVT3F-R$");
        avaliableCompanies.add("VIVT3-R$");
        avaliableCompanies.add("JSLG3F-NM");
        avaliableCompanies.add("NATU3-NM");
        avaliableCompanies.add("NATU3F-NM");
        avaliableCompanies.add("CSNA3F-R$");
    }

    protected void generateCompanys() {
        avaliableCompanies.add("VIVT3F-R$");
        avaliableCompanies.add("NATU3-NM");
        avaliableCompanies.add("JSLG3-NM");
        avaliableCompanies.add("CSNA3F-R$");
        avaliableCompanies.add("LEVE3-NM");
        avaliableCompanies.add("NATU3F-NM");
        avaliableCompanies.add("VIVT3-R$");
        avaliableCompanies.add("UGPA3-NM");
        avaliableCompanies.add("LREN3-NM");
        avaliableCompanies.add("WEGE3F-NM");
        avaliableCompanies.add("UGPA3F-NM");
        avaliableCompanies.add("GRND3-NM");
        avaliableCompanies.add("LEVE3F-NM");
        avaliableCompanies.add("CIEL3-NM");
        avaliableCompanies.add("WEGE3-NM");
        avaliableCompanies.add("GRND3F-NM");
        avaliableCompanies.add("CIEL3F-NM");
        avaliableCompanies.add("LREN3F-NM");
        avaliableCompanies.add("CSNA3-R$");
        avaliableCompanies.add("JSLG3F-NM");
    }

    protected void generateCompanysBase() {
        avaliableCompanies.add("CSNA3");
        avaliableCompanies.add("NATU3");
        avaliableCompanies.add("CIEL3");
        avaliableCompanies.add("VIVT3");
        avaliableCompanies.add("GRND3");
        avaliableCompanies.add("JSLG3");
        avaliableCompanies.add("WEGE3");
        avaliableCompanies.add("LREN3");
        avaliableCompanies.add("UGPA3");
        avaliableCompanies.add("LEVE3");
        avaliableCompanies.add("VEBM");
    }
}
