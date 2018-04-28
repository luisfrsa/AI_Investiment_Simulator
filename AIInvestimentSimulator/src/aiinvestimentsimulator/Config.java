package aiinvestimentsimulator;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Config {

    protected static final int PARAM_ELITISM_COUNT = 100;
    protected static final int PARAM_ELETISM_COUNT_TO_INVEST = 3;
    protected static final double PARAM_MIN_TO_BUY = 0.10;
    protected static final double PARAM_MIN_TO_SELL = 0.10;
    protected static boolean TO_PRINT_DATA = false;
    protected double MONEY = 10000;

    protected static SmartInvestiment SMART_INVESTIMENT = new SmartInvestiment();
    protected static final Service service = new Service();

    protected static final List<Company> ELITISM = new ArrayList<>();
    protected static final Set<DadosDoDia> DADOS_DO_DIAS = new HashSet<>();
    protected static final HashMap<String, Company> COMPANY_HASH_MAP = new HashMap<>();
    protected static final TreeMap<LocalDate, Set<DadosDoDia>> HASH_DATE_DADOS = new TreeMap<>();
//    protected static final HashMap<LocalDate, Set<DadosDoDia>> HASH_DATE_DADOS = new TreeMap<>();

    protected static final Set<String> avaliableCompanies = new HashSet<>();
    protected static final List<File> FILES = new ArrayList<>();


    protected List<File> setUpFiles() {
//        TO_PRINT_DATA = true;


//        FILES.add(new File(1L, Year.of(2014), "COTAHIST_A2014-REDUZIDO.TXT"));
        FILES.add(new File(1L, Year.of(2014), "COTAHIST_A2014.TXT"));
        FILES.add(new File(2L, Year.of(2015), "COTAHIST_A2015.TXT"));
//        FILES.add(new File(2L, Year.of(2015), "COTAHIST_A2015-REDUZIDO.TXT"));
        return FILES;
    }

    protected void generateCompanys() {
        avaliableCompanies.add("VIVT3F-R$");
        avaliableCompanies.add("NATU3-NM   R$");
        avaliableCompanies.add("JSLG3-NM   R$");
        avaliableCompanies.add("CSNA3F-R$");
        avaliableCompanies.add("LEVE3-NM   R$");
        avaliableCompanies.add("NATU3F-NM   R$");
        avaliableCompanies.add("VIVT3-R$");
        avaliableCompanies.add("UGPA3-NM   R$");
        avaliableCompanies.add("LREN3-NM   R$");
        avaliableCompanies.add("WEGE3F-NM   R$");
        avaliableCompanies.add("UGPA3F-NM   R$");
        avaliableCompanies.add("GRND3-NM   R$");
        avaliableCompanies.add("LEVE3F-NM   R$");
        avaliableCompanies.add("CIEL3-NM   R$");
        avaliableCompanies.add("WEGE3-NM   R$");
        avaliableCompanies.add("GRND3F-NM   R$");
        avaliableCompanies.add("CIEL3F-NM   R$");
        avaliableCompanies.add("LREN3F-NM   R$");
        avaliableCompanies.add("CSNA3-R$ ");
        avaliableCompanies.add("JSLG3F-NM   R$");
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
