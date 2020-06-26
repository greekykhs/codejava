package com.greekykhs.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {

	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);		
		Iterator<Integer> lIterator=list.iterator();
		while(lIterator.hasNext()) {
			Integer n=lIterator.next();
			System.out.println(n);
			if(n==3) {
				//lIterator.remove();//it will work	
				//list.add(4);//ConcurrentModificationException
				//list.remove(2);//ConcurrentModificationException
			}
		}
		System.out.println(list);
		
		List<Integer> clist=new CopyOnWriteArrayList<>();
		clist.add(1);
		clist.add(2);
		clist.add(3);		
		Iterator<Integer> cIterator=clist.iterator();
		while(cIterator.hasNext()) {
			Integer n=cIterator.next();
			System.out.println(n);
			if(n==3) {
				//cIterator.remove();//UnsupportedOperationException	
				//clist.add(4);//it will work
				clist.remove(2);//it will work
			}
		}
		System.out.println(clist);
	}

}
