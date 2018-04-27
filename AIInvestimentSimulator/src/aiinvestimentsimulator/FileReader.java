package aiinvestimentsimulator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author luisr
 */
public class FileReader {

    private static final Set<String> COMPANYS = new HashSet<>();
    private static final String FILE_PATH = System.getProperty("user.dir") + "/instances/";

    public void readFile(String fileDir) {
        try {
            generateCompanys();
            Stream<String> stream = Files.lines(Paths.get(FILE_PATH + fileDir));
            handleLines(stream);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + fileDir);
            e.printStackTrace();
        }
    }


    private void handleLines(Stream<String> stream) {
        Set<DadosDoDia> setDadosDoDia = new HashSet<>();
        HashMap<String, Company> hashCompany = new HashMap<>();
        TreeMap<LocalDate, Set<DadosDoDia>> hashDateDados = new TreeMap<>();

        stream.forEach(line -> {
            try {
                String codNegociation = line.substring(12, 24).trim();
                if(containsCompany(codNegociation)) {
                    LocalDate date = LocalDate.parse(line.substring(02, 10), DateTimeFormatter.BASIC_ISO_DATE);
                    String name = line.substring(27, 39).trim();
                    Company company = getCompany(hashCompany, codNegociation);
                    Double openPrice = Double.valueOf(line.substring(56, 69));
                    Double maxPrice = Double.valueOf(line.substring(69, 82));
                    Double minPrice = Double.valueOf(line.substring(82, 95));
                    Double avgPrice = Double.valueOf(line.substring(95, 108));
                    Double closePrice = Double.valueOf(line.substring(108, 121));
                    Double total = Double.valueOf(line.substring(152, 170));
                    Double percentage = MathUtil.getPercentage(openPrice, closePrice);
                    DadosDoDia dadosDoDia = new DadosDoDia(name, company, date, openPrice, maxPrice, minPrice, avgPrice, closePrice, total, percentage, codNegociation);

                    company.getDadosDoDiaSet().add(dadosDoDia);
                    setDadosDoDia.add(dadosDoDia);
                    addLocalDate(hashDateDados, date, dadosDoDia);
                }
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println("DateTimeParseException (primeira e ultima linha)");
            } catch (UncheckedIOException e) {
                System.out.println("UncheckedIOException: ");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Erro ao ao formatar dados: ");
                e.printStackTrace();
            }
        });
        WriteInFile.addToWrite(hashCompany.toString());
        WriteInFile.writeInFile("saida1.txt");
        WriteInFile.addToWrite(hashDateDados.toString());
        System.out.println("Escrevendo em arquivo");
        WriteInFile.writeInFile("saida2.txt");
////        System.out.println(hashCompany.toString());
//        System.out.println(hashDateDados.toString());
    }

    private void addLocalDate(TreeMap<LocalDate, Set<DadosDoDia>> hashDateDados, LocalDate date, DadosDoDia dadosDoDia) {
        if (!hashDateDados.containsKey(date)) {
            hashDateDados.put(date, new HashSet<>());
        }
        Set<DadosDoDia> dados = hashDateDados.get(date);
        dados.add(dadosDoDia);
        hashDateDados.replace(date, dados);
    }

    private Company getCompany(HashMap<String, Company> hashCompany, String name) {
        if (!hashCompany.containsKey(name)) {
            hashCompany.put(name, new Company(name));
        }
        return hashCompany.get(name);
    }

    private boolean containsCompany(String company){
        for(String comp :COMPANYS){
            if(company.contains(comp)){
                return true;
            }
        }
        return false;
    }

    private void  generateCompanys (){
        COMPANYS.add("CSNA3");
        COMPANYS.add("NATU3");
        COMPANYS.add("CIEL3");
        COMPANYS.add("VIVT3");
        COMPANYS.add("GRND3");
        COMPANYS.add("JSLG3");
        COMPANYS.add("WEGE3");
        COMPANYS.add("LREN3");
        COMPANYS.add("UGPA3");
        COMPANYS.add("LEVE3");
        COMPANYS.add("VEBM");
    }

}