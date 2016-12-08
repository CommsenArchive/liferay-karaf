# Custom commands

Custom Karaf commands are similar to Gogo commands but provide better support for 

 - help / documentation
 - autocompleters for arguments and options
 - posix style options
 - required / optional / multivalue arguments
   
The above is better illustrated by example. This is a command:
 
```java
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
	 ...
	}
}
``` 
It uses autocompletion for it's argument which looks like this:

```java
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
```

When installed, this command 

 - displays nice help screen
 
 ```ShellSession
 liferay@root>demo:hello --help
DESCRIPTION
        demo:hello

	Simple demo command

SYNTAX
        demo:hello [options] [people]

ARGUMENTS
        people
                Names of the people you want to say hello to!

OPTIONS
        -w, --world
                Say hello to the world
        --help
                Display this help message
 ```
 
 - updates subshell's help screen
 
 ```ShellSession
 liferay@root>demo --help
SUBSHELL
	demo

COMMANDS
    demo:hello Simple demo command
```

 - suggests completion for its argument
  
 ```shell
liferay@root>demo:hello <TAB>
Milen   Test
``` 

### Limitations

 - Karaf shell commands are not compatible with Gogo and thus not available in Gogo shell. 
 - Karaf shell commands do not use declarative services. They use similar but Karaf specific annotations. 
