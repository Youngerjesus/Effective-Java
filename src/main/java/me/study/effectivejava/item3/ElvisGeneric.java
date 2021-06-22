package me.study.effectivejava.item3;

public class ElvisGeneric <T extends Object> {
    private static final ElvisGeneric INSTANCE = new ElvisGeneric();

    public static <T> ElvisGeneric<T> getInstance(){return (ElvisGeneric<T>) INSTANCE;}

    private ElvisGeneric(){}

    public void leaveTheBuilding() {}
}
