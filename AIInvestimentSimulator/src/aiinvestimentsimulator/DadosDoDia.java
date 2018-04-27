/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiinvestimentsimulator;

import java.time.LocalDate;

/**
 * @author luisr
 */
public class DadosDoDia {

    private String name;
    private String codNegociation;
    private Company complany;
    private LocalDate date;
    private Double openPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double avgPrice;
    private Double closePrice;
    private Double total;
    private Double percent;


    public DadosDoDia(String name, Company complany, LocalDate date, Double openPrice, Double maxPrice, Double minPrice, Double avgPrice, Double closePrice, Double total, Double percent, String codNegociation) {
        this.name = name;
        this.complany = complany;
        this.date = date;
        this.openPrice = openPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.avgPrice = avgPrice;
        this.closePrice = closePrice;
        this.total = total;
        this.percent = percent;
        this.codNegociation = codNegociation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getComplany() {
        return complany;
    }

    public void setComplany(Company complany) {
        this.complany = complany;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    @Override
    public String toString() {
        return "\n     DadosDoDia{" +
                "name='" + name + '\'' +
                "codNegociation='" + codNegociation + '\'' +
                ", complany=" + complany.getName() +
                ", date=" + date +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", percentage=" + percent +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", avgPrice=" + avgPrice +
                ", total=" + total +
                "} ";
    }
}
