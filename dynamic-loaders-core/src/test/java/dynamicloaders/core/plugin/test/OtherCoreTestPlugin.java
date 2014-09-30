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
