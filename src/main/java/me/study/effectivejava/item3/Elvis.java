package me.study.effectivejava.item3;

import java.io.Serializable;

public class Elvis implements Serializable{
    public static final Elvis INSTANCE = new Elvis();

    private Elvis(){}

    public void leaveTheBuilding() {}

    protected Object readResolve(){
        return INSTANCE;
    }
}
