//package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SmartInvestiment extends Config {

    private static HashMap<DadosDoDia, Company> toInvest = new HashMap<>();
    private static HashMap<DadosDoDia, Company> toSell = new HashMap<>();
    private static final WriteInFile logWritter = new WriteInFile();


    private int count_high = 0;
    private int count_low = 0;

    public SmartInvestiment() {
    }

    public void run() {
        HASH_DATE_DADOS.forEach((localDate, dadosDoDiasSet) -> {
//            logWritter.addToWrite(localDate);
            dadosDoDiasSet.forEach(dadosDoDia -> {
                if (dadosDoDia.getComplany().getCount() > PARAM_MIN_DAYS_TO_BEGIN) {
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
//        int size = toInvest.size();
        List<String> delet_MELsit = new ArrayList<>();
        double totalPercent = toInvest.entrySet()
                .stream()
                .mapToDouble(entry -> {
                    String delete_ME = "DELETE_ME: AVG:" + entry.getValue().getAverage() + ", close " + entry.getKey().getClosePrice();
                    delete_ME += ", Result: " + (100 - MathUtil.getPercentage(entry.getValue().getAverage(), entry.getKey().getClosePrice()));
                    delet_MELsit.add(delete_ME);
                    return 100 - MathUtil.getPercentage(entry.getValue().getAverage(), entry.getKey().getClosePrice());
                }).sum();
//        logWritter.addToWrite("SHOW totalPercent -> " + totalPercent + "\n" + delet_MELsit.stream().collect(Collectors.joining("\n")));

        toInvest.forEach((dadosDoDia, company) -> {
//            logWritter.addToWrite("");
//            logWritter.addToWrite("PERCENT OF PERCENT @@@@@");
//            logWritter.addToWrite(company.getName() + " at " + dadosDoDia.getDate());
//            logWritter.addToWrite("Sum " + company.getSum());
//            logWritter.addToWrite("Count " + company.getCount());
//            logWritter.addToWrite("AVG " + company.getAverage());
            if ((getPercentOfPercent(company.getAverage(), dadosDoDia.getClosePrice(), totalPercent) / 100) < 0) {
                logWritter.addToWrite("MENOR QUE ZERO!!!:  AVG: " + company.getAverage() + ", close: " + dadosDoDia.getClosePrice() + ", totalPercent " + totalPercent);
                logWritter.addToWrite("FROM: " + delet_MELsit.stream().collect(Collectors.joining("\n")));
            }
            double moneyToInvest = (MONEY / PARAM_MAX_MONEY_TO_INVEST) * (getPercentOfPercent(company.getAverage(), dadosDoDia.getClosePrice(), totalPercent) / 100);
            if (moneyToInvest > 0) {
                investInCompany(dadosDoDia, company, moneyToInvest);
            }
        });

    }

    public double getPercentOfPercent(double average, double closePrice, double totalPercent) {
//        logWritter.addToWrite("closePrice : " + closePrice);
//        logWritter.addToWrite("totalPercent : " + totalPercent);
        double percent = 100 - MathUtil.getPercentage(average, closePrice);
//        logWritter.addToWrite("percent  getPercentage(average, closePric): " + percent);
//        logWritter.addToWrite("percent  ofPercent(totalPercent, percent): " + (MathUtil.getPercentage(totalPercent, percent)));
        return (MathUtil.getPercentage(totalPercent, percent));
    }

    public void sellRunner() {
        toSell.forEach((dadosDoDia, company) -> {
            sellActions(dadosDoDia, company);
        });
    }

    public void mapInvestList(DadosDoDia dadosDoDia, Company company) {
        if (worthToInvest(dadosDoDia.getClosePrice(), company.getAverage())) {
//            logWritter.addToWrite("");
//            logWritter.addToWrite("  WORTH to INVEST!!!!");
//            logWritter.addToWrite(company.getName() + " at " + dadosDoDia.getDate());
//            logWritter.addToWrite("AVG: " + company.getAverage());
//            logWritter.addToWrite("AVG + " + PARAM_MIN_TO_BUY + ": " + MathUtil.calcAVGWithParamPercentToBuy(PARAM_MIN_TO_BUY, company.getAverage()));
//            logWritter.addToWrite("Close: " + dadosDoDia.getClosePrice());
            company.setLastBuy(dadosDoDia.getClosePrice());
            toInvest.put(dadosDoDia, company);
        }
    }

    public void mapSellList(DadosDoDia dadosDoDia, Company company) {
        if (company.getActions() > 0) {
            if (worthToSell(dadosDoDia.getClosePrice(), company.getLastBuy())) {
                toSell.put(dadosDoDia, company);
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

    public boolean worthToSell(double closePrice, double average_or_last_buy) {
        int bypass = 1;
        if(bypass ==0) {
            return closePrice >= haveEnoughtPriceToSell(average_or_last_buy);
        }
        if (closePrice < average_or_last_buy) {
            count_low++;
            return false;
        } else {
            count_high++;
            return true;
        }

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
        logWritter.addToWrite("-------VENDA Ação da empresa " + dadosDoDia.getComplany().getName() + ", na data" + dadosDoDia.getDate().toString());
        logWritter.addToWrite("Ação com valor " + dadosDoDia.getClosePrice() + ", Sendo a media de " + dadosDoDia.getComplany().getAverage());
        logWritter.addToWrite("Com media de " + dadosDoDia.getComplany().getAverage());
        company.setProfit_prejudice(company.getProfit_prejudice() + (company.getActions()*(dadosDoDia.getClosePrice()-company.getLastBuy())));
        updateMoneyFromSoldAction(company.getActions(), dadosDoDia.getClosePrice());
        company.setActions(0);
    }

    public void investInCompany(DadosDoDia dadosDoDia, Company company, double money) {
        int numOfActions = MathUtil.numerOfActionsFromValue(money, dadosDoDia.getClosePrice());
        if (numOfActions > 0) {
            logWritter.addToWrite("");
            logWritter.addToWrite("-------COMPRA Ação da empresa " + dadosDoDia.getComplany().getName() + ", na data " + dadosDoDia.getDate().toString());
            logWritter.addToWrite("Ação com valor " + dadosDoDia.getClosePrice() + ", Sendo a media de " + dadosDoDia.getComplany().getAverage());
            logWritter.addToWrite("Quantidade Ações antes:  " + company.getActions() + " comprando + " + numOfActions + " acoes, então acoes agora " + (company.getActions() + numOfActions));
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
