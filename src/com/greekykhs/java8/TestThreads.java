package com.greekykhs.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//class MyThread extends Thread {
//    MyThread() {
//        System.out.print(" MyThread");
//    }
//
//    public void run() {
//        System.out.print(" bar");
//    }
//
//    public void run(String s) {
//        System.out.print(" baz");
//    }
//}

public class TestThreads {
    public static void main(String[] args) {
    	List<Integer> levels=new ArrayList();
    	Integer[] ar = (Integer[]) levels.toArray();
    	
       
    }
    public static int maxTrailing(List<Integer> levels) {

		int n = levels.size();
		Integer[] ar = (Integer[]) levels.toArray();
		int max = ar[n - 1];
		int maxDiff = 0;
		for (int i = n - 2; i >= 0; i--) {

			if (ar[i] < max) {
				int currentDif = max - ar[i];
				maxDiff = currentDif > maxDiff ? currentDif : maxDiff;
			} else if (ar[i] > max) {
				max = ar[i];
			}
		}
		return maxDiff;

	}
}