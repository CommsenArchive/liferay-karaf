package com.commsen.liferay.karaf.demo.command;

import java.util.List;
import java.util.SortedSet;

import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.console.CommandLine;
import org.apache.karaf.shell.api.console.Completer;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.completers.StringsCompleter;

@Service
public class DemoCommandCompleter implements Completer {

	@Override
	public int complete(Session session, CommandLine commandLine, List<String> candidates) {
		StringsCompleter delegate = new StringsCompleter();
		SortedSet<String> strings =  delegate.getStrings();
		strings.add("Milen");
		strings.add("Test");
		return delegate.complete(session, commandLine, candidates);
	}

}
