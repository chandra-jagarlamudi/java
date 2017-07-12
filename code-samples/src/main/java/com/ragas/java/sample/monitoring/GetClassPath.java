package com.ragas.java.sample.monitoring;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * The RuntimeMXBean.getClassPath() returns the Java class path that is used by
 * the system class loader to search for class files. This method is equivalent
 * to System.getProperty("java.class.path").
 * 
 * Multiple paths in the Java class path are separated by the path separator
 * character of the platform of the Java virtual machine being monitored.
 * 
 * @author cjagarlamudi
 *
 */
public class GetClassPath {
	public static void main(String[] args) {
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		String classPath = bean.getClassPath();
		System.out.println("classPath = " + classPath);
	}
}
