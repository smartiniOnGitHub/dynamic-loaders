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

// import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PluginManagerTest {

	static PluginManager pmGlobal = null;

	@Before
	public void setUp() throws Exception {
		synchronized (this) {
			// pm = new PluginManager();
			// pm = new PluginManager(null, null);
			// pm = PluginManagerCompanion.createWithOne();
			pmGlobal = PluginManagerCompanion.createWithAll();
			// pm = PluginManager.load(null);
		}
	}

	@After
	public void tearDown() throws Exception {
		synchronized (this) {
			pmGlobal = null;
		}
	}

	@Test
	public void testPluginManager() {
		// fail("Not yet implemented");
		assert(pmGlobal != null);
	}

	/*
	@Test
	public void testPluginManagerClassOfPluginClassLoaderMapOfStringPluginPluginManagerConfiguration() {
		fail("Not yet implemented");
	}
	 */

	/*
	@Test
	public void testGetApi() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLoader() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlugins() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConfig() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetConfig() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDefinedPlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadPlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnloadPlugin() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPluginActive() {
		fail("Not yet implemented");
	}
	 */

}
