# Available shell commands

## bundle subshell

    bundle                Enter the subshell
    bundle:capabilities   Displays OSGi capabilities of a given bundles.
    bundle:classes        Displays a list of classes/resources contained in the bundle
    bundle:diag           Displays diagnostic information why a bundle is not Active
    bundle:dynamic-import Enables/disables dynamic-import for a given bundle.
    bundle:find-class     Locates a specified class in any deployed bundle
    bundle:headers        Displays OSGi headers of a given bundles.
    bundle:id             Gets the bundle ID.
    bundle:info           Displays detailed information of a given bundles.
    bundle:install        Installs one or more bundles.
    bundle:list           Lists all installed bundles.
    bundle:load-test      Load test bundle lifecycle
    bundle:refresh        Refresh bundles.
    bundle:requirements   Displays OSGi requirements of a given bundles.
    bundle:resolve        Resolve bundles.
    bundle:restart        Restarts bundles.
    bundle:services       Lists OSGi services per Bundle
    bundle:start          Starts bundles.
    bundle:start-level    Gets or sets the start level of a bundle.
    bundle:status         Get the bundle current status
    bundle:stop           Stop bundles.
    bundle:tree-show      Shows the tree of bundles based on the wiring information.
    bundle:uninstall      Uninstall bundles.
    bundle:update         Update bundle.
    bundle:watch          Watches and updates bundles

## config subshell

    config                  Enter the subshell
    config:cancel          Cancels the changes to the configuration being edited.
    config:delete          Delete a configuration.
    config:edit            Creates or edits a configuration.
    config:list            Lists existing configurations.
    config:meta            Lists meta type information.
    config:property-append Appends the given value to an existing property or creates the property with the specified name and value.
    config:property-delete Deletes a property from the configuration being edited.
    config:property-get    Gets the value of a property in the currently edited configuration.
    config:property-list   Lists properties from the currently edited configuration.
    config:property-set    Sets a property in the currently edited configuration.
    config:update          Saves and propagates changes from the configuration being edited.

## feature subshell

	feature                    Enter the subshell
	feature:export-bundles     Export all of the bundles that make up a specified feature to a directory on the file system.
	feature:info               Shows information about selected feature.
	feature:install            Installs a feature with the specified name and version.
	feature:list               Lists all existing features available from the defined repositories.
	feature:regions            Prints information about region digraph.
	feature:repo-add           Add a features repository
	feature:repo-list          Displays a list of all defined repositories.
	feature:repo-refresh       Refresh a features repository
	feature:repo-remove        Removes the specified repository features service.
	feature:requirement-add    Add provisioning requirements.
	feature:requirement-list   List provisioning requirements.
	feature:requirement-remove Remove provisioning requirements.
	feature:start              Start features with the specified name and version.
	feature:stop               Start features with the specified name and version.
	feature:uninstall          Uninstalls a feature with the specified name and version.
	feature:version-list       Lists all versions of a feature available from the currently available repositories.

## log subshell

	log                        Enter the subshell
	log:clear                  Clear log entries.
	log:display                Displays log entries.
	log:exception-display      Displays the last occurred exception from the log.
	log:get                    Shows the currently set log level.
	log:log                    Log a message.
	log:set                    Sets the log level.
	log:tail                   Continuously display log entries. Use ctrl-c to quit this command

## package subshell

	package                    Enter the subshell
	package:exports            Lists exported packages and the bundles that export them
	package:imports            Lists imported packages and the bundles that import them

## service subshell

	service                    Enter the subshell
	service:list               Lists OSGi services.
	service:wait               Wait for a given OSGi service.

## shell subshell

	shell                      Enter the subshell
	shell:addCommand           Add a command
	shell:alias                Create an alias to a command
	shell:cat                  Displays the content of a file or URL.
	shell:clear                Clears the console buffer.
	shell:completion           Display or change the completion mode on the current console session.
	shell:date                 Display the current time in the given FORMAT
	shell:each                 Execute a closure on a list of arguments.
	shell:echo                 Echoes or prints arguments to STDOUT.
	shell:edit                 Calls a text editor.
	shell:env                  Get/set the value of a console session variable.
	shell:eval                 Evaluate
	shell:exec                 Executes system processes.
	shell:grep                 Prints lines matching the given pattern.
	shell:head                 Displays the first lines of a file.
	shell:history              Prints command history.
	shell:if                   If/Then/Else block.
	shell:info                 Prints system information.
	shell:java                 Executes a Java standard application.
	shell:less                 File pager.
	shell:logout               Disconnects shell from current session.
	shell:more                 File pager.
	shell:new                  Creates a new java object.
	shell:printf               Formats and prints arguments.
	shell:removeCommand        Remove a command
	shell:sleep                Sleeps for a bit then wakes up.
	shell:sort                 Writes sorted concatenation of all files to standard output.
	shell:source               Run a script
	shell:stack-traces-print   Prints the full stack trace in the console when the execution of a command throws an exception.
	shell:tac                  Captures the STDIN and returns it as a string. Optionally writes the content to a file.
	shell:tail                 Displays the last lines of a file.
	shell:threads              Prints the current threads (optionally with stacktraces)
	shell:watch                Watches & refreshes the output of a command
	shell:wc                   Print newline, word, and byte counts for each file.
	shell:while                Loop while the condition is true.

## ssh subshell

	ssh                        Enter the subshell
	ssh:ssh                    Connects to a remote SSH server
	ssh:sshd                   Creates a SSH server

## system subshell

	system                     Enter the subshell
	system:framework           OSGi Framework options.
	system:name                Show or change Karaf instance name.
	system:property            Get or set a system property.
	system:shutdown            Shutdown the Karaf container.
	system:start-level         Gets or sets the system start level.
	system:version             Display the instance version
		