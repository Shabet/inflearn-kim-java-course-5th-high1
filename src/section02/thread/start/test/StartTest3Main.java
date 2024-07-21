package section02.thread.start.test;

import static util.MyLogger.log;

public class StartTest3Main {

    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    log("value: " + (i + 1));
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        new Thread(runnable, "counter").start();
    }

}
