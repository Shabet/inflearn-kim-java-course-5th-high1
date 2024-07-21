package section02.thread.start.test;

import static util.MyLogger.log;

public class StartTest2Main {

    public static void main(String[] args) {
//        CountRunnable runnable = new CountRunnable();
//        Thread thread = new Thread(runnable);
//        thread.start();

        new Thread(new CountRunnable(), "counter").start();
    }

    static class CountRunnable implements Runnable {
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
    }
}
