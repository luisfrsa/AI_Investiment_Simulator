/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiinvestimentsimulator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author luisr
 */
public class DadosDoDia {

    private String name;
    private Company complany;
    private LocalDate date;
    private Double openPrice;
    private Double maxPrice;
    private Double minPrice;
    private Double avgPrice;
    private Double closePrice;
    private Double total;
    public DadosDoDia(String name, Company complany, LocalDate date, Double openPrice, Double maxPrice, Double minPrice, Double avgPrice, Double closePrice,Double total) {
        this.name = name;
        this.complany = complany;
        this.date = date;
        this.openPrice = openPrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.avgPrice = avgPrice;
        this.closePrice = closePrice;
        this.total = total;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DadosDoDia)) return false;
        DadosDoDia that = (DadosDoDia) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getComplany(), that.getComplany()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getOpenPrice(), that.getOpenPrice()) &&
                Objects.equals(getMaxPrice(), that.getMaxPrice()) &&
                Objects.equals(getMinPrice(), that.getMinPrice()) &&
                Objects.equals(getAvgPrice(), that.getAvgPrice()) &&
                Objects.equals(getClosePrice(), that.getClosePrice()) &&
                Objects.equals(getTotal(), that.getTotal());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getComplany(), getDate(), getOpenPrice(), getMaxPrice(), getMinPrice(), getAvgPrice(), getClosePrice(), getTotal());
    }

    @Override
    public String toString() {
        return "\n     DadosDoDia{" +
                "name='" + name + '\'' +
                ", complany=" + complany.getName() +
                ", date=" + date +
                ", openPrice=" + openPrice +
                ", maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                ", avgPrice=" + avgPrice +
                ", closePrice=" + closePrice +
                ", total=" + total +
                "} ";
    }
}
