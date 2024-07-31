package section04.interrupt;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task);
        thread.start();

        sleep(100); //시간을 줄임
        log("작업 중단 지시 thread.intterupt()");
        thread.interrupt();
        log("work 쓰레드 인터럽트 상태1=" + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while (!Thread.interrupted()) { //인터럽트 상태 변경O
                log("작업 중");
            }
            log("work 쓰레드 인터럽트 상태2=" + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리");
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 쓰레드 인터럽트 상태3=" + Thread.currentThread().isInterrupted());
            }
            log("자원 종료");
        }
    }
}
