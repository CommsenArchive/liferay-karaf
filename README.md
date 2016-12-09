# Liferay Karaf compatibility layer (v 0.0.2)

The goal of this project it to make some of the cool [Karaf](http://karaf.apache.org/)'s functionalities available in Liferay 7!

See "[Wait, there is Gogo shell, why install another one?](docs/why_karaf_shell.md)" for some ideas what you can do with it!

## What does it add

 - [SSH into Karaf shell](docs/ssh_into_liferay.md)
 - [Karaf shell commands](docs/commands.md)
 - [Init shell script](docs/init_shell_script.md) executed at startup in each shell session
 - [Support for features](docs/features.md)
 - [Posix style custom commands](docs/custom_commands.md) with help and autocompletion _(even for arguments and options)_
 
 
## Installation



__WARNINIG: LIFERAY WILL SHUTDOWN IF ONE OF THE BUNDLES INSTALLED BY THIS EXTENSION IS LATER ON REMOVED!__

One of the bundles delivered with this project (namely `com.commsen.liferay.karaf.extensionbundle`) is __OSGi framework extension__ bundle!
As per OSGi specification the framework 
 - MAY need to restart when such bundle is installed _(Liferay uses Equinox which does not)_
 - MUST shutdown if such bundle is removed!

---

There are 2 options to get the artifact:

 - Build it form source by cloning this repo and running `mvn package`. Then look for `assembly/target/liferay-karaf-<VERSION>.zip`
 - Download it form [releases page](https://github.com/azzazzel/liferay-karaf/releases/)

The zip file contains bundles in `osgi/modules` folder and configuration files in `osgi/configs`.

Once you have `liferay-karaf-<VERSION>.zip` simply extract it in your `<LIFERAY_7_HOME>` folder! That's it!


## Configuration

The following configuration files are installed by this extension: 

 - [branding-ssh.properties](assembly/src/main/resources/branding-ssh.properties) - Branding remote shell 
 - [org.apache.karaf.features.cfg](assembly/src/main/resources/org.apache.karaf.features.cfg) - Configuring features service
 - [org.apache.karaf.features.repos.cfg](assembly/src/main/resources/org.apache.karaf.features.repos.cfg) - Friendly names for features repositories
 - [org.apache.karaf.log.cfg](assembly/src/main/resources/org.apache.karaf.log.cfg) - Configuring Karaf's logging format
 - [org.apache.karaf.shell.cfg](assembly/src/main/resources/org.apache.karaf.shell.cfg) - Configuring Karaf's shell (SSH host, port, etc.)
 - [org.ops4j.pax.logging.cfg](assembly/src/main/resources/org.ops4j.pax.logging.cfg) -  Configuring Karaf's log appenders

