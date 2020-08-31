package victor.training.performance;

public class APlusPlus {
    private static Integer population = 0;

    public synchronized static void m() {
        population++;
    }

    public static class ThreadA extends Thread {

        public  void run() {
            for (int i = 0; i < 10_000; i++) {
//                synchronized (population) {
//                    population++;
//                }
                m();
            }
        }
    }

    public static class ThreadB extends Thread {
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                m();
//                synchronized (population) {
//                    population++;
//                }
            }
        }
    }

    // TODO (bonus): ConcurrencyUtil.useCPU(1)


    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        long t0 = System.currentTimeMillis();

        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        long t1 = System.currentTimeMillis();
        System.out.println("Total = " + population);
        System.out.println("Took = " + (t1 - t0));
    }
}
