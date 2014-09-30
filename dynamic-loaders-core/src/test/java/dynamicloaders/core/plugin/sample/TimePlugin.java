package dynamicloaders.core.plugin.sample;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import dynamicloaders.core.plugin.AbstractPlugin;
import dynamicloaders.core.plugin.PluginInfo;

public class TimePlugin extends AbstractPlugin {

	private static final PluginInfo info = new PluginInfo(
			"timePlugin", "1.0.0", "Sample plugin that returns the current timestamp, as long"
	);


	public TimePlugin() {
		super(info);
	}

	@SuppressWarnings("static-method")
	protected long getValue() {
		return System.currentTimeMillis();
	}

	@Override
	public PluginInfo getPluginInfo() {
		return info;
	}

	@Override
	public Map<String, String> getPluginFeatures() {
		// return null;
		// sample implementation
		Map<String, String> features = new LinkedHashMap<>();
		features.put("execute", "true");
		return features;
	}

	@Override
	public Map<String, ?> doExecute(Map<String, String> params) {
		System.out.println(this.getClass().getName() + ": " + "doExecute");
		// sample implementation
		Map<String, String> results = new HashMap<>();
		results.put("time", String.valueOf(getValue()));
		return results;
	}

}
