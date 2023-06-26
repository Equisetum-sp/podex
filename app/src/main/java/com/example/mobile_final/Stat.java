package com.example.mobile_final;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Stat implements Parcelable {
    int hp, atk, def, spc, spe;

    public Stat(int hp, int atk, int def, int spc, int spe) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spc = spc;
        this.spe = spe;
    }

    protected Stat(Parcel in) {
        hp = in.readInt();
        atk = in.readInt();
        def = in.readInt();
        spc = in.readInt();
        spe = in.readInt();
    }

    public static final Creator<Stat> CREATOR = new Creator<Stat>() {
        @Override
        public Stat createFromParcel(Parcel in) {
            return new Stat(in);
        }

        @Override
        public Stat[] newArray(int size) {
            return new Stat[size];
        }
    };

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpc() {
        return spc;
    }

    public int getSpe() {
        return spe;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setSpc(int spc) {
        this.spc = spc;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(hp);
        dest.writeInt(atk);
        dest.writeInt(def);
        dest.writeInt(spc);
        dest.writeInt(spe);
    }
}
