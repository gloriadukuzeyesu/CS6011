public class Main {

    public static int answer = 0;

    public static void sayHello() throws InterruptedException {
        int threadSize = 10;

        Thread[] myThreads = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            //create a myRunnable object
            myRunnable runnable = new myRunnable();
            // create a thread
            Thread myThread = new Thread(runnable);
            myThread.start();
            myThreads[i] = myThread;
        }
        //Join all the threads in your array. Waits until each thread is done doing its thing.
        for (int j = 0; j < myThreads.length; j++) {
            myThreads[j].join();
        }
    }

    public static void badSum() throws InterruptedException {

        int maxValue = 40000;
//      int maxValue = 100;

        Thread[] myThreads = new Thread[10];
        int numThreads = myThreads.length;
        for (int i = 0; i < numThreads; i++) {
            // final nt finalI = i; is a representation of i .
            final int finalI = i;
            Thread thread = new Thread(() -> {
                for (int j = (finalI * maxValue / numThreads); j < Math.min((finalI + 1) * maxValue / numThreads, maxValue); j++) {
                    answer += j;
                }
            });
            myThreads[i] = thread;
            myThreads[i].start();
        }

        //Join all the threads in your array. Waits until each thread is done doing its thing.
        for (int j = 0; j < numThreads; j++) {
            myThreads[j].join();
        }
        int correctAnswer = maxValue * (maxValue - 1) / 2;
        System.out.println(" Correct answer is: " + correctAnswer + " and bad answer is " + answer);
    }

    public static void main(String[] args) throws InterruptedException {
        sayHello();
        badSum();
    }
}

//TODO
/* the threads are no running in order.*/
// When switching  to using multiple threads the bad answer becomes lower than the correct answer.the thread is holding part of the total sum.
// This is because the answer variable is shared among threads and only
// one thread can access it at time.
// when the maxValue is set to 100. Sometimes the result is correct other time it is not. Each thread is holding little part of the total sum. Sometimes the thread
// don't finish their portion by the time, by the time other thread needs to use the variable. So it ends up having small result.
