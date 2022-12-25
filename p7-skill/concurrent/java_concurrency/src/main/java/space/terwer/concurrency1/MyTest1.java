package space.terwer.concurrency1;

/**
 * @name: MyTest1
 * @author: terwer
 * @date: 2022-11-16 23:38
 **/
public class MyTest1 {
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        synchronized (object){
            object.wait();
        }
    }
}
