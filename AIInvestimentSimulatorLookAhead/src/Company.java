/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luisr
 */
public class Company {

    private String name;
    private double average;
    private double sum;
    private int actions;
    private double count;
    private double lastBuy;
    private double lastBuyCount;
    private double profit_prejudice;
    private double num_actions;
    private Set<DadosDoDia> dadosDoDiaSet = new HashSet<>();


    public Company() {
        this.average = 0;
        this.sum = 0;
        this.count = 0;
        this.profit_prejudice = 0;
        this.lastBuyCount = 0;
        this.lastBuy = 0;

    }

    public void increment(double value) {
        this.count++;
        this.sum += value;
        this.average = this.sum / this.count;
    }

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DadosDoDia> getDadosDoDiaSet() {
        return dadosDoDiaSet;
    }

    public void setDadosDoDiaSet(Set<DadosDoDia> dadosDoDiaSet) {
        this.dadosDoDiaSet = dadosDoDiaSet;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public double getLastBuy() {
        return lastBuy;
    }

    public void setLastBuy(double lastBuy) {
        this.lastBuy = lastBuy;
    }

    public double getProfit_prejudice() {
        return profit_prejudice;
    }

    public void setProfit_prejudice(double profit_prejudice) {
        this.profit_prejudice = profit_prejudice;
    }

    public double getNum_actions() {
        return num_actions;
    }

    public void setNum_actions(double num_actions) {
        this.num_actions = num_actions;
    }

    public double getLastBuyCount() {
        return lastBuyCount;
    }

    public void setLastBuyCount(double lastBuyCount) {
        this.lastBuyCount = lastBuyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(getName(), company.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Company{ " +
                "name='" + name + '\'' +
                ", dadosDoDiaSet=" + dadosDoDiaSet.stream().map(d -> d.toString()).reduce((e1, e2) -> {
            return e2 + ", " + e1;
        }) +
                "}\n";
    }
}
