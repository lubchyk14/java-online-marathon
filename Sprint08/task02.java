class Accountant {
    public static int sum(int x, int y) {

        ParallelCalculator parallelCalculator = new ParallelCalculator((a,b)->x+y,x,y);
        Thread th = new Thread(parallelCalculator);
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return parallelCalculator.result;
    }
}