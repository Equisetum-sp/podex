package com.example.mobile_final;

import android.graphics.Color;

public class TypeList {
    static final Type[] types = {new Type(1, "Normal", 0xffa8a292),
            new Type(2, "Fighting",0xffcc4e0e),
            new Type(3, "Flying", 0xff94c9ff),
            new Type(4, "Poison", 0xff8417d1),
            new Type(5, "Ground", 0xfffadf7f),
            new Type(6, "Rock", 0xffbd9c44),
            new Type(7, "Bug", 0xffb1ee13),
            new Type(8, "Ghost", 0xff49319e),
            new Type(9, "Fire", 0xffff2f00),
            new Type(10, "Water", 0xff005eff),
            new Type(11, "Grass", 0xff0fff28),
            new Type(12, "Electric", 0xfffff700),
            new Type(13, "Psychic", 0xfff50ad5),
            new Type(14, "Ice", 0xff03f8fc),
            new Type(15, "Dragon", 0xff4f03fc)};

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
