package section07.thread.sync;

import section06.thread.sync.BankAccount;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {
    private int balanace;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balanace = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            log("[검증 시작] 출금액: " + amount + " , 잔액: " + balanace);

            // 잔고가 출금액 보다 적으면, 진행하면 안됨
            if (balanace < amount) {
                log("[검증 실패] 출금액: " + amount + " , 잔액: " + balanace);
                return false;
            }

            // 잔고가 출금액 보다 많으면, 진행
            log("[검증 완료] 출금액: " + amount + " , 잔액: " + balanace);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balanace -= amount;

            log("[출금 완료] 출금액: " + amount + " , 잔액: " + balanace);
        }
        finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제(반드시 호출해야함)
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // ReentrantLock 이용하여 lock 걸기
        try {
            return balanace;
        }
        finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제(반드시 호출해야함)
        }
    }
}
