package com.appslabtest.ormtools.utilities;

import java.security.SecureRandom;

public class IDGen {
	
	public String getGeneratedID(String dept) {
		String theID = "";
		String department = dept.trim();
		String numPart = "";
		String[] words = department.split("\\s+");
		int wordCount = words.length;
		if(wordCount >= 2) {
			String firstPart = words[0].substring(0, 2);
			String secondPart = words[1].substring(0, 2);
			numPart += randomInt(1000000);
			theID = firstPart + secondPart + numPart;
		}else {
			String part = words[0].substring(0, 3);
			numPart += randomInt(10000000);
			theID = part + numPart;
		}
		return theID;
	}
	
	private static int randomInt(int seed) {
        return new SecureRandom().nextInt(seed);
    }

}
