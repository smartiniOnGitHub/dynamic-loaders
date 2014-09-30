package dynamicloaders.core.plugin;

/**
 * Configuration of a PluginManager.<br/>
 * Usually set by developers.
 * 
 * @see PluginManager
 */
public class PluginManagerConfiguration {

	boolean failOnError;
	boolean runByPlugin;


	public PluginManagerConfiguration() {
		this(true, true);
	}

	public PluginManagerConfiguration(boolean failOnError, boolean runByPlugin) {
		this.failOnError = failOnError;
		this.runByPlugin = runByPlugin;
	}

	public boolean isFailOnError() {
		return failOnError;
	}

	public void setFailOnError(boolean failOnError) {
		this.failOnError = failOnError;
	}

	public boolean isRunByPlugin() {
		return runByPlugin;
	}

	public void setRunByPlugin(boolean runByPlugin) {
		this.runByPlugin = runByPlugin;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");
		// sb.append("\"PluginManagerConfiguration\": { ");
		sb.append("failOnError: " + failOnError);
		sb.append(", runByPlugin: " + runByPlugin);
		sb.append(" }");
		// sb.append(" }");

		return sb.toString();
	}

}
