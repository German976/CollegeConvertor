package com.example.convector_voitovich_303;

public class Unit {
    public String name;
    public double coeff;

    public Unit(String name, double coeff){
        this.coeff = coeff;
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
