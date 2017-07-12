package com.ragas.java.sample;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class KeyCount {
	public static void getKeyCounts(Map<String, Integer> lHashMap, String key, Integer value) {
		if (null != lHashMap) {
			if (lHashMap.containsKey(key)) {
				lHashMap.replace(key, lHashMap.get(key) + value);
			} else {
				lHashMap.put(key, value);
			}
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner consoleInput = new Scanner(System.in);
		String stringEntered;
		Map<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
		while ((stringEntered = consoleInput.nextLine()).length() > 0) {	
			String[] keyValuePair = stringEntered.split(",");
			if(null != keyValuePair && keyValuePair.length == 2){
				getKeyCounts(linkedHashMap, keyValuePair[0], new Integer(Integer.parseInt(keyValuePair[1])));
			}
		}
		for (String key : linkedHashMap.keySet()) {
			System.out.println(key + ":" + linkedHashMap.get(key));
		}
		System.exit(1);
	}
}
