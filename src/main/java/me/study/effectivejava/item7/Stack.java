package me.study.effectivejava.item7;

import java.util.*;

public class Stack {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_STACK_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_STACK_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if(size == elements.length) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    // memory leak method
    public Object pop_bad() {
        if(size == 0)
            throw new EmptyStackException();
        return elements[--size];
    }

    public Object pop_good(){
        if(size == 0)
            throw new EmptyStackException();

        Object element = elements[--size];
        elements[size] = null;
        return element;
    }

    public static void main(String[] args) throws InterruptedException {
        Map<Integer, Integer> map = new HashMap<>();
        Integer k1 = new Integer(5000);
        Integer k2 = new Integer(5001);
        Integer k3 = new Integer(5002);

        Integer v1 = new Integer(6000);
        Integer v2 = new Integer(6001);
        Integer v3 = new Integer(6002);

        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);

        k1 = null;
        k2 = null;
        k3 = null;
        System.gc();

        Thread.sleep(5000);
        System.out.println(map.isEmpty());
    }
}
