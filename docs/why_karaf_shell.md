
# Wait, there is Gogo shell, why install another one?

If you ask yourself this question, here are some _(perhaps good)_ reasons: 


## Login with username and password via SSH

From any Linux/Mac machine it's as simple as:

```ShellSession
ssh liferay@my-portal.com -p 11211
```

## Install bundles via multiple protocols

As with Gogo you can install bundle from `file:` and from `http:` URIs. With Karaf's shell you have 2 more options 
 
  - `mvn:` - install bundle from Maven (can be local, central, nexus, ...). 
  - `wrap:<other_protocol>:` - downlads a JAR file using specified protocol and than "wraps it" into OSGi bundle

## Interact with bundle by name

While Gogo allows you start and stop bundles by ID, Karaf's shell allows you to use their name or name/version to do so. For example:

```ShellSession
liferay@root>bundle:start "your bundle name"
```

This is available in almost all [bundle subshell commands](docs/commands.md#bundle-subshell) that normally expect bundle ID.
This comes very handy in functions or when you want to save aside some shell scripts to be executed long after bundle ID has changed.  

## See the bundle wiring tree

Here is an example worth a thousand words:

```ShellSession
liferay@root>bundle:tree-show "Liferay Users Admin Implementation"
Bundle com.liferay.users.admin.impl [340] is currently ACTIVE

com.liferay.users.admin.impl [340]
+- com.liferay.exportimport.service [381]
|  +- com.liferay.portal.background.task.api [241]
|  |  +- com.liferay.osgi.util [2]
|  +- com.liferay.portal.upgrade [328]
|  |  +- com.liferay.osgi.util [2]
|  |  +- com.liferay.portal.output.stream.container [270]
|  |  +- com.liferay.portal.configuration.metatype [4]
|  |     +- org.osgi.service.metatype [40]
|  +- org.apache.felix.scr [35]
|  |  +- org.osgi.service.metatype [40]
|  |  +- org.apache.felix.configadmin [27]
|  |  +- org.apache.felix.gogo.runtime [33]
|  |     +- org.apache.felix.eventadmin [30]
|  |        +- org.osgi.service.metatype [40]
|  |        +- org.apache.felix.configadmin [27]
|  +- com.liferay.portal.background.task.service [242]
|  |  +- com.liferay.portal.background.task.api [241]
|  |  +- com.liferay.portal.spring.extender [317]
|  |  |  +- com.liferay.portal.upgrade [328]
|  |  |  +- org.apache.felix.dependencymanager [28]
|  |  |     +- org.osgi.service.metatype [40]
|  |  |     +- org.apache.felix.configadmin [27]
|  |  +- com.liferay.portal.upgrade [328]
|  +- com.liferay.exportimport.api [379]
|  |  +- com.liferay.osgi.util [2]
|  +- com.liferay.xstream.configurator.api [343]
|  |  +- com.liferay.osgi.util [2]
|  +- com.liferay.petra.xml [3]
+- com.liferay.xstream.configurator.api [343]
```

## See what's inside given bundle

```ShellSession
liferay@root>bundle:classes -a "Liferay Site API"
com/liferay/application/list/BaseJSPPanelApp.class
com/liferay/application/list/BaseJSPPanelCategory.class
com/liferay/application/list/BasePanelApp.class
com/liferay/application/list/BasePanelCategory.class
com/liferay/application/list/GroupProvider.class
com/liferay/application/list/PanelApp.class
com/liferay/application/list/PanelAppRegistry$1.class
com/liferay/application/list/PanelAppRegistry$PanelAppOrderComparator.class
com/liferay/application/list/PanelAppRegistry$PanelAppsServiceTrackerMapListener.class
com/liferay/application/list/PanelAppRegistry.class
com/liferay/application/list/PanelCategory.class
com/liferay/application/list/PanelCategoryRegistry$1.class
com/liferay/application/list/PanelCategoryRegistry$2.class
com/liferay/application/list/PanelCategoryRegistry.class
com/liferay/application/list/PanelEntry.class
com/liferay/application/list/RootPanelCategory.class
com/liferay/application/list/adapter/
com/liferay/application/list/constants/
com/liferay/application/list/deploy/
com/liferay/application/list/display/
com/liferay/application/list/packageinfo
com/liferay/application/list/util/
com/liferay/application/list/display/context/logic/PanelCategoryHelper.class
com/liferay/application/list/display/context/logic/packageinfo
META-INF/
META-INF/MANIFEST.MF
META-INF/liferay-releng.changelog
OSGI-INF/
OSGI-INF/com.liferay.site.internal.application.list.LatentGroupProvider.xml
OSGI-INF/com.liferay.site.util.GroupSearchProvider.xml
OSGI-INF/com.liferay.site.util.GroupURLProvider.xml
OSGI-INF/com.liferay.site.util.RecentGroupManager.xml
com/
com/liferay/
com/liferay/site/
com/liferay/site/constants/
com/liferay/site/constants/SiteWebKeys.class
com/liferay/site/constants/packageinfo
com/liferay/site/internal/
com/liferay/site/internal/application/
com/liferay/site/internal/application/list/
com/liferay/site/internal/application/list/LatentGroupProvider.class
com/liferay/site/util/
com/liferay/site/util/GroupSearchProvider.class
com/liferay/site/util/GroupURLProvider.class
com/liferay/site/util/RecentGroupManager.class
com/liferay/site/util/packageinfo
```

## Watch SNPASHOT bundles

If you are Maven fan you'll love this. Install the SNAPSHOT bundle you are working on from maven and make Liefray watch it and update it every time you install it in your local Maven repo. Here is an example 

```ShellSession
liferay@root>bundle:install -s mvn:my.company.domain/myproject/1.0.1-SNAPSHOT
liferay@root>bundle:watch mvn:my.company.domain/myproject/1.0.1-SNAPSHOT
```

## Services configuration from shell

If you have local Liferay instance you can change configurations in `.cfg` files. With remote Liferay instance you only have access to what is available in Liferay's Control Panel. With Karaf's shell you can change/add any configuration (local and remote). Here is how to change SSH shell timeout value:

```ShellSession
liferay@root>config:list "(service.pid=org.apache.karaf.shell)"
----------------------------------------------------------------
Pid:            org.apache.karaf.shell
BundleLocation: file:/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/osgi/modules/org.apache.karaf.shell.ssh-4.0.7.jar
Properties:
   completionMode = FIRST
   felix.fileinstall.filename = file:/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/osgi/configs/org.apache.karaf.shell.cfg
   hostKey = /tmp/host.key
   hostKeyFormat = simple
   service.pid = org.apache.karaf.shell
   sshHost = 0.0.0.0
   sshIdleTimeout = 1800000
   sshPort = 11211
   sshRealm = karaf
   
liferay@root>config:edit org.apache.karaf.shell
liferay@root>config:property-set sshIdleTimeout 3600000
liferay@root>config:update
Connection to localhost closed by remote host.
```   

That's right! Connection was closed because configuration was saved which means the bundle providing Karaf's shell got restarted.
Let's reconnect and check the value

```ShellSession
liferay@root>config:list "(service.pid=org.apache.karaf.shell)"
----------------------------------------------------------------
Pid:            org.apache.karaf.shell
BundleLocation: file:/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/osgi/modules/org.apache.karaf.shell.ssh-4.0.7.jar
Properties:
   completionMode = FIRST
   felix.fileinstall.filename = file:/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/osgi/configs/org.apache.karaf.shell.cfg
   hostKey = /tmp/host.key
   hostKeyFormat = simple
   service.pid = org.apache.karaf.shell
   sshHost = 0.0.0.0
   sshIdleTimeout = 3600000
   sshPort = 11211
   sshRealm = karaf
``` 

## Shell history

Much like `bash`, Karaf's shell keeps track of what commands have been executed. This history is persistent and still available after recconect :

```ShellSession
liferay@root>history
    1  config:list
    2  config:list | grep timeout
    3  config:list | grep shell
    4  config:list "(service.pid=org.apache.karaf.shell)"
    5  config:edit org.apache.karaf.shell
    6  config:property-list
    7  config:property-set sshIdleTimeout 3600000
    8  config:update
    9  config:list "(service.pid=org.apache.karaf.shell)"
```

Obviously `Ctrl+R` works as expected:

```ShellSession
(reverse-i-search)`conf': config:list "(service.pid=org.apache.karaf.shell)"
```

## Edit a file from shell

This

```ShellSession
liferay@root> system:property liferay.home
/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3
liferay@root>edit /data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/portal-setup-wizard.properties

```

will open a `nano` like editor inside your shell:

```ShellSession
Karaf:/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3/portal-setup-wizard.properties                     L:1 C:1
admin.email.from.address=test@liferay.com
admin.email.from.name=Test Test
liferay.home=/data/projects/COMMSEN/Liferay/LiferayKaraf/test/liferay-ce-portal-7.0-ga3
setup.wizard.enabled=false


         ^X Quit    ^S Save    ^Z Undo    ^R Redo    ^G Go To    ^F Find    ^N Next    ^P Previous
```

Needless to say you can edit **ANY** file that the user you are running Liferay with has permissions to modify! So be careful with that!



## See packages exported by more than one bundle

If you have ever experiences "split package" errors in OSGi you'd appreciate this

```ShellSession
liferay@root>package:exports -d
Package Name                    | Version | Exporting bundles (ID)
------------------------------------------------------------------
org.apache.commons.logging.impl | 1.2.0   | 490 0
org.apache.commons.logging      | 1.2.0   | 490 0
org.osgi.service.log            | 1.3.0   | 490 0 32
org.osgi.service.repository     | 1.0.0   | 488 26
```

## Install a feature 

See [features page](features.md) for more information what features are. For now to keep it simple _(a.k.a. magical)_ let's just say all you need to do to install Felix webconsole is 

```ShellSession
liferay@root>feature:repo-add devops
Adding feature url https://raw.githubusercontent.com/azzazzel/liferay-karaf/master/features/devops-features/features-0.0.2.xml
liferay@root>feature:install webconsole
liferay@root>
``` 

## Schedule tasks

First make sure scheduler is installed :

```ShellSession
liferay@root>feature:install scheduler
```

then you can do crazy things like

```ShellSession
liferay@root>schedule --name reminder --at 2016-12-08T18:50:00 { echo Quit playing! Go get some work done! }
liferay@root>scheduler:list
Name     | Schedule
-----------------------------------
reminder | at(2016-12-08T18:50:00Z)
liferay@root>schedule --period 10 --times 5 { echo printing 5 times every 10 seconds }
liferay@root>printing 5 times every 10 seconds
printing 5 times every 10 seconds
liferay@root>scheduler:list
Name                                                                                      | Schedule
-------------------------------------------------------------------------------------------------------------------------------
reminder                                                                                  | at(2016-12-08T18:50:00Z)
org.apache.karaf.scheduler.command.support.ScriptJob:c1933430-6d93-4285-9c22-67b430d35ad6 | at(2016-12-08T17:50:52.986Z, 5, 10)
```
printing 5 times every 10 seconds
printing 5 times every 10 seconds
printing 5 times every 10 seconds
liferay@root>scheduler:list
Name     | Schedule
-----------------------------------
reminder | at(2016-12-08T18:50:00Z)
liferay@root>unschedule reminder
liferay@root>scheduler:list
Name | Schedule
---------------
```
  

