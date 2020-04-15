package com.greekykhs.collections;

import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<String> pQueue=new PriorityQueue<>();
		
		pQueue.add("Sebastian"); 
        pQueue.add("Boguslaw"); 
        pQueue.add("Kazimierz "); 
        pQueue.add("Bozena"); 

        System.out.println("Size of pQueue:"+pQueue.size()); //4
        
        Object[] arr = pQueue.toArray(); 
        System.out.println("Coverting pQueue into Array: "); 
        for (int i = 0; i < arr.length; i++) 
            System.out.print(arr[i].toString()+", ");//Boguslaw, Bozena, Kazimierz , Sebastian,
        
        System.out.println();        
        Iterator<String> pqItr = pQueue.iterator(); 
        while (pqItr.hasNext()) 
            System.out.print(pqItr.next()+", ");//Boguslaw, Bozena, Kazimierz, Sebastian,
        
        System.out.println();
        pQueue.remove("Bozena");
        System.out.println("Printing pQueue after removing Bozena");
        
        pqItr = pQueue.iterator(); 
        while (pqItr.hasNext()) 
            System.out.print(pqItr.next()+", ");//Boguslaw, Kazimierz , Sebastian,
        
        boolean isPresent = pQueue.contains("Bozena");
        System.out.println("pQueue contains Bozena or not? "+isPresent); //false
        isPresent = pQueue.contains("Sebastian");
        System.out.println("pQueue contains Sebastian or not? "+isPresent); //true
        
        System.out.println("Head of pQueue from peek:"+pQueue.peek());//Boguslaw
        System.out.println("Head of pQueue from poll:"+pQueue.poll());//Boguslaw
        
        pqItr = pQueue.iterator(); 
        while (pqItr.hasNext()) 
            System.out.print(pqItr.next()+", ");//Kazimierz , Sebastian,
        
        System.out.println();
        
        pQueue.clear();
        System.out.println("Size of pQueue after calling clear:"+pQueue.size()); //0
	}

}
