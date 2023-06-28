package com.example.mobile_final;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class Pokemon implements Parcelable {
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

    protected Pokemon(Parcel in) {
        id = in.readInt();
        name = in.readString();
        height = in.readDouble();
        weight = in.readDouble();
        stat = (Stat) in.readParcelable(Stat.class.getClassLoader());
        types = in.createTypedArray(Type.CREATOR);
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(height);
        dest.writeDouble(weight);
        dest.writeParcelable(stat, flags);
        dest.writeTypedArray(types, flags);
    }
}
