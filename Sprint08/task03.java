import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

class Interactor {

    int x;

    public synchronized void serve(UnaryOperator<Integer> uo, int initializer)throws InterruptedException{

        this.x = uo.apply(initializer);
        System.out.println("Serving thread running");
        System.out.println("Serving thread initializes the key");
        System.out.println("key = "+x);
        try{
            wait();
        }catch (InterruptedException e){

        }
        System.out.println("Serving thread resumed");
        

    }

    public synchronized void consume(BinaryOperator<Integer> bo, int operand2)throws InterruptedException{
        try{
            wait(3000);
        }catch (InterruptedException e ){

        }
        System.out.println("Consuming thread received the key. key = "+x);
        x=bo.apply(x,operand2);
        System.out.println("Consuming thread changed the key. key = "+x);
        notify();

    }
}
