//package aiinvestimentsimulator;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;

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
                if (dadosDoDia.getComplany().getCount() > PARAM_MIN_DAYS_TO_BEGIN && dadosDoDia.getComplany().getNum_actions() > NUM_ACTIONS) {
                    Double closeTomorrow = getDadosFromTomorrow(localDate, dadosDoDia.getComplany());
                    mapSellList(dadosDoDia, dadosDoDia.getComplany(), closeTomorrow);
                    mapInvestList(dadosDoDia, dadosDoDia.getComplany(), closeTomorrow);
                }
                updateDadosCompany(dadosDoDia);
            });
            sellRunner();
            investRunner();
            toInvest = new HashMap<>();
            toSell = new HashMap<>();
        });
        sellAllOnLastDay();
        logWritter.writeInFile(RESULT_PATH + RUNNED_INSTANCE_STRING + service.finalPercent() + "_logWritter.txt");
    }

    private Double getDadosFromTomorrow(LocalDate today, Company company) {
        Double closeDadoTomorrow = null;
        DadosDoDia dadoTomorrow = null;
        LocalDate tomorrow = today.plusDays(1L);
        int numOfDays = 720;

        while (numOfDays-- > 0) {
            tomorrow = today.plusDays(1L);
            Set<DadosDoDia> setDadosTomorrow = HASH_DATE_DADOS.get(tomorrow);
            if (Objects.nonNull(setDadosTomorrow) && setDadosTomorrow.size() > 0) {
                dadoTomorrow = setDadosTomorrow.stream().filter(el -> el.getComplany() == company).findAny().orElse(null);
                if (Objects.nonNull(dadoTomorrow)) {
                    closeDadoTomorrow = dadoTomorrow.getClosePrice();
//                    System.out.println("Empresa " + company.getName() + ", Today " + today.toString() + " tomorrow " + tomorrow + " Value " + dadoTomorrow);
                    break;
                }
            }
        }
        return closeDadoTomorrow;
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
        toInvest.forEach((dadosDoDia, company) -> {});
        int toInvestSize = toInvest.size();
        toInvest.forEach((dadosDoDia, company) -> {
            double moneyToInvest = (MONEY / PARAM_MAX_MONEY_TO_INVEST) * (1/toInvestSize);
            if (moneyToInvest > 0) {
                investInCompany(dadosDoDia, company, moneyToInvest);
            }
        });

    }

    public double getPercentOfPercent(double average, double closePrice, double totalPercent) {
        double percent = 100 - MathUtil.getPercentage(average, closePrice);
        return (MathUtil.getPercentage(totalPercent, percent));
    }

    public void sellRunner() {
        toSell.forEach((dadosDoDia, company) -> {
            sellActions(dadosDoDia, company);
        });
    }

    public void mapInvestList(DadosDoDia dadosDoDia, Company company, Double closeTomorrow) {
        if (worthToInvest(dadosDoDia.getClosePrice(), closeTomorrow)) {
            toInvest.put(dadosDoDia, company);
        }
    }

    public void mapSellList(DadosDoDia dadosDoDia, Company company, Double closeTomorrow) {
        if (company.getActions() > 0) {
            if (worthToSell(dadosDoDia.getClosePrice(), closeTomorrow)) {
                toSell.put(dadosDoDia, company);
            }
        }
    }

    public boolean worthToInvest(double closePrice, Double closeTomorrow) {
        if (closeTomorrow == null) {
            return false;
        }
        return closePrice < closeTomorrow;
    }

    public boolean worthToSell(double closePrice, Double closeTomorrow) {
        if (closeTomorrow == null) {
            return false;
        }
        return closePrice > closeTomorrow;
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
        logWritter.addToWrite("-------VENDA Ação da empresa " + company.getName() + ", na data " + dadosDoDia.getDate().toString());
        logWritter.addToWrite(format("Ação teve sua ultima compra com valor de %s, com a quantidade %s, totalizando %s", company.getLastBuy(), company.getLastBuyCount(), (company.getLastBuy() * company.getLastBuyCount())));
        logWritter.addToWrite(format("Ação teve sua venda pelo valor de        %s, com a quantidade %s, totalizando %s, sua media é de %s", dadosDoDia.getClosePrice(), company.getActions(), (dadosDoDia.getClosePrice() * company.getActions()), company.getAverage()));
//        logWritter.addToWrite("Com a ultima compra no valor de " + company.getLastBuy() + " com a quantidade " + company.getLastBuyCount()+ " Gasto total de compra: " + (company.getLastBuy() * company.getLastBuyCount()));
//        logWritter.addToWrite("Ação com valor  hoje" + dadosDoDia.getClosePrice() + ", Sendo a media de " + company.getAverage());
//        logWritter.addToWrite("Ação com valor " + dadosDoDia.getClosePrice() + ", Sendo a media de " + company.getAverage());
        company.setProfit_prejudice(company.getProfit_prejudice() + (company.getActions() * (dadosDoDia.getClosePrice() - company.getLastBuy())));
        updateMoneyFromSoldAction(company.getActions(), dadosDoDia.getClosePrice());
        company.setLastBuy(0);
        company.setLastBuyCount(0);
        company.setActions(0);
    }

    public void investInCompany(DadosDoDia dadosDoDia, Company company, double money) {
        int numOfActions = MathUtil.numerOfActionsFromValue(money, dadosDoDia.getClosePrice());
        if (MONEY > 0 && numOfActions > 0) {
            logWritter.addToWrite("");
            logWritter.addToWrite("-------COMPRA Ação da empresa " + company.getName() + ", na data " + dadosDoDia.getDate().toString());
            logWritter.addToWrite("Ação com valor " + dadosDoDia.getClosePrice() + ", Sendo a media de " + company.getAverage());
            logWritter.addToWrite("Quantidade Ações antes:  " + company.getActions() + " comprando + " + numOfActions + " acoes, então acoes agora " + (company.getActions() + numOfActions));
            company.setLastBuy(dadosDoDia.getClosePrice());
            company.setLastBuyCount(numOfActions);
            company.setActions(company.getActions() + numOfActions);
            updateMoneyFromBoughtAction(numOfActions, dadosDoDia.getClosePrice());
        }
    }

    public void updateMoneyFromSoldAction(int numOfActions, double closePrice) {
//        logWritter.addToWrite("Vendendo " + numOfActions + " com valor " + closePrice);
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
