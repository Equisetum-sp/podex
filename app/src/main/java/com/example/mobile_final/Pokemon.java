package com.example.mobile_final;

public class Pokemon {
    int id;
    double height, weight;
    String name;
    Stat stat;
    Type[] types = new Type[2];

    public Pokemon(int id, String name, double height, double weight, Stat stat, Type type1, Type type2){
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.stat = stat;
        this.types[0] = type1;
        this.types[1] = type2;
    }

    public int getId() {
        return id;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public Stat getStat() {
        return stat;
    }

    public Type[] getTypes() {
        return types;
    }

    public void getSprite(){

    }

    public void playSound(){

    }
}
