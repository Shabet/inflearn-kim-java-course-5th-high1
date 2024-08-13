package section06.thread.sync;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV2 implements BankAccount {
    private int balanace;
//    volatile private int balanace;

    public BankAccountV2(int initialBalance) {
        this.balanace = initialBalance;
    }

    @Override
    public synchronized boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

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

        log("거래 종료");
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return balanace;
    }
}
