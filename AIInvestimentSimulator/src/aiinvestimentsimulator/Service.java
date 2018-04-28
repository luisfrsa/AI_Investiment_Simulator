package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;

public class Service extends Config {

    public Service() {
    }

    public void addLocalDate(LocalDate date, DadosDoDia dadosDoDia) {
        if (!hashDateDados.containsKey(date)) {
            hashDateDados.put(date, new HashSet<>());
        }
        Set<DadosDoDia> dados = hashDateDados.get(date);
        dados.add(dadosDoDia);
        hashDateDados.replace(date, dados);
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
            elitism.add(company);
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


    public void addDados(DadosDoDia dadosDoDia) {
        DADOS_DO_DIAS.add(dadosDoDia);
    }

    public void print() {
        WriteInFile.addToWrite(COMPANY_HASH_MAP.toString());
        WriteInFile.writeInFile("saida1.txt");
        WriteInFile.addToWrite(hashDateDados.toString());
        System.out.println("Escrevendo em arquivo");
        WriteInFile.writeInFile("saida2.txt");
////        System.out.println(COMPANY_HASH_MAP.toString());
//        System.out.println(hashDateDados.toString());
    }
}
