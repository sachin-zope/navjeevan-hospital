package com.test.nav.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(2015021201);
		ids.add(2015021202);
		ids.add(2015012401);
		System.out.println("Initial List");
		for (Integer id : ids) {
			System.out.println(id);
		}
		
		Collections.sort(ids);
		System.out.println("Sorted List");
		for (Integer id : ids) {
			System.out.println(id);
		}
		
		System.out.println(ids.get(ids.size()-1));
	}

}
