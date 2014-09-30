package dynamicloaders.core.plugin;

import java.util.Objects;

/**
 * Plugin statuses.<br/>
 * Usually set by developers.
 * 
 */
public enum PluginStatus {
	ERROR(-1, "error"), 
	UNKNOWN(0, "unknown"), 
	INSTALLED(1, "installed"), 
	LOADED(2, "loaded"), 
	UNINSTALLED(3, "uninstalled"), 
	UNLOADED(4, "unloaded"),
	EXECUTED(5, "executed");

	private final int value;
	private final String text;
	
	PluginStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int value() {
    	return value;
    }
    
    public String text() {
    	return text;
    }

    public static final PluginStatus fromValue(int value) {
    	for (PluginStatus status : PluginStatus.values()) {
            if (status.value == value) 
                return status;
        }
		return null;
    }

    public static final int toValue(PluginStatus status) {
		Objects.requireNonNull(status);
		return status.value();
    }

}
