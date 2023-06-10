package com.example.mobile_final;

import android.graphics.Color;

public class TypeList {
    static final Type[] types = {new Type(1, "Normal", Color.valueOf(0xffa8a292)),
            new Type(2, "Fighting", Color.valueOf(0xffcc4e0e)),
            new Type(3, "Flying", Color.valueOf(0xff94c9ff)),
            new Type(4, "Poison", Color.valueOf(0xff8417d1)),
            new Type(5, "Ground", Color.valueOf(0xfffadf7f)),
            new Type(6, "Rock", Color.valueOf(0xffbd9c44)),
            new Type(7, "Bug", Color.valueOf(0xffb1ee13)),
            new Type(8, "Ghost", Color.valueOf(0xff49319e)),
            new Type(9, "Fire", Color.valueOf(0xffff2f00)),
            new Type(10, "Water", Color.valueOf(0xff005eff)),
            new Type(11, "Grass", Color.valueOf(0xff0fff28)),
            new Type(12, "Electric", Color.valueOf(0xfffff700)),
            new Type(13, "Psychic", Color.valueOf(0xfff50ad5)),
            new Type(14, "Ice", Color.valueOf(0xff03f8fc)),
            new Type(15, "Dragon", Color.valueOf(0xff4f03fc))};

    static Type getType(int id){
        return (id < 15) ? types[id-1] : null;
    }

    static Type getType(String name){
        for (int i=0; i<types.length; i++){
            if (types[i].name.equalsIgnoreCase(name)){
                return types[i];
            }
        }
        return null;
    }
}
