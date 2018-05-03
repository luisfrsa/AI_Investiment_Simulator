
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Config {

    protected static double[] STATIC_PARAM_MIN_TO_BUY;
    protected static double[] STATIC_PARAM_MIN_TO_SELL;
    protected static double[] STATIC_PARAM_DISCARD_DIV;
    protected static double[] STATIC_PARAM_MAX_MONEY_TO_INVEST;
    protected static int[] STATIC_PARAM_DAYS_TO_DISCARD;
    protected static int[] STATIC_PARAM_MIN_DAYS_TO_BEGIN;

    protected  double PARAM_MIN_TO_BUY;
    protected  double PARAM_MIN_TO_SELL;
    protected  double PARAM_DISCARD_DIV;
    protected  double PARAM_MAX_MONEY_TO_INVEST;
    protected  int PARAM_DAYS_TO_DISCARD;
    protected  int PARAM_MIN_DAYS_TO_BEGIN;

    protected static boolean TO_PRINT_DATA;
    protected double MONEY;
    protected static double START_MONEY;

    protected  SmartInvestiment SMART_INVESTIMENT = new SmartInvestiment();
    protected  final Service service = new Service();

    protected  final HashMap<String, Company> COMPANY_HASH_MAP = new HashMap<>();
    protected  final TreeMap<LocalDate, Set<DadosDoDia>> HASH_DATE_DADOS = new TreeMap<>();

    protected  final Set<String> avaliableCompanies = new HashSet<>();
    protected  final List<File> FILES_TO_TRAIN = new ArrayList<>();
    protected  final List<File> FILES_TO_RUN = new ArrayList<>();
    protected  final String RESULT_PATH = "../result/";
    protected static List<String> INSTANCES_TO_TRAIN = new ArrayList<>();
    protected static List<String> INSTANCE_TO_RUN = new ArrayList<>();
    protected static String RUNNED_INSTANCE_STRING = "";


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
        INSTANCES_TO_TRAIN.forEach(instance -> {
            FILES_TO_TRAIN.add(new File("COTAHIST_A" + instance + ".TXT"));
        });
        INSTANCE_TO_RUN.forEach(instance -> {
            RUNNED_INSTANCE_STRING += instance + "_";
            FILES_TO_RUN.add(new File("COTAHIST_A" + instance + ".TXT"));
        });

    }


    protected void generateCompanys() {
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

    protected void generateCompanys2() {
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
