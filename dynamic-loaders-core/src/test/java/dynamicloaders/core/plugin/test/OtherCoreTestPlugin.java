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
package dynamicloaders.core.plugin.test;

// import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dynamicloaders.core.plugin.PluginManager;

public class OtherCoreTestPlugin {

	static PluginManager pm = null;

	@Before
	public void setUp() throws Exception {
		synchronized (this) {
			pm = new PluginManager();
		}
	}

	@After
	public void tearDown() throws Exception {
		synchronized (this) {
			pm = null;
		}
	}

	@Test
	public void testGetPluginInfo() {
		// fail("Not yet implemented");
		System.out.println("testGetPluginInfo: Not yet implemented ...");
	}

	@Test
	public void testGetPluginFeatures() {
		// fail("Not yet implemented");
		System.out.println("testGetPluginFeatures: Not yet implemented ...");
	}

	@Test
	public void testDoExecute() {
		// fail("Not yet implemented");
		System.out.println("testDoExecute: Not yet implemented ...");
	}

	@Test
	public void testGetValue() {
		// fail("Not yet implemented");
		System.out.println("testGetValue: Not yet implemented ...");
	}

}
