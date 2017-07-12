package com.ragas.java.sample.security;

import java.security.Provider;
import java.security.Security;
import java.util.Set;
import java.util.HashSet;

public class SecurityProvider {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		//
		// Create a set so that we can have a unique result.
		//
		Set result = new HashSet();

		//
		// Returns an array containing all the installed providers.
		//
		Provider[] providers = Security.getProviders();

		for (Provider provider : providers) {

			//
			// Get provider's property keys
			//s
			Set keys = provider.keySet();
			for (Object key : keys) {
				String data = (String) key;
				data = data.split(" ")[0];

				//
				// Service type started by the "Alg.Alias" string
				//
				if (data.startsWith("Alg.Alias")) {
					data = data.substring(10);
				}

				data = data.substring(0, data.indexOf('.'));
				result.add(data);
			}
		}

		for (Object o : result) {
			System.out.println("Service Type = " + o);
		}
	}
}
