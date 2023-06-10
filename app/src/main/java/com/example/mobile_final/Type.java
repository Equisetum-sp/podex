package com.example.mobile_final;

import android.graphics.Color;

import java.util.ArrayList;

public class Type {
    int id;
    String name;
    Color color;

    public Type(int id, String name, Color color){
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
