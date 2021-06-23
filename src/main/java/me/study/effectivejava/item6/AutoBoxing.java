package me.study.effectivejava.item6;

public class AutoBoxing {

    // 잘못된 예
    public long badSum(){
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE ; i++) {
            sum += i;
        }
        return sum;
    }

    // 올바른 예
    public long goodSum(){
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE ; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        AutoBoxing autoBoxing = new AutoBoxing();
        long sum = autoBoxing.goodSum();
        System.out.println(sum);
    }
}
