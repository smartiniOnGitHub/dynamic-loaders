package dynamicloaders.plugin.sample;

import java.util.Map;

import dynamicloaders.core.plugin.AbstractPlugin;
import dynamicloaders.core.plugin.PluginInfo;

public abstract class NotInstantiablePlugin extends AbstractPlugin {

	private static final PluginInfo info = new PluginInfo(
			"samplePlugin", "1.0.0", "Sample plugin that does nothing useful"
	);

	@Override
	public PluginInfo getPluginInfo() {
		return info;
	}

	@Override
	public Map<String, String> getPluginFeatures() {
		return null;
	}

}
