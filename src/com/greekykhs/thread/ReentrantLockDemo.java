package com.greekykhs.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
	public static void main(String[] args) {
		ReentrantLock reentrantLock = new ReentrantLock();
		ExecutorService pool = Executors.newFixedThreadPool(2);
		Runnable runnable1 = new Worker(reentrantLock, "Job One");
		Runnable runnable2 = new Worker(reentrantLock, "Job Two");
		pool.execute(runnable1);
		pool.execute(runnable2);
		pool.shutdown();
	}
}

class Worker implements Runnable {
	String name;
	ReentrantLock reentrantLock;

	public Worker(ReentrantLock reentrantLock, String name) {
		this.reentrantLock = reentrantLock;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Name of Thread:"+this.name);
		// Getting Outer Lock
		boolean gotLock = reentrantLock.tryLock();
		if (gotLock) {
			try {
				// do something...				
				// Getting Inner Lock
				reentrantLock.lock();
				try {					
					// do something else...
					System.out.println("Lock Hold Count:" 
					+ reentrantLock.getHoldCount());
				} finally {
					// Inner lock release
					reentrantLock.unlock();
				}
			} finally {
				// Outer lock release
				reentrantLock.unlock();
				System.out.println("Lock Hold Count After Run:" 
						+ reentrantLock.getHoldCount());
			}
		}
	}
}