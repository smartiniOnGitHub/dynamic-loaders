/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dynamicloaders.core.plugin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Utilities and factory methods for Plugins and Plugin Managers.<br/>
 * 
 * @see PluginManager
 * @see Plugin
 */
public class PluginManagerCompanion {

	/**
	 * Utility method that check the given class loader, 
	 * and calculate a safe (good) one if needed.
	 * 
     * @param  loader the class loader to be used, or if null a default one
	 * @return a good class loader to use
	 */
	public static final ClassLoader safeClassloader(ClassLoader loader) {
		ClassLoader cl = null;

		if (loader != null)
			cl = loader;
		else
			cl = Thread.currentThread().getContextClassLoader();

		return cl;
	}

	/**
	 * Utility method to create a ServiceLoader of Plugins, 
	 * using the standard Java ServiceLoader class.<br/>
	 * Note that all classes written in service files must be found (and good),
	 * or a runtime error (ServiceConfigurationError) will be thrown by ServiceLoader.
	 * 
	 * @param api the interface/abstract/base class representing a plugin, or <tt>null</tt> to use a default one
     * @param  loader the class loader to be used, or <tt>null</tt> to use a default one
	 * @return the ServiceLoader provider
	 */
	@SuppressWarnings("unchecked")
	public static final ServiceLoader<Plugin> createServiceLoaderPlugin(
			final Class<? extends Plugin> api, final ClassLoader loader) {
		Class<Plugin> clazz = null;
		if (api != null)
			clazz = (Class<Plugin>) api;
		else
			clazz = Plugin.class;

		ClassLoader cl = safeClassloader(loader);

		ServiceLoader<Plugin> impl = ServiceLoader.load(clazz, cl);
		return impl;
	}

	/**
	 * Utility method to create and load the class of the first implementation found for the given api, 
	 * using the standard Java ServiceLoader class.<br/>
	 * @see #createServiceLoaderPlugin
	 * 
	 * @param api the interface/abstract/base class representing a plugin
     * @param  loader the class loader to be used, or <tt>null</tt> to use a default one
	 * @return the first implementation found (under META-INF/services/ files)
	 */
	public static final Plugin createPlugin(final Class<? extends Plugin> api, final ClassLoader loader) {
		Plugin result = null;
		ServiceLoader<Plugin> impl = PluginManagerCompanion.createServiceLoaderPlugin(api, loader);

		for (Plugin loadedImpl : impl) {
			result = loadedImpl;
			if (result != null)
				break;
		}

		// if (result == null) 
		// 	throw new RuntimeException("Implementation not found for: " + api);

        return result;
	}

	/**
	 * Utility method to create and load the class for all implementations found of the given api, 
	 * using the standard Java ServiceLoader class.<br/>
	 * @see #createServiceLoaderPlugin
	 * 
	 * @param api the interface/abstract/base class representing a plugin
     * @param  loader the class loader to be used, or <tt>null</tt> to use a default one
	 * @return a List<implementation> for all implementations found (under META-INF/services/ files)
	 */
	public static final List<Plugin> createPluginsList(final Class<? extends Plugin> api, final ClassLoader loader) {
		List<Plugin> result = new ArrayList<>();
		ServiceLoader<Plugin> impl = PluginManagerCompanion.createServiceLoaderPlugin(api, loader);

		for (Plugin createdImpl : impl) {
			if (createdImpl != null)
				result.add(createdImpl);
		}

        return result;
	}

	/**
	 * Utility method that returns the plugin key, depending on the given naming strategy.
	 * 
	 * @param plugin the plugin
     * @param namingStrategy the strategy to use for naming map keys:
     *   0 (and default) = full class name, 1 by plugin name, 2 by plugin name and version, etc ...
	 * @return the key used to identity the plugin, as a String
	 */
	public static final String pluginKeyByStrategy(final Plugin plugin, final int namingStrategy) {
		Objects.requireNonNull(plugin);

		String key = null;
		switch (namingStrategy) {
		case 1: 
			key = plugin.getPluginInfo().getName();
			break;
		case 2: 
			key = plugin.getPluginInfo().getName() 
				+ "-" + plugin.getPluginInfo().getVersion();
			break;
		default: 
			key = plugin.getClass().getName();
			break;
		}
		
		return key;
	}

	/**
	 * Utility method to create and load the class for all implementations found of the given api, 
	 * using the standard Java ServiceLoader class.<br/>
	 * @see #createServiceLoaderPlugin
	 * @see PluginException
	 * 
	 * @param api the interface/abstract/base class representing a plugin
     * @param loader the class loader to be used, or <tt>null</tt> to use a default one
     * @param namingStrategy the strategy to use for naming map keys:
     *   0 (and default) = full class name, 1 by plugin name, 2 by plugin name and version, etc ...
     * @param failOnDuplicates if true and (depending on the chosen key) a plugin is already present in Map,
     *   throw a plugin exception (at runtime)
	 * @return a Map<key, implementation> for all implementations found (under META-INF/services/ files)
	 */
	public static final Map<String, Plugin> createPluginsMap(final Class<? extends Plugin> api, final ClassLoader loader, 
			final int namingStrategy, final boolean failOnDuplicates) {
		Map<String, Plugin> result = new LinkedHashMap<>();
		ServiceLoader<Plugin> impl = PluginManagerCompanion.createServiceLoaderPlugin(api, loader);

		for (Plugin createdImpl : impl) {
			if (createdImpl != null) {
				String key = PluginManagerCompanion.pluginKeyByStrategy(createdImpl, namingStrategy);
				if (result.containsKey(key) && failOnDuplicates == true)
					throw new PluginException("plugin \"" + key + "\" already created.");

				result.put(key, createdImpl);
			}
		}

        return result;
	}

