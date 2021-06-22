package me.study.effectivejava.item3;

public class Elvis2 {
    private static final Elvis2 INSTANCE = new Elvis2();

    public static Elvis2 getInstance(){return INSTANCE;}

    private Elvis2(){}

    public void leaveTheBuilding() {}
}
