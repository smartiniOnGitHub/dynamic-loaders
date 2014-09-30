package dynamicloaders.core.plugin;

/**
 * Base class for a Plugin (Runtime) Exception.
 * 
 * @see RuntimeException
 * 
 */
public class PluginException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    public PluginException() {
        super();
    }

    public PluginException(String message) {
        super(message);
    }

    public PluginException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginException(Throwable cause) {
        super(cause);
    }

}
