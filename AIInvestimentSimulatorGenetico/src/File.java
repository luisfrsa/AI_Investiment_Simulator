/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package aiinvestimentsimulator;

import java.time.Year;

/**
 *
 * @author luisr
 */
public class File {
    private Long id;
    private Year year;
    private String name;

    public File(Long id, Year year, String name) {
        this.id = id;
        this.year = year;
        this.name = name;
    }
    public File( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id + ", year=" + year + ", name=" + name + '}';
    }
    
}
