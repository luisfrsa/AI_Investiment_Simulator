package aiinvestimentsimulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * @author luisr
 */
public class FileReader {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/instances/";

    public void readFile(String fileDir) {
        try {
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

        stream.parallel().forEach(line -> {
            try {
                LocalDate date = LocalDate.parse(line.substring(02, 10), DateTimeFormatter.BASIC_ISO_DATE);
                String name = line.substring(27, 39).trim();
                Company company = getCompany(hashCompany, line.substring(27, 39).trim());
                Double openPrice = Double.valueOf(line.substring(56, 69));
                Double maxPrice = Double.valueOf(line.substring(69, 82));
                Double minPrice = Double.valueOf(line.substring(82, 95));
                Double avgPrice = Double.valueOf(line.substring(95, 108));
                Double closePrice = Double.valueOf(line.substring(108, 121));
                Double total = Double.valueOf(line.substring(152, 170));
                DadosDoDia dadosDoDia = new DadosDoDia(name, company, date, openPrice, maxPrice, minPrice, avgPrice, closePrice, total);

                company.getDadosDoDiaSet().add(dadosDoDia);
                setDadosDoDia.add(dadosDoDia);
                addLocalDate(hashDateDados, date, dadosDoDia);
            } catch (DateTimeParseException dateTimeParseException) {
                System.out.println("DateTimeParseException (primeira e ultima linha)");
            }
        });
//        System.out.println(hashCompany.toString());
        hashDateDados.forEach((k,v)->{
            System.out.println(k.toString());
            System.out.println("->");
            System.out.println(v.toString());
        });
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

}