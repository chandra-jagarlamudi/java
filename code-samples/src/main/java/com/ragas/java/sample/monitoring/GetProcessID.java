package com.ragas.java.sample.monitoring;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * The code below show you how to get the process id of a Java application. We
 * can use the ManagementFactory.getRuntimeMXBean().getName() to get the process
 * id. In Windows the method return a string in the form of
 * [PID]@[MACHINE_NAME].
 * 
 * @author cjagarlamudi
 *
 */
public class GetProcessID {
	public static void main(String[] args) {
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();

		//
		// Get name representing the running Java virtual machine.
		// It returns something like 6460@AURORA. Where the value
		// before the @ symbol is the PID.
		//
		String jvmName = bean.getName();
		System.out.println("Name = " + jvmName);

		//
		// Extract the PID by splitting the string returned by the
		// bean.getName() method.
		//
		long pid = Long.valueOf(jvmName.split("@")[0]);
		System.out.println("PID  = " + pid);
	}
}
