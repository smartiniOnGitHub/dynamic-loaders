dynamic-loaders - TODO
======================

TODO
----
- general: core, add other classes for future features (in other packages), and see later if move to other subprojects ...
- general: in any subproject, check where to put sources for documentation to be generated (like src/docs/) ...
- general: check if move existing plugin classes (and related tests and resources) in a dedicated plugin subproject (and rename all packages, etc) ...
- general: put build (and maybe its tests) of plugin-sample subproject as optional ...
- general: check if add license header in all other files/resources, but in the right format ...
- general: add a build task to generate single jars (one per subproject) ...
- general: add a build task to generate a single jar, like <name>-all (merge all other jars) ...
- general: add one runXxx task to run (with Gradle) single samples, like this: '
task runXxx(type: JavaExec) {
	description 'Run ...'
	classpath = sourceSets.main.runtimeClasspath
	main = 'full.class.name'
}
', inside the build.gradle file of related subproject ...

- plugins: extend with additional features (if needed) ...
- plugins: fix todo ...
- plugins: add others in main webapp (opt.), and in federated webapps ...

- plugins: move generic code in another (not plugin-specific, like dynlo-core subproject) class, 
  package and project (dynamic-loaders, others there but in sub-projects) on github ...
- dynamic-loaders: make them environment aware ...
- dynamic-loaders: companion class, check if make them singletons, and implement some interface, and write as standard methods (not static) ...
- dynamic-loaders: utility/companion class (ServiceFactory) as factory using Java ServiceLoader (with something similar even in the name) ...
- dynamic-loaders: put here my old loader with variants (environment/context) ...
- dynamic-loaders: factorize a common interface between them (and maybe no more static methods) ...
- dynamic-loaders: modules subproject, a module has: a plugin map/list, a plugin manager, dedicated class loader ...
- dynamic-loaders: handle other formats for service load files in the custom version (like properties, json, xml, etc) ...

- plugins: companion, add utility methods to return a list of resources 
  (or resource names and then another method to return resources) for another implementation ...
- plugins: create Java abstract scripting plugin that run a script (if present) in do methods, using the standard jsr way ...
- plugins: create Groovy abstract plugin that run a script (if present) in do methods, in a Gradle/eclipse project ...
- plugins: create Scala abstract plugin that run a script (if present) in do methods, in a Gradle/eclipse project ...

- plugins: manager, get active plugins (two methods: as a list, and as a map with usual naming strategies for the key) ...
- plugins: manager, in case of error, like in Functional Programming, 
  check if return a specific plugin (ErrorPlugin ?) containing the exception (instead of null) and with status error (-1) ...
- plugins: manager load(), check if change with a lazy singleton instance ...
- plugins: manager, runtime info for any plugin (maybe only for loaded and enabled ones, but keep this variable in manager) ...
- plugins: manager, load by dependencies (sort map/list by dependencies, if possible) ...

- plugins: check how to make them work (using features ?) ...
- plugins: document standard features: ...
  phase (build, test, run), 
  environment (dev, test, prod, etc), 
  hook (by extension, by path/uri, by event, etc) 
- plugins: check if add even the isActive method to Plugin interface ...
- plugins: check if add even onError, etc methods to Plugin interface ...

- general: periodic checks with FindBugs ...
- general: move all code in a dedicated project in my area at GitHUB, and add usual README, licenses, etc ...

- sample: add ErrorPlugin, LogPlugin, etc ...

- etc ...

---------------


