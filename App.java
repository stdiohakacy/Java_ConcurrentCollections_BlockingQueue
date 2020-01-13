package _13;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Worker1 implements Runnable {
	private BlockingQueue<Integer> blockingQueue;

	public Worker1(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		int counter = 0;
		while (true) {
			try {
				
				blockingQueue.put(counter);
				System.out.println("put " + counter);
				counter++;
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Worker2 implements Runnable {
	private BlockingQueue<Integer> blockingQueue;

	public Worker2(BlockingQueue<Integer> blockingQueue) {
		super();
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		while (true) {
			int number;
			try {
				number = blockingQueue.take();
				System.out.println("Taking " + number);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class App {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		
		Worker1 w1 = new Worker1(queue);
		Worker2 w2 = new Worker2(queue);
		
		new Thread(w1).start();
		new Thread(w2).start();
		
	}
}
