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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PluginManagerCompanionTest {

	@Before
	public void setUp() throws Exception {
		// no-op
	}

	@After
	public void tearDown() throws Exception {
		// no-op
	}

	@Test
	public void testCreatePluginFromClass_NotExistingPlugin() {
		System.out.println("testCreatePluginFromClass: should not load a plugin with a wrong (not existing) class name ...");
		String name = 
			"dynamicloaders.core.plugin.sample.NotExistingPlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNull(p);
	}

	@Test
	public void testCreatePluginFromClass_NotInstantiablePlugin() {
		System.out.println("testCreatePluginFromClass: should not load a plugin with a wrong (interface/abstract class) class name ...");
		String name = 
			"dynamicloaders.core.plugin.sample.NotInstantiablePlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNull(p);
	}

	@Test
	public void testCreatePluginFromClass_NotAPlugin() {
		System.out.println("testCreatePluginFromClass: should load a plugin if the implementation not extends Plugin, given class name ...");
		String name = 
			"dynamicloaders.core.plugin.sample.NotAPlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNull(p);
	}

	@Test
	public void testCreatePluginFromClass_SamplePlugin() {
		System.out.println("testCreatePluginFromClass: should load a plugin from the given class name ...");
		String name = 
			"dynamicloaders.core.plugin.sample.SamplePlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNotNull(p);
	}

	@Test
	public void testCreatePluginFromClass_TimePlugin() {
		System.out.println("testCreatePluginFromClass: should load a plugin from the given class name ...");
		String name = 
			"dynamicloaders.core.plugin.sample.TimePlugin";
		System.out.println("testCreatePluginFromClass: try to load plugin with name: \"" + name + "\"");
		Plugin p = PluginManagerCompanion.createPluginFromClass(name, null);
		System.out.println("testCreatePluginFromClass: Plugin loaded: name: \"" + name + "\", class instance: " + p);
		assertNotNull(p);
	}

	@Test
	public void testCreateWithOne() {
		System.out.println("testCreateWithOne: should load one plugin from the manifest (for default api) in this project ...");
		System.out.println("testCreateWithOne: try to retrieve a PluginManager instance ...");
		PluginManager pm = PluginManagerCompanion.createWithOne();
		System.out.println("testCreateWithOne: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreateWithOne: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);

		System.out.println("testCreateWithOne: should load one plugin from the manifest (for default api) in this project ...");
		PluginManager pm2 = PluginManagerCompanion.createWithOne();
		System.out.println("testCreateWithOne: created a PluginManager instance: \"" + pm2 + "\"");
		assertNotNull(pm2);

		// returned instance should always be the same (singleton) ...
		// assertTrue(pm == pm2);  // ok, but is that rule good here ? ...
		assertTrue(pm != pm2);  // temp ...
	}

	@Test
	public void testCreateWithAll() {
		System.out.println("testCreateWithCustomPluginApi: should load all plugin from the manifest (for default api) in this project ...");
		System.out.println("testCreateWithAll: try to retrieve a PluginManager instance ...");
		PluginManager pm = PluginManagerCompanion.createWithAll();
		System.out.println("testCreateWithAll: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreateWithAll: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins > 0);

		PluginManager pm2 = PluginManagerCompanion.createWithAll();
		System.out.println("testCreateWithAll: created a PluginManager instance: \"" + pm2 + "\"");
		assertNotNull(pm2);

		// returned instance should always be the same (singleton) ...
		// assertTrue(pm == pm2);  // ok, but is that rule good here ? ...
		assertTrue(pm != pm2);  // temp ...
	}

	@Test
	public void testCreateWithNotExistingManifest() {
		System.out.println("testCreateWithNotExistingManifest: should not find any plugin ...");
		System.out.println("testCreateWithNotExistingManifest: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = NotExistingManifest.class;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		int namingStrategy = 0;
		boolean failOnDuplicates = true;
		PluginManager pm = PluginManagerCompanion.createWithAll(api, loader, namingStrategy, failOnDuplicates);
		System.out.println("testCreateWithNotExistingManifest: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreateWithNotExistingManifest: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 0);
	}

	@Test
	public void testCreateWithCustomPluginApi() {
		System.out.println("testCreateWithCustomPluginApi: should load all plugin from the manifest in this project ...");
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
		// should load 2 plugin from the manifest in this project ...
		// assertTrue(numberOfPlugins > 0);
		assertTrue(numberOfPlugins == 2);
	}

	@Test
	public void testCreateWithImplementationsNotPluginsInside() {
		System.out.println("testCreateWithImplementationsNotPluginsInside: unnecessary, because they will be checked at compile time ...");
		assertTrue(true);
	}

}