DONE
----
- plugins: check if instead use Spring (or standard Java EE annotations) for PostInit instead ... maybe later 
- plugins: add ServiceLoader related classes (base and implementations) and files (more than one per project) ... ok
- plugins: add first unit tests ... ok
- plugins: add unit tests, here and even in other projects (sc-plugin-sample, etc) ... ok
- plugins: make tests work with multiple service loader manifest files (sc-plugin-sample, etc) ... ok
- plugins: AbstractPlugin, add a PluginConfiguration variable (set inside a plugin ?), set by PluginManager ... ok
- plugins: AbstractPlugin, check if add some static variables inside ... maybe later
- plugins: pluginStatus get set only the int enum value ... ok
- plugins: pluginStatus enum, add other values, then add toValue and fromValue static methods ... ok
- plugins: pluginStatus check only on right enum values (but generated directly from the enum) ... ok
- plugins: pluginStatus keep as standalone variable in a plugin, and keep a copy of it in plugin manager runtime info ... ok
- plugins: PluginManager, move features outside PluginInfo ... ok
- plugins: PluginConfiguration, status, uri (calculated), etc ... ok
- plugins: manager, move static methods in dedicated Companion class (like in Scala) ... ok
- plugins: manager, map, instead of HashMap use LinkedHashMap (to preserve order, when used as List) ... ok
- plugins: add PluginException ... ok
- plugins: PluginManagerCompanion, check if catch errors (if any) in static methods ... maybe later
- plugins: companion, createPlugins (and similar) check if return a List instead ... ok, but provide instead a new method, could be useful too
- plugins: companion, add createWithOne and createWithAll utility methods ... ok
- plugins: companion, handle (basic naming strategy (class, or name, or name and version) ... ok
- plugins: companion, check how to do in case of duplicate plugin key, with constructor argument ... ok
- plugins: companion, add a boolean moreThanOne utility method, based on the number of plugins found, to check if more than one is found ... ok
- plugins: companion, load plugin by class name and maybe other ways ... ok, maybe later improve this
- plugins: companion (tests), more tests for better code coverage ... ok
- plugins: companion (tests), test even with a custom api argument ... ok
- plugins: manager, set a config (maybe later) and now set boolean failOnError ... ok
- plugins: manager, handle all plugins single (by plugin) or all (by phase), with a config param in the manager (runByPlugin) ... ok
- plugins: manager, getters for api and loader set in constructors ... ok
- plugins: manager, change setter methods to protected (and maybe others too), for more immutability of inner data structures ... ok, but improve later
- plugins: manager, implement equals but in PluginInfo and use it ... ok
- plugins: returns error if already existing (because it could be already loaded), both in companion and in manager ... ok
- plugins: configuration as standalone variable in a plugin (like in Grails) ... ok

- general: initial check with FindBugs ... ok
- general: rename load with create, to avoid confusion with Plugin load method/phase ... ok


- general: package names dynamicloaders (ok, not following usual naming standards) ... ok
- general: setup subprojects (and the new core, very generic) with latest Gradle (stable) ... ok
- general: fix failing test MultiProjectPluginsTest.testCreateWithCustomPluginApi ... ok, it was due to Gradle cross-project test resources handling
- general: generate eclipse project files, but add to ignore list in source control ... ok
- general: add license header in all sources ... ok
- general: add BUILD file ... ok

- general: api, check if keep that subproject (as base dependency for others here) ... yes, maybe remove later
- general: api, put some content in that subproject (required ad base dependency here), but currently empty ... ok, but note that empty folders are not committed by git (at least a .gitignore or other file is needed to have an empty folder committed), so folders 'src\main\java\dynamicloaders\api', 'src\test\java\dynamicloaders\api', and 'src\test\resources' are not committed yet (maybe for a simple backup with that inner structure, keep a zip of that subproject folder)
- general: update Gradle wrapper to latest (currently 2.8), and repeat later ... ok, but instead of add a wrapper task to build.gradle (like: '
task wrapper(type: Wrapper) {
	gradleVersion = '2.8'
}
') note that since Gradle 2.4 it's possible to do the same even with a command line like this: 'gradle wrapper --gradle-version 2.8'
- general: update dependencies, and repeat later ... ok, and force a refresh with 'gradle clean' and then 'gradle build --refresh-dependencies'


---------------
