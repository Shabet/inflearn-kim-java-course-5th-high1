package section02.thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {

    public static void main(String[] args) throws InterruptedException {

        CurrentThread thread = new CurrentThread();
        thread.start();
    }

    static class CurrentThread extends Thread {

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
