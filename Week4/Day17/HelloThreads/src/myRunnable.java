public class myRunnable implements Runnable {
    @Override
    public void run() {
//        int count = (int) (Thread.currentThread().getId() * 100);
        int count = 0;
        while (count <= 100) {
            System.out.println("Hello from this threads: " + Thread.currentThread().getId() + " : " + count++);
        }
    }
}