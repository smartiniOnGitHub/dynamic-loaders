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