	/**
	 * Utility method that tell if the given map contains more than one plugin
	 * (in some cases could be due to a configuration error).
	 * 
	 * @param plugins a map of plugins
	 * @return true if more than one plugin is contained, false otherwise
	 */
	public static final boolean moreThanOne(Map<String, Plugin> plugins) {
		Objects.requireNonNull(plugins);

		boolean moreThanOne = plugins.size() > 1;
		return moreThanOne;
	}

	/**
	 * Utility method to create the given plugin by loading its full class name, 
	 * using the given class loader (or a default one if not given).
	 * 
	 * @param name the full class name of the plugin
	 * @param loader the class loader to be used, or <tt>null</tt> to use a default one
	 * @return a new Plugin instance, or null
	 */
	public static Plugin createPluginFromClass(final String name, final ClassLoader loader) {
		Objects.requireNonNull(name);

		ClassLoader cl = safeClassloader(loader);

		Class<?> classLoaded = null;
		Plugin pluginCreated = null;

		try {
			classLoaded = cl.loadClass(name);
			if (classLoaded != null)
				pluginCreated = (Plugin) classLoaded.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
		}

		return pluginCreated;
	}

    /**
     * Creates a new plugin manager, setting in it only one available plugin in classpath.
     *
     * @return a new plugin manager instance
     */
    public static PluginManager createWithOne()
    {
        return PluginManagerCompanion.createWithOne(null, null);
    }

    /**
     * Creates a new plugin manager, with inside only one available plugin in classpath.
     *
	 * @param api the interface/abstract/base class representing a plugin
     * @param  loader the class loader to be used, or <tt>null</tt> to use a default one
     * @return a new plugin manager instance
     */
    public static PluginManager createWithOne(final Class<? extends Plugin> api, final ClassLoader loader)
    {
    	return PluginManagerCompanion.createWithOne(api, loader, 0);
    }

    /**
     * Creates a new plugin manager, with inside only one available plugin in classpath.
     *
	 * @param api the interface/abstract/base class representing a plugin
     * @param loader the class loader to be used, or <tt>null</tt> to use a default one
     * @param namingStrategy the strategy to use for naming map keys:
     *   0 (and default) = full class name, 1 by plugin name, 2 by plugin name and version, etc ...
     * @return a new plugin manager instance
     */
    public static PluginManager createWithOne(final Class<? extends Plugin> api, final ClassLoader loader, 
			final int namingStrategy)
    {
    	Map<String, Plugin> plugins = null;
    	Plugin plugin = PluginManagerCompanion.createPlugin(api, loader);
    	if (plugin != null) {
    		String key = PluginManagerCompanion.pluginKeyByStrategy(plugin, namingStrategy);
    		plugins = new LinkedHashMap<>();
    		plugins.put(key, plugin);
    	}

    	return new PluginManager(api, loader, plugins, null);
    }

    /**
     * Creates a new plugin manager, setting in it all available plugins in classpath.
     *
     * @return a new plugin manager instance
     */
    public static PluginManager createWithAll()
    {
        return PluginManagerCompanion.createWithAll(null);
    }

    /**
     * Creates a new plugin manager, with inside all available plugins in classpath.
     *
     * @param  loader the class loader to be used, or <tt>null</tt> to use a default one
     * @return a new plugin manager instance
     */
    public static PluginManager createWithAll(final ClassLoader loader)
    {
    	Class<Plugin> api = Plugin.class;
    	Map<String, Plugin> plugins = PluginManagerCompanion.createPluginsMap(api, loader, 0, true);
        return new PluginManager(api, loader, plugins, null);
    }

	/**
     * Creates a new plugin manager, with inside all available plugins in classpath.
	 * @see #createPluginsMap
	 * @see PluginException
	 * 
	 * @param api the interface/abstract/base class representing a plugin
     * @param loader the class loader to be used, or <tt>null</tt> to use a default one
     * @param namingStrategy the strategy to use for naming map keys:
     *   0 (and default) = full class name, 1 by plugin name, 2 by plugin name and version, etc ...
     * @param failOnDuplicates if true and (depending on the chosen key) a plugin is already present in Map,
     *   throw a plugin exception (at runtime)
     * @return a new plugin manager instance
	 */
    public static PluginManager createWithAll(final Class<? extends Plugin> api, final ClassLoader loader, 
			final int namingStrategy, final boolean failOnDuplicates) {
    	Map<String, Plugin> plugins = PluginManagerCompanion.createPluginsMap(
    			api, loader, namingStrategy, failOnDuplicates);
        return new PluginManager(api, loader, plugins, null);
    }


	/**
	 * Private default constructor.
	 */
    protected PluginManagerCompanion() {
    	// no-op
	}

}
