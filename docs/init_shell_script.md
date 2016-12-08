# Shell initialization script

Karaf's shell makes use of initialization script that gets executed every time a connection to the shell is made (much like `.bashrc` in linux)
This script is located in `<LIFERAY_7_HOME>/osgi/configs/shell.init.script`

### Configure shell aliases

Initialization script can be used to define convenient aliases. For example: 

```properties
lb = { bundle:list -t 0 $args } ;
ls = { service:list $args } ;
lc = { config:list "(service.pid=$args)" } ;
help = { *:help $args | more } ;
man = { help $args } ;
service:get = { $.context getService ($.context getServiceReference $args) };
```
### Define functions

Furthermore it allows to define functions and provide aliases to them. For example this

```properties
\#UserLocalService = { service:get com.liferay.portal.kernel.service.UserLocalService }
liferay:list_users = { \
  shell:printf '| %-20s | %-20s | %-20s |%n' 'First Name' 'Last Name' 'E-mail';\
  shell:printf '| %-20s | %-20s | %-20s |%n' '--------------------' '--------------------' '--------------------' ;\
  each ($UserLocalService users -1 -1)  { \
    shell:printf '| %-20s | %-20s | %-20s |%n' ($it FirstName) ($it LastName) ($it DisplayEmailAddress)\
  }
}
```

defines a function that gets users by calling Liferay's `UserLocalService` and prints the result in a table (using [shell subshell](docs/commands.md#shell-subshell)). Executing `liferay:list_users` would therefore print something like this:

```ShellSession
liferay@root>liferay:list_users
| First Name           | Last Name            | E-mail               |
| -------------------- | -------------------- | -------------------- |
|                      |                      | default@liferay.com  |
| Test                 | Test                 | test@liferay.com     |
```