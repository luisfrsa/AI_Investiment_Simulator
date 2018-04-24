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
import java.util.stream.Stream;

/**
 * @author luisr
 */
public class FileReader {

    private static final String FILE_PATH = System.getProperty("user.dir")+"/instances/";

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

        stream.parallel().forEach(line -> {
            try {
                LocalDate date = LocalDate.parse(line.substring(02, 10), DateTimeFormatter.BASIC_ISO_DATE);
                String name = line.substring(27, 39);
                Company complany = getCompany(hashCompany, line.substring(27, 39).trim());
                Double openPrice = Double.valueOf(line.substring(56, 69));
                Double maxPrice = Double.valueOf(line.substring(69, 82));
                Double minPrice = Double.valueOf(line.substring(82, 95));
                Double avgPrice = Double.valueOf(line.substring(95, 108));
                Double closePrice = Double.valueOf(line.substring(108, 121));
                Double total = Double.valueOf(line.substring(152, 170));
                DadosDoDia dadosDoDia = new DadosDoDia(name, complany, date, openPrice, maxPrice, minPrice, avgPrice, closePrice, total);

                complany.setDadosDoDiaSet(setDadosDoDia);
                setDadosDoDia.add(dadosDoDia);
            }catch (DateTimeParseException dateTimeParseException){
                System.out.println("DateTimeParseException (primeira e ultima linha)");
            }
        });
    }

    private Company getCompany(HashMap<String, Company> hashCompany, String name) {
        if (!hashCompany.containsKey(name)) {
            hashCompany.put(name, new Company(name));
        }
        return hashCompany.get(name);
    }

}