package Thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 可循环使用的屏障，让一组线程到达一个同步点时被阻塞，直到最后一个线程到达同步点时，屏障解除，该线程组的线程均继续执行
 * @author xutao
 *
 */
public class CyclicBarrierDemo {
	public static void main(String[] args) {
		Runnable barrier1Action = new Runnable() {
		    public void run() {
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("BarrierAction 1 executed ");
		    }
		};
		Runnable barrier2Action = new Runnable() {
		    public void run() {
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		        System.out.println("BarrierAction 2 executed ");
		    }
		};

		// CyclicBarrier构造函数的参数1表示设置两个屏障，后面的Runnable表示当屏障解除后执行的操作
		CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
		CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

		CyclicBarrierRunnable barrierRunnable1 =
		        new CyclicBarrierRunnable(barrier1, barrier2);

		CyclicBarrierRunnable barrierRunnable2 =
		        new CyclicBarrierRunnable(barrier1, barrier2);

		//两个线程会先阻塞在屏障1，等到await足够2以后，屏障1被解除，执行BarrierAction 1
		new Thread(barrierRunnable1).start(); 
		new Thread(barrierRunnable2).start();
		
		/**
		 *  Thread-1 waiting at barrier 1
		 *	Thread-0 waiting at barrier 1
		 *	BarrierAction 1 executed 
		 *	Thread-0 waiting at barrier 2
		 *	Thread-1 waiting at barrier 2
		 *	BarrierAction 2 executed 
		 * 	Thread-0 done!
		 *  Thread-1 done!
		 */
	}

}

class CyclicBarrierRunnable implements Runnable{

    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;

    public CyclicBarrierRunnable(
            CyclicBarrier barrier1,
            CyclicBarrier barrier2) {

        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at barrier 1");
            this.barrier1.await();

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +
                                " waiting at barrier 2");
            this.barrier2.await();

            System.out.println(Thread.currentThread().getName() +
                                " done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
