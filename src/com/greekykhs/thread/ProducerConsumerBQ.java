package com.greekykhs.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBQ {
	public static void main(String[] args){
		BlockingQueue<Item> queue=
				new ArrayBlockingQueue<>(10);		
		//producer
		final Runnable producer=()->{
			while(true)
				try {
					//thread will be blocked when 
					//queue is full
					queue.put(createItem());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		};
		//creating two producers
		new Thread(producer).start();
		new Thread(producer).start();
		
		//consumer
		final Runnable consumer=()->{
			while(true)
				try {
					//thread will be blocked when 
					//queue is empty
					queue.take();
					//do something
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		};
		// creating two consumers
		new Thread(consumer).start();
		new Thread(consumer).start();
	}
	public static Item createItem() {
		return new Item();
	}
}
class Item{}
