package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;

public class Config {

    protected static final int PARAM_ELITISM_COUNT = 100;
    protected static final int PARAM_ELETISM_COUNT_TO_INVEST = 3;
    protected static final int PARAM_MIN_TO_BUY = 20;
    protected static final int PARAM_MIN_TO_SELL = 20;
    protected static final double MONEY = 0;

    protected static final SmartInvestiment SMART_INVESTIMENT = new SmartInvestiment();
    protected static final Service service = new Service();

    protected static final List<Company> elitism = new ArrayList<>();
    protected static final Set<DadosDoDia> DADOS_DO_DIAS = new HashSet<>();
    protected static final HashMap<String, Company> COMPANY_HASH_MAP = new HashMap<>();
    protected static final TreeMap<LocalDate, Set<DadosDoDia>> hashDateDados = new TreeMap<>();

    protected static final Set<String> avaliableCompanies = new HashSet<>();


    protected void generateCompanys() {
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
