import java.util.function.BinaryOperator;

public class ParallelCalculator implements Runnable {
    public int result;
    public ParallelCalculator(BinaryOperator<Integer> oper,int operand1,int operand2) {
        this.result= oper.apply(operand1,operand2);
    }

    @Override
    public void run() {
        Thread t = new Thread();
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}