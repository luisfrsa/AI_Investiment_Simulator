package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SmartInvestiment extends Config {
    private static HashMap<DadosDoDia, Company> toInvest = new HashMap<>();
    private static HashMap<DadosDoDia, Company> toSell = new HashMap<>();
    private static final WriteInFile logWritter = new WriteInFile();

    public SmartInvestiment() {
    }

    public void run() {
        HASH_DATE_DADOS.forEach((localDate, dadosDoDiasSet) -> {
//            logWritter.addToWrite(localDate);
            dadosDoDiasSet.forEach(dadosDoDia -> {
                if (dadosDoDia.getComplany().getCount() > 5) {
                    mapSellList(dadosDoDia, dadosDoDia.getComplany());
                    mapInvestList(dadosDoDia, dadosDoDia.getComplany());
                }
                updateDadosCompany(dadosDoDia);
            });
            sellRunner();
            investRunner();
            toInvest = new HashMap<>();
            toSell = new HashMap<>();
        });
        sellAllOnLastDay();
        logWritter.writeInFile("logWritter.txt");
    }

    private void sellAllOnLastDay() {
        toSell = new HashMap<>();
        logWritter.addToWrite("------------------------");
        logWritter.addToWrite("VENDENDO TUDO");
        logWritter.addToWrite("------------------------");
        Map.Entry<LocalDate, Set<DadosDoDia>> lastEntry = HASH_DATE_DADOS.lastEntry();
        lastEntry.getValue().forEach(dadosDoDia -> {
            if (dadosDoDia.getComplany().getActions() > 0) {
                toSell.put(dadosDoDia, dadosDoDia.getComplany());
            }
        });
        sellRunner();
    }

    public void investRunner() {
        List<String> delet_MELsit = new ArrayList<>();
        double totalPercent = toInvest.entrySet()
                .stream()
                .mapToDouble(entry -> {
                    String delete_ME = "DELETE_ME: AVG:" + entry.getValue().getAverage() + ", close " + entry.getKey().getClosePrice();
                    delete_ME += ", Result: " + (100-MathUtil.getPercentage(entry.getValue().getAverage(), entry.getKey().getClosePrice()));
                    delet_MELsit.add(delete_ME);
                    return 100 -MathUtil.getPercentage(entry.getValue().getAverage(), entry.getKey().getClosePrice());
                }).sum();
        logWritter.addToWrite("SHOW totalPercent -> "+totalPercent+"\n" + delet_MELsit.stream().collect(Collectors.joining("\n")));

        toInvest.forEach((dadosDoDia, company) -> {
            logWritter.addToWrite("");
            logWritter.addToWrite("PERCENT OF PERCENT @@@@@");
            logWritter.addToWrite(company.getName() + " at " + dadosDoDia.getDate());
            logWritter.addToWrite("Sum " + company.getSum());
            logWritter.addToWrite("Count " + company.getCount());
            logWritter.addToWrite("AVG " + company.getAverage());
            if ((getPercentOfPercent(company.getAverage(), dadosDoDia.getClosePrice(), totalPercent) / 100) < 0) {
                logWritter.addToWrite("MENOR QUE ZERO!!!:  AVG: " + company.getAverage() + ", close: " + dadosDoDia.getClosePrice() + ", totalPercent " + totalPercent);
                logWritter.addToWrite("FROM: " + delet_MELsit.stream().collect(Collectors.joining("\n")));
            }
            double moneyToInvest = MONEY * (getPercentOfPercent(company.getAverage(), dadosDoDia.getClosePrice(), totalPercent) / 100);
            if (moneyToInvest > 0) {
                investInCompany(dadosDoDia, company, moneyToInvest);
            }
        });

    }

    public double getPercentOfPercent(double average, double closePrice, double totalPercent) {
        logWritter.addToWrite("closePrice : " + closePrice);
        logWritter.addToWrite("totalPercent : " + totalPercent);
        double percent = MathUtil.getPercentage(average, closePrice) - 100;
        logWritter.addToWrite("percent  getPercentage(average, closePric): " + percent);
        logWritter.addToWrite("percent  ofPercent(totalPercent, percent): " + (MathUtil.getPercentage(totalPercent, percent)));
        return (MathUtil.getPercentage(totalPercent, percent));
    }

    public void sellRunner() {
        toSell.forEach((dadosDoDia, company) -> {
            sellActions(dadosDoDia, company);
        });
    }

    public void mapInvestList(DadosDoDia dadosDoDia, Company company) {
        if (worthToInvest(dadosDoDia.getClosePrice(), dadosDoDia.getComplany().getAverage())) {
            logWritter.addToWrite("");
            logWritter.addToWrite("  WORTH to INVEST!!!!");
            logWritter.addToWrite(company.getName() + " at " + dadosDoDia.getDate());
            logWritter.addToWrite("AVG: " + company.getAverage());
            logWritter.addToWrite("AVG + " + PARAM_MIN_TO_BUY + ": " + MathUtil.calcAVGWithParamPercentToBuy(PARAM_MIN_TO_BUY, company.getAverage()));
            logWritter.addToWrite("Close: " + dadosDoDia.getClosePrice());
            toInvest.put(dadosDoDia, dadosDoDia.getComplany());
        }
    }

    public void mapSellList(DadosDoDia dadosDoDia, Company company) {
        if (company.getActions() > 0) {
            if (worthToSell(dadosDoDia.getClosePrice(), dadosDoDia.getComplany().getAverage())) {
                toSell.put(dadosDoDia, dadosDoDia.getComplany());
            }
        }
    }

    public boolean worthToInvest(double closePrice, double average) {
//        if(closePrice <= haveEnoughtPriceToBuy(average)){
//            logWritter.addToWrite("DEBUGGER: " + company.getAverage());
//
//        }
        return closePrice <= haveEnoughtPriceToBuy(average);
    }

    public boolean worthToInvest(DadosDoDia dadosDoDia, Company company) {
        return dadosDoDia.getClosePrice() <= haveEnoughtPriceToBuy(company.getAverage());
    }

    public boolean worthToSell(double closePrice, double average) {
        return closePrice >= haveEnoughtPriceToSell(average);
    }

    public boolean worthToSell(DadosDoDia dadosDoDia, Company company) {
        if (company.getActions() > 0) {
            return dadosDoDia.getClosePrice() >= haveEnoughtPriceToSell(company.getAverage());
        }
        return false;
    }

    public double haveEnoughtPriceToBuy(double avgPrice) {
        return MathUtil.calcAVGWithParamPercentToBuy(PARAM_MIN_TO_BUY, avgPrice);
    }

    public double haveEnoughtPriceToSell(double avgPrice) {
        return MathUtil.calcAVGWithParamPercentToSell(PARAM_MIN_TO_SELL, avgPrice);
    }

    public void sellActions(DadosDoDia dadosDoDia, Company company) {
        logWritter.addToWrite("");
        logWritter.addToWrite("VENDA-------");
        logWritter.addToWrite(dadosDoDia.getDate().toString());
        logWritter.addToWrite(" Ação da empresa " + dadosDoDia.getComplany().getName());
        logWritter.addToWrite(" Com media de " + dadosDoDia.getComplany().getAverage());

        updateMoneyFromSoldAction(company.getActions(), dadosDoDia.getClosePrice());
        company.setActions(0);
    }

    public void investInCompany(DadosDoDia dadosDoDia, Company company, double money) {
        int numOfActions = MathUtil.numerOfActionsFromValue(money, dadosDoDia.getClosePrice());
        if (numOfActions > 0) {
            logWritter.addToWrite("");
            logWritter.addToWrite("-------COMPRA");
            logWritter.addToWrite(dadosDoDia.getDate().toString());
            logWritter.addToWrite(" Ação da empresa " + dadosDoDia.getComplany().getName());
            logWritter.addToWrite(" Com media de " + dadosDoDia.getComplany().getAverage());
            company.setActions(company.getActions() + numOfActions);
            updateMoneyFromBoughtAction(numOfActions, dadosDoDia.getClosePrice());
        }
    }

    public void updateMoneyFromSoldAction(int numOfActions, double closePrice) {

        logWritter.addToWrite("Vendendo " + numOfActions + " com valor " + closePrice);
        String strprint = "Money Antes: " + MONEY;
        MONEY += (numOfActions * closePrice);
        strprint += ", Ganhando " + (numOfActions * closePrice);
        strprint += ", E agora com " + MONEY;
        logWritter.addToWrite(strprint);
    }

    public void updateMoneyFromBoughtAction(int numOfActions, double closePrice) {

        logWritter.addToWrite("Comprando " + numOfActions + " com valor " + closePrice);
        String strprint = "Money Agora: " + MONEY;
        MONEY = MONEY - (numOfActions * closePrice);
        strprint += ", Gastando " + (numOfActions * closePrice);
        strprint += ", E agora com " + MONEY;
        logWritter.addToWrite(strprint);
    }

    public void updateDadosCompany(DadosDoDia dadosDoDia) {
        service.updateAverage(dadosDoDia.getComplany(), dadosDoDia.getClosePrice());
    }

}
