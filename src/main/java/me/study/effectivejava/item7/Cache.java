package me.study.effectivejava.item7;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache {
    WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Cache cache = new Cache();
        Integer k1 = new Integer(5000); // Integer 는 내부적으로 캐싱을 한다.
        Integer k2 = new Integer(5001);
        Integer k3 = new Integer(5002);

        Integer v1 = new Integer(6000);
        Integer v2 = new Integer(6001);
        Integer v3 = new Integer(6002);

        cache.weakHashMap.put(k1, v1);
        cache.weakHashMap.put(k2, v2);
        cache.weakHashMap.put(k3, v3);

        k1 = null;
        k2 = null;
        k3 = null;

        System.gc();
        Thread.sleep(5000);

        System.out.println(cache.weakHashMap.isEmpty());
        for(Integer i : cache.weakHashMap.values()) {
            System.out.println(i);
        }
    }
}
