package dynamicloaders.core.plugin;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract base class for a Plugin.<br/>
 * Use it even as an adapter.
 * 
 * @see Plugin
 * 
 */
public abstract class AbstractPlugin implements Plugin {

	PluginInfo info = null;
	int status = PluginStatus.UNKNOWN.value();
	PluginConfiguration configuration = null;
	Map<String, String> features = null;

	/**
	 * Default constructor
	 */
	public AbstractPlugin() {
		this(null, null, null);
	}

	/**
	 * Constructor with minimal data to be set.
	 * 
	 * @param info plugin general info, usually set by developers
	 */
	public AbstractPlugin(PluginInfo info) {
		this(info, null, null);
	}

	/**
	 * Constructor with main data to be set, 
	 * or a default value will be created if a given arguments is null.
	 * 
	 * @param info plugin general info, usually set by developers
	 * @param configuration plugin configuration, usually set by developers
	 * @param features plugin general features, usually set by developers
	 */
	public AbstractPlugin(PluginInfo info, PluginConfiguration configuration, 
		Map<String, String> features
	) {
		if (info != null)
			this.info = info;
		else
			this.info = new PluginInfo();

		if (configuration != null)
			this.configuration = configuration;
		else
			this.configuration = new PluginConfiguration();

		if (features != null)
			this.features = features;
		else
			this.features = new LinkedHashMap<>();

	}

	@Override
	public PluginInfo getPluginInfo() {
		return info;
	}

	@Override
	public int getPluginStatus() {
		// return ((configuration != null) ? configuration.getStatus() : PluginStatus.UNKNOWN.value());
		return status;
	}

	@Override
	public PluginConfiguration getPluginConfiguration() {
		return configuration;
	}

	@Override
	public Map<String, String> getPluginFeatures() {
		return features;
	}

	@Override
	public void doInstall() {
		// no-op
		System.out.println("doInstall");
		setPluginStatus(PluginStatus.INSTALLED.value());
	}

	@Override
	public void doUninstall() {
		// no-op
		System.out.println("doUninstall");
		setPluginStatus(PluginStatus.UNINSTALLED.value());
	}

	@Override
	public void doLoad() {
		// no-op
		System.out.println("doLoad");
		setPluginStatus(PluginStatus.LOADED.value());
	}

	@Override
	public void doUnload() {
		// no-op
		System.out.println("doUnload");
		setPluginStatus(PluginStatus.UNLOADED.value());
	}

	@Override
	public Map<String, ?> doExecute(Map<String, String> params) {
		System.out.println("doExecute");
		setPluginStatus(PluginStatus.EXECUTED.value());
		// // sample implementation
		// Map<String, String> results = new LinkedHashMap<String, String>();
		// return results;
		// no-op
		return null;
	}

	protected void setPluginStatus(int status) {
		this.status = status;

		// replicate even in plugin configuration ...
		if (configuration != null)
			configuration.setStatus(status);
	}

    public boolean isActive() {
		return (getPluginConfiguration().isEnabled() 
			&& getPluginStatus() > PluginStatus.UNKNOWN.value()
		);
    }

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"AbstractPlugin\": { ");
		sb.append("\"PluginInfo\": " + ((info != null) ? info : ""));
		sb.append(", \"PluginConfiguration\": " + ((configuration != null) ? configuration : ""));
		sb.append(", \"PluginStatus\": \"" + getPluginStatus() + "\"");
		sb.append(", \"PluginFeatures\": " + dumpFeatures());
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractPlugin))
			return false;
		AbstractPlugin other = (AbstractPlugin) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}

	protected String dumpFeatures() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"LinkedHashMap\": { ");
		if (features != null) {
			// TODO: loop over features, and format them as String like other fields ...
		}
		sb.append(" }");
		return sb.toString();
    }

}
