package dynamicloaders.core.plugin;

import java.util.Map;

/**
 * Interface for a Plugin.
 * 
 */
public interface Plugin {

	// plugin-related
	public PluginInfo getPluginInfo();
	public int getPluginStatus();
	public PluginConfiguration getPluginConfiguration();
	public Map<String, String> getPluginFeatures();

	// application context
	// TODO: setContext ... and maybe getContext() ...

	// lifecycle events
	public void doInstall();
	public void doUninstall();
	public void doLoad();
	public void doUnload();
	public Map<String, ?> doExecute(Map<String, String> params);

	// other

}
