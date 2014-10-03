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
package dynamicloaders.plugin.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dynamicloaders.core.plugin.AbstractPlugin;
import dynamicloaders.core.plugin.Plugin;
import dynamicloaders.core.plugin.PluginManager;
import dynamicloaders.core.plugin.PluginManagerCompanion;

public class MultiProjectPluginsTest {

	static PluginManager pmGlobal = null;

	@Before
	public void setUp() throws Exception {
		synchronized (this) {
			pmGlobal = new PluginManager();
		}

	}

	@After
	public void tearDown() throws Exception {
		synchronized (this) {
			pmGlobal = null;
		}
	}

	@Test
	public void testCreatePluginFromClass_TimePlugin() {
		System.out.println("testCreatePluginFromClass: should load a plugin from the given class name ...");
		System.out.println("testCreatePluginFromClass: plugin in this project ...");
		String name = 
			"dynamicloaders.plugin.sample.TimePlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNotNull(p);

		System.out.println("testCreatePluginFromClass: plugin in dependency project ...");
		String name2 = 
			"dynamicloaders.core.plugin.sample.TimePlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name2 + "\"");
		Plugin p2 = PluginManagerCompanion.createPluginFromClass(name2, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name2 + "\", class instance: " + p2);
		assertNotNull(p2);
	}

	@Test
	public void testCreateWithCustomPluginApi() {
		System.out.println("testCreateWithCustomPluginApi: should load all plugin from two manifest files, one in this project and one in dependency project too ...");
		System.out.println("testCreateWithCustomPluginApi: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = AbstractPlugin.class;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		int namingStrategy = 0;
		boolean failOnDuplicates = true;
		PluginManager pm = PluginManagerCompanion.createWithAll(api, loader, namingStrategy, failOnDuplicates);
		System.out.println("testCreateWithCustomPluginApi: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreateWithCustomPluginApi: PluginManager instance contains " + numberOfPlugins + " plugins");
		// important test:
		// should load 2 plugin from the manifest in this project, 
		// and 2 from the manifest in the other project (as dependency for this project) ...
		// assertTrue(numberOfPlugins > 0);
		assertTrue(numberOfPlugins == 4);
	}

	// TODO: add more tests ...

}
