package com.commsen.liferay.karaf.extensionbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class KarafShellExtensionBundleActivator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		String liferayHome = System.getProperty("liferay.home");
		
		System.setProperty("karaf.home", liferayHome);
        System.setProperty("karaf.base", liferayHome);
        System.setProperty("karaf.data", liferayHome + "/data");
        System.setProperty("karaf.etc", liferayHome + "/osgi/configs");
        System.setProperty("karaf.history", liferayHome + "/data/history.txt");
        System.setProperty("karaf.instances", liferayHome + "/data/instances");
        System.setProperty("karaf.shell.init.script", liferayHome + "/osgi/configs/shell.init.script");
        
		System.setProperty("karaf.startLocalConsole", "false");
		System.setProperty("karaf.startRemoteShell", "true");
        System.setProperty("karaf.lock", "false");		
	}

	public void stop(BundleContext context) throws Exception {
		// nothing to do
	}

}
