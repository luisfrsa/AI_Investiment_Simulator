package aiinvestimentsimulator;

import java.awt.image.WritableRenderedImage;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Service extends Config {

    public static final int PARAM_DISCARD_DIV = 2;
    public static final int PARAM_DAYS_TO_DISCARD = 30;
    public static final int MIN_COUNT_TO_PROCESS = 200;

    public Service() {
    }

    public void addLocalDate(LocalDate date, DadosDoDia dadosDoDia) {
        if (!HASH_DATE_DADOS.containsKey(date)) {
            HASH_DATE_DADOS.put(date, new HashSet<>());
        }
        Set<DadosDoDia> dados = HASH_DATE_DADOS.get(date);
        dados.add(dadosDoDia);
        HASH_DATE_DADOS.replace(date, dados);
    }

    public Company getCompany(String name) {
        if (!COMPANY_HASH_MAP.containsKey(name)) {
            Company company = new Company(name);
            populateCompany(company);
            COMPANY_HASH_MAP.put(name, company);
        }
        return COMPANY_HASH_MAP.get(name);
    }


    public void populateCompany(Company company) {
        for (int i = 0; i < PARAM_ELITISM_COUNT; i++) {
            ELITISM.add(company);
        }
    }

    public boolean containsCompany(String company) {
        for (String comp : avaliableCompanies) {
            if (company.contains(comp)) {
                return true;
            }
        }
        return false;
    }

    public void updateAverage(Company company, double value) {
        if (company.getCount() % PARAM_DAYS_TO_DISCARD == 0) {
//            discardDaysFromAverage(company);
        }
        company.increment(value);
    }

    public void discardDaysFromAverage(Company company) {
        company.setCount(company.getCount() / PARAM_DISCARD_DIV);
        company.setSum(company.getCount() / PARAM_DISCARD_DIV);

    }

    public void addDados(DadosDoDia dadosDoDia) {
        DADOS_DO_DIAS.add(dadosDoDia);
    }

    public void printLoadedData() {
        if (TO_PRINT_DATA) {

            WriteInFile loadedCompanyData = new WriteInFile();
            loadedCompanyData.addToWrite(COMPANY_HASH_MAP.toString());
            loadedCompanyData.writeInFile("loadedCompanyData.txt");

            String str = "";
            for (Map.Entry<LocalDate, Set<DadosDoDia>> entry : HASH_DATE_DADOS.entrySet()) {
                str += "\n{" + entry.getKey().toString() + "}";
                for (DadosDoDia dado : entry.getValue()) {
                    str += dado.toString();
                }
            }

            WriteInFile loadedDateCompany = new WriteInFile();

            loadedDateCompany.addToWrite(str);
            loadedDateCompany.writeInFile("loadedDateCompany.txt");

            String qdeEmpresa = "";
            for (Map.Entry<String, Company> entry : COMPANY_HASH_MAP.entrySet()) {
                qdeEmpresa += "Empresa " + entry.getKey() + " com " + entry.getValue().getDadosDoDiaSet().size() + " registros\n";
            }

            WriteInFile qdeCompany = new WriteInFile();

            qdeCompany.addToWrite(qdeEmpresa);
            qdeCompany.writeInFile("qdeCompany.txt");

        }
    }


}
