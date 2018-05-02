//package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;

public class Service extends Config {


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
            COMPANY_HASH_MAP.put(name, company);
        }
        return COMPANY_HASH_MAP.get(name);
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
            discardDaysFromAverage(company);
        }
        company.increment(value);
    }

    public void discardDaysFromAverage(Company company) {
//        System.out.println("--");
//        System.out.println("ANTES: count -> [" + company.getCount() + "], sum -> [" + company.getSum() + "], avg ->[" + company.getAverage() + "]");
        company.setCount(company.getCount() / PARAM_DISCARD_DIV);
        company.setSum(company.getSum() / PARAM_DISCARD_DIV);
//        System.out.println("ANTES: count -> [" + company.getCount() + "], sum -> [" + company.getSum() + "], avg ->[" + company.getAverage() + "]");


    }


    public void printLoadedData() {
        if (TO_PRINT_DATA) {

            WriteInFile loadedCompanyData = new WriteInFile();
            loadedCompanyData.addToWrite(COMPANY_HASH_MAP.toString());
            loadedCompanyData.writeInFile(RESULT_PATH + RUNNED_INSTANCE_STRING + finalPercent() + "loadedCompanyData.txt");

            String str = "";
            for (Map.Entry<LocalDate, Set<DadosDoDia>> entry : HASH_DATE_DADOS.entrySet()) {
                str += "\n{" + entry.getKey().toString() + "}";
                for (DadosDoDia dado : entry.getValue()) {
                    str += dado.toString();
                }
            }

            WriteInFile loadedDateCompany = new WriteInFile();

            loadedDateCompany.addToWrite(str);
            loadedDateCompany.writeInFile(RESULT_PATH + RUNNED_INSTANCE_STRING + finalPercent() + "loadedDateCompany.txt");


        }

    }

    public void printResultCompanies() {
        String qdeEmpresa = "";
        TreeMap<Double, Company> empresaOrdenada = new TreeMap<Double, Company>();
        for (Map.Entry<String, Company> entry : COMPANY_HASH_MAP.entrySet()) {
            empresaOrdenada.put(entry.getValue().getProfit_prejudice(), entry.getValue());
        }

        for (Map.Entry<Double, Company> entry : empresaOrdenada.entrySet()) {
            qdeEmpresa += "Empresa " + entry.getValue().getName() + " com " + entry.getValue().getDadosDoDiaSet().size() + " registros ";
            qdeEmpresa += "E lucro/prejuizo de " + entry.getValue().getProfit_prejudice();
            qdeEmpresa += "\n";
        }


        WriteInFile qdeCompany = new WriteInFile();
        qdeCompany.addToWrite(qdeEmpresa);
        qdeCompany.writeInFile(RESULT_PATH + RUNNED_INSTANCE_STRING + finalPercent() + "_companyDetails" + ".txt");
//        qdeCompany.writeInFile(RESULT_PATH + finalPercent() + "_"+System.currentTimeMillis()+"_companyDetails" + ".txt");

    }


    public void printFinalResult() {
        String finalResult = "Resultado dos parametros\n";
        finalResult += "INSTANCE: " + RUNNED_INSTANCE_STRING + "\n";
        finalResult += "PARAM_MIN_TO_BUY: " + PARAM_MIN_TO_BUY + "\n";
        finalResult += "PARAM_MIN_TO_SELL: " + PARAM_MIN_TO_SELL + "\n";
        finalResult += "PARAM_DISCARD_DIV: " + PARAM_DISCARD_DIV + "\n";
        finalResult += "PARAM_DAYS_TO_DISCARD: " + PARAM_DAYS_TO_DISCARD + "\n";
        finalResult += "PARAM_MIN_DAYS_TO_BEGIN: " + PARAM_MIN_DAYS_TO_BEGIN + "\n";
        finalResult += "PARAM_MAX_MONEY_TO_INVEST: " + PARAM_MAX_MONEY_TO_INVEST + "\n";
        finalResult += "START_MONEY: " + START_MONEY + "\n";
        finalResult += "MONEY: " + MONEY + "\n";
        finalResult += "PERCENT: " + finalPercent();

        WriteInFile finalResultFile = new WriteInFile();

        finalResultFile.addToWrite(finalResult);
        finalResultFile.writeInFile(RESULT_PATH + RUNNED_INSTANCE_STRING + finalPercent() + "_finalResult" + ".txt");
//        finalResultFile.writeInFile(RESULT_PATH + finalPercent() + "_"+System.currentTimeMillis()+ "_finalResult" + ".txt");

    }


    public double finalPercent() {
        return MathUtil.getPercentage(START_MONEY, MONEY);
    }
}
