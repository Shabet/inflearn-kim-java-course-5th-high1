package section03.thread.control;

import static util.ThreadUtils.sleep;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() /*throws Exception*/ /*에러 발생*/ {
//            throw new Exception(); //에러발생
            sleep(1000);
        }
    }
}
