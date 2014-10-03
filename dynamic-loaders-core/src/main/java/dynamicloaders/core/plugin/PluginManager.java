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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Manager for Plugins (one or more).<br/>
 * 
 * @see Plugin
 */
public class PluginManager {

	protected Class<Plugin> api = null;
	protected ClassLoader loader = null;
	protected Map<String, Plugin> plugins = null;
	protected PluginManagerConfiguration config = null;

	/**
	 * Default constructor.
	 */
    public PluginManager() {
		this(null, null, null, null);
	}

	/**
	 * Creates a new Plugin Manager
	 * 
	 * @param api 
	 *        the interface/abstract/base class representing a plugin
	 * @param loader
	 *        the class loader to use, or <tt>null</tt> to use a default one
	 * @param plugins 
	 *        a pre-populated map of Plugins (with the fully qualified class name as key),
	 *        it will be set is not null
	 * @param config
	 *        the plugin manager configuration to use, or <tt>null</tt> to use a default one
	 */
    @SuppressWarnings("unchecked")
	public PluginManager(Class<? extends Plugin> api, ClassLoader loader, Map<String, Plugin> plugins, PluginManagerConfiguration config) {
		// initialization
    	if (api != null)
    		this.api = (Class<Plugin>) api;
    	else
    		this.api = Plugin.class;

    	if (loader != null)
    		this.loader = loader;
    	else
    		this.loader = Thread.currentThread().getContextClassLoader();

    	if (plugins != null) {
			this.plugins = plugins;
		}
		else {
			// this.plugins = PluginManagerCompanion.createPluginsMap(Plugin.class, loader, 0, true);  // pre-fill with plugins
			this.plugins = new LinkedHashMap<>();  // empty plugin container
		}

		if (config != null)
			this.config = config;
		else
			this.config = new PluginManagerConfiguration();

		// TODO: check if set here even a context from outside ...
	}


	public Class<Plugin> getApi() {
		return api;
	}

	public ClassLoader getLoader() {
		return loader;
	}

	public Map<String, Plugin> getPlugins() {
		return plugins;
	}

	public PluginManagerConfiguration getConfig() {
		return config;
	}

	public void setConfig(PluginManagerConfiguration config) {
		this.config = config;
	}

	public synchronized void addPlugin(String name, Plugin plugin) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(plugin);
		plugins.put(name,  plugin);
	}

	public synchronized void removePlugin(String name) {
		Objects.requireNonNull(name);
		plugins.remove(name);
	}

	public Plugin getPlugin(String name) {
		Objects.requireNonNull(name);
		return plugins.get(name);
	}

	public boolean isDefinedPlugin(String name) {
		return (plugins.get(name) != null);
	}

	public void loadPlugin(String name) {
		Plugin plugin = getPlugin(name);
		Objects.requireNonNull(plugin);

		// tell the plugin to process its load method
		plugin.doLoad();

		addPlugin(name, plugin);
	}

	public void unloadPlugin(String name) {
		Plugin plugin = getPlugin(name);
		Objects.requireNonNull(plugin);

		// tell the plugin to process its unload method
		plugin.doUnload();

		removePlugin(name);
		plugin = null;
	}

    public boolean isPluginActive(String name) {
		Plugin plugin = getPlugin(name);
		// return (plugin != null && plugin.isActive());
		return (plugin != null && plugin.getPluginConfiguration().isEnabled() 
			&& plugin.getPluginStatus() > PluginStatus.UNKNOWN.value()
		);
    }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"PluginManager\": {");
		sb.append("\"api\": \"" + api.toString() + "\"");
		sb.append(", \"loader\": \"" + loader.toString() + "\"");
		sb.append(", \"configuration\": " + config.toString());
		sb.append(", \"plugins count\": " + plugins.size());
		sb.append(", \"plugins\": " + plugins.toString());
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

}
