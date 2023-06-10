package com.example.mobile_final;

public class Stat {
    int hp, atk, def, spc, spe;

    public Stat(int hp, int atk, int def, int spc, int spe) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spc = spc;
        this.spe = spe;
    }

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
}
