package lesson07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by APS2
 * on 01.03.2016
 */
public class MainSum {
    static int syncSum;
    static volatile int sum;
    static final AtomicInteger ATOMIC_SUM = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<10; i++) {
            new Thread() {
                @Override
                public void run() {
                    sum++;
                    ATOMIC_SUM.incrementAndGet();
                    synchronized (this){
                        syncSum+=1;
                    }
                }
            }.start();
        }

        Thread.sleep(500);

        System.out.println(sum);
        System.out.println(ATOMIC_SUM.get());
        System.out.println(syncSum);
    }
}
