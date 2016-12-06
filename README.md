# Liferay Karaf extension

The goal of this project it to add of some of the cool [Karaf](http://karaf.apache.org/)'s functionalities into Liferay!

## What does it add

 - SSH shell
 - Shell commands in the following sub-shells:
   - bundle: - allows to inspect and manage OSGi bundles
   - config: - allows to inspect and modify configurations
   - jaas: - allows to inspect and modify JAAS realms
   - package: - shows imported / exported packages
   - shell: - advanced shell commands
   - system: - allows to inspect and modify system properties
 - Init shell script executed at startup in each shell session
 - Command / parameter autocompletion
 - Command details with `<command> --help` and `man <command>`
 - Ability to deploy more Karaf commands  

## Installation

---

__WARNINIG: SYSTEM WILL SHUTDOWN IF ONE OF THE INSTALLED BUNDLES IS REMOVED!__

One of the bundles delivered with this project (namely `com.commsen.liferay.karaf.extensionbundle`) is __OSGi framework extension__ bundle!
As per OSGi specification the framework MAY need a restart when such bundles is installed (Liferay typically does not) and MUST shutdown if such bundle is removed!

---

### Get the extension

There are 2 options:

 - Build it form source. Simply clone this repo and run `mvn package`. Then look for `assembly/target/liferay-karaf-<VERSION>.zip`
 - Download it form [releases page](https://github.com/azzazzel/liferay-karaf/releases/)

The zip file contains bundles in `osgi/modules` folder and configuration files in `osgi/configs`.

### Installation

Simply extract the file in your `<LIFERAY_7_HOME>/` folder! That's it!

## Connect to console

Simply SSH to your server. Default port is `11211`, default user is `liferay` with default password of `test`!

## Using the console

Please see [Karaf's documentation](http://karaf.apache.org/manual/latest)!

## Configuration

### Configure SSH host and port

Karaf shell is configurable in `osgi/configs/org.apache.karaf.shell.cfg`:
```properties
sshPort = 11211
sshHost = 0.0.0.0
```

### Configure SSH user and password

Karaf shell users, groups and passwords are configurable in `osgi/configs/users.properties`:
```properties
#
# This file contains the users, groups, and roles.
# Each line has to be of the format:
#
# USER=PASSWORD,ROLE1,ROLE2,...
# USER=PASSWORD,_g_:GROUP,...
# _g_\:GROUP=ROLE1,ROLE2,...
#
# All users, groups, and roles entered in this file are available after Karaf startup
# and modifiable via the JAAS command group. These users reside in a JAAS domain
# with the name "karaf".
#
liferay = test,_g_:admingroup
_g_\:admingroup = group,admin,manager,viewer,systembundles

```

### Configure shell aliases

The file `osgi/configs/shell.init.script` is run every time a connection to the console is made. This allows to set some aliases:
```properties
lb = { bundle:list -t 0 $args } ;
ls = { service:list $args } ;
lc = { config:list "(service.pid=$args)" } ;
help = { *:help $args | more } ;
man = { help $args } ;
service:get = { $.context getService ($.context getServiceReference $args) };
```
or execute custom initialization code.
