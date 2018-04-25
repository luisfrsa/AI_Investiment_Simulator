/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiinvestimentsimulator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author luisr
 */
public class Company {

    private String name;
    private Set<DadosDoDia> dadosDoDiaSet = new HashSet<>();

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
                ", dadosDoDiaSet=" + dadosDoDiaSet.stream().map(d->d.toString()).reduce((e1,e2)->{return e2+", "+e1;}) +
                "}\n";
    }
}
