package section08.thread.bounded;

public interface BoundedQueue {
    void put(String data);

    String take();
}
