package dynamicloaders.plugin.sample;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dynamicloaders.core.plugin.Plugin;
import dynamicloaders.core.plugin.PluginManager;
import dynamicloaders.core.plugin.PluginManagerCompanion;

public class TimePluginTest {

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
			"dynamicloaders.core.plugin.sample.TimePlugin";
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
	public void testCreatePluginWithCustomPluginApi() {
		System.out.println("testCreatePluginWithCustomPluginApi: should load the plugin from two manifest files (but without failing on duplicates), one in this project and one in dependency project too ...");
		System.out.println("testCreatePluginWithCustomPluginApi: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		int namingStrategy = 0;
		// boolean failOnDuplicates = true;
		boolean failOnDuplicates = false;
		System.out.println("testCreatePluginWithCustomPluginApi: failOnDuplicates = " + failOnDuplicates);
		PluginManager pm = PluginManagerCompanion.createWithAll(api, loader, namingStrategy, failOnDuplicates);
		System.out.println("testCreatePluginWithCustomPluginApi: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreatePluginWithCustomPluginApi: PluginManager instance contains " + numberOfPlugins + " plugins");
		// important test:
		// should load 1 plugin from the manifest in this project, 
		// and 1 from the manifest in the other project (as dependency for this project),
		// but with failOnDuplicates false the last should update existing one ...
		// assertTrue(numberOfPlugins > 0);
		assertTrue(numberOfPlugins == 1);
	}

	@Test
	public void testCreatePluginOneWithCustomPluginApi() {
		System.out.println("testCreatePluginOneWithCustomPluginApi: should load the plugin from manifest files and chose the first ...");
		System.out.println("testCreatePluginWOneithCustomPluginApi: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		int namingStrategy = 0;
		PluginManager pm = PluginManagerCompanion.createWithOne(api, loader, namingStrategy);
		System.out.println("testCreatePluginOneWithCustomPluginApi: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testCreatePluginWithCustomPluginApi: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);
	}

	@Test
	public void testPluginDefaults() {
		System.out.println("testPluginDefaults: testing plugin manager defaults, after creation with default constructor ...");
		assertNotNull(pmGlobal);
		assertNotNull(pmGlobal.getApi());
		assertTrue(pmGlobal.getApi() == Plugin.class);
		assertNotNull(pmGlobal.getLoader());
		assertNotNull(pmGlobal.getConfig());
		assertNotNull(pmGlobal.getPlugins());
		assertTrue(pmGlobal.getPlugins().size() == 0);
	}

	@Test
	public void testGetPluginInfo() {
		System.out.println("testGetPluginInfo: check plugin info for a created plugin under test here ...");
		System.out.println("testGetPluginInfo: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		String pluginDefaultKey = api.getName();
		PluginManager pm = PluginManagerCompanion.createWithOne(api, null);
		System.out.println("testGetPluginInfo: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testGetPluginInfo: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);

		assertNotNull(pm.getPlugin(pluginDefaultKey));
		assertNotNull(pm.getPlugin(pluginDefaultKey).getPluginInfo());
		assertNotNull(pm.getPlugin(pluginDefaultKey).getPluginInfo().getName());
		assertTrue(pm.getPlugin(pluginDefaultKey).getPluginInfo().getName() == "timePlugin");
		assertTrue(pm.getPlugin(pluginDefaultKey).getPluginInfo().getVersion() == "1.0.0");
	}

	@Test
	public void testGetPluginFeatures() {
		System.out.println("testGetPluginFeatures: check plugin features for a created plugin under test here ...");
		System.out.println("testGetPluginFeatures: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		String pluginDefaultKey = api.getName();
		PluginManager pm = PluginManagerCompanion.createWithOne(api, null);
		System.out.println("testGetPluginFeatures: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testGetPluginFeatures: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);

		assertNotNull(pm.getPlugin(pluginDefaultKey));
		assertNotNull(pm.getPlugin(pluginDefaultKey).getPluginFeatures());
		assertTrue(pm.getPlugin(pluginDefaultKey).getPluginFeatures().size() == 1);
	}

	@Test
	public void testGetPluginStatus() {
		System.out.println("testGetPluginStatus: check plugin status for a created plugin under test here ...");
		System.out.println("testGetPluginStatus: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		String pluginDefaultKey = api.getName();
		PluginManager pm = PluginManagerCompanion.createWithOne(api, null);
		System.out.println("testGetPluginStatus: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testGetPluginStatus: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);

		assertNotNull(pm.getPlugin(pluginDefaultKey));
		assertTrue(pm.getPlugin(pluginDefaultKey).getPluginStatus() == 0);
	}

	@Test
	public void testDoExecute() {
		System.out.println("testDoExecute: check plugin info for a loaded plugin under test here ...");
		System.out.println("testDoExecute: try to retrieve a PluginManager instance ...");
		Class<? extends Plugin> api = dynamicloaders.core.plugin.sample.TimePlugin.class;
		String pluginDefaultKey = api.getName();
		PluginManager pm = PluginManagerCompanion.createWithOne(api, null);
		System.out.println("testDoExecute: created a PluginManager instance: \"" + pm + "\"");
		assertNotNull(pm);
		int numberOfPlugins = pm.getPlugins().size();
		System.out.println("testDoExecute: PluginManager instance contains " + numberOfPlugins + " plugins");
		assertTrue(numberOfPlugins == 1);

		Plugin p = pm.getPlugin(pluginDefaultKey);
		System.out.println("testDoExecute: Plugin instance is " + p);
		assertNotNull(p);
		Map<String, String> params = null;
		System.out.println("testDoExecute: Plugin params (for execution) are: " + params);
		Map<String, ?> results = p.doExecute(params);
		System.out.println("testDoExecute: Plugin results (of execution) are: " + results);
		assertNotNull(results);
		assertNotNull(results.get("time"));
		Long l = new Long((String) results.get("time"));
		assertNotNull(l);
		// another execution
		results = p.doExecute(params);
		System.out.println("testDoExecute: Plugin results (of execution) are: " + results);
		Long l2 = new Long((String) results.get("time"));
		assertNotNull(l2);
		assertTrue(l.longValue() <= l2.longValue());
	}

	// TODO: add more tests on pmGlobal, like addPlugin() etc ...

}
