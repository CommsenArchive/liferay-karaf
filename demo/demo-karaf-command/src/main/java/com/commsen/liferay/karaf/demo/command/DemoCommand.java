package com.commsen.liferay.karaf.demo.command;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;

@Command(scope = "demo", name = "hello", description = "Simple demo command ")
@Service
public class DemoCommand implements Action {

	@Argument(index = 0, name = "people", description = "Names of the people you want to say hello to!", required = false, multiValued = true)
	@Completion(DemoCommandCompleter.class)
	List<String> people;

	@Option(name = "-w", description = "Say hello to the world", aliases = {"--world" }, required = false, multiValued = false)
	boolean all;

	@Override
	public Object execute() throws Exception {

		if (all) {
			System.out.println("Hello World!");
		} else {
			if (people == null || people.isEmpty()) {
				throw new Exception("Please add some names to say hello to!");
			} else {
				System.out.println("Hello " + people.stream().collect(Collectors.joining(", ")) + "!");
			}
		}

		return null;
	}

}
