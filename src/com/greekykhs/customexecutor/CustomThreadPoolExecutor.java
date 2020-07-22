package com.greekykhs.customexecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPoolExecutor {
	private final BlockingQueue<Runnable> workerQueue;
	private final Thread[] workerThreads;
	private volatile boolean shutDown;

	public CustomThreadPoolExecutor(int numThreads) {
		workerQueue = new LinkedBlockingQueue<>();
		workerThreads = new Thread[numThreads];
		shutDown = false;
		int i = 0;
		for (Thread worker : workerThreads) {
			worker = new Worker("Custom Pool Thread " + ++i);
			worker.start();
		}
	}

	public void submit(Runnable r) throws InterruptedException{
		if(!shutDown) {
			try {
				workerQueue.put(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			throw new InterruptedException("Shut down in progress");
		}
	}
	
	public void shutDown() {
		shutDown=true;
    }

	class Worker extends Thread {
		public Worker(String name) {
			super(name);
		}

		public void run() {
			while (!shutDown) {
				try {
					workerQueue.take().run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

    public static void main(String[] args)  throws InterruptedException{
    	CustomThreadPoolExecutor threadPoolExecutor=
    			MyExecutors.myFixedThreadPool(7);
        threadPoolExecutor.submit(
        		() -> System.out.println("First task"));
        threadPoolExecutor.submit(
        		() -> System.out.println("Second task"));
    }

}
class MyExecutors{
	static CustomThreadPoolExecutor myFixedThreadPool(int capacity) {
		return new CustomThreadPoolExecutor(capacity);
	}
}