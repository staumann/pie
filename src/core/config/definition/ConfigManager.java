package core.config.definition;

public interface ConfigManager {

	String getString(String key);
	int getInteger(String key);
	boolean getBoolean(String key);
	
	void setConfigFound(boolean found);
	void setConfigValue(String key, String value);
	void setConfigValue(String key, int value);
	void setConfigValue(String key, boolean value);
}
