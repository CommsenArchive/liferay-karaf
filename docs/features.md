# Features

Features are Karaf's way to provision applications! A feature describes an application as:

 - a name
 - a version
 - a optional description (eventually with a long description)
 - a set of bundles
 - optionally a set configurations or configuration files
 - optionally a set of dependency features
 
For detailed information please refer to [Karaf's documentation about features](http://karaf.apache.org/manual/latest/#_feature_and_resolver)
 
Features are defined in a XML file which contains one or more features. Such XML descriptor is called "features repository". For detailed information please refer to [Karaf's documentation about features repositories](http://karaf.apache.org/manual/latest/#_features_repositories_2)

By adding Karf's features functionality to Lferay, it is now possible to 

 - add feature repositories to Liferay from code or [feature subshell](docs/commands.md#feature-subshell)
 - install/upgrade/uninstall features from those repositories with commands from code or [feature subshell](docs/commands.md#feature-subshell)
 - deploy whole applications by coping XML descriptor to Liferay's `deploy` folder
 - configure a list of features repositories available by default
 - configure features to be installed automatically

## Limitations

There are many features repositories for Karaf features. Many open source projects (such as Apache CXF, Apache Camel, Apache Aries, Hibernate, ...) provide own features repositories __(inluded in Karaf by default)__. However features in those repositories typically assume they will be installed in KAraf container and expect some Karaf specific features to be available. Unfortunately this often conflicts with Liferay's own functionality. For example 

 - most web application provided as Karaf features depend on `war` feature which depends on `http` feature which when installed conflicts with Liferay's bundles providing http services.
 - some features depends on `spring` feature which conflicts with the way Liferay handles Spring configurations
 - some features expect `jpa` and `transactions` which conflicts with how  Liferay handles those 
 
Therefore features repositories provided with this compatibility layer is limited to bare minimum (it may be expanded with future versions) and great care should be taken while adding 3rd party features repositories as those **can brake your Liferay instalation**!


 
## Default features and repositories  

### Friendly repository names

 Friendly names for feature repositories can be configured via `org.apache.karaf.features.repos` service pid. By default this extensions installs `<LIFERAY_7_NOME>/osgi/configs/org.apache.karaf.features.repos.cfg` file in which there are two repositories defined:
 
```
karaf-compat=https://raw.githubusercontent.com/azzazzel/liferay-karaf/master/features/karaf-compat-features/features-${release.version}.xml
devops=https://raw.githubusercontent.com/azzazzel/liferay-karaf/master/features/devops-features/features-${release.version}.xml
```
 - karaf-compat - repository providing some of the core Karaf features
   - shell-commands - collection of bundles providing Karaf's base [commands](docs/commands.md)
   - scheduler - service and shell command to schedule tasks 
   - wrap - service and shell command to wrap non-OSGi jar file as bundles 
   - kar - KAR deployer (see [Karaf's documentation](http://karaf.apache.org/manual/latest/kar#_kar))
 - devops - repository providing some of tools useful for developers/operators
   - jolokia - remote JMX with JSON over HTTP (see Jolokia web site](https://jolokia.org/))
   - webconsole - simple tool to inspect and manage OSGi framework from web browser(see [Felix Webconsole web site](http://felix.apache.org/documentation/subprojects/apache-felix-web-console.html))

### Automatically added repositories and started features
 
Which repositories will be automatically added and which of their features will be automatically started __(when features service starts)__ is defined in `org.apache.karaf.features` configuration. By default this extensions installs `<LIFERAY_7_NOME>/osgi/configs/org.apache.karaf.features.cfg` file with following configuration:

```
featuresRepositories = \
    https://raw.githubusercontent.com/azzazzel/liferay-karaf/master/features/karaf-compat-features/features-${release.version}.xml
featuresBoot = \
    shell-commands
```

## Installing / uninstalling features repositories and features

### From command line 

 - make sure the repository containing feature is added. If it is not, add it with `feature:repo-add <PATH>`. Available protocols are 
   - `file:` - features repository from local file system
   - `mvn:` - features repository from Maven (local/central/nexus/...). Typically formated as `mvn:<GROUP_ID>/<ARTIFACT_ID>/<VERSION>/xml/features`
   - `http:` - features repository from accessible URL
 - optionally call `feature:list` to make sure the feature is indeed available 
 - install it with `feature:install <name>`. 
 - to uninstall it run `feature:uninstall <name>` 
 
### By deploying features repository XML file 

A features descriptor XML file can be deployed simply by coping it to Liferay's `deploy` folder. Any feature with `install="true"` attribute will be automatically installed.



