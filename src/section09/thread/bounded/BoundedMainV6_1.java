package section09.thread.bounded;

public class BoundedMainV6_1 {

    public static void main(String[] args) {
        // 1. BoundedQueue 선택
        BoundedQueue queue = new BoundedQueueV6_1(2);

        // 2. 생산자, 소비자 실행 순서 선택, 반드시 하나만 선택!
        BoundedFirst.producerFirst(queue); // 생산자 먼저 실행
        //BoundedFirst.consumerFirst(queue); // 소비자 먼저 실행
    }

}
