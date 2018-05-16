//package aiinvestimentsimulator;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

/**
 * @author luisr
 */
public class FileReader extends Config {
    private static Service service;
    private static final String FILE_PATH = System.getProperty("user.dir") + "/../instances/";

    public FileReader(Service service) {
        this.service = service;
    }

    public void readFileToTrain(String fileDir) {
        try {
            service.generateCompanys();
            Stream<String> stream = Files.lines(Paths.get(FILE_PATH + fileDir), Charset.defaultCharset());
            System.out.println("TRain " + fileDir);
            handleLinesToTrain(stream);

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + fileDir);
            e.printStackTrace();
        }
    }

    public void readFileToRun(String fileDir) {
        try {
            service.generateCompanys();
            Stream<String> stream = Files.lines(Paths.get(FILE_PATH + fileDir));
            System.out.println("Run " + fileDir);
            handleLinesToRun(stream);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + fileDir);
            e.printStackTrace();
        }
    }

    private void handleLinesToRun(Stream<String> stream) {
        try {
            stream.forEach(line -> {
                String codNegociation = line.substring(12, 24).trim() + "-" + line.substring(45, 56).trim();
                if (service.containsCompany(codNegociation)) {
                    DadosDoDia dadosDoDia = buildDadosDoDia(codNegociation, line);
                    Company company = dadosDoDia.getComplany();
                    company.getDadosDoDiaSet().add(dadosDoDia);
                    company.increment(dadosDoDia.getClosePrice());
                    company.getDadosDoDiaSet().add(dadosDoDia);
                    company.setNum_actions(company.getNum_actions() + 1);
                    service.addLocalDate(dadosDoDia.getDate(), dadosDoDia);
                }
            });
        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("DateTimeParseException (primeira e ultima linha)");
        } catch (UncheckedIOException e) {
            System.out.println("UncheckedIOException: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro ao ao formatar dados: ");
            e.printStackTrace();
        }
    }

    private void handleLine(String line) {
        String codNegociation = line.substring(12, 24).trim() + "-" + line.substring(45, 56).trim();
        if (service.containsCompany(codNegociation)) {
            DadosDoDia dadosDoDia = buildDadosDoDia(codNegociation, line);
            dadosDoDia.getComplany().getDadosDoDiaSet().add(dadosDoDia);
            dadosDoDia.getComplany().increment(dadosDoDia.getClosePrice());
        }
    }

    private void handleLinesToTrain(Stream<String> stream) {
        stream.forEach(line -> {
            try {
                handleLine(line);
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

    }

    private DadosDoDia buildDadosDoDia(String codNegociation, String line) {
        LocalDate date = LocalDate.parse(line.substring(02, 10), DateTimeFormatter.BASIC_ISO_DATE);
        String name = line.substring(27, 39).trim();
        Company company = service.getCompany(codNegociation);
        Double openPrice = Double.valueOf(line.substring(56, 69)) / AHUNDREAD;
        Double maxPrice = Double.valueOf(line.substring(69, 82)) / AHUNDREAD;
        Double minPrice = Double.valueOf(line.substring(82, 95)) / AHUNDREAD;
        Double avgPrice = Double.valueOf(line.substring(95, 108)) / AHUNDREAD;
        Double closePrice = Double.valueOf(line.substring(108, 121)) / AHUNDREAD;
        Double total = Double.valueOf(line.substring(152, 170)) / AHUNDREAD;
        Double percentage = MathUtil.getFormatedPercentage(openPrice, closePrice);
        DadosDoDia dadosDoDia = new DadosDoDia(name, company, date, openPrice, maxPrice, minPrice, avgPrice, closePrice, total, percentage, codNegociation);
        return dadosDoDia;
    }


}