package aiinvestimentsimulator;

import java.io.IOException;
import java.io.UncheckedIOException;
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
    private static Service service;
    private static final String FILE_PATH = System.getProperty("user.dir") + "/instances/";

    public FileReader(Service service) {
        this.service = service;
    }

    public void readFile(String fileDir) {
        try {
            service.generateCompanys();
            Stream<String> stream = Files.lines(Paths.get(FILE_PATH + fileDir));
            handleLines(stream);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + fileDir);
            e.printStackTrace();
        }
    }


    private void handleLines(Stream<String> stream) {
        String s = stream.toString();
        stream.forEach(line -> {
            try {
                String codNegociation = line.substring(12, 24).trim();
                if (service.containsCompany(codNegociation)) {
                    LocalDate date = LocalDate.parse(line.substring(02, 10), DateTimeFormatter.BASIC_ISO_DATE);
                    String name = line.substring(27, 39).trim();
                    Company company = service.getCompany(codNegociation);
                    Double openPrice = Double.valueOf(line.substring(56, 69));
                    Double maxPrice = Double.valueOf(line.substring(69, 82));
                    Double minPrice = Double.valueOf(line.substring(82, 95));
                    Double avgPrice = Double.valueOf(line.substring(95, 108));
                    Double closePrice = Double.valueOf(line.substring(108, 121));
                    Double total = Double.valueOf(line.substring(152, 170));
                    Double percentage = MathUtil.getPercentage(openPrice, closePrice);
                    DadosDoDia dadosDoDia = new DadosDoDia(name, company, date, openPrice, maxPrice, minPrice, avgPrice, closePrice, total, percentage, codNegociation);

                    company.getDadosDoDiaSet().add(dadosDoDia);
                    service.addDados(dadosDoDia);
                    service.addLocalDate(date, dadosDoDia);
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
        service.print();
    }


}