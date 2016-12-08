# Connect to Liferay Karaf shell via SSH

Liferay Karaf compatibility layer exposes Karaf shell that users can connect to via any SSH client. For the purpose of an example lets assume there is Liferay instance running on `my-portal.com` and Karaf shell is available on port `11211`. User `liferay` can connect from any linux machine simply by doing

```ShellSession
ssh liferay@my-portal.com -p 11211
```

## Configure Karaf's shell 

Karaf shell is configurable OSGi service with service Pid of `org.apache.karaf.shell` and meta-type information. You can therefore configure it like any other OSGi service that supports configuration (via OSGi's ConfigAdmin). There are many ways to do so. For example:

   - **configuration file** (`<LIFERAY_HOME>/osgi/configs/org.apache.karaf.shell.cfg`)
   - **[config subshell](docs/commands.md#config-subshell)**
   - **Liferay's Control Panel** (`Control Panel -> Configuration -> System Settings -> Other -> Apache Karaf Shell`)  
   - from code

### SSH host and port

Change the `sshHost` and/or `sshPort` in the service configuration __(NOTE: Karaf shell will restart and thus you'll be disconnected)__.
Default values are:

```properties
sshPort = 11211
sshHost = 0.0.0.0
```

### Users, passwords, roles and realms

Karaf shell has it's own set of users and groups! It is unaware of Liferay's users and their permissions.
Authentication and authorization is based on JAAS realms! Default realm is `karaf` (it is configurable in `org.apache.karaf.shell` ) and uses users and roles defined in properties file `<LIFERAY_HOME>/osgi/configs/users.properties`. Default is


```properties
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

which means there is one user `liferay` with password `test` that belongs to `admingroup` group which has `admin,manager,viewer,systembundles` roles
Roles and groups can be used to limit the commands/services given user can see/execute!

## Limitations

Karaf's shell does not support Gogo commands by default. There is compatibility layer which need to be installed in order to get Gogo commands to work in Karaf's shell. However as of Karaf 4.0.7 (which is used here) this drags additional dependency on blueprint. Karaf 4.1 solves that problem but it's not released at the time of writing. Once it is released, this project will be updated to use shell from 4.1 and thus provide support for running Gogo commands in it. 
